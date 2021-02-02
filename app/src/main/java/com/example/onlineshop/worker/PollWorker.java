package com.example.onlineshop.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.onlineshop.utils.ServiceUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PollWorker extends Worker {

    public static final String TAG = "PollWorker";
    public static final String POLL_WORKER_NAME = "PollWorker";

    public PollWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        ServiceUtils.pollAndShowNotification(getApplicationContext(), TAG);

        return Result.success();
    }

    public static void enqueueWork(Context context, boolean isOn) {
        WorkManager workManager = WorkManager.getInstance(context);

        if (isOn) {
            Log.d(TAG, "enqueued");
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .build();

            PeriodicWorkRequest periodicWorkRequest =
//                    new PeriodicWorkRequest.Builder(PollWorker.class, 3, TimeUnit.HOURS)
                    new PeriodicWorkRequest.Builder(PollWorker.class, 15, TimeUnit.MINUTES)
                            .setConstraints(constraints)
                            .build();
            workManager.enqueueUniquePeriodicWork(
                    POLL_WORKER_NAME,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    periodicWorkRequest);
        } else {
            workManager.cancelUniqueWork(POLL_WORKER_NAME);
        }
    }

    public static boolean isWorkEnqueued(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);

        try {
            List<WorkInfo> workInfos =
                    workManager.getWorkInfosForUniqueWork(POLL_WORKER_NAME).get();

            for (WorkInfo workInfo : workInfos) {
                if (workInfo.getState() == WorkInfo.State.ENQUEUED ||
                        workInfo.getState() == WorkInfo.State.RUNNING) {
                    return true;
                }
                return false;
            }

        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }

        return true;
    }
}

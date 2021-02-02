package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.onlineshop.utils.SharedPreferencesOnlineShop;
import com.example.onlineshop.worker.PollWorker;

public class SettingViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mActive = new MutableLiveData<>();
    private int mTime;


    public SettingViewModel(@NonNull Application application) {
        super(application);
    }


    public void setStatusActiveToggle(boolean check) {
        mActive.setValue(check);
    }


    public void setTimeNotification(String time) {
        mTime = Integer.valueOf(mTime);

    }


    public boolean isPollWorkerActive() {
        return PollWorker.isWorkEnqueued(getApplication());
    }

    public LiveData<Boolean> getActive() {
        return mActive;
    }

    public void saveSetting() {
        SharedPreferencesOnlineShop.setTimeNotification(getApplication(), mTime);
        PollWorker.enqueueWork(getApplication(), mActive.getValue(), mTime);
    }

    public int getTimeFromSharedPreferences(){
       return SharedPreferencesOnlineShop.getSaveTimeNotification(getApplication());
    }
}

package com.example.onlineshop.view;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.event.NotificationEvent;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;
import com.example.onlineshop.view.activity.SplashActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OnlineShopApplication extends Application {
    public static final String TAG_EVENT_BUS = "PGEventBus";



    @Override
    public void onCreate() {
        super.onCreate();


        Log.d("PGEventBus", "App onCreate");

        createNotificationChannel();

        EventBus.getDefault().register(this);



    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.d("PGEventBus", "App onCreate");

        EventBus.getDefault().unregister(this);

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.channel_id);
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 1)
    public void onNotificationEventListener(NotificationEvent notificationEvent) {
        String msg = "Application received the notification event";
        Log.d("PGEventBus", msg);

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(
                notificationEvent.getNotificationId(),
                notificationEvent.getNotification());
    }


}

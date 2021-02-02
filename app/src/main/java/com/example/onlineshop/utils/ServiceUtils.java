package com.example.onlineshop.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.view.activity.OnlineShoppingMainActivity;

import java.util.List;

public class ServiceUtils {
    private static final int NOTIFICATION_ID = 1;

    public static void pollAndShowNotification(Context context, String tag) {
        ShopRepository shopRepository = ShopRepository.getInstance();
        shopRepository.getAllProductsAsync();
        List<Products> items = shopRepository.getAllProductsLiveData().getValue();

        if(items.size() == 0 || items == null)
            return;

        int lastItemId = items.get(0).getId();

        int lastSavedId = SharedPreferencesOnlineShop.getIdLastProduct(context);


        if(lastSavedId != lastItemId) {
            Log.d(tag, "show notification");
            sendNotificationEvent(context);
        }

        SharedPreferencesOnlineShop.setLastProductId(context, lastItemId);
    }

    private static void sendNotificationEvent(Context context) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                OnlineShoppingMainActivity.newIntent(context),
                0);

        String channelId = context.getResources().getString(R.string.channel_id);
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getResources().getString(R.string.new_product_title))
                .setContentText(context.getResources().getString(R.string.new_product_text))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }



}

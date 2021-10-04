package com.example.jotno.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.jotno.R;

import androidx.core.app.NotificationCompat;

public class DailyReceiver extends BroadcastReceiver {

    public static String MESSAGE = "message" ;
    public static String ID = "id";
    private NotificationChannel mChannel;

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(intent.getStringExtra(MESSAGE));
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(context, MedicinesActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Notification.Builder mNotifyBuilder = null;  // Declair VIBRATOR Permission in AndroidManifest.xml
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mNotifyBuilder = new Notification.Builder(
                    context,CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                    .setSmallIcon(R.mipmap.jotno_icon)
                    .setContentTitle("Jotno Notification")
                    .setContentText(intent.getStringExtra(MESSAGE)).setSound(alarmSound)
                    .setAutoCancel(true).setWhen(when)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        }
        notificationManager.notify(intent.getIntExtra(ID,-1), mNotifyBuilder.build());
    }

}

package com.example.jotno.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.example.jotno.R;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class DailyReceiver extends BroadcastReceiver {

    public static String MESSAGE = "message" ;
    public static String ID = "id";
    private NotificationChannel mChannel;
    private MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManager notificationManager2 = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        mediaPlayer = MediaPlayer.create(context, R.raw.take_your_medicines_speech);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        },2000);



        Uri alarmSound =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.take_your_medicines_speech);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(intent.getStringExtra(MESSAGE));
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setSound(alarmSound, attributes);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(context, MedicinesActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);




                Notification.Builder mNotifyBuilder = null;  // Declair VIBRATOR Permission in AndroidManifest.xml
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mNotifyBuilder = new Notification.Builder(
                    context,CHANNEL_ID)
                    .setSmallIcon(R.mipmap.jotno_icon)
                    .setContentTitle("Jotno Notification")
                    .setContentText(intent.getStringExtra(MESSAGE))
                    .setSound(alarmSound)
                    .setAutoCancel(true).setWhen(when)
//                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jotno_icon))
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            notificationManager.notify(intent.getIntExtra(ID,-1), mNotifyBuilder.build());
        }else{

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.jotno_icon)
                    .setContentTitle("Jotno Notification")
                    .setContentText(intent.getStringExtra(MESSAGE))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true).setWhen(when)
                    .setSound(alarmSound)
//                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jotno_icon))
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});


            notificationManager2.notify(intent.getIntExtra(ID,-1), builder.build());


        }

    }

}

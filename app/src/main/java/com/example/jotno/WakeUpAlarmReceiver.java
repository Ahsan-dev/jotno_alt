package com.example.jotno;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.jotno.Activity.DailyReceiver;
import com.example.jotno.Activity.MainActivity;
import com.example.jotno.Activity.MedicinesActivity;
import com.example.jotno.PaperDB.AlarmPaper;
import com.example.jotno.PaperDB.Test;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

import io.paperdb.Paper;

public class WakeUpAlarmReceiver extends BroadcastReceiver {

    private int morningCount, noonCount, nightCount;
    private List<String> morningList, noonList, nightList;
    private String morningMsg, noonMsg, nightMsg;

    @Override
    public void onReceive(Context context, Intent intent) {

        Paper.init(context);

        morningCount = Paper.book().read(AlarmPaper.morningCount);
        noonCount = Paper.book().read(AlarmPaper.noonCount);
        nightCount = Paper.book().read(AlarmPaper.nightCount);
        morningList = Paper.book().read(AlarmPaper.morningList);
        noonList = Paper.book().read(AlarmPaper.noonList);
        nightList = Paper.book().read(AlarmPaper.nightList);
        morningMsg = Paper.book().read(AlarmPaper.morningMessage);
        noonMsg = Paper.book().read(AlarmPaper.noonMessage);
        nightMsg = Paper.book().read(AlarmPaper.nightMessage);

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            if (morningCount > 0) {

                setNotification(9, 40, morningMsg, 10, context);

            }

            if (noonCount > 0) {

                setNotification(14, 30, noonMsg, 11, context);

            }

            if (nightCount > 0) {


                setNotification(21, 30, nightMsg, 12, context);

            }
        }

//      // activity.setNotification();
//
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
////            Intent i = new Intent();
////            i.setClassName("com.example.jotno",
////                    "com.example.jotno.MedicinesActivity");
////            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(i);
////
////            createNotificationChannel(context);
////            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, String.valueOf(1000));
//            setNotification(15, 29, nightMsg, 1, context);
//            Paper.book().delete(Test.testString);
//            Paper.book().write(Test.testString,"Booted");
//
//        }else{
//
//            Paper.book().delete(Test.testString);
//            Paper.book().write(Test.testString,"Not Booted");
//        }

    }





    private void setNotification(int hour, int minute, String message, int id, Context context){

        Toast.makeText(context, "Reboot Completed !!!", Toast.LENGTH_SHORT).show();


        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 1);
        calendar.set(Calendar.MILLISECOND, 1);

        Calendar cur = Calendar.getInstance();

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra(DailyReceiver.MESSAGE,message);
        myIntent.putExtra(DailyReceiver.ID,id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }


}

package com.example.jotno;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jotno.Activity.DailyReceiver;
import com.example.jotno.Activity.MainActivity;
import com.example.jotno.Activity.MedicinesActivity;
import com.example.jotno.PaperDB.AlarmPaper;
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


//        Paper.init(context);
//
//        MedicinesActivity activity = new MedicinesActivity();

//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//
//            morningCount = Paper.book().read(AlarmPaper.morningCount);
//            noonCount = Paper.book().read(AlarmPaper.noonCount);
//            nightCount = Paper.book().read(AlarmPaper.nightCount);
//            morningList = Paper.book().read(AlarmPaper.morningList);
//            noonList = Paper.book().read(AlarmPaper.noonList);
//            nightList = Paper.book().read(AlarmPaper.nightList);
//            morningMsg = Paper.book().read(AlarmPaper.morningMessage);
//            noonMsg = Paper.book().read(AlarmPaper.noonMessage);
//            nightMsg = Paper.book().read(AlarmPaper.nightMessage);
//
//
//            if (morningCount > 0) {
//
//
//                setNotification(12, 5, morningMsg, 10, context);
//
//            }
//
//            if (noonCount > 0) {
//
//                setNotification(12, 7, noonMsg, 11, context);
//
//            }
//
//            if (nightCount > 0) {
//
//
//                setNotification(12, 8, nightMsg, 12, context);
//
//            }
//        }

      // activity.setNotification();

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            Intent i = new Intent();
//            i.setClassName("com.example.jotno",
//                    "com.example.jotno.MedicinesActivity");
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
            setNotification(12,25,"Reboot",10,context);

        }


    }


    private void setNotification(int hour, int minute, String message, int id, Context context){

        Toast.makeText(context, "Reboot Completed !!!", Toast.LENGTH_SHORT).show();


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar cur = Calendar.getInstance();

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra(DailyReceiver.MESSAGE,message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }


}

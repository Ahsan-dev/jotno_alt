package com.example.jotno;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jotno.Activity.DailyReceiver;
import com.example.jotno.PaperDB.AlarmPaper;

import java.util.Calendar;
import java.util.List;

import io.paperdb.Paper;

public class WakeUpAlarmReceiver extends BroadcastReceiver {

    private int morningCount, noonCount, nightCount;
    private List<String> morningList, noonList, nightList;
    private String morningMsg, noonMsg, nightMsg;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            morningCount = Paper.book().read(AlarmPaper.morningCount);
            noonCount = Paper.book().read(AlarmPaper.noonCount);
            nightCount = Paper.book().read(AlarmPaper.nightCount);
            morningList = Paper.book().read(AlarmPaper.morningList);
            noonList = Paper.book().read(AlarmPaper.noonList);
            nightList = Paper.book().read(AlarmPaper.nightList);
            morningMsg = Paper.book().read(AlarmPaper.morningMessage);
            noonMsg = Paper.book().read(AlarmPaper.noonMessage);
            nightMsg = Paper.book().read(AlarmPaper.nightMessage);


            if (morningCount > 0) {


                setNotification(17, 55, morningMsg, 10, context);

            }

            if (noonCount > 0) {

                setNotification(17, 56, noonMsg, 11, context);

            }

            if (nightCount > 0) {


                setNotification(17, 57, nightMsg, 12, context);

            }
        }

    }

    private void setNotification(int hour, int minute, String message, int id, Context context){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        Calendar cur = Calendar.getInstance();

//        if (cur.after(calendar)) {
//            calendar.add(Calendar.DATE, 1);
//        }

        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra(DailyReceiver.MESSAGE,message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, id, myIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }
}

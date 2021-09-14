package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jotno.MedicineItemAdapter;
import com.example.jotno.R;
import com.example.jotno.Room.Entity.Medicine;
import com.example.jotno.ViewModel.MedicinesViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedicinesActivity extends AppCompatActivity {

    private RecyclerView medicineRecycler;
    private List<Medicine> medicineList;
    private MedicineItemAdapter medicineItemAdapter;
    private MedicinesViewModel medicinesViewModel;
    private TextView notifyMeTxt;
    private AlarmManager alarmManager;
    private Context context;
    private String message = "Hello";

    private int morningCount, noonCount, nightCount;
    private List<String> morningList, noonList, nightList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        medicinesViewModel = new ViewModelProvider(this).get(MedicinesViewModel.class);

        notifyMeTxt = findViewById(R.id.set_alarm_text_btn);

        medicineList = new ArrayList<>();



        medicineRecycler = findViewById(R.id.medicines_recycler_id);

        medicinesViewModel.getMorningsCount().observe(this, s -> {

            morningCount = Integer.parseInt(s);

        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        medicinesViewModel.getMedicines().observe(this,medicines -> {

            medicineList = medicines;
            medicineRecycler.setLayoutManager(new LinearLayoutManager(this));
            medicineRecycler.hasFixedSize();
            medicineItemAdapter = new MedicineItemAdapter(medicineList);
            medicineRecycler.setAdapter(medicineItemAdapter);
            medicineItemAdapter.notifyDataSetChanged();

        });
    }

    public void reloadMedicine(View view){

        medicinesViewModel.deleteAll();
        medicinesViewModel.insertMedicines();


        medicinesViewModel.getMedicines().observe(this, medicines -> {

            medicineList = medicines;
            medicineRecycler.setLayoutManager(new LinearLayoutManager(this));
            medicineRecycler.hasFixedSize();
            medicineItemAdapter = new MedicineItemAdapter(medicineList);
            medicineRecycler.setAdapter(medicineItemAdapter);
            medicineItemAdapter.notifyDataSetChanged();


        });

        notifyMeTxt.setVisibility(View.VISIBLE);

    }

    public void setAlarm(View view) {




    }

    private void setNotification(int hour, int minute){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 32);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra(DailyReceiver.MESSAGE,message);
        int ALARM1_ID = 10000;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }

}
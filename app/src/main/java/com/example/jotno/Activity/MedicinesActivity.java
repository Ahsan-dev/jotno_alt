package com.example.jotno.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import androidx.appcompat.widget.Toolbar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jotno.Adapter.MedicineItemAdapter;
import com.example.jotno.PaperDB.AlarmPaper;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PaperDB.Test;
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
    private String morningMessage = "Hello", noonMessage = "Hello", nightMessage = "Hello";
    private ImageView backBtn;
    private TextView toolTitle;
    private Toolbar toolbar;
    private int morningCount, noonCount, nightCount;
    private List<String> morningList, noonList, nightList;
    String test = "not booted";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        toolbar = findViewById(R.id.medicines_activity_toolbar);
        toolTitle = findViewById(R.id.toolbar2_title_txt);
        backBtn = findViewById(R.id.toolbar2_title_img);
        toolTitle.setText("Your Medicines");
        setSupportActionBar(toolbar);

        Paper.init(this);

        medicinesViewModel = new ViewModelProvider(this).get(MedicinesViewModel.class);

        notifyMeTxt = findViewById(R.id.set_alarm_text_btn);

        medicineList = new ArrayList<>();

        medicineRecycler = findViewById(R.id.medicines_recycler_id);

        backBtn.setOnClickListener(view -> {

            startActivity(new Intent(MedicinesActivity.this,MainActivity.class));

        });


        test = Paper.book().read(Test.testString);
        Toast.makeText(MedicinesActivity.this, test, Toast.LENGTH_SHORT).show();
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


        medicinesViewModel.getMorningsCount().observe(this, s -> {

            morningCount = Integer.parseInt(s);
            Paper.book().write(AlarmPaper.morningCount,morningCount);

        });

        medicinesViewModel.getNoonsCount().observe(this, s -> {

            noonCount = Integer.parseInt(s);
            Paper.book().write(AlarmPaper.noonCount,noonCount);

        });

        medicinesViewModel.getNightsCount().observe(this, s -> {

            nightCount = Integer.parseInt(s);
            Paper.book().write(AlarmPaper.nightCount,nightCount);

        });

        medicinesViewModel.getAllMorningMedicines().observe(this, s -> {

            morningList = s;
            Paper.book().write(AlarmPaper.morningList,morningList);

        });

        medicinesViewModel.getAllNoonMedicine().observe(this, s -> {

            noonList = s;
            Paper.book().write(AlarmPaper.noonList,noonList);

        });

        medicinesViewModel.getAllNightMedicines().observe(this, s -> {

            nightList = s;
            Paper.book().write(AlarmPaper.nightList,nightList);

        });





    }

    public void reloadMedicine(View view){

        medicinesViewModel.deleteAll();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                medicinesViewModel.insertMedicines();

            }
        },1000);




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

        notifyMeTxt.setVisibility(View.GONE);
        setNotification();


    }

    public void setNotification(){


        if(morningCount>0){

            morningMessage = "";
            morningMessage = "Hello "+Paper.book().read(PermanentPatient.userNameString)+" You have "+morningCount+" medicine at morning\n";
            for(int i=0;i<morningList.size();i++){

                morningMessage += morningList.get(i).toString();
                morningMessage += "\n";


            }
            Paper.book().write(AlarmPaper.morningMessage,morningMessage);

            setNotification(9,30,morningMessage,15);

        }

        if(noonCount>0){

            noonMessage = "";
            noonMessage = "Hello "+Paper.book().read(PermanentPatient.userNameString)+" You have "+noonCount+" medicine at noon\n";
            for(int i=0;i<noonList.size();i++){

                noonMessage += noonList.get(i).toString();
                noonMessage += "\n";


            }

            Paper.book().write(AlarmPaper.noonMessage,noonMessage);

            setNotification(14,30,noonMessage,16);

        }

        if(nightCount>0){

            nightMessage = "";
            nightMessage = "Hello "+Paper.book().read(PermanentPatient.userNameString)+" You have "+nightCount+" medicine at night\n";
            for(int i=0;i<nightList.size();i++){

                nightMessage += nightList.get(i).toString();
                nightMessage += "\n";

            }

            Paper.book().write(AlarmPaper.nightMessage,nightMessage);

            setNotification(21,30,nightMessage,17);

        }




    }

    private void setNotification(int hour, int minute, String message, int id){

        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 1);
        calendar.set(Calendar.MILLISECOND, 1);


        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }


        Intent myIntent = new Intent(this, DailyReceiver.class);
        myIntent.putExtra(DailyReceiver.MESSAGE,message);
        myIntent.putExtra(DailyReceiver.ID,id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }

}
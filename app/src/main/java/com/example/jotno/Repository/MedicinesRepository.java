package com.example.jotno.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.jotno.Activity.MedicinesActivity;
import com.example.jotno.Models.AlMedicineBody;
import com.example.jotno.Models.AllMedicines;
import com.example.jotno.Models.MedicineRemote;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PaperDB.RemoteMedicinesSP;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.example.jotno.Room.Database.MedicineDB;
import com.example.jotno.Room.Entity.Medicine;
import com.example.jotno.Room.dao.MedicineDao;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicinesRepository {

    private MedicineDao medicineDao;
    private LiveData<List<Medicine>> getAllMedicines;
    private List<AlMedicineBody> medicineRemoteList;
    private LiveData<String> morningMedicineCountString, noonMedicineCountString, nightMedicineCountString;
    private LiveData<List<String>> morningMedicinesList, noonMedicinesList, nightMedicinesList;
    private Api api;
    private Context context;



    public MedicinesRepository(Application application) {

        Paper.init(application.getApplicationContext());

        MedicineDB db = MedicineDB.getDatbase(application);

        medicineDao = db.MedicineDao();
        getAllMedicines = medicineDao.getAllMedicines();

        morningMedicineCountString = medicineDao.getMorningMedicinesCount();
        noonMedicineCountString = medicineDao.getNoonMedicinesCount();
        nightMedicineCountString = medicineDao.getNightMedicinesCount();

        morningMedicinesList = medicineDao.getMorningMedicines();
        noonMedicinesList = medicineDao.getNoonMedicines();
        nightMedicinesList = medicineDao.getNightMedicines();

        api = RetroClient.getClient().create(Api.class);
        context = application.getApplicationContext();


    }

    public LiveData<List<Medicine>> getAllMedicines(){

        return getAllMedicines;

    }


    public LiveData<List<String>> getAllMorningMedicinesList(){

        return morningMedicinesList;

    }

    public LiveData<List<String>> getAllNoonMedicinesList(){

        return noonMedicinesList;

    }

    public LiveData<List<String>> getAllNightMedicinesList(){

        return nightMedicinesList;

    }

    public LiveData<String> getMorningMedicinesCount(){

        return morningMedicineCountString;

    }

    public LiveData<String> getNoonMedicinesCount(){

        return noonMedicineCountString;

    }

    public LiveData<String> getNightMedicinesCount(){

        return nightMedicineCountString;

    }


    public void insert(List<Medicine> medicineList){

        MedicineDB.databaseWriteExecutor.execute(()->{

            medicineDao.insertMedicine(medicineList);

        });
    }

//    public LiveData<List<MedicineRemote>> getAllMedicinesFromRemote(){
//
//       medicineRemoteList = new ArrayList<>();
//
//        medicineRemoteList.add(new MedicineRemote("Serjel","1+0+1"));
//        medicineRemoteList.add(new MedicineRemote("Rozith","1+0+1"));
//        medicineRemoteList.add(new MedicineRemote("Rupa","0+0+1"));
//        medicineRemoteList.add(new MedicineRemote("Napa","1+1+1"));
//
//
//
//        return LiveData<medicineRemoteList>;
//
//    }



    public void getAllMedicinesFromRemote(){

        medicineRemoteList = new ArrayList<>();

        api.getAllMedicines(Paper.book().read(PermanentPatient.userIdString))
                .enqueue(new Callback<AllMedicines>() {
                    @Override
                    public void onResponse(Call<AllMedicines> call, Response<AllMedicines> response) {

                        if(response.isSuccessful()){

                            if(response.body().getStatus().equals("success")){

                                medicineRemoteList = response.body().getMedicine();
                                Paper.book().write(RemoteMedicinesSP.remoteMedicinesListString,medicineRemoteList);
                                Toast.makeText(context, "Data loaded", Toast.LENGTH_SHORT).show();
                                Log.d("remote_data0", String.valueOf(medicineRemoteList.size()));


                            }else{

                                Toast.makeText(context,"Something wrong!!!", Toast.LENGTH_SHORT).show();

                            }

                        }else{

                            Toast.makeText(context, "Response not found!!!", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AllMedicines> call, Throwable t) {

                        Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("remote_data", String.valueOf(medicineRemoteList.size()));
            }
        },1000);



    }

    public void deleteAll(){

        new deleteAllMedicinesAsyncTask(medicineDao).execute();

    }


    private static class deleteAllMedicinesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MedicineDao mAsyncTaskDao;

        deleteAllMedicinesAsyncTask(MedicineDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


}

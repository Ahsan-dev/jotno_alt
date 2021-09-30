package com.example.jotno.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jotno.Models.MedicineRemote;
import com.example.jotno.Room.Database.MedicineDB;
import com.example.jotno.Room.Entity.Medicine;
import com.example.jotno.Room.dao.MedicineDao;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class MedicinesRepository {

    private MedicineDao medicineDao;
    private LiveData<List<Medicine>> getAllMedicines;
    private List<MedicineRemote> medicineRemoteList;
    private LiveData<String> morningMedicineCountString, noonMedicineCountString, nightMedicineCountString;
    private LiveData<List<String>> morningMedicinesList, noonMedicinesList, nightMedicinesList;


    public MedicinesRepository(Application application) {

        MedicineDB db = MedicineDB.getDatbase(application);

        medicineDao = db.MedicineDao();
        getAllMedicines = medicineDao.getAllMedicines();

        morningMedicineCountString = medicineDao.getMorningMedicinesCount();
        noonMedicineCountString = medicineDao.getNoonMedicinesCount();
        nightMedicineCountString = medicineDao.getNightMedicinesCount();

        morningMedicinesList = medicineDao.getMorningMedicines();
        noonMedicinesList = medicineDao.getNoonMedicines();
        nightMedicinesList = medicineDao.getNightMedicines();


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

    public List<MedicineRemote> getAllMedicinesFromRemote(){

        medicineRemoteList = new ArrayList<>();

        medicineRemoteList.add(new MedicineRemote("Serjel","1+0+1"));
        medicineRemoteList.add(new MedicineRemote("Rozith","1+0+1"));
        medicineRemoteList.add(new MedicineRemote("Rupa","0+0+1"));
        medicineRemoteList.add(new MedicineRemote("Napa","1+1+1"));
        medicineRemoteList.add(new MedicineRemote("Amodis","1+1+1"));
        medicineRemoteList.add(new MedicineRemote("Clonatril","0+0+1"));
        medicineRemoteList.add(new MedicineRemote("Ace","1+0+1"));



        return medicineRemoteList;

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

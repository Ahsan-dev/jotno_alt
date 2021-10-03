package com.example.jotno.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jotno.Activity.MedicinesActivity;
import com.example.jotno.Models.AlMedicineBody;
import com.example.jotno.Models.MedicineRemote;
import com.example.jotno.PaperDB.RemoteMedicinesSP;
import com.example.jotno.Repository.MedicinesRepository;
import com.example.jotno.Room.Entity.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import io.paperdb.Paper;

public class MedicinesViewModel extends AndroidViewModel {

    private MedicinesRepository medicinesRepository;
    private LiveData<List<Medicine>> listLiveData;
    private List<AlMedicineBody> remoteMedicinesList;
    private List<Medicine> giveMedicines;
    private LiveData<String> morningMedicineCountString, noonMedicineCountString, nightMedicineCountString;
    private LiveData<List<String>> morningMedicinesList, noonMedicinesList, nightMedicinesList;


    public MedicinesViewModel(@NonNull @NotNull Application application) {
        super(application);

        medicinesRepository = new MedicinesRepository(application);
        listLiveData = medicinesRepository.getAllMedicines();
        medicinesRepository.getAllMedicinesFromRemote();

        Paper.init(application.getApplicationContext());


        morningMedicineCountString = medicinesRepository.getMorningMedicinesCount();
        noonMedicineCountString = medicinesRepository.getNoonMedicinesCount();
        nightMedicineCountString = medicinesRepository.getNightMedicinesCount();

        morningMedicinesList = medicinesRepository.getAllMorningMedicinesList();
        noonMedicinesList = medicinesRepository.getAllNoonMedicinesList();
        nightMedicinesList = medicinesRepository.getAllNightMedicinesList();

    }

    public LiveData<List<Medicine>>  getMedicines(){

        return listLiveData;

    }

    public LiveData<List<String>> getAllMorningMedicines(){

        return morningMedicinesList;

    }

    public LiveData<List<String>> getAllNoonMedicine(){

        return noonMedicinesList;

    }

    public LiveData<List<String>> getAllNightMedicines(){

        return nightMedicinesList;

    }

    public LiveData<String> getMorningsCount(){

        return morningMedicineCountString;

    }

    public LiveData<String> getNoonsCount(){

        return noonMedicineCountString;

    }

    public LiveData<String> getNightsCount(){

        return nightMedicineCountString;

    }

    public  void deleteAll(){

        medicinesRepository.deleteAll();

    }

    public void insertMedicines(){
        remoteMedicinesList = new ArrayList<>();
        remoteMedicinesList = Paper.book().read(RemoteMedicinesSP.remoteMedicinesListString);
        giveMedicines = new ArrayList<>();
        for(int i=0;i<remoteMedicinesList.size();i++){


            giveMedicines.add(new Medicine(
                    remoteMedicinesList.get(i).getName(),
                    String.valueOf(remoteMedicinesList.get(i).getTiming().charAt(0)),
                    String.valueOf(remoteMedicinesList.get(i).getTiming().charAt(2)),
                    String.valueOf(remoteMedicinesList.get(i).getTiming().charAt(4))
            ));

            Log.d("give_medicines", String.valueOf(giveMedicines.size()));

        }

        medicinesRepository.insert(giveMedicines);
    }




}

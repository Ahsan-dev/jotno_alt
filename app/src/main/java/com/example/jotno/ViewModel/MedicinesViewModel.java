package com.example.jotno.ViewModel;

import android.app.Application;

import com.example.jotno.Models.MedicineRemote;
import com.example.jotno.Repository.MedicinesRepository;
import com.example.jotno.Room.Entity.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MedicinesViewModel extends AndroidViewModel {

    private MedicinesRepository medicinesRepository;
    private LiveData<List<Medicine>> listLiveData;
    private List<MedicineRemote> remoteMedicinesList;
    private List<Medicine> giveMedicines;
    private LiveData<String> morningMedicineCountString, noonMedicineCountString, nightMedicineCountString;
    private LiveData<List<String>> morningMedicinesList, noonMedicinesList, nightMedicinesList;


    public MedicinesViewModel(@NonNull @NotNull Application application) {
        super(application);

        medicinesRepository = new MedicinesRepository(application);
        listLiveData = medicinesRepository.getAllMedicines();
        remoteMedicinesList = medicinesRepository.getAllMedicinesFromRemote();

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

        giveMedicines = new ArrayList<>();
        for(int i=0;i<remoteMedicinesList.size();i++){

            giveMedicines.add(new Medicine(
                    remoteMedicinesList.get(i).getMedicine(),
                    String.valueOf(remoteMedicinesList.get(i).getInstruction().charAt(0)),
                    String.valueOf(remoteMedicinesList.get(i).getInstruction().charAt(2)),
                    String.valueOf(remoteMedicinesList.get(i).getInstruction().charAt(4))
            ));

        }

        medicinesRepository.insert(giveMedicines);
    }




}

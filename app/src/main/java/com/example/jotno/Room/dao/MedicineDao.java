package com.example.jotno.Room.dao;

import com.example.jotno.Room.Entity.Medicine;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMedicine(List<Medicine> medicineLIst);

    @Query("DELETE FROM medicine_table")
    void deleteAll();

    @Query("SELECT * FROM medicine_table")
    LiveData<List<Medicine>> getAllMedicines();

    @Query("SELECT COUNT(*) FROM medicine_table WHERE morning > 0")
    LiveData<String> getMorningMedicinesCount();

    @Query("SELECT medicine FROM medicine_table WHERE morning > 0")
    LiveData<List<String>> getMorningMedicines();

    @Query("SELECT COUNT(*) FROM medicine_table WHERE noon > 0")
    LiveData<String> getNoonMedicinesCount();

    @Query("SELECT medicine FROM medicine_table WHERE noon > 0")
    LiveData<List<String>> getNoonMedicines();

    @Query("SELECT COUNT(*) FROM medicine_table WHERE night > 0")
    LiveData<String> getNightMedicinesCount();

    @Query("SELECT medicine FROM medicine_table WHERE night > 0")
    LiveData<List<String>> getNightMedicines();


}

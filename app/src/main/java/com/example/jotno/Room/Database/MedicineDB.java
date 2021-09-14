package com.example.jotno.Room.Database;

import android.content.Context;

import com.example.jotno.Room.Entity.Medicine;
import com.example.jotno.Room.dao.MedicineDao;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Medicine.class},version = 1,exportSchema = false)
public abstract class MedicineDB extends RoomDatabase {

    public abstract MedicineDao MedicineDao();

    private static volatile MedicineDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MedicineDB getDatbase(Context context){

        if(INSTANCE==null){

            synchronized (MedicineDB.class){

                if(INSTANCE==null){

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicineDB.class,
                            "medicine_database").build();

                }


            }

        }
        return INSTANCE;

    }


    private static RoomDatabase.Callback medicineDbCallback = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{


                MedicineDao dao = INSTANCE.MedicineDao();

                dao.deleteAll();

            });

        }
    };


}

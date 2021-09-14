package com.example.jotno.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine_table")
public class Medicine {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "medicine")
    public String medicine;

    @ColumnInfo(name = "morning")
    public String morning;

    @ColumnInfo(name = "noon")
    public String noon;

    @ColumnInfo(name = "night")
    public String night;

    public Medicine(String medicine, String morning, String noon, String night) {
        this.medicine = medicine;
        this.morning = morning;
        this.noon = noon;
        this.night = night;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }
}

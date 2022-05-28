package com.example.telikoapp.ui.localDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacation")
public class Vacation {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "VacationId")
    private int id;

    @ColumnInfo(name = "VacationCity")
    private String city;

    @ColumnInfo(name = "VacationCountry")
    private String country;

    @ColumnInfo(name = "VacationDurDays")
    private int durationDays;

    @ColumnInfo(name = "VacationType")
    private String vacationType;

    public Vacation(){
    }

    public Vacation(int id, String city, String country, int durationDays, String vacationType) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.durationDays = durationDays;
        this.vacationType = vacationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public String getVacationType() {
        return vacationType;
    }

    public void setVacationType(String vacationType) {
        this.vacationType = vacationType;
    }
}


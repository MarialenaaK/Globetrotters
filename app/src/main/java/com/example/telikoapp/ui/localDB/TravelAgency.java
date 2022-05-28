package com.example.telikoapp.ui.localDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "travelAgency")
public class TravelAgency {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "AgencyId")
    private int id;

    @ColumnInfo(name = "AgencyName")
    private String name;

    @ColumnInfo(name = "AgencyAddress")
    private String address;

    public TravelAgency() {
    }

    public TravelAgency(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


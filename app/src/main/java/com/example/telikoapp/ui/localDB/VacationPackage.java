package com.example.telikoapp.ui.localDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "vacationPackage", foreignKeys = {@ForeignKey(entity = TravelAgency.class, parentColumns = "AgencyId", childColumns = "PackageAgencyId", onDelete = ForeignKey.RESTRICT), @ForeignKey(entity = Vacation.class, parentColumns = "VacationId", childColumns = "PackageVacationId", onDelete = ForeignKey.RESTRICT)})
public class VacationPackage {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "PackageId")
    private int id;

    @ColumnInfo(name = "PackageAgencyId")
    private int agencyId;

    @ColumnInfo(name = "PackageVacationId")
    private int vacationId;

    @ColumnInfo(name = "PackageDate")
    private String date;

    @ColumnInfo(name = "PackagePrice")
    private int price;

    public VacationPackage(){
    }

    public VacationPackage(int id, int agencyId, int vacationId, String date, int price) {
        this.id = id;
        this.agencyId = agencyId;
        this.vacationId = vacationId;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getVacationId() {
        return vacationId;
    }

    public void setVacationId(int vacationId) {
        this.vacationId = vacationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


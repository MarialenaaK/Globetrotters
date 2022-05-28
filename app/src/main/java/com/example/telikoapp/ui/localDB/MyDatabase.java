package com.example.telikoapp.ui.localDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TravelAgency.class,  Vacation.class, VacationPackage.class}, version = 3)
public abstract class MyDatabase<MIGRATION_2_3> extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;
    public abstract MyDao myDao();


    public void execSQL(String drop_table_if_exists_table_tmp) {
    }
}


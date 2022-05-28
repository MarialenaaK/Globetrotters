package com.example.telikoapp.ui.localDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.telikoapp.ui.cloudDB.Customer;
import com.example.telikoapp.ui.home.ResultsIntInt;
import com.example.telikoapp.ui.home.ResultsStringInt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;

import java.util.List;

@Dao
public interface MyDao {



    //Question 1
    @Query("select AgencyId from travelAgency "+
          "where AgencyAddress IN (select AgencyAddress "+ "from travelAgency"
           +" where AgencyAddress ='Θεσσαλονίκη')")
    public List<Integer>getQuery1();

    //Question 2
    @Query("select * from travelAgency")
    public List<TravelAgency>getTravelAgencies();

    //Question 3
    @Query("select AgencyName as field1, COUNT(*)  as field2 from travelAgency GROUP BY AgencyName")
    public List<ResultsStringInt>getQuery3();


   //Question 4

    @Query("select VacationCity from VACATION "+
            "where VacationCountry IN (select VacationCountry "+"from Vacation "+"where VacationCountry='Ελλάδα' ) ")
    public List<String>getQuery4();

    //Question 5
    @Query("select * from vacation")
    public List<Vacation> getEkdromi();

    //Question 6
    @Query("select VacationCity as field1, COUNT(*)  as field2 from Vacation GROUP BY VacationCity")
    public List<ResultsStringInt>getQuery6();

    //Question 7
    @Query("select PackageDate from vacationPackage " + "where PackagePrice IN (select PackagePrice "+"from vacationPackage "+"where PackagePrice <200) ")
    List<String>getQuery7();



    //question 9
    @Query("select PackageAgencyId as field3, MIN(PackagePrice) as field4 from vacationPackage GROUP BY PackageAgencyId ")
    public List<ResultsIntInt>getQuery9();




    //FROM eleni's travelAgencyDAo
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertAgency(TravelAgency travelAgency);



    @Delete
    public void deleteTravelAgency(TravelAgency travelAgency);

    @Update
    public void update(TravelAgency travelAgency);

    //FROM eleni's VacationDao
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertVacation(Vacation vacation);

    @Query("select * from vacation")
    public List<Vacation> getVacations();

    @Delete
    public void deleteVacation(Vacation vacation);

    @Update
    public void update(Vacation vacation);

    //FROM eleni's vacationPackageDAo
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertVacationPackage(VacationPackage vacationPackage);

    @Query("select * from vacationPackage")
    public List<VacationPackage> getVacationPackages();

    @Delete
    public void deleteVacationPackage(VacationPackage vacationPackage);

    @Update
    public void update(VacationPackage vacationPackage);
}
//

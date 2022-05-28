package com.example.telikoapp.ui.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.telikoapp.R;
import com.example.telikoapp.ui.cloudDB.Customer;
import com.example.telikoapp.ui.localDB.MainActivity;
import com.example.telikoapp.ui.localDB.TravelAgency;
import com.example.telikoapp.ui.localDB.Vacation;
import com.example.telikoapp.ui.localDB.VacationPackage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class QueryFragment extends Fragment {

    Spinner spinner, spinner2;
    TextView queryquestions,queryresults;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    Button anazitisi;
    int test;

    //test for firebase queries
    DocumentReference documentReference;

    public QueryFragment() {
        // Required empty public constructor
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_query,container,false);
        final String [] queryArray=getResources().getStringArray(R.array.queries_desc);

        final String [] queryArray2=getResources().getStringArray(R.array.queries_desc2);

        queryquestions=view.findViewById(R.id.textView9);
        spinner=view.findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(getContext(),R.array.queries_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //test gia spinner2
        adapter2= ArrayAdapter.createFromResource(getContext(),R.array.queries_array2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner2=view.findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);
        //end

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                queryquestions.setText(queryArray[position]);
                test=position+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                queryquestions.setText(queryArray2[position]);
                test=position+10;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });



        queryresults=view.findViewById(R.id.textResults);
        anazitisi=view.findViewById(R.id.btnAnazitisi);
        anazitisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queryresults.setText("test" + test);
                String results = "";
                switch (test) {
                    case 2:
                        List<Integer> intcode = MainActivity.myDatabase.myDao().getQuery1();
                        for (Integer i : intcode) {
                            results = results + "\n Κωδικοί: " + i + "\n";
                        }

                        queryresults.setText(results);
                        break;
                    case 3:
                        List<TravelAgency> travelAgencies = MainActivity.myDatabase.myDao().getTravelAgencies();
                        for (TravelAgency i : travelAgencies) {
                            String name = i.getName();
                            int code = i.getId();
                            String adress = i.getAddress();

                            results = results + "Όνομα: " + name + "\n Κωδικός: " + code + "\n Διεύθυνση: " + adress + "\n";
                        }
                        queryresults.setText(results);
                        break;
                    case 4:
                        List<ResultsStringInt> resultsStringInt = MainActivity.myDatabase.myDao().getQuery3();
                        for (ResultsStringInt i : resultsStringInt) {
                            String name = i.getField1();
                            int code = i.getField2();

                            results = results + "Όνομα: " + name + "\n";
                        }
                        queryresults.setText(results);
                        break;


                    case 5:
                        List<String> string = MainActivity.myDatabase.myDao().getQuery4();
                        for (String i : string) {
                            results = results + "\n Οι πόλεις με ταξίδια είναι: " + i;
                        }
                        queryresults.setText(results);
                        break;


                    case 6:
                        List<Vacation> ekdromi = MainActivity.myDatabase.myDao().getEkdromi();
                        for (Vacation i : ekdromi) {
                            int code = i.getId();
                            String city = i.getCity();
                            String country = i.getCountry();
                            int duration=i.getDurationDays();
                            String type = i.getVacationType();


                            results = results + "\n Πόλη: " + city + " Κωδικός: " + code + "\n" + " Χώρα: " + country + "\n" +" Διάρκεια: " + duration + "\n" + "Τύπος: " + type + "\n";
                        }
                        queryresults.setText(results);
                        break;
                    case 7:
                        List<ResultsStringInt> resultsStringInt2 = MainActivity.myDatabase.myDao().getQuery6();
                        for (ResultsStringInt i : resultsStringInt2) {
                            String city = i.getField1();
                            int count = i.getField2();

                            results = results + "\n Πόλη: " + city + "\n Εγγραφές: " + count + "\n";
                        }
                        queryresults.setText(results);
                        break;
                    case 8:
                        List<String> intcode2 = MainActivity.myDatabase.myDao().getQuery7();
                        for (String i : intcode2) {

                            results = results + "\n Ημερομηνία: " + i + "\n";
                        }

                        queryresults.setText(results);
                        break;
                    case 9:
                        List<VacationPackage> paketo = MainActivity.myDatabase.myDao().getVacationPackages();
                        for (VacationPackage i : paketo) {

                            String date = i.getDate();
                            int price = i.getPrice();

                            results = results + "\n Ημερομηνία Αναχώρησης: " + date + "\n Τιμή(σε ευρώ): " + price + "\n";
                        }
                        queryresults.setText(results);
                        break;
                    case 10:
                        List<ResultsIntInt> resultsIntInts = MainActivity.myDatabase.myDao().getQuery9();
                        for (ResultsIntInt i : resultsIntInts) {
                            int codeTaxidiotiko = i.getField3();
                            int minPrice = i.getField4();

                           results = results + "\n Κωδικός Ταξιδιωτικού Γραφείου: " + codeTaxidiotiko + "\n Ελάχιστη τιμή: " + minPrice + "\n";
                        }
                        queryresults.setText(results);
                        break;
                    case 11:


                        CollectionReference collectionReference = MainActivity.db.collection("Εύβοια");
                        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                String results = "";
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Customer customer = documentSnapshot.toObject(Customer.class);
                                    String name = customer.getName();
                                    String sirname=customer.getSirname();
                                    String hotel=customer.getHotel();
                                    String id=customer.getId();
                                    String phone=customer.getPhone();
                                    results= results + "\n Όνομα:" + name +"\n Επώνυμο:" + sirname +"\n Ξενοδοχείο:" + hotel + "\n Κωδικός:" + id +"\n Τηλέφωνο:" + phone + "\n"+"\n"+"\n";
                                }
                                queryresults.setText(results);
                            }
                        });


                        break;
                   case 12:
                       DocumentReference documentReference = MainActivity.db.collection("Εύβοια").document("dave591");
                       documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                               String results = "";

                                   if (documentSnapshot.exists()) {
                                       Customer customer = documentSnapshot.toObject(Customer.class);
                                       String name = customer.getName();
                                       String sirname=customer.getSirname();
                                       String hotel=customer.getHotel();
                                       String id=customer.getId();
                                       String phone=customer.getPhone();

                                       results = results + "\n Όνομα:" + name + "\n Επώνυμο:" + sirname + "\n Ξενοδοχείο:" + hotel + "\n Κωδικός:" + id + "\n Τηλέφωνο:" + phone + "\n" + "\n" + "\n";
                                   }

                               queryresults.setText(results);
                           }
                       });
                        queryresults.setText(results);
                        break;
                    case 13:
                      CollectionReference collectionReference3 = MainActivity.db.collection("Ρέθυμνο");
                        collectionReference3.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                String results = "";
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Customer customer = documentSnapshot.toObject(Customer.class);
                                    String name = customer.getName();
                                    String sirname=customer.getSirname();
                                    String hotel=customer.getHotel();
                                    String id=customer.getId();
                                    String phone=customer.getPhone();
                                    results= results + "\n Όνομα:" + name +"\n Επώνυμο:" + sirname +"\n Ξενοδοχείο:" + hotel + "\n Κωδικός:" + id +"\n Τηλέφωνο:" + phone + "\n"+"\n"+"\n";
                                }
                                queryresults.setText(results);
                            }
                        });




                }
            }
        });

        return view;
    }




}




package com.example.telikoapp.ui.localDB;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.telikoapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


public class addVacationPackage extends Fragment {

    private EditText id, agency_id, vacation_id, date, price;
    private Button submit;

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}.\\d{2}.\\d{4}$");

    public addVacationPackage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_vacation_package, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        id = view.findViewById(R.id.idTextView);
        agency_id = view.findViewById(R.id.AgencyIdTextView);
        vacation_id = view.findViewById(R.id.VacationIdTextView);
        date = view.findViewById(R.id.dateTextView);
        price = view.findViewById(R.id.priceTextView);

        final int[] idTemp = {0};

        submit = view.findViewById(R.id.add3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(id.getText())) {
                    Toast.makeText(getContext(), "Παρακαλώ δώστε κωδικό!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        idTemp[0] = Integer.parseInt(id.getText().toString());

                        int agencyIdTemp = Integer.parseInt(agency_id.getText().toString());
                        int vacationIdTemp = Integer.parseInt(vacation_id.getText().toString());

                        String dateTemp = date.getText().toString();
                        boolean isValidPattern = isValid(dateTemp);
                        if (!isValidPattern) {
                            Toast.makeText(getContext(), "Παρακαλώ συμπληρώστε σωστά την ημερομηνία!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            SimpleDateFormat sdf = new SimpleDateFormat("ηη-μμ-εεεε");
                            sdf.setLenient(false);

                            try {
                                sdf.parse(dateTemp);
                            }
                            catch (ParseException e) {

                            }

                            int priceTemp = Integer.parseInt(price.getText().toString());

                            VacationPackage vacationPackage = new VacationPackage();
                            vacationPackage.setId(idTemp[0]);
                            vacationPackage.setAgencyId(agencyIdTemp);
                            vacationPackage.setVacationId(vacationIdTemp);
                            vacationPackage.setDate(dateTemp);
                            vacationPackage.setPrice(priceTemp);

                            try {
                                MainActivity.myDatabase.myDao().insertVacationPackage(vacationPackage);

                                id.setText("");
                                agency_id.setText("");
                                vacation_id.setText("");
                                date.setText("");
                                price.setText("");

                                Toast.makeText(getContext(), "Αποθηκεύτηκε!", Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e) {
                                Toast.makeText(getContext(), "Παρακαλώ ελέγξτε τις τιμές που συμπληρώσατε!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (NumberFormatException ex) {
                        Toast.makeText(getContext(), "Παρακαλώ ελέγξτε τις τιμές που συμπληρώσατε!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String dateStr){
        if(DATE_PATTERN.matcher(dateStr).matches())
            return true;
        return false;
    }

    public void onResume(){
        super.onResume();
        id.setText("");
        agency_id.setText("");
        vacation_id.setText("");
        date.setText("");
        price.setText("");
    }
}

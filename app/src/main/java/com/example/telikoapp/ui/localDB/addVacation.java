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

public class addVacation extends Fragment {

    private EditText id, city, country, duration, type;
    private Button submit;

    public addVacation() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_vacation, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        id = view.findViewById(R.id.codeTextView);
        city = view.findViewById(R.id.cityTextView);
        country = view.findViewById(R.id.countryTextView);
        duration = view.findViewById(R.id.durationTextView);
        type = view.findViewById(R.id.typeTextView);

        final int[] idTemp = {0};

        submit = view.findViewById(R.id.add2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(id.getText())){
                    Toast.makeText(getContext(), "Παρακαλώ δώστε κωδικό!", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        idTemp[0] = Integer.parseInt(id.getText().toString());

                        String cityTemp = city.getText().toString();
                        String countryTemp = country.getText().toString();
                        int durationTemp = Integer.parseInt(duration.getText().toString());
                        String typeTemp = type.getText().toString();

                        Vacation vacation = new Vacation();
                        vacation.setId(idTemp[0]);
                        vacation.setCity(cityTemp);
                        vacation.setCountry(countryTemp);
                        vacation.setDurationDays(durationTemp);
                        vacation.setVacationType(typeTemp);

                        try{
                            MainActivity.myDatabase.myDao().insertVacation(vacation);

                            id.setText("");
                            city.setText("");
                            country.setText("");
                            duration.setText("");
                            type.setText("");

                            Toast.makeText(getContext(), "Αποθηκεύτηκε!", Toast.LENGTH_LONG).show();
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Παρακαλώ ελέγξτε τις τιμές που συμπληρώσατε!", Toast.LENGTH_LONG).show();
                        }
                    } catch (NumberFormatException ex) {
                        Toast.makeText(getContext(), "Παρακαλώ ελέγξτε τις τιμές που συμπληρώσατε!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void onResume(){
        super.onResume();
        id.setText("");
        city.setText("");
        country.setText("");
        duration.setText("");
        type.setText("");
    }
}
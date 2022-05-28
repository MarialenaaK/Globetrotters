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

import java.util.List;


public class addTravelAgency extends Fragment {

    private EditText id, name, address;
    private Button submit;

    public addTravelAgency() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_travel_agency, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        id = view.findViewById(R.id.codeTextView);
        name = view.findViewById(R.id.nameTextView);
        address = view.findViewById(R.id.addressTextView);

        final int[] idTemp = {0};

        submit = view.findViewById(R.id.add1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(id.getText())){
                    Toast.makeText(getContext(), "Παρακαλώ δώστε κωδικό", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        idTemp[0] = Integer.parseInt(id.getText().toString());

                        String nameTemp = name.getText().toString();
                        String addressTemp = address.getText().toString();

                        TravelAgency travelAgency = new TravelAgency();
                        travelAgency.setId(idTemp[0]);
                        travelAgency.setName(nameTemp);
                        travelAgency.setAddress(addressTemp);

                        try{
                            MainActivity.myDatabase.myDao().insertAgency(travelAgency);

                            id.setText("");
                            name.setText("");
                            address.setText("");

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
        name.setText("");
        address.setText("");
    }
}


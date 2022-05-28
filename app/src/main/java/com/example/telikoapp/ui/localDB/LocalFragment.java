package com.example.telikoapp.ui.localDB;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.telikoapp.R;

import com.example.telikoapp.databinding.FragmentLocaldbBinding;

public class LocalFragment extends Fragment{

    private FragmentLocaldbBinding binding;

    Button addTravelAgency, seeTravelAgency, addVacation, seeVacation, addPackage, seePackage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_localdb, container, false);

        addTravelAgency = view.findViewById(R.id.addTravelAgencies);
        addTravelAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new addTravelAgency(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        seeTravelAgency = view.findViewById(R.id.seeTravelAgencies);
        seeTravelAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new showTravelAgency(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        addVacation = view.findViewById(R.id.addVacations);
        addVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new addVacation(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        seeVacation = view.findViewById(R.id.seeVacations);
        seeVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new showVacation(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        addPackage = view.findViewById(R.id.addPackages);
        addPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new addVacationPackage(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        seePackage = view.findViewById(R.id.seePackages);
        seePackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.local_options, new showVacationPackage(), null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

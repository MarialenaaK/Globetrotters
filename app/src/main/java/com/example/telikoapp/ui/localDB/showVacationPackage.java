package com.example.telikoapp.ui.localDB;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telikoapp.R;

import java.util.List;

public class showVacationPackage extends Fragment {

    private RecyclerView recyclerView;
    public List<VacationPackage> vacationPackages;

    private VacationPackageAdapter adapter;

    public showVacationPackage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_vacation_package, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerThing3);

        vacationPackages = MainActivity.myDatabase.myDao().getVacationPackages();

        adapter = new VacationPackageAdapter(getContext(), vacationPackages);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        //Here the listener becomes active and calls the actual delete function from TravelAgencyAdapter
        adapter.setOnItemClickListenerDelete(new VacationPackageAdapter.OnItemClickListenerDelete(){
            @Override
            public void onClickDelete(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Θέλετε σίγουρα να προχωρήσετε;");
                builder.setMessage("Τα δεδομένα δεν μπορούν να ανακτηθούν μετά τη διαγραφή!");
                builder.setPositiveButton("Διαγραφή", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            MainActivity.myDatabase.myDao().deleteVacationPackage(vacationPackages.get(position));
                            vacationPackages.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(0, vacationPackages.size());
                            Toast.makeText(showVacationPackage.this.getContext(), "Η διαγραφή ολοκληρώθηκε.", Toast.LENGTH_LONG).show();
                        }catch(SQLiteConstraintException ex){
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                            builder2.setTitle("Θέλετε σίγουρα να προχωρήσετε;");
                            builder2.setMessage("Τα δεδομένα αναφέρονται στο πακέτο εκδρομών, βεβαιωθείτε ότι διαγράψατε πρώτα το πακέτο!");
                            builder2.setPositiveButton("Εντάξει", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(showVacationPackage.this.getContext(), "Εντάξει", Toast.LENGTH_LONG).show();
                                }
                            });
                            builder2.show();
                        }
                    }
                });

                builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(showVacationPackage.this.getContext(), "Ακυρώθηκε", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }
}

package com.example.telikoapp.ui.cloudDB;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.telikoapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class fragment_firebase_see extends Fragment{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef;

    private CustomerAdapter adapterCostumer;

    private RecyclerView recyclerView;

    private Button deleteAllButton;

    public fragment_firebase_see() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_firebase_see, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deleteAllButton = view.findViewById(R.id.delete_all_button);

        recyclerView = view.findViewById(R.id.recycler_viou);

        //Bundle receives data from fragment_firebase, inside we have which trip the user chose to see
        Bundle bundle = getArguments();
        String destination = bundle.getString("Destination");

        notebookRef = db.collection(destination);

        Query query = notebookRef.orderBy("name",Query.Direction.DESCENDING);

        //System.out.println("should be an ID : "+notebookRef.getId());
        //THIS PRINTS OUT Trip001
        FirestoreRecyclerOptions<Customer> options = new FirestoreRecyclerOptions.Builder<Customer>()
                .setQuery(query, Customer.class)
                .build();

        adapterCostumer = new CustomerAdapter((options));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterCostumer);

        //Here the listener becomes active and calls the actual delete function from CostumerAdapter
        adapterCostumer.setOnItemClickListenerDelete(new CustomerAdapter.OnItemClickListenerDelete() {
            @Override
            public void onKlickDelete(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment_firebase_see.this.getContext());
                builder.setTitle("Είστε σίγουροι;");
                builder.setMessage("Τα δεδομένα δεν μπορούν να ανακτηθούν μετά τη διαγραφή!");
                builder.setPositiveButton("Διαγραφή", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterCostumer.deleteItem(position);
                        Toast.makeText(fragment_firebase_see.this.getContext(), "Η διαγραφή ολοκληρώθηκε.", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Ακύρωση.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(fragment_firebase_see.this.getContext(), "Ακυρώθηκε.", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(fragment_firebase_see.this.getContext());
                builder.setTitle("Είστε σίγουροι;");
                builder.setMessage("Αυτή η ενέργεια θα διαγράψει όλους τους αποθηκευμένους πελάτες! ");
                builder.setPositiveButton("Διαγραφή", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int itemCount = recyclerView.getAdapter().getItemCount();
                        for (int j=0; j<itemCount; j++) {
                            adapterCostumer.deleteItem(j);
                        }
                        Toast.makeText(fragment_firebase_see.this.getContext(), "Η λίστα των πελατών καθαρίστηκε.", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Ακύρωση.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(fragment_firebase_see.this.getContext(), "Ακυρώθηκε.", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        adapterCostumer.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapterCostumer.stopListening();
    }


}

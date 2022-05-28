package com.example.telikoapp.ui.home;




import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.telikoapp.ui.home.QueryFragment;
import com.example.telikoapp.R;
import com.example.telikoapp.databinding.FragmentHomeBinding;
import com.example.telikoapp.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Button btnQueries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnQueries = view.findViewById(R.id.buttonQueries);
        btnQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryFragment query = new QueryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main, query);
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

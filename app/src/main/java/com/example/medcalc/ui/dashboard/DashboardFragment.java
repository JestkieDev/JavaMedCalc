package com.example.medcalc.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcalc.R;
import com.example.medcalc.databinding.FragmentDashboardBinding;
import com.example.medcalc.db.MainDb;
import com.example.medcalc.db.Pacient;
import com.example.medcalc.db.PacientDao;
import com.example.medcalc.ui.AddFragment;
import com.example.medcalc.ui.InfFragment;
import com.example.medcalc.ui.PacientsAdapter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Pacient> pacients;
    PacientsAdapter pacientsAdapter;
    MainDb mainDb;
    PacientDao pacientDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button addBTN = view.findViewById(R.id.addBTN);
        recyclerView = view.findViewById(R.id.pacientsRV);
        mainDb = MainDb.getINSTANCE(getContext());
        pacients = new ArrayList<Pacient>();
        pacientDao = mainDb.pacientDao();
        if(pacientDao.getAll() != null) {
            pacients.addAll(pacientDao.getAll());

        }
        pacientsAdapter = new PacientsAdapter(getActivity(),pacients);
        pacientsAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(pacientsAdapter);



        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_addpacients);
            }
        });
        return view;
    }
}
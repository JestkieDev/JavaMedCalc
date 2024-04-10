package com.example.medcalc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.medcalc.R;
import com.example.medcalc.db.MainDb;
import com.example.medcalc.db.Pacient;
import com.example.medcalc.db.PacientDao;
import com.example.medcalc.ui.dashboard.DashboardFragment;

public class AddFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        Button backBTN = view.findViewById(R.id.backaddBTN);
        Button addBTN = view.findViewById(R.id.addBTN);
        EditText nameET = view.findViewById(R.id.nameET);
        EditText ageET = view.findViewById(R.id.ageET);
        MainDb mainDb = MainDb.getINSTANCE(getContext());
        PacientDao pacientDao = mainDb.pacientDao();

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pacient pacient = new Pacient(nameET.getText().toString(), Integer.parseInt(ageET.getText().toString()));
                pacient.cap = 0;
                pacient.def = 0;
                pacient.nf = 0;
                pacient.imt = 0;
                pacientDao.insert(pacient);
            }
        });





        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardFragment dashboardFragment = new DashboardFragment();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_dashboard);
            }
        });
        return view;
    }
}

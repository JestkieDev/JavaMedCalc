package com.example.medcalc.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.medcalc.R;
import com.example.medcalc.databinding.FragmentHomeBinding;
import com.example.medcalc.ui.CapFragment;
import com.example.medcalc.ui.DefFragment;
import com.example.medcalc.ui.ImtFragment;
import com.example.medcalc.ui.InfFragment;

public class HomeFragment extends Fragment {
    Button imtBTN;
    Button infBTN;
    Button defBTN;
    Button capBTN;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imtBTN = view.findViewById(R.id.imtBTN);
        infBTN = view.findViewById(R.id.infBTN);
        defBTN = view.findViewById(R.id.calBTN);
        capBTN = view.findViewById(R.id.capBTN);

        imtBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_imt);

            }
        });

        infBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_inf);

            }
        });

        defBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_def);
            }
        });

        capBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_cap);
            }
        });

        return view;


    }



}
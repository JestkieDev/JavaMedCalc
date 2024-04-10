package com.example.medcalc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.medcalc.R;
import com.example.medcalc.db.MainDb;
import com.example.medcalc.db.Pacient;
import com.example.medcalc.db.PacientDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PacientFragment extends Fragment {

    MainDb mainDb;
    PacientDao pacientDao;
    List<Pacient> pacients;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pacient, container, false);
        TextView pnameTv = view.findViewById(R.id.pnameTV);
        TextView pageTv = view.findViewById(R.id.pageTV);
        TextView pimtTv = view.findViewById(R.id.pimtTV);
        TextView pinfTv = view.findViewById(R.id.pinfTV);
        TextView pcapTv = view.findViewById(R.id.pcapTV);
        TextView pdefTv = view.findViewById(R.id.pdefTV);
        Button backaddBTN = view.findViewById(R.id.backpacBTN);

        DecimalFormat df = new DecimalFormat("###.##");
        String nme;
        mainDb = MainDb.getINSTANCE(getContext());
        pacientDao = mainDb.pacientDao();
        pacients = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            nme = bundle.getString("nme", "egor");
            pacients = pacientDao.getWithName(nme);
        }
        pnameTv.setText("ФИО: " + pacients.get(0).name.toString());
        pageTv.setText("Возраст: " + String.valueOf(pacients.get(0).age) + " лет");
        int n = String.valueOf(pacients.get(0).age).length();
        if (n == 1) {
            switch (String.valueOf(pacients.get(0).age).charAt(n-1)) {
                case '1':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " год");
                    break;
                case '2': case '3': case '4':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " года");
                    break;
                case '5': case '6': case '7': case '8': case '9':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " лет");
                    break;
            }
        } else if (pacients.get(0).age < 21) {
            pageTv.setText(String.valueOf(pacients.get(0).age) + " лет");
        } else  {
            switch (String.valueOf(pacients.get(0).age).charAt(n-1)) {
                case '1':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " год");
                    break;
                case '2': case '3': case '4':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " года");
                    break;
                case '5': case '6': case '7': case '8': case '9': case '0':
                    pageTv.setText(String.valueOf(pacients.get(0).age) + " лет");
                    break;
            }
        }
        pimtTv.setText("ИМТ: " + df.format(pacients.get(0).imt));
        pinfTv.setText("Скорость инфузии препарата: " + df.format(pacients.get(0).nf) + " мл/ч");
        pcapTv.setText("Скорость внутривенного капельного введения препарата: " + df.format(pacients.get(0).cap) + " капель в мин");
        pdefTv.setText("Дефицит: " + df.format(pacients.get(0).def) + " ммоль(мл)");
        if (pacients.get(0).def > 0){
            pdefTv.setText("Дефицит: " + df.format(pacients.get(0).def) + " ммоль(мл)");
        } else if (pacients.get(0).def == 0) {
            pdefTv.setText("Дефицит: " + "Нормокалиемия");
        } else if (pacients.get(0).def < 0) {
            pdefTv.setText("Дефицит: " + "Гиперкалиемия");
        }

        backaddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_dashboard);
            }
        });



        return view;
    }
}

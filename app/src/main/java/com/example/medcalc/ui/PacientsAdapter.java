package com.example.medcalc.ui;

import android.content.Context;
import android.content.PeriodicSync;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcalc.R;
import com.example.medcalc.db.Pacient;

import java.util.ArrayList;

public class PacientsAdapter extends RecyclerView.Adapter<PacientsAdapter.ViewHolder> {



    FragmentActivity context;
    ArrayList<Pacient> pacients;

    public PacientsAdapter(FragmentActivity c, ArrayList<Pacient> pacients) {
        this.context = c;
        this.pacients = pacients;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pacient pacient = pacients.get(position);
        holder.pacientnameTV.setText(pacient.name);
        int n = String.valueOf(pacient.age).length();
        if (n == 1) {
            switch (String.valueOf(pacient.age).charAt(n-1)) {
                case '1':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " год");
                    break;
                case '2': case '3': case '4':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " года");
                    break;
                case '5': case '6': case '7': case '8': case '9':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " лет");
                    break;
            }
        } else if (pacient.age < 21) {
            holder.pacientageTV.setText(String.valueOf(pacient.age) + " лет");
        } else  {
            switch (String.valueOf(pacient.age).charAt(n-1)) {
                case '1':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " год");
                    break;
                case '2': case '3': case '4':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " года");
                    break;
                case '5': case '6': case '7': case '8': case '9': case '0':
                    holder.pacientageTV.setText(String.valueOf(pacient.age) + " лет");
                    break;
            }
        }
        String name = pacient.name;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                PacientFragment pacientFragment = new PacientFragment();
                bundle.putString("nme", name);
                pacientFragment.setArguments(bundle);
                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_pacient,bundle);


            }
        });
    }


    @Override
    public int getItemCount() {
        return pacients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pacientnameTV;
        TextView pacientageTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pacientnameTV = itemView.findViewById(R.id.pacientname);
            pacientageTV = itemView.findViewById(R.id.pacientage);

        }
    }


}

package com.example.jotno;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Fragment.AppointmentViewFragment;
import com.example.jotno.Models.AppointmentResponse;
import com.example.jotno.Models.Appointments;
import com.example.jotno.Models.Datum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentsRecyclerAdapter extends RecyclerView.Adapter<AppointmentViewHolder> {

    private List<Datum> appoList;
    private int st = 0;
    private Context context;

    public AppointmentsRecyclerAdapter(List<Datum> appoList) {
        this.appoList = appoList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item_layout,parent,false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        Datum appo = appoList.get(position);

        holder.appointmentNoTxt.setText(appo.getAppoinmentNo());

        if(appo.getStatus().equals("Confirm")){
            holder.appointmentStatusTxt.setText(appo.getStatus());
            holder.appointmentStatusTxt.setTextColor(context.getResources().getColor(R.color.green));
        }else  if(appo.getStatus().equals("Completed")){
            holder.appointmentStatusTxt.setText(appo.getStatus());
            holder.appointmentStatusTxt.setTextColor(context.getResources().getColor(R.color.dark_green));
        }else  if(appo.getStatus().equals("Pending")){
            holder.appointmentStatusTxt.setText(appo.getStatus());
            holder.appointmentStatusTxt.setTextColor(context.getResources().getColor(R.color.yello));
        }else{
            holder.appointmentStatusTxt.setText(appo.getStatus());
            holder.appointmentStatusTxt.setTextColor(context.getResources().getColor(R.color.red));
        }





        holder.appointmentDateTxt.setText("Appointment Date:\n "+appo.getAppoinmentDate());

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.secondLayout.setVisibility(View.GONE);
                st = 1;
            }else{
                holder.secondLayout.setVisibility(View.VISIBLE);
                st = 0;
            }


        });

        holder.viewBtn.setOnClickListener(v -> {

            AppointmentViewFragment fragment = new AppointmentViewFragment();
            FragmentManager fragmentManager = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundles = new Bundle();
            bundles.putInt("position",position);
            fragment.setArguments(bundles);

            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

    }

    @Override
    public int getItemCount() {
        return appoList.size();
    }
}

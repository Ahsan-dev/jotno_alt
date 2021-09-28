package com.example.jotno.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Fragment.PrescriptAppointFragment;
import com.example.jotno.Models.Prescriptions;
import com.example.jotno.Models.PrescriptionsDatum;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptionItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionRecyclerAdapter extends RecyclerView.Adapter<PrescriptionItemViewHolder> {

    private List<PrescriptionsDatum> prescriptionsList;
    private int st = 0;

    public PrescriptionRecyclerAdapter(List<PrescriptionsDatum> prescriptionsList) {

        this.prescriptionsList = prescriptionsList;
    }

    @NonNull
    @Override
    public PrescriptionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item_layout,parent,false);
        return new PrescriptionItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionItemViewHolder holder, int position) {

        PrescriptionsDatum prescriptions = prescriptionsList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.showLinear.setVisibility(View.GONE);
                st = 1;
            }else{
                holder.showLinear.setVisibility(View.VISIBLE);
                st = 0;
            }


        });

        holder.prescriptionNoTxt.setText(prescriptions.getPrescriptionNo());
        holder.appointmentNoTxt.setText("Appointment No:\n"+prescriptions.getAppoinmentNo());
        holder.dateTxt.setText(prescriptions.getCreatedAt());
        holder.noteTxt.setText("Advice:\n"+prescriptions.getAdvice());
        holder.doctorsNameTxt.setText("Doctor's Name:\n"+prescriptions.getDoctorName());


        holder.viewPresBtn.setOnClickListener(v -> {

            PrescriptAppointFragment fragment = new PrescriptAppointFragment();
            FragmentManager fragmentManager = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundles = new Bundle();
            bundles.putInt("prescription_id",prescriptions.getId());
            bundles.putString("iWant","prescriptions");
            fragment.setArguments(bundles);
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

    }

    @Override
    public int getItemCount() {

        return prescriptionsList.size();

    }
}

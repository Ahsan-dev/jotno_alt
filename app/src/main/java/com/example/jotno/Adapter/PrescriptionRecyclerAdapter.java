package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Models.Prescriptions;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptionItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionRecyclerAdapter extends RecyclerView.Adapter<PrescriptionItemViewHolder> {

    private List<Prescriptions> prescriptionsList;
    private int st = 0;

    public PrescriptionRecyclerAdapter(List<Prescriptions> prescriptionsList) {
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

        Prescriptions prescriptions = prescriptionsList.get(position);

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
        holder.appointmentNoTxt.setText("Appointment No:\n"+prescriptions.getAppointmentNo());
        holder.dateTxt.setText(prescriptions.getDate());
        holder.noteTxt.setText("Note:\n"+prescriptions.getNote());
        holder.doctorsNameTxt.setText("Doctor's Name:\n"+prescriptions.getDoctorName());


        holder.viewPresBtn.setOnClickListener(v -> {

            Toast.makeText(v.getContext(), "It will be viewed...", Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return prescriptionsList.size();
    }
}

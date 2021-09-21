package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Medicines;
import com.example.jotno.Models.Medicine;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptMedicinesRecyclerItemViewHolder;

import java.util.List;

public class PrescriptMedicinesItemRecyclerAdapter extends RecyclerView.Adapter<PrescriptMedicinesRecyclerItemViewHolder> {

    private List<Medicine> medicinesList;

    public PrescriptMedicinesItemRecyclerAdapter(List<Medicine> medicinesList) {
        this.medicinesList = medicinesList;
    }

    @NonNull
    @Override
    public PrescriptMedicinesRecyclerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptMedicinesRecyclerItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_medicines_recycler_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptMedicinesRecyclerItemViewHolder holder, int position) {

        Medicine medicines = medicinesList.get(position);

        holder.medicineNameTxt.setText(medicines.getName());
        if(medicines.getType().equals("OR")){

            holder.medicineThreeTimesStatusTxt.setText(medicines.getTiming());

        }else{

            holder.medicineNameTxt.setText(medicines.getName());
            holder.medicineThreeTimesStatusTxt.setText(medicines.getTiming());
            holder.medicineMealStatusSpanTxt.setText(medicines.getMeal()+", "+medicines.getSpan());


        }


    }

    @Override
    public int getItemCount() {
        return medicinesList.size();
    }
}

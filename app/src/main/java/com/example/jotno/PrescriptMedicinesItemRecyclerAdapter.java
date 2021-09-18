package com.example.jotno;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrescriptMedicinesItemRecyclerAdapter extends RecyclerView.Adapter<PrescriptMedicinesRecyclerItemViewHolder> {

    private List<Medicines> medicinesList;

    public PrescriptMedicinesItemRecyclerAdapter(List<Medicines> medicinesList) {
        this.medicinesList = medicinesList;
    }

    @NonNull
    @Override
    public PrescriptMedicinesRecyclerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptMedicinesRecyclerItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_medicines_recycler_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptMedicinesRecyclerItemViewHolder holder, int position) {

        Medicines medicines = medicinesList.get(position);

        holder.medicineNameTxt.setText(medicines.getMedicineName());
        if(medicines.getMedicineType().equals("OR")){

            holder.medicineThreeTimesStatusTxt.setText(medicines.getMedicineTime());

        }else{

            holder.medicineNameTxt.setText(medicines.getMedicineName());
            holder.medicineThreeTimesStatusTxt.setText(medicines.getMedicineTime());
            holder.medicineMealStatusSpanTxt.setText(medicines.getMedicineMeal()+", "+medicines.getMedicineSpan());


        }


    }

    @Override
    public int getItemCount() {
        return medicinesList.size();
    }
}

package com.example.jotno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotno.Room.Entity.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicineItemAdapter extends RecyclerView.Adapter<MedicineItemViewHolder> {

    private List<Medicine> medicineList;

    public MedicineItemAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @NotNull
    @Override
    public MedicineItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_item_layout,parent,false);
        return new MedicineItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MedicineItemViewHolder holder, int position) {

        Medicine medicine = medicineList.get(position);

        holder.medicineTxt.setText(medicine.getMedicine());
        holder.morningTxt.setText(medicine.getMorning());
        holder.noonTxt.setText(medicine.getNoon());
        holder.nightTxt.setText(medicine.getNight());

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }
}

package com.example.jotno;

import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicineItemViewHolder extends RecyclerView.ViewHolder {

    public TextView medicineTxt, morningTxt, noonTxt, nightTxt;

    public MedicineItemViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        medicineTxt = itemView.findViewById(R.id.medicine_item_medicine_txt_id);
        morningTxt = itemView.findViewById(R.id.medicine_item_morning_txt_id);
        noonTxt = itemView.findViewById(R.id.medicine_item_noon_txt_id);
        nightTxt = itemView.findViewById(R.id.medicine_item_night_txt_id);



    }
}

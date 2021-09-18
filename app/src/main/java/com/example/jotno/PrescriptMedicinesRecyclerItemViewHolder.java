package com.example.jotno;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptMedicinesRecyclerItemViewHolder extends RecyclerView.ViewHolder {

    public TextView medicineNameTxt, medicineThreeTimesStatusTxt, medicineMealStatusSpanTxt;

    public PrescriptMedicinesRecyclerItemViewHolder(@NonNull View itemView) {
        super(itemView);

        medicineNameTxt = itemView.findViewById(R.id.prescript_medicine_recycler_item_medicine_name_txt);
        medicineThreeTimesStatusTxt = itemView.findViewById(R.id.prescript_medicine_recycler_item_medicine_three_times_txt);
        medicineMealStatusSpanTxt = itemView.findViewById(R.id.prescript_medicine_recycler_item_medicine_meal_status_span_txt);

    }
}

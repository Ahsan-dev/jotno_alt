package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class BillFieldItemViewAHolder extends RecyclerView.ViewHolder {

    public TextView testNameTxt, billAmountTxt;
    public RelativeLayout relativeTextScreen;

    public BillFieldItemViewAHolder(@NonNull View itemView) {
        super(itemView);

        testNameTxt = itemView.findViewById(R.id.bill_field_test_name_id);
        billAmountTxt = itemView.findViewById(R.id.bill_field_bill_amount_id);
        relativeTextScreen = itemView.findViewById(R.id.bill_field_text_row_relative);

    }
}

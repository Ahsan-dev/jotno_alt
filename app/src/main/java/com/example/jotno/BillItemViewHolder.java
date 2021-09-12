package com.example.jotno;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BillItemViewHolder extends RecyclerView.ViewHolder {

    public TextView prescriptionNoTxt, billTxt, billDateTxt;
    public LinearLayout billLayout;
    public Button showBillBtn;

    public BillItemViewHolder(@NonNull View itemView) {
        super(itemView);

        prescriptionNoTxt = itemView.findViewById(R.id.bill_item_prescription_no_id);
        billTxt = itemView.findViewById(R.id.bill_item_bill_amount_id);
        billDateTxt = itemView.findViewById(R.id.bill_item_date_txt_id);
        billLayout = itemView.findViewById(R.id.bill_item_second_row_id);
        showBillBtn = itemView.findViewById(R.id.bill_item_view_btn_id);

    }
}

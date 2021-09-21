package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class TestItemViewHolder extends RecyclerView.ViewHolder {

    public TextView prescriptionNo, testItemName, testDate;
    public LinearLayout hideLayout;
    public Button showReportBtn;

    public TestItemViewHolder(@NonNull View itemView) {
        super(itemView);

        prescriptionNo = itemView.findViewById(R.id.test_item_prescription_no_id);
        testItemName = itemView.findViewById(R.id.test_item_test_name_id);
        testDate = itemView.findViewById(R.id.test_item_date_txt_id);
        hideLayout = itemView.findViewById(R.id.test_item_second_row_id);
        showReportBtn = itemView.findViewById(R.id.test_item_view_btn_id);

    }
}

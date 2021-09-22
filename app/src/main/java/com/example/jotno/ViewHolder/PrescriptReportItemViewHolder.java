package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class PrescriptReportItemViewHolder extends RecyclerView.ViewHolder {

    public TextView tesNameTxt;
    public ImageView reportImage;
    public LinearLayout toggleLayout;
    public Button editReportBtn, deleteReportBtn;

    public PrescriptReportItemViewHolder(@NonNull View itemView) {
        super(itemView);

        tesNameTxt = itemView.findViewById(R.id.prescript_report_item_test_name_id);
        reportImage = itemView.findViewById(R.id.prescript_report_item_report_img_id);
        toggleLayout = itemView.findViewById(R.id.prescript_report_item_second_row_id);
        editReportBtn = itemView.findViewById(R.id.prescript_report_item_edit_btn_id);
        deleteReportBtn = itemView.findViewById(R.id.prescript_report_item_delete_btn_id);


    }


}

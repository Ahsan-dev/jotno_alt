package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class PrescriptionComplainsFindingsViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTypeTxt;
    public ListView itemDetailList;

    public PrescriptionComplainsFindingsViewHolder(@NonNull View itemView) {
        super(itemView);

        itemTypeTxt = itemView.findViewById(R.id.prescript_complain_findings_item_type_text);
        itemDetailList = itemView.findViewById(R.id.prescript_complain_findings_item_type_details_list);

    }
}

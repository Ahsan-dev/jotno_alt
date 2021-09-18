package com.example.jotno;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptTestsItemViewHolder extends RecyclerView.ViewHolder {

    public TextView prescriptTestNameTxt, prescriptTestValueTxt;

    public PrescriptTestsItemViewHolder(@NonNull View itemView) {
        super(itemView);

        prescriptTestNameTxt = itemView.findViewById(R.id.prescript_test_recycler_item_test_name_txt);
        prescriptTestNameTxt = itemView.findViewById(R.id.prescript_test_recycler_item_test_name_value_txt);

    }
}

package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class PrescriptTestsItemViewHolder extends RecyclerView.ViewHolder {

    public TextView prescriptTestNameTxt, prescriptTestValueTxt;

    public PrescriptTestsItemViewHolder(@NonNull View itemView) {
        super(itemView);

        prescriptTestNameTxt = itemView.findViewById(R.id.prescript_test_recycler_item_test_name_txt);
        prescriptTestValueTxt = itemView.findViewById(R.id.prescript_test_recycler_item_test_name_value_txt);

    }
}

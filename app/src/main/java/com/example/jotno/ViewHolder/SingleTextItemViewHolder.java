package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class SingleTextItemViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTxt;

    public SingleTextItemViewHolder(@NonNull View itemView) {
        super(itemView);

        itemTxt = itemView.findViewById(R.id.single_text_item_text_id);

    }
}

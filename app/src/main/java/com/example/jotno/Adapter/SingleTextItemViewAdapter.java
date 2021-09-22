package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;
import com.example.jotno.ViewHolder.SingleTextItemViewHolder;

import java.util.List;

public class SingleTextItemViewAdapter extends RecyclerView.Adapter<SingleTextItemViewHolder> {

    private List<String> itemList;

    public SingleTextItemViewAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SingleTextItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingleTextItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_text_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleTextItemViewHolder holder, int position) {

        holder.itemTxt.setText(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

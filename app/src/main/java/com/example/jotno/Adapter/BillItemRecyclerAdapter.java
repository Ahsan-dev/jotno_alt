package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Models.Bills;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.BillItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BillItemRecyclerAdapter extends RecyclerView.Adapter<BillItemViewHolder> {



    private List<Bills> billsList;
    private int st = 0;


    public BillItemRecyclerAdapter(List<Bills> billsList) {
        this.billsList = billsList;
    }

    @NonNull

    @Override
    public BillItemViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_layout,parent,false);
        return new BillItemViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull BillItemViewHolder holder, int position) {

        Bills bills = billsList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.billLayout.setVisibility(View.GONE);
                st = 1;
            }else{
                holder.billLayout.setVisibility(View.VISIBLE);
                st = 0;
            }


        });

        holder.prescriptionNoTxt.setText(bills.getPrescriptionNo());
        holder.billTxt.setText(bills.getBill());
        holder.billDateTxt.setText("Bill Date:\n"+bills.getBillDate());



        holder.showBillBtn.setOnClickListener(v -> {

            Toast.makeText(v.getContext(), "It will be viewed...", Toast.LENGTH_SHORT).show();

        });

    }



    @Override
    public int getItemCount() {
        return billsList.size();
    }
}

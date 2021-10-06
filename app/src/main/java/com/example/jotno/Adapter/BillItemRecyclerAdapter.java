package com.example.jotno.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Fragment.PrescriptAppointFragment;
import com.example.jotno.Models.Bills;
import com.example.jotno.Models.PrescriptionsDatum;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.BillItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class BillItemRecyclerAdapter extends RecyclerView.Adapter<BillItemViewHolder> {



    private List<PrescriptionsDatum> billsList;
    private int st = 0;
    private Context context;


    public BillItemRecyclerAdapter(List<PrescriptionsDatum> billsList) {
        this.billsList = billsList;
    }

    @NonNull

    @Override
    public BillItemViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_layout,parent,false);
        return new BillItemViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull BillItemViewHolder holder, int position) {

        PrescriptionsDatum bills = billsList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.billLayout.setVisibility(View.GONE);
                holder.billExpandImage.setImageDrawable(context.getResources().getDrawable(R.drawable.left_triangle_icon));

                st = 1;
            }else{
                holder.billLayout.setVisibility(View.VISIBLE);
                holder.billExpandImage.setImageDrawable(context.getResources().getDrawable(R.drawable.down_triange_icon));

                st = 0;
            }


        });

        holder.prescriptionNoTxt.setText(bills.getPrescriptionNo());
        holder.billTxt.setText(bills.getTotal());
        holder.billDateTxt.setText("Bill Date:\n"+bills.getCreatedAt());



        holder.showBillBtn.setOnClickListener(v -> {

            PrescriptAppointFragment fragment = new PrescriptAppointFragment();
            FragmentManager fragmentManager = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundles = new Bundle();
            bundles.putInt("prescription_id",bills.getId());
            bundles.putString("iWant","bills");
            fragment.setArguments(bundles);
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

    }



    @Override
    public int getItemCount() {
        return billsList.size();
    }
}

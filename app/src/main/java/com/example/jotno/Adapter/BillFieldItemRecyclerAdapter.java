package com.example.jotno.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Models.TestType;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.BillFieldItemViewAHolder;

import java.util.List;

public class BillFieldItemRecyclerAdapter extends RecyclerView.Adapter<BillFieldItemViewAHolder> {

    private List<TestType> testBillList;
    private Context context;
    private int s = 0;

    public BillFieldItemRecyclerAdapter(List<TestType> testBillList) {
        this.testBillList = testBillList;
    }

    @NonNull
    @Override
    public BillFieldItemViewAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BillFieldItemViewAHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_field_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillFieldItemViewAHolder holder, int position) {

        TestType tests = testBillList.get(position);

        holder.testNameTxt.setText(tests.getName());
        holder.billAmountTxt.setText(tests.getPrice());


        if(s==0){

            holder.relativeTextScreen.setBackgroundResource(android.R.color.white);
            s = 1;

        }else{

            holder.relativeTextScreen.setBackgroundResource(R.color.light_gray);
            s = 0;

        }

    }

    @Override
    public int getItemCount() {
        return testBillList.size();
    }
}

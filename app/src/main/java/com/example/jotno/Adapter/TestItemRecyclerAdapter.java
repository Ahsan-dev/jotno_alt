package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Models.Tests;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.TestItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestItemRecyclerAdapter extends RecyclerView.Adapter<TestItemViewHolder> {



    private List<Tests> testsList;
    private int st = 0;


    public TestItemRecyclerAdapter(List<Tests> testsList) {
        this.testsList = testsList;
    }

    @NonNull
    @Override
    public TestItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout,parent,false);

        return new TestItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestItemViewHolder holder, int position) {

        Tests tests = testsList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.hideLayout.setVisibility(View.GONE);
                st = 1;
            }else{
                holder.hideLayout.setVisibility(View.VISIBLE);
                st = 0;
            }


        });

        holder.prescriptionNo.setText(tests.getPrescriptionNo());
        holder.testItemName.setText(tests.getTestName());
        holder.testDate.setText("Test Date:\n"+tests.getTestDate());



        holder.showReportBtn.setOnClickListener(v -> {

            Toast.makeText(v.getContext(), "It will be viewed...", Toast.LENGTH_SHORT).show();

        });


    }

    @Override
    public int getItemCount() {
        return testsList.size();
    }
}

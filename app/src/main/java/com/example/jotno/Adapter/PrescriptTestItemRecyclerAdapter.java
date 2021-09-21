package com.example.jotno.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Models.InitialTest;
import com.example.jotno.Models.TestsOnPrescript;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptTestsItemViewHolder;

import java.util.List;

public class PrescriptTestItemRecyclerAdapter extends RecyclerView.Adapter<PrescriptTestsItemViewHolder> {

    private List<InitialTest> testsList;

    public PrescriptTestItemRecyclerAdapter(List<InitialTest> testsList) {
        this.testsList = testsList;
    }

    @NonNull
    @Override
    public PrescriptTestsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptTestsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescript_tests_recycler_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptTestsItemViewHolder holder, int position) {

        InitialTest tests = testsList.get(position);

        holder.prescriptTestNameTxt.setText(tests.getName());
        holder.prescriptTestValueTxt.setText(tests.getResult());

    }

    @Override
    public int getItemCount() {
        return testsList.size();
    }
}

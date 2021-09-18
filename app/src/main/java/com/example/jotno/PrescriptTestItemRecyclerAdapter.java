package com.example.jotno;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Models.TestsOnPrescript;

import java.util.List;

public class PrescriptTestItemRecyclerAdapter extends RecyclerView.Adapter<PrescriptTestsItemViewHolder> {

    private List<TestsOnPrescript> testsList;

    public PrescriptTestItemRecyclerAdapter(List<TestsOnPrescript> testsList) {
        this.testsList = testsList;
    }

    @NonNull
    @Override
    public PrescriptTestsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptTestsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescript_tests_recycler_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptTestsItemViewHolder holder, int position) {

        TestsOnPrescript tests = testsList.get(position);

        holder.prescriptTestNameTxt.setText(tests.getTestName());
        holder.prescriptTestValueTxt.setText(tests.getTestValue());

    }

    @Override
    public int getItemCount() {
        return testsList.size();
    }
}

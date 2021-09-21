package com.example.jotno.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Models.MainTest;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptionComplainsFindingsViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionComplainsFindingsAdapter extends RecyclerView.Adapter<PrescriptionComplainsFindingsViewHolder> {

    private List<MainTest> mainTestList;
    private Context context;
    private ArrayAdapter<String> mainTestAdapter;
    private List<String> leftList;

    private String[] listString ;

    public PrescriptionComplainsFindingsAdapter(List<MainTest> mainTestList) {
        this.mainTestList = mainTestList;
    }

    @NonNull
    @Override
    public PrescriptionComplainsFindingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new PrescriptionComplainsFindingsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescriction_complains_findings_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionComplainsFindingsViewHolder holder, int position) {

        Toast.makeText(context, "Adapter", Toast.LENGTH_SHORT).show();

        MainTest test = mainTestList.get(position);

        holder.itemTypeTxt.setText(test.getTestType());

        int size = test.getTestTypeList().size();
        leftList = new ArrayList<>();

        for(int i=0;i<size;i++){

            leftList.add(test.getTestTypeList().get(i).getName());

        }

        listString = new String[leftList.size()];
        listString = leftList.toArray(listString);
        mainTestAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,android.R.id.text1,listString);
        holder.itemDetailList.setAdapter(mainTestAdapter);

    }

    @Override
    public int getItemCount() {
        return mainTestList.size();
    }
}

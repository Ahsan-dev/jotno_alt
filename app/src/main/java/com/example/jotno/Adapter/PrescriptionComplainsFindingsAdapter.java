package com.example.jotno.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private SingleTextItemViewAdapter singleTextItemViewAdapter;
    private StringBuffer buffer;
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


        MainTest test = mainTestList.get(position);

        holder.itemTypeTxt.setText(test.getTestType());

        int size = test.getTestTypeList().size();
        leftList = new ArrayList<>();
        buffer = new StringBuffer();

        for(int i=0;i<size-1;i++){
            buffer.append("->");
            buffer.append(test.getTestTypeList().get(i).getName());
            buffer.append("\n");

        }
        buffer.append("->");
        buffer.append(test.getTestTypeList().get(size-1).getName());

//        singleTextItemViewAdapter = new SingleTextItemViewAdapter(leftList);
//        holder.itemDetailList.setHasFixedSize(true);
//        holder.itemDetailList.setLayoutManager(new LinearLayoutManager(context));
//        holder.itemDetailList.setAdapter(singleTextItemViewAdapter);
//        singleTextItemViewAdapter.notifyDataSetChanged();

        holder.itemTypeListTxt.setText(buffer.toString());


    }

    @Override
    public int getItemCount() {
        return mainTestList.size();
    }
}

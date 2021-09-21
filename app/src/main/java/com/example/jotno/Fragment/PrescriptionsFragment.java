package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotno.Adapter.PrescriptionRecyclerAdapter;
import com.example.jotno.Models.Prescriptions;
import com.example.jotno.R;

import java.util.ArrayList;
import java.util.List;


public class PrescriptionsFragment extends Fragment {

    private View view;
    private RecyclerView prescriptionRecycler;
    private List<Prescriptions> prescriptionsList;
    private PrescriptionRecyclerAdapter prescriptionRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_prescriptions, container, false);

        prescriptionsList = new ArrayList<>();

        prescriptionsList.add(new Prescriptions("P-123698","01/02/2021","A-256987","md name","hfbvjhfkbvj ghwefjvjh f jwefvf jwefwvjhaef"));
        prescriptionsList.add(new Prescriptions("P-123598","08/02/2021","A-256987","md name","hfbvjhfkbvj ghwefjvjh f jwefvf jwefwvjhaef"));
        prescriptionsList.add(new Prescriptions("P-127698","01/03/2021","A-256987","md name","hfbvjhfkbvj ghwefjvjh f jwefvf jwefwvjhaef"));
        prescriptionsList.add(new Prescriptions("P-133698","09/02/2021","A-256987","md name","hfbvjhfkbvj ghwefjvjh f jwefvf jwefwvjhaef"));
        prescriptionsList.add(new Prescriptions("P-123690","01/02/2021","A-256987","md name","hfbvjhfkbvj ghwefjvjh f jwefvf jwefwvjhaef"));


        prescriptionRecycler = view.findViewById(R.id.prescriptions_fragment_prescriptions_recycler_id);
        prescriptionRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        prescriptionRecyclerAdapter = new PrescriptionRecyclerAdapter(prescriptionsList);
        prescriptionRecycler.hasFixedSize();
        prescriptionRecyclerAdapter.notifyDataSetChanged();
        prescriptionRecycler.setAdapter(prescriptionRecyclerAdapter);

        return view;
    }
}
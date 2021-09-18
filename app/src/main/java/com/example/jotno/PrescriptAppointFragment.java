package com.example.jotno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotno.Models.TestsOnPrescript;

import java.util.ArrayList;
import java.util.List;

public class PrescriptAppointFragment extends Fragment {

    private View view;
    private RecyclerView testsRecycler, medicinesRecycler;
    private List<TestsOnPrescript> testsList;
    private List<Medicines> medicinesList;
    private PrescriptTestItemRecyclerAdapter prescriptTestsAdapter;
    private PrescriptMedicinesItemRecyclerAdapter prescriptMedicinesItemRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_prescript_appoint, container, false);

        testsRecycler = view.findViewById(R.id.prescript_appointment_tests_recycler);
        medicinesRecycler = view.findViewById(R.id.prescript_appointment_medicines_recycler);
        testsList = new ArrayList<>();
        medicinesList = new ArrayList<>();
        prescriptTestsAdapter = new PrescriptTestItemRecyclerAdapter(testsList);
        prescriptMedicinesItemRecyclerAdapter = new PrescriptMedicinesItemRecyclerAdapter(medicinesList);
        testsRecycler.hasFixedSize();
        medicinesRecycler.hasFixedSize();
        testsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        medicinesRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        testsRecycler.setAdapter(prescriptTestsAdapter);
        medicinesRecycler.setAdapter(prescriptMedicinesItemRecyclerAdapter);
        prescriptTestsAdapter.notifyDataSetChanged();
        prescriptMedicinesItemRecyclerAdapter.notifyDataSetChanged();

        return view;
    }
}
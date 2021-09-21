package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotno.Adapter.TestItemRecyclerAdapter;
import com.example.jotno.Models.Tests;
import com.example.jotno.R;

import java.util.ArrayList;
import java.util.List;


public class TestsFragment extends Fragment {

    private View view;
    private RecyclerView testsRecycler;
    private List<Tests> testList;
    private TestItemRecyclerAdapter testItemRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tests, container, false);

        testList = new ArrayList<>();

        testList.add(new Tests("P-123698","Urine Test","01/02/2021"));
        testList.add(new Tests("P-123598","Blood Test","08/02/2021"));
        testList.add(new Tests("P-127698","Urine Test","01/03/2021"));
        testList.add(new Tests("P-133698","Blood Test","09/02/2021"));
        testList.add(new Tests("P-123690","Urine Test","01/02/2021"));


        testsRecycler = view.findViewById(R.id.tests_fragment_tests_recycler_id);
        testsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        testItemRecyclerAdapter = new TestItemRecyclerAdapter(testList);
        testsRecycler.hasFixedSize();
        testItemRecyclerAdapter.notifyDataSetChanged();
        testsRecycler.setAdapter(testItemRecyclerAdapter);

        return view;


    }
}
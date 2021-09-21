package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotno.Adapter.BillItemRecyclerAdapter;
import com.example.jotno.Models.Bills;
import com.example.jotno.R;

import java.util.ArrayList;
import java.util.List;


public class BillsFragment extends Fragment {

    private View view;
    private RecyclerView billsRecycler;
    private List<Bills> billList;
    private BillItemRecyclerAdapter billItemRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bills, container, false);

        billList = new ArrayList<>();

        billList.add(new Bills("P-123698","1258","01/02/2021"));
        billList.add(new Bills("P-123598","500","08/02/2021"));
        billList.add(new Bills("P-123698","1258","01/02/2021"));
        billList.add(new Bills("P-123598","500","08/02/2021"));
        billList.add(new Bills("P-123698","1258","01/02/2021"));
        billList.add(new Bills("P-123598","500","08/02/2021"));


        billsRecycler = view.findViewById(R.id.bill_fragment_bill_recycler_id);
        billsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        billItemRecyclerAdapter = new BillItemRecyclerAdapter(billList);
        billsRecycler.hasFixedSize();
        billItemRecyclerAdapter.notifyDataSetChanged();
        billsRecycler.setAdapter(billItemRecyclerAdapter);

        return view;
    }
}
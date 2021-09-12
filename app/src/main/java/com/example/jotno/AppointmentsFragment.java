package com.example.jotno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jotno.Models.Appointments;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class AppointmentsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button todayBtn, allBtn;
    private ExtendedFloatingActionButton getAppointmentBtn;
    private RecyclerView appointmentRecycler;
    private List<Appointments> appointmentList;
    private AppointmentsRecyclerAdapter appoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointments, container, false);

        todayBtn = view.findViewById(R.id.appointments_today_option_btn_id);
        allBtn = view.findViewById(R.id.appointments_all_option_btn_id);
        getAppointmentBtn = view.findViewById(R.id.appointments_get_appointment_btn_id);
        appointmentRecycler = view.findViewById(R.id.appointments_fragment_appointments_recycler_id);

        todayBtn.setBackgroundColor(getResources().getColor(R.color.red));
        todayBtn.setTextColor(getResources().getColor(android.R.color.white));
        allBtn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        allBtn.setTextColor(getResources().getColor(R.color.red));

        appointmentList = new ArrayList<>();
        appointmentList.clear();
        appointmentList.add(new Appointments("E-12356","completed","12/01/2021"));
        appointmentList.add(new Appointments("A-12356","pending","12/01/2021"));
        appointmentList.add(new Appointments("D-12356","confirm","12/01/2021"));
        appointmentList.add(new Appointments("G-12356","pending","12/01/2021"));
        appointmentList.add(new Appointments("K-12356","completed","12/01/2021"));
        appointmentList.add(new Appointments("L-12356","cancelled","12/01/2021"));

        appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
        appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        appointmentRecycler.hasFixedSize();
        appointmentRecycler.setAdapter(appoAdapter);
        appoAdapter.notifyDataSetChanged();

        todayBtn.setOnClickListener(this);
        allBtn.setOnClickListener(this);
        getAppointmentBtn.setOnClickListener(this);



        return view;

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.appointments_today_option_btn_id){

            todayBtn.setBackgroundColor(getResources().getColor(R.color.red));
            todayBtn.setTextColor(getResources().getColor(android.R.color.white));
            allBtn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            allBtn.setTextColor(getResources().getColor(R.color.red));
            appointmentList.clear();
            appointmentList.add(new Appointments("E-12356","completed","12/01/2021"));
            appointmentList.add(new Appointments("A-12356","pending","12/01/2021"));
            appointmentList.add(new Appointments("D-12356","confirm","12/01/2021"));
            appointmentList.add(new Appointments("G-12356","pending","12/01/2021"));
            appointmentList.add(new Appointments("K-12356","completed","12/01/2021"));
            appointmentList.add(new Appointments("L-12356","cancelled","12/01/2021"));

            appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
            appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
            appointmentRecycler.hasFixedSize();
            appointmentRecycler.setAdapter(appoAdapter);
            appoAdapter.notifyDataSetChanged();




        }

        if(v.getId()==R.id.appointments_all_option_btn_id){

            todayBtn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            todayBtn.setTextColor(getResources().getColor(R.color.red));
            allBtn.setBackgroundColor(getResources().getColor(R.color.red));
            allBtn.setTextColor(getResources().getColor(android.R.color.white));

            appointmentList.clear();
            appointmentList.add(new Appointments("E-12356","completed","12/01/2021"));
            appointmentList.add(new Appointments("A-12356","pending","12/01/2021"));
            appointmentList.add(new Appointments("D-12356","confirm","12/01/2021"));
            appointmentList.add(new Appointments("G-12356","pending","12/01/2021"));
            appointmentList.add(new Appointments("K-12356","completed","12/01/2021"));
            appointmentList.add(new Appointments("L-12356","cancelled","12/01/2021"));

            appointmentList.add(new Appointments("E-12356","completed","13/01/2021"));
            appointmentList.add(new Appointments("A-12356","pending","15/01/2021"));
            appointmentList.add(new Appointments("D-12356","confirm","10/01/2021"));
            appointmentList.add(new Appointments("G-12356","pending","10/01/2021"));
            appointmentList.add(new Appointments("K-12356","completed","22/01/2021"));
            appointmentList.add(new Appointments("L-12356","cancelled","12/01/2021"));

            appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
            appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
            appointmentRecycler.hasFixedSize();
            appointmentRecycler.setAdapter(appoAdapter);
            appoAdapter.notifyDataSetChanged();

        }

        if(v.getId()==R.id.appointments_get_appointment_btn_id){

            Toast.makeText(v.getContext(), "You have got Appointment...", Toast.LENGTH_SHORT).show();

        }

    }
}
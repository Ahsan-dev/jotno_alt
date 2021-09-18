package com.example.jotno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppointmentDetailsFragment extends Fragment implements View.OnClickListener {

    private TextView summaryTxtBtn, doctorTxtBtn, testsTxtBtn;
    private LinearLayout summaryLinear, testsLinear;
    private RelativeLayout doctorRelative;
    private TextView appointDateTxt, appointTotalBillTxt, appointStatusTxt, appointPaymentStatusTxt;
    private ImageView doctorImage;
    private TextView doctorNameTxt, doctorDesignationTxt, doctorIdTxt, doctorMobileTxt, doctorEmailTxt, doctorHospitalTxt, doctorAddressTxt, doctorTimeTxt;
    private ListView testsListView;
    private View view;
    private List<String> testsList;
    private ArrayAdapter<String> testAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointment_details, container, false);

        summaryTxtBtn = view.findViewById(R.id.appointment_details_summary_txt_btn);
        doctorTxtBtn = view.findViewById(R.id.appointment_details_doctor_txt_btn);
        testsTxtBtn = view.findViewById(R.id.appointment_details_Tests_txt_btn);

        summaryLinear = view.findViewById(R.id.appointment_details_summary_linear);
        doctorRelative = view.findViewById(R.id.appointment_details_doctor_relative);
        testsLinear = view.findViewById(R.id.appointment_details_tests_linear);

        appointDateTxt = view.findViewById(R.id.appointment_details_appointment_date_txt);
        appointTotalBillTxt = view.findViewById(R.id.appointment_details_appointment_bill_txt);
        appointStatusTxt = view.findViewById(R.id.appointment_details_appointment_status_txt);
        appointPaymentStatusTxt = view.findViewById(R.id.appointment_details_appointment_payment_status_txt);

        doctorImage = view.findViewById(R.id.appointment_details_doctor_image);
        doctorNameTxt = view.findViewById(R.id.appointment_details_doctor_name);
        doctorDesignationTxt = view.findViewById(R.id.appointment_details_doctor_designation);
        doctorIdTxt = view.findViewById(R.id.appointment_details_doctor_id);
        doctorEmailTxt = view.findViewById(R.id.appointment_details_doctor_email);
        doctorMobileTxt = view.findViewById(R.id.appointment_details_doctor_mobile);
        doctorHospitalTxt = view.findViewById(R.id.appointment_details_doctor_hospital);
        doctorAddressTxt = view.findViewById(R.id.appointment_details_doctor_address);
        doctorTimeTxt = view.findViewById(R.id.appointment_details_doctor_time);

        testsList = new ArrayList<>();
        testsList.add("Height: 50cm");
        testsList.add("Weight: 70kg");
        testsList.add("BMI: 21");
        testsListView = view.findViewById(R.id.appointment_details_tests_list);
        testAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,android.R.id.text1,testsList);
        testsListView.setAdapter(testAdapter);

        summaryTxtBtn.setOnClickListener(this);
        doctorTxtBtn.setOnClickListener(this);
        testsTxtBtn.setOnClickListener(this);

        summaryLinear.setVisibility(View.VISIBLE);
        doctorRelative.setVisibility(View.GONE);
        testsLinear.setVisibility(View.GONE);

        summaryTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.white));
        doctorTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));
        testsTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));



        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.appointment_details_summary_txt_btn){

            summaryLinear.setVisibility(View.VISIBLE);
            doctorRelative.setVisibility(View.GONE);
            testsLinear.setVisibility(View.GONE);

            summaryTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.white));
            doctorTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));
            testsTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));


        }

        if(view.getId() == R.id.appointment_details_doctor_txt_btn){

            summaryLinear.setVisibility(View.GONE);
            doctorRelative.setVisibility(View.VISIBLE);
            testsLinear.setVisibility(View.GONE);

            summaryTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));
            doctorTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.white));
            testsTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));


        }

        if(view.getId() == R.id.appointment_details_Tests_txt_btn){

            summaryLinear.setVisibility(View.GONE);
            doctorRelative.setVisibility(View.GONE);
            testsLinear.setVisibility(View.VISIBLE);

            summaryTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));
            doctorTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.gray));
            testsTxtBtn.setTextColor(view.getContext().getResources().getColor(R.color.white));


        }

    }
}
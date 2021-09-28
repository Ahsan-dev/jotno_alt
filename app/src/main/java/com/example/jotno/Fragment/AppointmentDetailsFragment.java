package com.example.jotno.Fragment;

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

import com.example.jotno.Models.Datum;
import com.example.jotno.Models.Datum__1;
import com.example.jotno.PaperDB.AppointmentPermanent;
import com.example.jotno.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class AppointmentDetailsFragment extends Fragment implements View.OnClickListener {

    private TextView summaryTxtBtn, doctorTxtBtn, testsTxtBtn;
    private LinearLayout summaryLinear, testsLinear;
    private RelativeLayout doctorRelative;
    private TextView appointDateTxt, appointTotalBillTxt, appointStatusTxt, appointPaymentStatusTxt;
    private ImageView doctorImage;
    private TextView doctorNameTxt, doctorDesignationTxt, doctorIdTxt, doctorMobileTxt, doctorEmailTxt, doctorHospitalTxt, doctorAddressTxt, doctorTimeTxt, noDoctorTxt;
    private ListView testsListView;
    private int testListSize = 0;
    private View view;
    private List<String> testsList;
    private ArrayAdapter<String> testAdapter;
    private int position = -1;
    private List<Datum> appoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointment_details, container, false);

        position = getArguments().getInt("position",0);

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

        appoList = Paper.book().read(AppointmentPermanent.appointmentListString);

        appointDateTxt.setText("Appointment Date: "+appoList.get(position).getAppoinmentDate());
        appointTotalBillTxt.setText("Total Bill: "+appoList.get(position).getTotal());
        appointStatusTxt.setText("Appointment Status: "+appoList.get(position).getStatus());
        appointPaymentStatusTxt.setText("Payment Status: "+appoList.get(position).getPaymentStatus());


        doctorImage = view.findViewById(R.id.appointment_details_doctor_image);
        doctorNameTxt = view.findViewById(R.id.appointment_details_doctor_name);
        doctorDesignationTxt = view.findViewById(R.id.appointment_details_doctor_designation);
        doctorIdTxt = view.findViewById(R.id.appointment_details_doctor_id);
        doctorEmailTxt = view.findViewById(R.id.appointment_details_doctor_email);
        doctorMobileTxt = view.findViewById(R.id.appointment_details_doctor_mobile);
        doctorHospitalTxt = view.findViewById(R.id.appointment_details_doctor_hospital);
        doctorAddressTxt = view.findViewById(R.id.appointment_details_doctor_address);
        doctorTimeTxt = view.findViewById(R.id.appointment_details_doctor_time);
        noDoctorTxt = view.findViewById(R.id.appointment_details_no_doctor);

        if(appoList.get(position).getDoctor()!=null){
            doctorNameTxt.setText(appoList.get(position).getDoctor().getName());
            doctorDesignationTxt.setText(appoList.get(position).getDoctor().getDesignation());
            doctorIdTxt.setText(appoList.get(position).getDoctor().getDoctorId());
            doctorEmailTxt.setText(appoList.get(position).getDoctor().getEmail());
            doctorMobileTxt.setText(appoList.get(position).getDoctor().getPhone());
            doctorHospitalTxt.setText(appoList.get(position).getDoctor().getChamber());
            doctorAddressTxt.setText(appoList.get(position).getDoctor().getLocation());
            Picasso.get().load(appoList.get(position).getDoctor().getImage()).placeholder(R.drawable.rehi).into(doctorImage);

            StringBuffer daysBuffer = new StringBuffer();
            int daysSize = appoList.get(position).getDoctor().getDays().getData().size();

            for(int i=0;i<daysSize-1;i++){

                daysBuffer.append(appoList.get(position).getDoctor().getDays().getData().get(i).getName());
                daysBuffer.append(", ");

            }
            daysBuffer.append(appoList.get(position).getDoctor().getDays().getData().get(daysSize-1).getName());


            doctorTimeTxt.setText("Time: "+daysBuffer.toString()+", "+appoList.get(position).getDoctor().getIn()+" to "+appoList.get(position).getDoctor().getOut());
            noDoctorTxt.setVisibility(View.GONE);
        }else{
            doctorImage.setVisibility(View.GONE);
            doctorNameTxt.setVisibility(View.GONE);
            doctorDesignationTxt.setVisibility(View.GONE);
            doctorIdTxt.setVisibility(View.GONE);
            doctorEmailTxt.setVisibility(View.GONE);
            doctorMobileTxt.setVisibility(View.GONE);
            doctorHospitalTxt.setVisibility(View.GONE);
            doctorAddressTxt.setVisibility(View.GONE);
            doctorTimeTxt.setVisibility(View.GONE);
            noDoctorTxt.setVisibility(View.VISIBLE);

        }



        testsList = new ArrayList<>();

        testListSize = appoList.get(position).getInitialTests().getData().size();

        for(int i=0;i<testListSize;i++){

            testsList.add(appoList.get(position).getInitialTests().getData().get(i).getName()+" : "+appoList.get(position).getInitialTests().getData().get(i).getValue());

        }

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
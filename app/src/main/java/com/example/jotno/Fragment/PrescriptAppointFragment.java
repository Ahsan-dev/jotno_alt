package com.example.jotno.Fragment;


import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Adapter.BillFieldItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptMedicinesItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptTestItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptionComplainsFindingsAdapter;
import com.example.jotno.Medicines;
import com.example.jotno.Models.Datum;
import com.example.jotno.Models.InitialTest;
import com.example.jotno.Models.InitialTests;
import com.example.jotno.Models.MainTest;
import com.example.jotno.Models.Medicine;
import com.example.jotno.Models.PrescriptionResponse;
import com.example.jotno.Models.TestType;
import com.example.jotno.Models.TestsOnPrescript;
import com.example.jotno.PaperDB.AppointmentPermanent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;


import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptAppointFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView testsRecycler, medicinesRecycler, billsRecycler, prescriptLeftSideRecycler;
    private List<InitialTest> testsList;
    private List<TestType> billsList;
    private List<Medicine> medicinesList;
    private List<MainTest> mainTestList;
    private String prescriptionNo;
    private PrescriptTestItemRecyclerAdapter prescriptTestsAdapter;
    private BillFieldItemRecyclerAdapter billFieldItemRecyclerAdapter;
    private PrescriptMedicinesItemRecyclerAdapter prescriptMedicinesItemRecyclerAdapter;
    private PrescriptionComplainsFindingsAdapter prescriptionComplainsFindingsAdapter;
    private Button downloadBtn, billBtn, reportBtn, addReportBtn;
    private Api api;
    private int position = -1;
    private List<Datum> appoList;
    private TextView doctorNameTxt, doctorDesignationTxt, doctorMobileTxt, doctorEmailTxt, doctorAddressTxt, doctorHospitalTxt,  doctorAvailableTxt;
    private TextView patientNameTxt, patientLocationTxt, patientMobileTxt, patientEmailTxt, prescriptionNoTxt, statusTxt, invoiceDateTxt, amountTxt, totalBillTxt;
    private TextView patientIdTxt, patientPrescriptNameTxt, patientAgeTxt, patientGenderTxt, prescriptFooterTxt;
    private TextView adviceTxt;
    private LinearLayout prescriptionLinear, billLinear, reportLinear, addReportLinear;
    String age = " ";
    private int prescriptionId = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_prescript_appoint, container, false);

        api = RetroClient.getClient().create(Api.class);
        position = getArguments().getInt("position",0);
        appoList = Paper.book().read(AppointmentPermanent.appointmentListString);

        downloadBtn = view.findViewById(R.id.prescript_appointment_download_btn);
        billBtn = view.findViewById(R.id.prescript_appointment_bill_btn);
        reportBtn = view.findViewById(R.id.prescript_appointment_report_btn);
        addReportBtn = view.findViewById(R.id.prescript_appointment_add_report_btn);

        downloadBtn.setOnClickListener(this);
        billBtn.setOnClickListener(this);
        reportBtn.setOnClickListener(this);
        addReportBtn.setOnClickListener(this);

        prescriptionLinear = view.findViewById(R.id.prescript_appointment_prescription_view_linear);
        billLinear = view.findViewById(R.id.prescript_appointment_bill_view_linear);
        reportLinear = view.findViewById(R.id.prescript_appointment_all_report_view_linear);
        addReportLinear = view.findViewById(R.id.prescript_appointment_add_report_view_linear);

        prescriptionLinear.setVisibility(View.VISIBLE);
        billLinear.setVisibility(View.GONE);
        reportLinear.setVisibility(View.GONE);
        addReportLinear.setVisibility(View.GONE);

        patientNameTxt = view.findViewById(R.id.prescript_appointment_bill_patient_name_text);
        patientLocationTxt = view.findViewById(R.id.prescript_appointment_bill_patient_location_text);
        patientMobileTxt = view.findViewById(R.id.prescript_appointment_bill_patient_mobile_text);
        patientEmailTxt = view.findViewById(R.id.prescript_appointment_bill_patient_email_text);
        prescriptionNoTxt = view.findViewById(R.id.prescript_appointment_bill_prescription_no_text);
        statusTxt = view.findViewById(R.id.prescript_appointment_bill_status_text);
        invoiceDateTxt = view.findViewById(R.id.prescript_appointment_bill_date_text);
        amountTxt = view.findViewById(R.id.prescript_appointment_bill_total_amount_text);
        totalBillTxt = view.findViewById(R.id.prescript_appointment_bill_total_bill_invoice_txt);
        prescriptFooterTxt = view.findViewById(R.id.prescript_appointment_prescription_footer_text);
        adviceTxt = view.findViewById(R.id.prescript_appointment_advice_text_id);

        patientIdTxt = view.findViewById(R.id.prescript_appointment_patient_id_txt);
        patientPrescriptNameTxt = view.findViewById(R.id.prescript_appointment_patient_name_txt);
        patientAgeTxt = view.findViewById(R.id.prescript_appointment_patient_age_txt);
        patientGenderTxt = view.findViewById(R.id.prescript_appointment_patient_gender_txt);

        prescriptFooterTxt.setText(appoList.get(position).getDoctor().getChamber()+", "+appoList.get(position).getDoctor().getLocation());

        doctorNameTxt = view.findViewById(R.id.prescript_appointment_doctor_name);
        doctorDesignationTxt = view.findViewById(R.id.prescript_appointment_doctor_designation);
        doctorMobileTxt = view.findViewById(R.id.prescript_appointment_doctor_mobile);
        doctorAddressTxt = view.findViewById(R.id.prescript_appointment_doctor_address);
        doctorHospitalTxt = view.findViewById(R.id.prescript_appointment_doctor_hospital);
        doctorAvailableTxt = view.findViewById(R.id.prescript_appointment_doctor_time);


        doctorNameTxt.setText(appoList.get(position).getDoctor().getName());
        doctorDesignationTxt.setText(appoList.get(position).getDoctor().getDesignation());
        doctorMobileTxt.setText(appoList.get(position).getDoctor().getPhone());
        doctorAddressTxt.setText(appoList.get(position).getDoctor().getLocation());
        doctorHospitalTxt.setText(appoList.get(position).getDoctor().getChamber());
        doctorAvailableTxt.setText(appoList.get(position).getDoctor().getIn()+" to "+appoList.get(position).getDoctor().getOut());

        patientIdTxt.setText(appoList.get(position).getPatient().getPatientId());
        patientPrescriptNameTxt.setText(appoList.get(position).getPatient().getName());


        patientGenderTxt.setText(appoList.get(position).getPatient().getGender());

        patientNameTxt.setText(appoList.get(position).getPatient().getName());
        patientLocationTxt.setText(appoList.get(position).getPatient().getAddress());
        patientMobileTxt.setText(appoList.get(position).getPatient().getPhone());
        patientEmailTxt.setText(appoList.get(position).getPatient().getEmail());

        String status = appoList.get(position).getPaymentStatus();
        if(status.equals("Paid")){
            statusTxt.setTextColor(view.getContext().getResources().getColor(R.color.green));
        }else{
            statusTxt.setTextColor(view.getContext().getResources().getColor(R.color.red));
        }

        statusTxt.setText(status);


        amountTxt.setText(appoList.get(position).getTotal());
        totalBillTxt.setText(appoList.get(position).getTotal());



        testsRecycler = view.findViewById(R.id.prescript_appointment_tests_recycler);
        medicinesRecycler = view.findViewById(R.id.prescript_appointment_medicines_recycler);
        billsRecycler = view.findViewById(R.id.prescript_appointment_bill_details_recycler);
        prescriptLeftSideRecycler = view.findViewById(R.id.prescript_appointment_advice_findings_recycler);

        testsList = new ArrayList<>();
        medicinesList = new ArrayList<>();
        billsList = new ArrayList<>();
        mainTestList = new ArrayList<>();


        api.getPrescriptionData(appoList.get(position).getPrescriptionId())
                .enqueue(new Callback<PrescriptionResponse>() {
                    @Override
                    public void onResponse(Call<PrescriptionResponse> call, Response<PrescriptionResponse> response) {

                        if(response.isSuccessful()){

                            prescriptionId = response.body().getPrescription().getId();
                            //Prescription No
                            prescriptionNo = response.body().getPrescription().getPrescriptionNo();
                            prescriptionNoTxt.setText(prescriptionNo);

                            age = response.body().getAge();
                            patientAgeTxt.setText(age);

                            adviceTxt.setText(response.body().getPrescription().getAdvice());

                            //Initial Tests
                            testsList = response.body().getInitialTests();
                            prescriptTestsAdapter = new PrescriptTestItemRecyclerAdapter(testsList);
                            testsRecycler.hasFixedSize();
                            testsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
                            testsRecycler.setAdapter(prescriptTestsAdapter);
                            prescriptTestsAdapter.notifyDataSetChanged();

                            //Medicines
                            medicinesList = response.body().getMedicine();
                            prescriptMedicinesItemRecyclerAdapter = new PrescriptMedicinesItemRecyclerAdapter(medicinesList);
                            medicinesRecycler.hasFixedSize();
                            medicinesRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            medicinesRecycler.setAdapter(prescriptMedicinesItemRecyclerAdapter);
                            prescriptMedicinesItemRecyclerAdapter.notifyDataSetChanged();

                            //Main Tests or Prescription Left Side

                            mainTestList = response.body().getMainTest();
                            prescriptionComplainsFindingsAdapter = new PrescriptionComplainsFindingsAdapter(mainTestList);
                            prescriptLeftSideRecycler.setHasFixedSize(true);
                            prescriptLeftSideRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            prescriptLeftSideRecycler.setAdapter(prescriptionComplainsFindingsAdapter);
                            prescriptionComplainsFindingsAdapter.notifyDataSetChanged();

                            //Bills
                            int mainTestSize = response.body().getMainTest().size();


                            for(int i=0;i<mainTestSize;i++){

                                if(response.body().getMainTest().get(i).getTestType().equals("Examination Finding")){

                                    billsList = response.body().getMainTest().get(i).getTestTypeList();
                                    billFieldItemRecyclerAdapter = new BillFieldItemRecyclerAdapter(billsList);
                                    billsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                    billsRecycler.hasFixedSize();
                                    billsRecycler.setAdapter(billFieldItemRecyclerAdapter);
                                    billFieldItemRecyclerAdapter.notifyDataSetChanged();


                                }

                            }




                        }else{

                            Toast.makeText(view.getContext(), "Response not found!!!", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<PrescriptionResponse> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



        return view;
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.prescript_appointment_download_btn){

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jotno.beautyclinicbd.com/api/prescription-pdf/5"));
            startActivity(browserIntent);
        }

        if(view.getId() == R.id.prescript_appointment_bill_btn){

            prescriptionLinear.setVisibility(View.GONE);
            billLinear.setVisibility(View.VISIBLE);
            reportLinear.setVisibility(View.GONE);
            addReportLinear.setVisibility(View.GONE);

        }

        if(view.getId() == R.id.prescript_appointment_report_btn){

            prescriptionLinear.setVisibility(View.GONE);
            billLinear.setVisibility(View.GONE);
            reportLinear.setVisibility(View.VISIBLE);
            addReportLinear.setVisibility(View.GONE);

        }

        if(view.getId() == R.id.prescript_appointment_add_report_btn){

            prescriptionLinear.setVisibility(View.GONE);
            billLinear.setVisibility(View.GONE);
            reportLinear.setVisibility(View.GONE);
            addReportLinear.setVisibility(View.VISIBLE);

        }

    }
}
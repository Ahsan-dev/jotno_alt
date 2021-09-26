package com.example.jotno.Fragment;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Adapter.BillFieldItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptMedicinesItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptReportItemAdapter;
import com.example.jotno.Adapter.PrescriptTestItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptionComplainsFindingsAdapter;
import com.example.jotno.Medicines;
import com.example.jotno.Models.Datum;
import com.example.jotno.Models.EventModel;
import com.example.jotno.Models.ImageSenderInfo;
import com.example.jotno.Models.InitialTest;
import com.example.jotno.Models.InitialTests;
import com.example.jotno.Models.MainTest;
import com.example.jotno.Models.Medicine;
import com.example.jotno.Models.PrescriptionReportResponse;
import com.example.jotno.Models.PrescriptionResponse;
import com.example.jotno.Models.ReportDatum;
import com.example.jotno.Models.TestType;
import com.example.jotno.Models.TestsOnPrescript;
import com.example.jotno.NetworkCall;
import com.example.jotno.PaperDB.AppointmentPermanent;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptAppointFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView testsRecycler, medicinesRecycler, billsRecycler, prescriptLeftSideRecycler, prescriptReportRecycler;
    private List<InitialTest> testsList;
    private List<TestType> billsList;
    private List<Medicine> medicinesList;
    private List<MainTest> mainTestList;
    private List<ReportDatum> prescriptReportList;
    private String prescriptionNo;
    private PrescriptTestItemRecyclerAdapter prescriptTestsAdapter;
    private BillFieldItemRecyclerAdapter billFieldItemRecyclerAdapter;
    private PrescriptMedicinesItemRecyclerAdapter prescriptMedicinesItemRecyclerAdapter;
    private PrescriptionComplainsFindingsAdapter prescriptionComplainsFindingsAdapter;
    private PrescriptReportItemAdapter prescriptReportItemAdapter;
    private Button downloadBtn, billBtn, reportBtn, addReportBtn, downloadBillInvoiceBtn;
    private Api api;
    private int position = -1;
    private List<Datum> appoList;
    private TextView doctorNameTxt, doctorDesignationTxt, doctorMobileTxt, doctorEmailTxt, doctorAddressTxt, doctorHospitalTxt,  doctorAvailableTxt;
    private TextView patientNameTxt, patientLocationTxt, patientMobileTxt, patientEmailTxt, prescriptionNoTxt, statusTxt, invoiceDateTxt, amountTxt, totalBillTxt;
    private TextView patientIdTxt, patientPrescriptNameTxt, patientAgeTxt, patientGenderTxt, prescriptFooterTxt;
    private TextView adviceTxt;
    private LinearLayout prescriptionLinear, billLinear, reportLinear, addReportLinear;
    private EditText addReportTestEdt, addReportResourceEdt;
    private ImageView addReportImgBtn;
    private Button addReportASendBtn;
    private String age = " ";
    private File reportImageFile;
    private int prescriptionId = 0;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    private String imagePath;
    private String action = "upload";
    private int report_id = -1;




    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_prescript_appoint, container, false);

        action = String.valueOf(getArguments().getString("action"));



        api = RetroClient.getClient().create(Api.class);
        position = getArguments().getInt("position",0);
        appoList = Paper.book().read(AppointmentPermanent.appointmentListString);

        downloadBtn = view.findViewById(R.id.prescript_appointment_download_btn);
        billBtn = view.findViewById(R.id.prescript_appointment_bill_btn);
        reportBtn = view.findViewById(R.id.prescript_appointment_report_btn);
        addReportBtn = view.findViewById(R.id.prescript_appointment_add_report_btn);
        downloadBillInvoiceBtn = view.findViewById(R.id.prescript_appointment_bill_invoice_download_btn);

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

        addReportTestEdt = view.findViewById(R.id.prescript_appointment_add_report_test_name_edt);
        addReportResourceEdt = view.findViewById(R.id.prescript_appointment_add_report_resource_edt);
        addReportImgBtn = view.findViewById(R.id.prescript_appointment_add_report_image_btn);
        addReportASendBtn = view.findViewById(R.id.prescript_appointment_add_report_send_btn);


        if(action.equals("edit")){

            report_id = getArguments().getInt("id",-1);
            prescriptionLinear.setVisibility(View.GONE);
            billLinear.setVisibility(View.GONE);
            reportLinear.setVisibility(View.GONE);
            addReportLinear.setVisibility(View.VISIBLE);

            addReportTestEdt.setText(getArguments().getString("name"));

        }


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
        prescriptReportRecycler = view.findViewById(R.id.prescript_appointment_report_recycler);

        testsList = new ArrayList<>();
        medicinesList = new ArrayList<>();
        billsList = new ArrayList<>();
        mainTestList = new ArrayList<>();
        prescriptReportList = new ArrayList<>();


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

        downloadBillInvoiceBtn.setOnClickListener(view-> {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jotno.beautyclinicbd.com/api/billing-pdf/"+prescriptionId));
            startActivity(browserIntent);


        });


        addReportImgBtn.setOnClickListener(view1 -> {


            if(checkAndRequestPermissions(view1.getContext())){
                selectImage(view1.getContext());
            }

        });

        addReportASendBtn.setOnClickListener(view1 -> {

            String testName = addReportTestEdt.getText().toString();
            String imageTxt = addReportResourceEdt.getText().toString();

            if(testName.equals("")){

                addReportTestEdt.setError("Enter a test name");
                addReportTestEdt.requestFocus();
                return;

            }else if(imageTxt.equals("")){

                Toast.makeText(view1.getContext(), "Report must be attached", Toast.LENGTH_SHORT).show();

            }else{

                if(action.equals("upload"))

                    NetworkCall.fileUpload2(imagePath,testName,prescriptionId);

                else {
                    report_id = getArguments().getInt("id", -1);
                    NetworkCall.fileUpload3(imagePath, testName, report_id);
                }

            }



        });




        return view;
    }

    private  void  showReportApi(){

        api.getPrescriptionReports(prescriptionId)
                .enqueue(new Callback<PrescriptionReportResponse>() {
                    @Override
                    public void onResponse(Call<PrescriptionReportResponse> call, Response<PrescriptionReportResponse> response) {
                        if(response.isSuccessful()){

                            prescriptReportList = response.body().getBody().getData();
                            prescriptReportItemAdapter = new PrescriptReportItemAdapter(prescriptReportList);
                            prescriptReportRecycler.setHasFixedSize(true);
                            prescriptReportRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            prescriptReportRecycler.setAdapter(prescriptReportItemAdapter);
                            prescriptReportItemAdapter.notifyDataSetChanged();


                        }else{

                            Toast.makeText(view.getContext(), "Response not found!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<PrescriptionReportResponse> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.prescript_appointment_download_btn){

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jotno.beautyclinicbd.com/api/prescription-pdf/"+prescriptionId));
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

            showReportApi();


        }

        if(view.getId() == R.id.prescript_appointment_add_report_btn){

            prescriptionLinear.setVisibility(View.GONE);
            billLinear.setVisibility(View.GONE);
            reportLinear.setVisibility(View.GONE);
            addReportLinear.setVisibility(View.VISIBLE);
            action = "upload";

        }
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = event.getMessage();
            Toast.makeText(view.getContext(), "Message: "+responseMessage, Toast.LENGTH_SHORT).show();
            addReportTestEdt.setText("");
            addReportResourceEdt.setText("");
        }

        if (event.isTagMatchWith("delete_response")) {

            showReportApi();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public boolean checkAndRequestPermissions(final Context context) {
        int WExtstorePermission = checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = checkSelfPermission(context, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    // Handled permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(view.getContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view.getContext(), "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view.getContext(), "FlagUp Requires Access to Your Storage.", Toast.LENGTH_SHORT).show();
                } else {
                    selectImage(view.getContext());
                }
                break;
        }
    }



    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(view.getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                        }
                        else
                        {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 0);
                        }
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        Uri cameraUri = data.getData();
                        imagePath = getPath(cameraUri);
                        addReportResourceEdt.setText(imagePath);
                        addReportResourceEdt.setEnabled(false);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        imagePath = getPath(selectedImage);
                        addReportResourceEdt.setText(imagePath);
                        addReportResourceEdt.setEnabled(false);

                    }
                    break;
            }
        }
    }


    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
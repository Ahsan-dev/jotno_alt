package com.example.jotno.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.AppointmentsFragment;
import com.example.jotno.AppointmentsRecyclerAdapter;
import com.example.jotno.BillsFragment;
import com.example.jotno.Models.Appointments;
import com.example.jotno.Models.GetAppointmentResponse;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PrescriptionsFragment;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.example.jotno.TestsFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ExtendedFloatingActionButton getAppointmentBtn;
    private CardView appointmentsCard, prescriptionsCard, allTestsCard, billsCard;
    private TextView appointmentTxt, prescriptionsTxt, allTestsTxt, billsTxt, welcomeTxt;
    private Fragment fragment;
    private ProgressDialog loadingBar;
    private Api api;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Paper.init(view.getContext());
        loadingBar = new ProgressDialog(view.getContext());

        api = RetroClient.getClient().create(Api.class);

        getAppointmentBtn = view.findViewById(R.id.dashboard_get_appointment_btn_id);
        getAppointmentBtn.setOnClickListener(this);

        appointmentsCard = view.findViewById(R.id.dashboard_appoints_card_id);
        prescriptionsCard = view.findViewById(R.id.dashboard_prescriptions_card_id);
        allTestsCard = view.findViewById(R.id.dashboard_tests_card_id);
        billsCard = view.findViewById(R.id.dashboard_bills_card_id);

        appointmentTxt = view.findViewById(R.id.dashboard_statistics_appointments_txt_id);
        welcomeTxt = view.findViewById(R.id.dashboard_welcome_patient_txt_id);
        welcomeTxt.setText("Welcome\n     "+Paper.book().read(PermanentPatient.userNameString));

        prescriptionsTxt = view.findViewById(R.id.dashboard_statistics_prescriptions_txt_id);
        billsTxt = view.findViewById(R.id.dashboard_statistics_tests_txt_id);

        appointmentsCard.setOnClickListener(v -> {

            AppointmentsFragment fragment = new AppointmentsFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        prescriptionsCard.setOnClickListener(v -> {

            PrescriptionsFragment fragment = new PrescriptionsFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        allTestsCard.setOnClickListener(v -> {

            TestsFragment fragment = new TestsFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        billsCard.setOnClickListener(v -> {

            BillsFragment fragment = new BillsFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        return  view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.dashboard_get_appointment_btn_id){

            loadingBar.setTitle("Getting an appointment...");
            loadingBar.setMessage("Plz wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            api.getAppointment(Paper.book().read(PermanentPatient.userIdString))
                    .enqueue(new Callback<GetAppointmentResponse>() {
                        @Override
                        public void onResponse(Call<GetAppointmentResponse> call, Response<GetAppointmentResponse> response) {
                            if(response.isSuccessful()){

                                if(response.body().getStatus().equals("success")){

                                    loadingBar.dismiss();
                                    Toast.makeText(v.getContext(), "You have got Appointment...", Toast.LENGTH_SHORT).show();

                                }else{

                                    loadingBar.dismiss();
                                    Toast.makeText(v.getContext(), "Something wrong!!!", Toast.LENGTH_SHORT).show();


                                }

                            }else{

                                loadingBar.dismiss();
                                Toast.makeText(v.getContext(), "Response not found!!!", Toast.LENGTH_SHORT).show();


                            }
                        }

                        @Override
                        public void onFailure(Call<GetAppointmentResponse> call, Throwable t) {
                            loadingBar.dismiss();
                            Toast.makeText(v.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });


        }

    }
}
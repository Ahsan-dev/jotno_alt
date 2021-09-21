package com.example.jotno.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jotno.Adapter.AppointmentsRecyclerAdapter;
import com.example.jotno.Models.AppointmentResponse;
import com.example.jotno.Models.Datum;
import com.example.jotno.Models.GetAppointmentResponse;
import com.example.jotno.PaperDB.AppointmentPermanent;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppointmentsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button todayBtn, allBtn;
    private ExtendedFloatingActionButton getAppointmentBtn;
    private RecyclerView appointmentRecycler;
    private List<Datum> appointmentList;
    private AppointmentsRecyclerAdapter appoAdapter;
    private ProgressDialog loadingBar;
    private Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointments, container, false);

        loadingBar = new ProgressDialog(view.getContext());

        api = RetroClient.getClient().create(Api.class);
        Paper.init(view.getContext());

        todayBtn = view.findViewById(R.id.appointments_today_option_btn_id);
        allBtn = view.findViewById(R.id.appointments_all_option_btn_id);
        getAppointmentBtn = view.findViewById(R.id.appointments_get_appointment_btn_id);
        appointmentRecycler = view.findViewById(R.id.appointments_fragment_appointments_recycler_id);

        todayBtn.setBackgroundColor(getResources().getColor(R.color.red));
        todayBtn.setTextColor(getResources().getColor(android.R.color.white));
        allBtn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        allBtn.setTextColor(getResources().getColor(R.color.red));

        appointmentList = new ArrayList<>();


        api.getTodayAppointmentList(Paper.book().read(PermanentPatient.userIdString))
                .enqueue(new Callback<AppointmentResponse>() {
                    @Override
                    public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {

                        if(response.isSuccessful()){

                            appointmentList = response.body().getBody().getData();
                            Paper.book().write(AppointmentPermanent.appointmentListString,appointmentList);
                            appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
                            appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            appointmentRecycler.hasFixedSize();
                            appointmentRecycler.setAdapter(appoAdapter);
                            appoAdapter.notifyDataSetChanged();

                        }else{

                            Toast.makeText(view.getContext(), "Response Null!!", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



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


            api.getTodayAppointmentList(Paper.book().read(PermanentPatient.userIdString))
                    .enqueue(new Callback<AppointmentResponse>() {
                        @Override
                        public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {

                            if(response.isSuccessful()){

                                appointmentList = response.body().getBody().getData();
                                Paper.book().write(AppointmentPermanent.appointmentListString,appointmentList);
                                appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
                                appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                appointmentRecycler.hasFixedSize();
                                appointmentRecycler.setAdapter(appoAdapter);
                                appoAdapter.notifyDataSetChanged();

                            }else{

                                Toast.makeText(view.getContext(), "Response Null!!", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                            Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });





        }

        if(v.getId()==R.id.appointments_all_option_btn_id){

            todayBtn.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            todayBtn.setTextColor(getResources().getColor(R.color.red));
            allBtn.setBackgroundColor(getResources().getColor(R.color.red));
            allBtn.setTextColor(getResources().getColor(android.R.color.white));



            api.getAllAppointmentList(Paper.book().read(PermanentPatient.userIdString))
                    .enqueue(new Callback<AppointmentResponse>() {
                        @Override
                        public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {

                            if(response.isSuccessful()){

                                appointmentList = response.body().getBody().getData();
                                Paper.book().write(AppointmentPermanent.appointmentListString,appointmentList);
                                appoAdapter = new AppointmentsRecyclerAdapter(appointmentList);
                                appointmentRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                appointmentRecycler.hasFixedSize();
                                appointmentRecycler.setAdapter(appoAdapter);
                                appoAdapter.notifyDataSetChanged();

                            }else{

                                Toast.makeText(view.getContext(), "Response Null!!", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                            Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        }

        if(v.getId()==R.id.appointments_get_appointment_btn_id){

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
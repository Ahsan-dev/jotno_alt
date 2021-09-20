package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jotno.AppointmentDetailsFragment;
import com.example.jotno.PrescriptAppointFragment;
import com.example.jotno.PrescriptionsFragment;
import com.example.jotno.R;


public class AppointmentViewFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView detailsTxtBtn, prescriptionTxtBtn, appointmentNo;
    private RelativeLayout mainRelativeScreen;
    private int position = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointment_view, container, false);

        position = getArguments().getInt("position",0);

        appointmentNo = view.findViewById(R.id.view_appointment_appointment_no_text);
        detailsTxtBtn = view.findViewById(R.id.view_appointment_appointment_details_text_id);
        prescriptionTxtBtn = view.findViewById(R.id.view_appointment_prescription_text_id);
        mainRelativeScreen = view.findViewById(R.id.view_appointment_relative);

        detailsTxtBtn.setOnClickListener(this);
        prescriptionTxtBtn.setOnClickListener(this);

        detailsTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.white));
        detailsTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(R.drawable.welcome_register_btn_back));
        prescriptionTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.black));
        prescriptionTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(android.R.color.transparent));

        AppointmentDetailsFragment fragment = new AppointmentDetailsFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundles = new Bundle();
        bundles.putInt("position",position);
        fragment.setArguments(bundles);
        fragmentTransaction.replace(R.id.view_appointment_relative, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.view_appointment_appointment_details_text_id){

            detailsTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.white));
            detailsTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(R.drawable.welcome_register_btn_back));
            prescriptionTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.black));
            prescriptionTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(android.R.color.transparent));

            AppointmentDetailsFragment fragment = new AppointmentDetailsFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundles = new Bundle();
            bundles.putInt("position",position);
            fragment.setArguments(bundles);
            fragmentTransaction.replace(R.id.view_appointment_relative, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

        if(view.getId() == R.id.view_appointment_prescription_text_id){

            detailsTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.black));
            detailsTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(android.R.color.transparent));
            prescriptionTxtBtn.setHintTextColor(view.getContext().getResources().getColor(R.color.white));
            prescriptionTxtBtn.setBackgroundDrawable(view.getContext().getDrawable(R.drawable.welcome_register_btn_back));

            PrescriptAppointFragment fragment = new PrescriptAppointFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view_appointment_relative, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

    }
}
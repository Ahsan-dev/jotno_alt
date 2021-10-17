package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jotno.PaperDB.PermanentApp;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class AboutUsFragment extends Fragment {

    private TextView tileText, descriptionText, satisfiedPatientsNumberTxt, patientsPerYearTxt;
    private View view;
    private ImageView aboutUpImage;
    private Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_us, container, false);

        tileText = view.findViewById(R.id.about_us_title_text_id);
        descriptionText = view.findViewById(R.id.about_us_details_txt_id);
        satisfiedPatientsNumberTxt = view.findViewById(R.id.about_us_satisfied_patients_number_txt);
        patientsPerYearTxt = view.findViewById(R.id.about_us_patients_per_year_number_txt);
        aboutUpImage = view.findViewById(R.id.about_us_image_id);


        tileText.setText(Paper.book().read(PermanentApp.aboutUsTitleString));
        descriptionText.setText(Paper.book().read(PermanentApp.aboutUsDetailString));
        satisfiedPatientsNumberTxt.setText(Paper.book().read(PermanentApp.aboutUsSatisfiedPatientsString));
        patientsPerYearTxt.setText(Paper.book().read(PermanentApp.aboutUsPatientPerYearString));

        String image = Paper.book().read(PermanentApp.aboutUsImageUrlString);

        Picasso.get().load(image).placeholder(R.drawable.jotno_logo).into(aboutUpImage);

        return view;
    }
}
package com.example.jotno.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.PaperDB.PermanentApp;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;

import java.util.regex.Pattern;

import io.paperdb.Paper;


public class ContuctUsFragment extends Fragment {

    private View view;
    private EditText detailsEdt, fullnameEdt, emailEdt, subjectEdt;
    private String fullName, email, subject, details;
    private Button sendMsgBtn;
    private TextView locationTxt, emailTxt, mobileTxt;
    private ImageView fbIcon, twitterIcon, linkedinIcon;
    private Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contuct_us, container, false);

        fullnameEdt = view.findViewById(R.id.contact_us_full_name_edt);
        emailEdt = view.findViewById(R.id.contact_us_email_edt);
        subjectEdt = view.findViewById(R.id.contact_us_subject_edt);
        detailsEdt = view.findViewById(R.id.contact_us_message_edt);
        sendMsgBtn = view.findViewById(R.id.contact_us_send_message_btn_id);

        locationTxt = view.findViewById(R.id.contact_us_get_in_touch_location_txt);
        emailTxt = view.findViewById(R.id.contact_us_get_in_touch_email_txt);
        mobileTxt = view.findViewById(R.id.contact_us_get_in_touch_mobile_txt);
        fbIcon = view.findViewById(R.id.contact_us_get_in_touch_fb_icon);
        twitterIcon = view.findViewById(R.id.contact_us_get_in_touch_twitter_icon);
        linkedinIcon = view.findViewById(R.id.contact_us_get_in_touch_linkedin_icon);


        locationTxt.setText(Paper.book().read(PermanentApp.contactUsAddressString));
        emailTxt.setText(Paper.book().read(PermanentApp.contactUsEmailString));
        mobileTxt.setText(Paper.book().read(PermanentApp.contactUsMobileString));

        fbIcon.setOnClickListener(view1 -> {

            Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Paper.book().read(PermanentApp.contactUsFacebookUrlString)));
            startActivity(fbIntent);

        });

        twitterIcon.setOnClickListener(view1 -> {

            Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Paper.book().read(PermanentApp.contactUsTwitterUrlString)));
            startActivity(fbIntent);

        });

        linkedinIcon.setOnClickListener(view1 -> {

            Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Paper.book().read(PermanentApp.contactUsLinkedinString)));
            startActivity(fbIntent);

        });




        detailsEdt.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (detailsEdt.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        sendMsgBtn.setOnClickListener(v -> {

            validateFields();

        });

        return view;
    }

    void validateFields(){

        fullName = fullnameEdt.getText().toString();
        email = emailEdt.getText().toString();
        subject = subjectEdt.getText().toString();
        details = detailsEdt.getText().toString();

        if(fullName.equals("")){

            fullnameEdt.setError("Enter your full name!");
            fullnameEdt.requestFocus();
            return;

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            emailEdt.setError("Enter valid email!");
            emailEdt.requestFocus();
            return;

        }else if(subject.equals("")){

            subjectEdt.setError("Enter your message subject!");
            subjectEdt.requestFocus();
            return;

        }else if(details.equals("") || details.length()<10){

            detailsEdt.setError("Enter your message of minimum 10 words!");
            detailsEdt.requestFocus();
            return;

        }else{

            Toast.makeText(view.getContext(), "Message sent successfully", Toast.LENGTH_SHORT).show();

        }

    }

}
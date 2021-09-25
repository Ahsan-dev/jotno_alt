package com.example.jotno.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jotno.Activity.RegisterActivity;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

import io.paperdb.Paper;


public class ChangePassFragment extends Fragment {

    private EditText oldPassEdt, newPassEdt, confirmPassEdt;
    private boolean oldEyeState = true;
    private boolean newEyeState = true;
    private boolean confirmEyeState = true;
    private String  oldPassword, newPassword, confirmPass;
    private Button submitBtn;
    private Api api;
    private View view;
    private ProgressDialog loadingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_change_pass, container, false);

        oldPassEdt = view.findViewById(R.id.change_pass_old_password_edt);
        newPassEdt = view.findViewById(R.id.change_pass_new_password_edt);
        confirmPassEdt = view.findViewById(R.id.change_pass_confirm_password_edt);
        submitBtn = view.findViewById(R.id.change_pass_change_pass_now_btn_id);


        api = RetroClient.getClient().create(Api.class);

        Paper.init(view.getContext());
        loadingBar = new ProgressDialog(view.getContext());

        oldPassEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (oldPassEdt.getRight() - oldPassEdt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(oldEyeState == true){

                            oldEyeState = false;
                            oldPassEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            oldPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off_24, 0);

                        }else{

                            oldEyeState = true;
                            oldPassEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            oldPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_24, 0);

                        }

                        return true;
                    }
                }


                return false;
            }
        });

        newPassEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (newPassEdt.getRight() - newPassEdt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(newEyeState == true){

                            newEyeState = false;
                            newPassEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            newPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off_24, 0);

                        }else{

                            newEyeState = true;
                            newPassEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            newPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_24, 0);

                        }

                        return true;
                    }
                }


                return false;
            }
        });

        confirmPassEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (confirmPassEdt.getRight() - confirmPassEdt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(confirmEyeState == true){

                            confirmEyeState = false;
                            confirmPassEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            confirmPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off_24, 0);

                        }else{

                            confirmEyeState = true;
                            confirmPassEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            confirmPassEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_24, 0);

                        }

                        return true;
                    }
                }


                return false;
            }
        });


        submitBtn.setOnClickListener(view1 -> {

            validateFields();

        });

        return view;
    }

    private void validateFields() {

        oldPassword = oldPassEdt.getText().toString();
        newPassword = newPassEdt.getText().toString();
        confirmPass = confirmPassEdt.getText().toString();



         if (oldPassword.equals("") || oldPassword.length() < 6) {

            oldPassEdt.setError("Enter password of 6 digit at least");
            oldPassEdt.requestFocus();
            return;

        }if (newPassword.equals("") || newPassword.length() < 6) {

            newPassEdt.setError("Enter password of 6 digit at least");
            newPassEdt.requestFocus();
            return;

        } else if (confirmPass.equals("") || confirmPass.length() < 6 || !confirmPass.equals(newPassword)) {

            confirmPassEdt.setError("Password does not match!");
            confirmPassEdt.requestFocus();
            return;

        } {


            Toast.makeText(view.getContext(), "Password changed successfully..", Toast.LENGTH_SHORT).show();

        }
    }

}
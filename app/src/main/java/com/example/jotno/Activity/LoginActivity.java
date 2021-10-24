package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Models.ContentResponse;
import com.example.jotno.Models.LoginUser;
import com.example.jotno.Models.LoginResponse;
import com.example.jotno.PaperDB.PermanentApp;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PaperDB.Prevalent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdt, passEdt;
    private boolean eyeState = true;
    private String email, password;
    private CheckBox rememberChecker;
    private boolean rememberChecked = true;
    private TextView forgetPassTextBtn;
    private Button loginBtn;
    private TextView registerTxtBtn;
    private ProgressDialog loadingBar;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdt = findViewById(R.id.login_email_edt);
        passEdt = findViewById(R.id.login_password_edt);
        rememberChecker = findViewById(R.id.logIn_remember_checkboxId);
        forgetPassTextBtn = findViewById(R.id.login_forget_passId);
        loginBtn = findViewById(R.id.login_buttonId);
        registerTxtBtn = findViewById(R.id.login_register_txt_btn_id);

        api = RetroClient.getClient().create(Api.class);

        loadingBar = new ProgressDialog(this);
        Paper.init(this);




        passEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (passEdt.getRight() - passEdt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        if(eyeState==true){
                            eyeState = false;
                            passEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off_24, 0);

                        }else{
                            eyeState = true;
                            passEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passEdt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_24, 0);
                        }

                        return true;
                    }
                }
                return false;
            }
        });

        loginBtn.setOnClickListener(v -> {

            loginaccess();

        });

        registerTxtBtn.setOnClickListener(v -> {

            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);

        });

        forgetPassTextBtn.setOnClickListener(view -> {

            Dialog dialog = new Dialog(view.getContext());
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.forget_password_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            EditText emailEdt = dialog.findViewById(R.id.forget_pass_email_edt);
            Button sendBtn = dialog.findViewById(R.id.forget_pass_send_btn);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);

            sendBtn.setOnClickListener(view1 -> {

                String email = emailEdt.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                    emailEdt.setError("Enter valid email address");
                    emailEdt.requestFocus();
                    return;

                }else{

                    api.forgetPassword(email)
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()){

                                        try {
                                            Toast.makeText(view1.getContext(), response.body().string(), Toast.LENGTH_SHORT).show();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }else{

                                        Toast.makeText(view1.getContext(), "Response not found!!", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    Toast.makeText(view1.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }


                dialog.dismiss();


            });

            dialog.show();

        });



    }


    private void loginaccess() {
        email = emailEdt.getText().toString().trim();
        password = passEdt.getText().toString().trim();
        rememberChecked = rememberChecker.isChecked();

//        emailEdt.setText("");
//        passEdt.setText("");


        if(TextUtils.isEmpty(email)){
            emailEdt.setError("Enter your email or mobile number");
            emailEdt.requestFocus();
            return;
        }

        else if(TextUtils.isEmpty(password) || password.length()<6){
            passEdt.setError("Plz enter your password of minimum 6 characters");
            passEdt.requestFocus();
            return;
        }

        else{
            loadingBar.setTitle("Logging in to your Account...");
            loadingBar.setMessage("Plz wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

                loginUser(email,password);
        }
    }

    private void loginUser( String email, String password) {

        if (rememberChecker.isChecked()) {
            Paper.book().write(Prevalent.userEmailKey, email);
            Paper.book().write(Prevalent.userPassKey, password);

        }

        LoginUser user = new LoginUser();

        user.setEmail(email);
        user.setPassword(password);

        Call<LoginResponse> loginCall = api.loginUser(user);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("success")){
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


                        Paper.book().write(PermanentPatient.userIdString,response.body().getBody().getId());
                        Paper.book().write(PermanentPatient.patientIdString,response.body().getBody().getPatientId());
                        Paper.book().write(PermanentPatient.userNameString,response.body().getBody().getName());
                        Paper.book().write(PermanentPatient.userDateOfBirthString,response.body().getBody().getDateOfBirth());
                        Paper.book().write(PermanentPatient.userBloodGrpString,response.body().getBody().getBloodGroup());
                        Paper.book().write(PermanentPatient.userGenderString,response.body().getBody().getGender());
                        Paper.book().write(PermanentPatient.userEmailString,response.body().getBody().getEmail());
                        Paper.book().write(PermanentPatient.userMobileString,response.body().getBody().getPhone());
                        Paper.book().write(PermanentPatient.userCityString,response.body().getBody().getCity());
                        Paper.book().write(PermanentPatient.userDistrictString,response.body().getBody().getDistrict());
                        Paper.book().write(PermanentPatient.userAddressString,response.body().getBody().getAddress());
                        Paper.book().write(PermanentPatient.userImageString,response.body().getBody().getImage());


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();

                    }


                }else {
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(),"Response is null !!",Toast.LENGTH_LONG).show();

                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
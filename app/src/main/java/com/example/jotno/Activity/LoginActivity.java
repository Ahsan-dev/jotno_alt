package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Models.LoginUser;
import com.example.jotno.Prevalent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

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



    }


    private void loginaccess() {
        email = emailEdt.getText().toString().trim();
        password = passEdt.getText().toString().trim();
        rememberChecked = rememberChecker.isChecked();

        emailEdt.setText("");
        passEdt.setText("");


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdt.setError("Enter your email with proper format");
            emailEdt.requestFocus();
            return;
        }

        else if(TextUtils.isEmpty(password)){
            passEdt.setError("Plz enter your password.");
            passEdt.requestFocus();
            return;
        }

        else{
            loadingBar.setTitle("Login to your Account");
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

        Call<ResponseBody> loginCall = api.loginUser(user);

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    if(!response.body().toString().equals("failed")){
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


//                        String bg = loginResponse.getBloodGroup();
//                        Paper.book().write(Permanent.uid,loginResponse.getUserId());
//                        Paper.book().write(Permanent.userName,loginResponse.getUserName());
//                        Paper.book().write(Permanent.days,loginResponse.getDays());
//                        Paper.book().write(Permanent.bloodGrp,bg);
//                        Paper.book().write(Permanent.sameBlood,loginResponse.getSameBlood());
//                        Paper.book().write(Permanent.gender,loginResponse.getGender());
//                        Paper.book().write(Permanent.image,loginResponse.getImage());
//                        Paper.book().write(Permanent.policeStation,loginResponse.getPoliceStation());
//                        Paper.book().write(Permanent.district,loginResponse.getDistrict());
//                        Paper.book().write(Permanent.detailsAbout,loginResponse.getDetails());

//                        Log.d("bg",bg);

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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
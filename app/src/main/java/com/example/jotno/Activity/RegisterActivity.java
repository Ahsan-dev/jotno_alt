package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Models.RegisterResponse;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.google.android.material.navigation.NavigationBarView;

public class RegisterActivity extends AppCompatActivity {

    private Spinner bldGrpSpinner, genderSpinner;
    private String[] bloodGrpArray, genderArray;
    private ArrayAdapter<String> bloodGrpAdapter, genderAdapter ;
    private EditText fullNameEdt, dobEdt, weightEdt, emailEdt, mobileEdt, addressEdt, cityEdt, districtEdt, passEdt, confirmPassEdt;
    private boolean eyeState = true;
    private boolean confirmEyeState = true;
    private String fullName, dob, bloodGroup, gender, email, mobile, address, city, district, password, confirmPass;
    private DatePickerDialog dobPicker;
    private StringBuilder dobString;
    private int bDay, bMonth, bYear;
    private boolean checked;
    private CheckBox registerCheck;
    private Button registerNowBtn;
    private Api api;
    private TextView loginTxtBtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bldGrpSpinner = findViewById(R.id.register_bld_grp_spinner_id);
        genderSpinner = findViewById(R.id.register_gender_spinner_id);
        fullNameEdt = findViewById(R.id.register_full_name_edt);
        dobEdt = findViewById(R.id.register_dob_edt);
        weightEdt = findViewById(R.id.register_weight_edt);
        emailEdt = findViewById(R.id.register_email_edt);
        mobileEdt = findViewById(R.id.register_mobile_edt);
        addressEdt = findViewById(R.id.register_address_edt);
        cityEdt = findViewById(R.id.register_city_edt);
        districtEdt = findViewById(R.id.register_district_edt);
        passEdt = findViewById(R.id.register_password_edt);
        confirmPassEdt = findViewById(R.id.register_confirm_password_edt);
        registerCheck = findViewById(R.id.register_terms_policy_checker);
        registerNowBtn = findViewById(R.id.register_register_now_btn_id);
        loginTxtBtn = findViewById(R.id.register_login_txt_btn_id);

        api = RetroClient.getClient().create(Api.class);

        Paper.init(this);
        loadingBar = new ProgressDialog(this);

        bloodGrpArray = getResources().getStringArray(R.array.blood_grp);
        genderArray = getResources().getStringArray(R.array.gender);

        bloodGrpAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, R.id.spinner_item_text_id,bloodGrpArray){

            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent).findViewById(R.id.spinner_item_text_id);

                ((TextView) v).setTextColor(getResources().getColor(R.color.purple_200));

                return v;

            }

            public View getDropDownView(int position, View convertView,ViewGroup parent) {

                View v = super.getDropDownView(position, convertView,parent).findViewById(R.id.spinner_item_text_id);

                ((TextView) v).setGravity(Gravity.CENTER);

                return v;

            }





        };

        bldGrpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView spinnerTxt = (TextView)view.findViewById(R.id.spinner_item_text_id);

                if(i==0){

                    spinnerTxt.setTextColor(view.getContext().getResources().getColor(R.color.purple_200));
                }else
                    spinnerTxt.setTextColor(view.getContext().getResources().getColor(R.color.black));


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bloodGrpAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        bldGrpSpinner.setAdapter(bloodGrpAdapter);

        genderAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, R.id.spinner_item_text_id,genderArray){

            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent).findViewById(R.id.spinner_item_text_id);

                ((TextView) v).setTextColor(getResources().getColor(R.color.purple_200));

                return v;

            }

            public View getDropDownView(int position, View convertView,ViewGroup parent) {

                View v = super.getDropDownView(position, convertView,parent).findViewById(R.id.spinner_item_text_id);

                ((TextView) v).setGravity(Gravity.CENTER);

                return v;

            }


        };
        genderAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        genderSpinner.setAdapter(genderAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView spinnerTxt = (TextView)view.findViewById(R.id.spinner_item_text_id);

                if(i==0){

                    spinnerTxt.setTextColor(view.getContext().getResources().getColor(R.color.purple_200));
                }else
                    spinnerTxt.setTextColor(view.getContext().getResources().getColor(R.color.black));


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

        dobEdt.setOnClickListener(v -> {
            dobMaker();
        });



        passEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (passEdt.getRight() - passEdt.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(eyeState == true){

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

        registerNowBtn.setOnClickListener(v -> {
            loadingBar.setTitle("Registering your Account....");
            loadingBar.setMessage("Plz wait, while we are registering you to our platform.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            validateFields();



        });


        loginTxtBtn.setOnClickListener(v -> {

            Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(loginIntent);

        });


    }

    private void dobMaker() {

        DatePicker datePicker = new DatePicker(this);
        int bdyear = datePicker.getYear();
        final int bdMonth = (datePicker.getMonth())+1;
        final int bdDay = datePicker.getDayOfMonth();

        dobPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                bYear = year;
                bMonth = month;
                bDay = dayOfMonth;

                dobString = new StringBuilder();
                dobString.append(dayOfMonth+"/");
                dobString.append((month+1)+"/");
                dobString.append(year);

                dob = dobString.toString();

                dobEdt.setText(dobString);
                dobEdt.setSelection(dobEdt.getText().length());


            }
        },bdyear,bdMonth,bdDay);
        dobPicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
        dobPicker.show();

    }


    private void validateFields(){

        fullName = fullNameEdt.getText().toString();
        dob = dobEdt.getText().toString();
        bloodGroup = bldGrpSpinner.getSelectedItem().toString();
        gender = genderSpinner.getSelectedItem().toString();
        email = emailEdt.getText().toString();
        mobile = mobileEdt.getText().toString();
        address = addressEdt.getText().toString();
        city = cityEdt.getText().toString();
        district = districtEdt.getText().toString();
        password = passEdt.getText().toString();
        confirmPass = confirmPassEdt.getText().toString();
        checked = registerCheck.isChecked();


        if(fullName.equals("")){
            fullNameEdt.setError("Enter your full name!");
            fullNameEdt.requestFocus();
            return;
        }else if(dob.equals("")){

            dobEdt.setError("Enter your Date of Birth!");
            fullNameEdt.requestFocus();
            return;

        }else if(bloodGroup.equals("--Select--")) {

            Toast.makeText(this, "Select blood group", Toast.LENGTH_SHORT).show();

        }else if(gender.equals("--Select--")) {

            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdt.setError("Enter your email with proper format");
            emailEdt.requestFocus();
            return;
        }else if(mobile.equals("") || mobile.length()<11){
            mobileEdt.setError("Enter mobile number of 11 digits");
            mobileEdt.requestFocus();
            return;
        }else if(address.equals("")){
            addressEdt.setError("Enter your detailed address");
            cityEdt.requestFocus();
            return;
        }else if(city.equals("")){
            cityEdt.setError("Enter your city/division");
            cityEdt.requestFocus();
            return;
        }else if(district.equals("")){
            districtEdt.setError("Enter your district");
            districtEdt.requestFocus();
            return;
        }else if(password.equals("") || password.length()<6){

            passEdt.setError("Enter password of 6 digit at least");
            passEdt.requestFocus();
            return;

        }else if(confirmPass.equals("") || confirmPass.length()<6 || !confirmPass.equals(password)){

            confirmPassEdt.setError("Password does not match!");
            confirmPassEdt.requestFocus();
            return;

        }else if(checked == false){

            Toast.makeText(RegisterActivity.this, "Check our policies checkbox", Toast.LENGTH_SHORT).show();

        } else{

            api.registerUser(fullName,dob,bloodGroup,gender,email,mobile,address,city,district,password,confirmPass)
                    .enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if(response.isSuccessful()){
                            loadingBar.dismiss();
                                if(response.body().getStatus().equals("success")){
                                    Toast.makeText(RegisterActivity.this,response.body().getStatus()+"\n Registration Successfully done.",Toast.LENGTH_SHORT).show();

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

                                    Intent homeIntent = new Intent(RegisterActivity.this,MainActivity.class);
                                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(homeIntent);

                                }else{
                                    loadingBar.dismiss();
                                    Toast.makeText(RegisterActivity.this,response.body().getStatus()+"\n Email or Mobile already exists!",Toast.LENGTH_SHORT).show();

                                }


                            }else{
                                loadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this,"Response not found!",Toast.LENGTH_SHORT).show();


                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            loadingBar.dismiss();
                            Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        }


    }


}
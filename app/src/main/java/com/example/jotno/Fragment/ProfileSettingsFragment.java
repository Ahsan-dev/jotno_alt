package com.example.jotno.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.example.jotno.Activity.LoginActivity;
import com.example.jotno.Activity.MainActivity;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingsFragment extends Fragment {

    private Spinner bldGrpSpinner, genderSpinner;
    private String[] bloodGrpArray, genderArray;
    private ArrayAdapter<String> bloodGrpAdapter, genderAdapter ;
    private EditText fullNameEdt, dobEdt,  emailEdt, mobileEdt, addressEdt, cityEdt, districtEdt;
    private String fullName, dob, bloodGroup, gender, email, mobile, address, city, district, image;
    private DatePickerDialog dobPicker;
    private StringBuilder dobString;
    private int bDay, bMonth, bYear;
    private CheckBox profile_settingsCheck;
    private Button profile_settingsNowBtn;
    private Api api;
    private ProgressDialog loadingBar;
    private  View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_profile_settings, container, false);


        bldGrpSpinner = view.findViewById(R.id.profile_settings_bld_grp_spinner_id);
        genderSpinner = view.findViewById(R.id.profile_settings_gender_spinner_id);
        fullNameEdt = view.findViewById(R.id.profile_settings_full_name_edt);
        dobEdt = view.findViewById(R.id.profile_settings_dob_edt);
        emailEdt = view.findViewById(R.id.profile_settings_email_edt);
        mobileEdt = view.findViewById(R.id.profile_settings_mobile_edt);
        addressEdt = view.findViewById(R.id.profile_settings_address_edt);
        cityEdt = view.findViewById(R.id.profile_settings_city_edt);
        districtEdt = view.findViewById(R.id.profile_settings_district_edt);
        profile_settingsNowBtn = view.findViewById(R.id.profile_settings_profile_settings_now_btn_id);





        api = RetroClient.getClient().create(Api.class);

        Paper.init(view.getContext());
        loadingBar = new ProgressDialog(view.getContext());


        fullNameEdt.setText(Paper.book().read(PermanentPatient.userNameString));
        dobEdt.setText(Paper.book().read(PermanentPatient.userDateOfBirthString));
        emailEdt.setText(Paper.book().read(PermanentPatient.userEmailString));
        mobileEdt.setText(Paper.book().read(PermanentPatient.userMobileString));
        addressEdt.setText(Paper.book().read(PermanentPatient.userAddressString));
        cityEdt.setText(Paper.book().read(PermanentPatient.userCityString));
        districtEdt.setText(Paper.book().read(PermanentPatient.userDistrictString));


        bloodGrpArray = getResources().getStringArray(R.array.blood_grp);
        genderArray = getResources().getStringArray(R.array.gender);

        bloodGrpAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item_layout, R.id.spinner_item_text_id,bloodGrpArray){

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
        bloodGrpAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        bldGrpSpinner.setAdapter(bloodGrpAdapter);

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

        genderAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item_layout, R.id.spinner_item_text_id,genderArray){

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



        genderSpinner.setSelection(genderAdapter.getPosition(Paper.book().read(PermanentPatient.userGenderString)));
        bldGrpSpinner.setSelection(bloodGrpAdapter.getPosition(Paper.book().read(PermanentPatient.userBloodGrpString)));

        dobEdt.setOnClickListener(v -> {
            dobMaker();
        });


        profile_settingsNowBtn.setOnClickListener(v -> {
            loadingBar.setTitle("Customising your Account....");
            loadingBar.setMessage("Plz wait, while we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            validateFields();



        });



        return view;

    }

    private void dobMaker() {

        DatePicker datePicker = new DatePicker(view.getContext());
        int bdyear = datePicker.getYear();
        final int bdMonth = (datePicker.getMonth())+1;
        final int bdDay = datePicker.getDayOfMonth();

        dobPicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
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


        if(fullName.equals("")){
            fullNameEdt.setError("Enter your full name!");
            fullNameEdt.requestFocus();
            return;
        }else if(dob.equals("")){

            dobEdt.setError("Enter your Date of Birth!");
            fullNameEdt.requestFocus();
            return;

        }else if(bloodGroup.equals("--Select--")) {

            Toast.makeText(view.getContext(), "Select blood group", Toast.LENGTH_SHORT).show();

        }else if(gender.equals("--Select--")) {

            Toast.makeText(view.getContext(), "Select Gender", Toast.LENGTH_SHORT).show();

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
        } else{

            api.customiseProfile(fullName,dob,bloodGroup,gender,email,mobile,address,city,district,image)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            if(response.isSuccessful()){
//                                loadingBar.dismiss();
//                                if(response.body().getStatus().equals("success")){
//                                    Toast.makeText(view.getContext(),response.body().getStatus()+"\n Profile customization done.",Toast.LENGTH_SHORT).show();
//
//                                    Paper.book().write(PermanentPatient.userEmailString,response.body().getBody().getEmail());
//
//
//                                }else{
//                                    loadingBar.dismiss();
//                                    Toast.makeText(view.getContext(),response.body().getStatus()+"\n Something wrong! Check again.",Toast.LENGTH_SHORT).show();
//
//                                }
//
//
//                            }else{
//                                loadingBar.dismiss();
//                                Toast.makeText(view.getContext(),"Response not found!",Toast.LENGTH_SHORT).show();
//
//
//                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            loadingBar.dismiss();
                            Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        }


    }

}
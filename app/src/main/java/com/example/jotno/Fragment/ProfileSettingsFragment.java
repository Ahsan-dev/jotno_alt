package com.example.jotno.Fragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Activity.LoginActivity;
import com.example.jotno.Activity.MainActivity;
import com.example.jotno.Models.CustomiseEventModel;
import com.example.jotno.Models.EventModel;
import com.example.jotno.NetworkCall;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String fullName, dob, bloodGroup, gender, email, mobile, address, city, district, imagePath = "";
    private DatePickerDialog dobPicker;
    private StringBuilder dobString;
    private int bDay, bMonth, bYear;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 102;
    private Button profile_settingsNowBtn, uploadImgBtn;
    private ImageView uploadImageView;
    private TextView uploadImgNameTxt;
    private Api api;
    private ProgressDialog loadingBar;
    private  View view;
    private int patientId = -1;


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
        uploadImgBtn = view.findViewById(R.id.profile_settings_upload_image_btn);
        uploadImageView = view.findViewById(R.id.profile_settings_upload_image_view);
        uploadImgNameTxt = view.findViewById(R.id.profile_settings_upload_image_name_txt);




        api = RetroClient.getClient().create(Api.class);

        Paper.init(view.getContext());
        loadingBar = new ProgressDialog(view.getContext());

        patientId = Paper.book().read(PermanentPatient.userIdString);



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



        loadAllFields();


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

        uploadImgBtn.setOnClickListener(view1 -> {


            if(checkAndRequestPermissions(view1.getContext())){
                selectImage(view1.getContext());
            }

        });



        return view;

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CustomiseEventModel event) throws ClassNotFoundException {


        if (event.getEventTag().equals("customise_response")) {
            loadingBar.dismiss();
            Toast.makeText(view.getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
            Paper.book().write(PermanentPatient.userNameString,event.getPatient().getName());
            Paper.book().write(PermanentPatient.userDateOfBirthString,event.getPatient().getDateOfBirth());
            Paper.book().write(PermanentPatient.userBloodGrpString,event.getPatient().getBloodGroup());
            Paper.book().write(PermanentPatient.userGenderString,event.getPatient().getGender());
            Paper.book().write(PermanentPatient.userEmailString,event.getPatient().getEmail());
            Paper.book().write(PermanentPatient.userMobileString,event.getPatient().getPhone());
            Paper.book().write(PermanentPatient.userCityString,event.getPatient().getCity());
            Paper.book().write(PermanentPatient.userDistrictString,event.getPatient().getDistrict());
            Paper.book().write(PermanentPatient.userAddressString,event.getPatient().getAddress());
            Paper.book().write(PermanentPatient.userImageString,event.getPatient().getImage());

            loadAllFields();

            EventBus.getDefault().post(new EventModel("nameSendToMain",event.getPatient().getName()));
            EventBus.getDefault().post(new EventModel("imageSendToMain",event.getPatient().getImage()));



        }
        if (event.getEventTag().equals("customise_response_null")){
            loadingBar.dismiss();
            Toast.makeText(view.getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();

        }
        if (event.getEventTag().equals("customise_response_error")){
            loadingBar.dismiss();
            Toast.makeText(view.getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    public boolean checkAndRequestPermissions(final Context context) {
        int WExtstorePermission = checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = checkSelfPermission(context, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(view.getContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view.getContext(), "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view.getContext(), "FlagUp Requires Access to Your Storage.", Toast.LENGTH_SHORT).show();
                } else {
                    selectImage(view.getContext());
                }
                break;
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(view.getContext(),
                        "com.example.jotno.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 0);
            }
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        imagePath = image.getAbsolutePath();
        //addReportResourceEdt.setText(imagePath);
        Log.d("camera_image",imagePath);
        return image;
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {

                    dispatchTakePictureIntent();

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK ) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        uploadImgNameTxt.setText(imagePath.substring(imagePath.lastIndexOf("/")+1));
                        uploadImageView.setImageURI(data.getData());


                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        imagePath = getPath(selectedImage);
                        uploadImgNameTxt.setText(imagePath.substring(imagePath.lastIndexOf("/")+1));
                        uploadImageView.setImageURI(selectedImage);


                    }
                    break;
            }
        }
    }


    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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

            NetworkCall.profileSettingsUpload(patientId,fullName,dob,bloodGroup,gender,email,mobile,city,district,address,imagePath);

        }


    }


    private void loadAllFields(){

        fullNameEdt.setText(Paper.book().read(PermanentPatient.userNameString));
        dobEdt.setText(Paper.book().read(PermanentPatient.userDateOfBirthString));
        emailEdt.setText(Paper.book().read(PermanentPatient.userEmailString));
        mobileEdt.setText(Paper.book().read(PermanentPatient.userMobileString));
        addressEdt.setText(Paper.book().read(PermanentPatient.userAddressString));
        cityEdt.setText(Paper.book().read(PermanentPatient.userCityString));
        districtEdt.setText(Paper.book().read(PermanentPatient.userDistrictString));
        Picasso.get().load((String) Paper.book().read(PermanentPatient.userImageString)).placeholder(R.drawable.person_image).into(uploadImageView);
        uploadImgNameTxt.setText(Paper.book().read(PermanentPatient.userImageString));
        genderSpinner.setSelection(genderAdapter.getPosition(Paper.book().read(PermanentPatient.userGenderString)));
        bldGrpSpinner.setSelection(bloodGrpAdapter.getPosition(Paper.book().read(PermanentPatient.userBloodGrpString)));

    }

}
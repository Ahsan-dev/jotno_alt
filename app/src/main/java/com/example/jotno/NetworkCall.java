package com.example.jotno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.jotno.Models.CustomiseEventModel;
import com.example.jotno.Models.CustomiseProfileResponse;
import com.example.jotno.Models.EventModel;
import com.example.jotno.Models.ImageSenderInfo;
import com.example.jotno.Models.ResponseModel;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    public static void profileSettingsUpload(int id, String fullName, String dob, String bloodGrp, String gend, String pEmail, String pMobile, String pCity, String pDistrict, String pAddress, String filePath) {

        Api apiInterface = RetroClient.getClient().create(Api.class);
        Logger.addLogAdapter(new AndroidLogAdapter());
        File file;
        MultipartBody.Part body;

        if(filePath.equals("")){
            body = null;
        }else{

            file = new File(filePath);
            //create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file); //allow image and any other file

            // MultipartBody.Part is used to send also the actual file name
             body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        }

//
//        Gson gson = new Gson();
//        String patientData = gson.toJson(imageSenderInfo);

        RequestBody patient_id = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(id));
        RequestBody full_name = RequestBody.create(okhttp3.MultipartBody.FORM, fullName);
        RequestBody date_of_birth = RequestBody.create(okhttp3.MultipartBody.FORM, dob);
        RequestBody blood_group = RequestBody.create(okhttp3.MultipartBody.FORM, bloodGrp);
        RequestBody gender = RequestBody.create(okhttp3.MultipartBody.FORM, gend);
        RequestBody email = RequestBody.create(okhttp3.MultipartBody.FORM, pEmail);
        RequestBody mobile = RequestBody.create(okhttp3.MultipartBody.FORM, pMobile);
        RequestBody city = RequestBody.create(okhttp3.MultipartBody.FORM, pCity);
        RequestBody district = RequestBody.create(okhttp3.MultipartBody.FORM, pDistrict);
        RequestBody address = RequestBody.create(okhttp3.MultipartBody.FORM, pAddress);

//        Logger.d("description",description);
//        Log.d("description",description.toString());

        // finally, execute the request
        Call<CustomiseProfileResponse> call = apiInterface.updateProfile(patient_id, full_name, date_of_birth, blood_group, gender, email, mobile, city, district, address, body);
        call.enqueue(new Callback<CustomiseProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<CustomiseProfileResponse> call, @NonNull Response<CustomiseProfileResponse> response) {
                Logger.d("Response: " + response);


                if(response.isSuccessful()){
                    EventBus.getDefault().post(new CustomiseEventModel("customise_response", response.body().getBody().getMessage(),response.body().getBody().getPatient()));

                    Logger.d("Response code " + response.code() +
                            " Response Message: " + response.body().toString());
                } else
                    EventBus.getDefault().post(new EventModel("customise_response_null", "Response is NULL"));
            }

            @Override
            public void onFailure(@NonNull Call<CustomiseProfileResponse> call, @NonNull Throwable t) {
                EventBus.getDefault().post(new EventModel("customise_response_error", t.getMessage()));
            }
        });
    }

    public static void fileUpload2(String filePath, String imageSenderInfo,int id) {

        Api apiInterface = RetroClient.getClient().create(Api.class);
        Logger.addLogAdapter(new AndroidLogAdapter());

        File file = new File(filePath);
        //create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file); //allow image and any other file

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Gson gson = new Gson();
        String patientData = gson.toJson(imageSenderInfo);

        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, imageSenderInfo);
        RequestBody prescription_id = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(id));
        Logger.d("description",description);
        Log.d("description",description.toString());

        // finally, execute the request
        Call<ResponseBody> call = apiInterface.uploadReport2(description, prescription_id,  body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Logger.d("Response: " + response);



                if(response.isSuccessful()){
                    try {
                        EventBus.getDefault().post(new EventModel("response", response.body().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Logger.d("Response code " + response.code() +
                                " Response Message: " + response.body().string()) ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    EventBus.getDefault().post(new EventModel("response", "ResponseModel is NULL"));
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                EventBus.getDefault().post(new EventModel("response", t.getLocalizedMessage()));
            }
        });
    }

    public static void fileUpload3(String filePath, String imageSenderInfo,  int id) {

        Api apiInterface = RetroClient.getClient().create(Api.class);
        Logger.addLogAdapter(new AndroidLogAdapter());

        File file = new File(filePath);
        //create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file); //allow image and any other file

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Gson gson = new Gson();
        String patientData = gson.toJson(imageSenderInfo);

        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, imageSenderInfo);
        RequestBody report_id = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(id));
        Logger.d("description",description);
        Log.d("description",description.toString());

        // finally, execute the request
        Call<ResponseBody> call = apiInterface.uploadReport3(description, report_id,  body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Logger.d("Response: " + response);

                if(response.isSuccessful()){
                    try {
                        EventBus.getDefault().post(new EventModel("response", response.body().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Logger.d("Response code " + response.code() +
                                " Response Message: " + response.body().string()) ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    EventBus.getDefault().post(new EventModel("response", "ResponseModel is NULL"));
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                EventBus.getDefault().post(new EventModel("response", t.getLocalizedMessage()));
            }
        });
    }


    public static void deleteReport(int id , Context context){

        Api apiInterface = RetroClient.getClient().create(Api.class);
        Logger.addLogAdapter(new AndroidLogAdapter());

        apiInterface.deleteReport(id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){

                            try {
                                EventBus.getDefault().post(new EventModel("delete_response", response.body().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                Logger.d("Response code " + response.code() +
                                        " Response Message: " + response.body().string()) ;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else
                            EventBus.getDefault().post(new EventModel("delete_response", "Response is NULL"));

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        EventBus.getDefault().post(new EventModel("delete_response", t.getLocalizedMessage()));

                    }
                });

    }

}

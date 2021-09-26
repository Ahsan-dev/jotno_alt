package com.example.jotno;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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

//    public static void fileUpload(String filePath, String imageSenderInfo) {
//
//        Api apiInterface = RetroClient.getClient().create(Api.class);
//        Logger.addLogAdapter(new AndroidLogAdapter());
//
//        File file = new File(filePath);
//        //create RequestBody instance from file
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file); //allow image and any other file
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//
//        Gson gson = new Gson();
//        String patientData = gson.toJson(imageSenderInfo);
//
//        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, imageSenderInfo);
//        Logger.d("description",description);
//        Log.d("description",description.toString());
//
//        // finally, execute the request
//        Call<ResponseModel> call = apiInterface.uploadReport2(description, body);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
//                Logger.d("Response: " + response);
//
//                ResponseModel responseModel = response.body();
//
//                if(responseModel != null){
//                    EventBus.getDefault().post(new EventModel("response", responseModel.getMessage()));
//                    Logger.d("Response code " + response.code() +
//                            " Response Message: " + responseModel.getMessage());
//                } else
//                    EventBus.getDefault().post(new EventModel("response", "ResponseModel is NULL"));
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
//                EventBus.getDefault().post(new EventModel("response", t.getMessage()));
//            }
//        });
//    }

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

    public static void fileUpload3(String filePath, String imageSenderInfo,int id) {

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


    public static void deleteReport(int id){

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

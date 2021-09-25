package com.example.jotno.Retrofit;



import com.example.jotno.Models.AppointmentResponse;
import com.example.jotno.Models.GetAppointmentResponse;
import com.example.jotno.Models.LoginUser;
import com.example.jotno.Models.PrescriptionReportResponse;
import com.example.jotno.Models.PrescriptionResponse;
import com.example.jotno.Models.RegisterResponse;
import com.example.jotno.Models.ResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> registerUser(

            @Field("name") String name,
            @Field("date_of_birth") String date_of_birth,
            @Field("blood_group") String blood_group,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("city") String city,
            @Field("district") String district,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation

    );

    @FormUrlEncoded
    @POST("profile_settings")
    Call<ResponseBody> customiseProfile(

            @Field("name") String name,
            @Field("date_of_birth") String date_of_birth,
            @Field("blood_group") String blood_group,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("city") String city,
            @Field("district") String district,
            @Field("image") String image


    );


    @POST("login")
    Call<RegisterResponse> loginUser(@Body LoginUser userCredentials);

    @FormUrlEncoded
    @POST("appoinment-get")
    Call<GetAppointmentResponse> getAppointment(

            @Field("id") int id

    );


    @GET("appoinment-today")
    Call<AppointmentResponse> getTodayAppointmentList(

            @Query("id") int id

    );



    @GET("appoinment-all")
    Call<AppointmentResponse> getAllAppointmentList(

            @Query("id") int id

    );


    @GET("prescription-get")
    Call<PrescriptionResponse> getPrescriptionData(

            @Query("id") int id

    );


    @GET("prescription-report")
    Call<PrescriptionReportResponse> getPrescriptionReports(

            @Query("id") int id

    );

//    @Multipart
//    @POST("report-create")
//    Call<ResponseModel> uploadReport(
//
//            @Part("sender") RequestBody description,
//            @Part MultipartBody.Part file);
//
//
//    );



//
//
//    @GET("getpostfeed")
//    Call<List<GetPostFeed>> getPostFeed();
//
//    @POST("writepost")
//    Call<ResponseBody> writePost(@Body WritePost postRequired);
//
//    @FormUrlEncoded
//    @POST("getactivity")
//    Call<List<ActivityLogModel>> getActivity(
//
//            @Field("id") int id
//
//    );
//
//
//    @FormUrlEncoded
//    @POST("getmyposts")
//    Call<List<MyPostRequestsModel>> getMyPosts(
//
//            @Field("id") int id
//
//    );
//
//    @FormUrlEncoded
//    @POST("getmyacceptors")
//    Call<List<PostResponseModel>> getMyPostAcceptors(
//
//            @Field("post_id") int post_id
//
//    );
//
//    @FormUrlEncoded
//    @POST("donationcomplete")
//    Call<ResponseBody> donationCompletePost(
//            @Field("post_id") int post_id,
//            @Field("status") String status
//            );
//
//    @FormUrlEncoded
//    @POST("getdonationhistory")
//    Call<List<HistoryModel>> getDonationHistory(
//
//            @Field("user_id") int user_id
//
//    );
//
//    @FormUrlEncoded
//    @POST("getservicetakenhistory")
//    Call<List<HistoryModel>> getServiceTakenHistory(
//
//            @Field("user_id") int user_id
//
//    );
//
//    @GET("postsadmintosign")
//    Call<List<AdminDateAssignModel>> postsAdminToSign();
//
//    @FormUrlEncoded
//    @POST("admindateassign")
//    Call<ResponseBody> adminDateAssign(
//            @Field("donate_id") int donate_id,
//            @Field("date") String date
//    );
//
//    @FormUrlEncoded
//    @POST("donateconfirm")
//    Call<ResponseBody> donateConfirm(
//            @Field("donate_id") int donate_id,
//            @Field("status") String status
//    );
//
//    @FormUrlEncoded
//    @POST("acceptpost")
//    Call<ResponseBody> acceptPost(
//            @Field("post_id") int post_id,
//            @Field("user_id") int user_id,
//            @Field("status") String status,
//            @Field("admin") String admin
//
//    );




}

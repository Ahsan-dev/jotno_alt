package com.example.jotno.Retrofit;



import com.example.jotno.Models.AllMedicines;
import com.example.jotno.Models.AppointmentResponse;
import com.example.jotno.Models.BannerResponse;
import com.example.jotno.Models.ContentResponse;
import com.example.jotno.Models.CustomiseProfileResponse;
import com.example.jotno.Models.GetAppointmentResponse;
import com.example.jotno.Models.LoginResponse;
import com.example.jotno.Models.LoginUser;
import com.example.jotno.Models.PrescriptionReportResponse;
import com.example.jotno.Models.PrescriptionResponse;
import com.example.jotno.Models.Prescriptions;
import com.example.jotno.Models.RegisterResponse;
import com.example.jotno.Models.ResponseModel;
import com.example.jotno.Models.Tests;

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

    @GET("content")
    Call<ContentResponse> getAllContents();


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
    Call<LoginResponse> loginUser(@Body LoginUser userCredentials);

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

    @GET("all-test")
    Call<Tests> getAllTests(

            @Query("id") int id

    );

    @GET("banner")
    Call<BannerResponse> getAllBanners();


    @GET("prescription-get")
    Call<PrescriptionResponse> getPrescriptionData(

            @Query("id") int id

    );

    @GET("prescription-list")
    Call<Prescriptions> getAllPrescriptions(

            @Query("id") int id

    );

    @GET("medicine-all")
    Call<AllMedicines> getAllMedicines(

            @Query("patient_id") int patient_id

    );


    @GET("prescription-report")
    Call<PrescriptionReportResponse> getPrescriptionReports(

            @Query("id") int id

    );



    @Multipart
    @POST("patient-profile-update")
    Call<CustomiseProfileResponse> updateProfile(

            @Part("patient_id") RequestBody patient_id,
            @Part("full_name") RequestBody full_name,
            @Part("date_of_birth") RequestBody date_of_birth,
            @Part("blood_group") RequestBody blood_group,
            @Part("gender") RequestBody gender,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile,
            @Part("city") RequestBody city,
            @Part("district") RequestBody district,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part file


    );


    @Multipart
    @POST("report-create")
    Call<ResponseBody> uploadReport2(

            @Part("description") RequestBody description,
            @Part("prescription_id") RequestBody prescription_id,
            @Part MultipartBody.Part file


    );

    @Multipart
    @POST("report-edit")
    Call<ResponseBody> uploadReport3(

            @Part("description") RequestBody description,
            @Part("report_id") RequestBody report_id,
            @Part MultipartBody.Part file


    );

    @FormUrlEncoded
    @POST("report-delete")
    Call<ResponseBody> deleteReport(

            @Field("report_id") int report_id

    );

    @FormUrlEncoded
    @POST("password-reset")
    Call<ResponseBody> resetPassword(

            @Field("id") int id,
            @Field("old_password") String old_password,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation

    );


    @FormUrlEncoded
    @POST("patient-forget-password")
    Call<ResponseBody> forgetPassword(

            @Field("email") String email

    );






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

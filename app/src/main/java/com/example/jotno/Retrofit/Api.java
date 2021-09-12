package com.example.jotno.Retrofit;



import com.example.jotno.Models.LoginUser;
import com.example.jotno.Models.RegisterResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> registerUser(

            @Field("name") String name,
            @Field("date_of_birth") String date_of_birth,
            @Field("blood_group") String blood_group,
            @Field("weight") String weight,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("city") String city,
            @Field("district") String district,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation

    );


    @POST("login")
    Call<ResponseBody> loginUser(@Body LoginUser userCredentials);
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

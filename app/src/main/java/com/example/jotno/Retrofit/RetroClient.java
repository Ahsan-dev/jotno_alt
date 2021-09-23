package com.example.jotno.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = "https://jotno.beautyclinicbd.com/api/";

    private static RetroClient mInstance;
    private static Retrofit retrofit;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();



    private RetroClient(){

    }



    public static Retrofit getClient(){


        if(retrofit==null){

            synchronized (RetroClient.class){
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS).build();

                if(retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                }
            }
        }
        return retrofit;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }


}

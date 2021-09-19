package com.example.jotno.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                if(retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
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

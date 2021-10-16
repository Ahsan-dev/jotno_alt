package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Models.ContentResponse;
import com.example.jotno.PaperDB.PermanentApp;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    Animation blink, bounce, fadeIn, fadeOut, moveLeft, moveRight, rotate, sequential, slideDown, slideUp, together, zoomIn, zoomOut, moveUp;
    private ImageView logoImg;
    private TextView sloganTxt, developerTxt;
    private RelativeLayout fullRelative;
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);


        Paper.init(this);
        api = RetroClient.getClient().create(Api.class);

        logoImg = findViewById(R.id.splash_screen_jotno_logo);
        sloganTxt = findViewById(R.id.splash_screen_jonto_slogan);
        developerTxt = findViewById(R.id.splash_screen_developer);

        fullRelative = findViewById(R.id.splash_screen_full_relative);
//


        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        bounce = AnimationUtils.loadAnimation(this,R.anim.bounce);
        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        moveLeft = AnimationUtils.loadAnimation(this,R.anim.move_left);
        moveRight = AnimationUtils.loadAnimation(this,R.anim.move_right);
        moveUp = AnimationUtils.loadAnimation(this,R.anim.move_up);
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        sequential = AnimationUtils.loadAnimation(this,R.anim.sequential);
        slideDown = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        together = AnimationUtils.loadAnimation(this,R.anim.together);
        zoomIn = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        zoomOut = AnimationUtils.loadAnimation(this,R.anim.zoom_out);


        api.getAllContents()
                .enqueue(new Callback<ContentResponse>() {
                    @Override
                    public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
                        if(response.isSuccessful()){

                            Paper.book().write(PermanentApp.logoUrlString,response.body().getBody().getLogo());
                            Paper.book().write(PermanentApp.titleString,response.body().getBody().getTitle());
                            Paper.book().write(PermanentApp.aboutUsTitleString,response.body().getBody().getAboutTitle());
                            Paper.book().write(PermanentApp.aboutUsDetailString,response.body().getBody().getAboutDescription());
                            Paper.book().write(PermanentApp.aboutUsSatisfiedPatientsString,response.body().getBody().getSatisfiedPatient());
                            Paper.book().write(PermanentApp.aboutUsPatientPerYearString,response.body().getBody().getPatientPerYear());
                            Paper.book().write(PermanentApp.aboutUsImageUrlString,response.body().getBody().getAboutImage());
                            Paper.book().write(PermanentApp.contactUsAddressString,response.body().getBody().getAddress());
                            Paper.book().write(PermanentApp.contactUsEmailString,response.body().getBody().getEmail());
                            Paper.book().write(PermanentApp.contactUsMobileString,response.body().getBody().getPhone());
                            Paper.book().write(PermanentApp.contactUsFacebookUrlString,response.body().getBody().getFacebookUrl());
                            Paper.book().write(PermanentApp.contactUsTwitterUrlString,response.body().getBody().getTwitterUrl());
                            Paper.book().write(PermanentApp.contactUsLinkedinString,response.body().getBody().getLinkedinUrl());
                            String imageUrl = Paper.book().read(PermanentApp.logoUrlString);
                            Picasso.get().load(imageUrl).placeholder(R.drawable.jotno_logo).into(logoImg);
                            sloganTxt.setText(Paper.book().read(PermanentApp.titleString));

                        }else{

                            Toast.makeText(SplashScreenActivity.this, "Response nul!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ContentResponse> call, Throwable t) {

                        Toast.makeText(SplashScreenActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        logoImg.startAnimation(fadeIn);
        sloganTxt.startAnimation(fadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fullRelative.startAnimation(fadeOut);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent welcomeIntent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(welcomeIntent);

                    }
                },500);

            }
        },1500);


    }
}
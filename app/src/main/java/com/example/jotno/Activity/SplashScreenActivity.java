package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jotno.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation blink, bounce, fadeIn, fadeOut, moveLeft, moveRight, rotate, sequential, slideDown, slideUp, together, zoomIn, zoomOut, moveUp;
    private ImageView logoImg;
    private TextView sloganTxt, developerTxt;
    private RelativeLayout fullRelative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);



        logoImg = findViewById(R.id.splash_screen_jotno_logo);
        sloganTxt = findViewById(R.id.splash_screen_jonto_slogan);
        developerTxt = findViewById(R.id.splash_screen_developer);

        fullRelative = findViewById(R.id.splash_screen_full_relative);

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
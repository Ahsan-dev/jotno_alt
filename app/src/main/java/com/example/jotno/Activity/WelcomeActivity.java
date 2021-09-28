package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;


import com.example.jotno.Models.SliderItem;
import com.example.jotno.PaperDB.Prevalent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private List<SliderItem> imageList;
    private SliderView sliderView;
    private FloatingActionButton nextBtn;
    private Button registerBtn;
    private TextView loginTxtbtn;
    private ProgressDialog loadingBar;
    private Api api;
    private String email, password;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        api = RetroClient.getClient().create(Api.class);

        loadingBar = new ProgressDialog(this);
        Paper.init(this);

//        imageList = new ArrayList<>();
//
//        imageList.add(new SliderItem(R.drawable.dummy));
//        imageList.add(new SliderItem(R.drawable.dummy1));
//        imageList.add(new SliderItem(R.drawable.doctor));
//
//
//        WelcomeSliderAdapter adapter = new WelcomeSliderAdapter(this,imageList);
//
//
//
//        //sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        //sliderView.setIndicatorSelectedColor(Color.BLACK);
//        //sliderView.setIndicatorUnselectedColor(Color.GRAY);
//        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
//        sliderView.startAutoCycle();
//
//        sliderView.setSliderAdapter(adapter);
//
//        nextBtn.setOnClickListener(v -> {
//
//            Intent loginIntent = new Intent(WelcomeActivity.this,LoginActivity.class);
//            startActivity(loginIntent);
//
//        });


        registerBtn = findViewById(R.id.welcome_register_now_btn_id);
        loginTxtbtn = findViewById(R.id.welcome_login_txt_btn_id);

        registerBtn.setOnClickListener(v -> {

            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);

        });

        loginTxtbtn.setOnClickListener(v -> {

            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);


        });

        email = Paper.book().read(Prevalent.userEmailKey);
        


        if(email != ""){
            if(!TextUtils.isEmpty(email)){
                allowDirectAccessToLogIn(email);

                //loadingBar.setTitle("Already logged in...");
                loadingBar.setMessage("Loading. Wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

            }
        }




    }

    private void allowDirectAccessToLogIn(String email) {


//        LoginUser user = new LoginUser();
//        user.setEmail(email);
//        user.setPassword(password);
//        Call<RegisterResponse> loginCall = api.loginUser(user);
//
//        loginCall.enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//
//
//                if(response.isSuccessful()){
//                    if(!response.body().toString().equals("failed")){
//                        loadingBar.dismiss();
//                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }else{
//                        loadingBar.dismiss();
//                        Toast.makeText(WelcomeActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else {
//                    loadingBar.dismiss();
//                    Toast.makeText(getApplicationContext(),"Response is null !!",Toast.LENGTH_LONG).show();
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                loadingBar.dismiss();
//                Toast.makeText(WelcomeActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            loadingBar.dismiss();
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (loadingBar != null)
        {
            loadingBar.dismiss(); loadingBar = null;
        }
    }
}


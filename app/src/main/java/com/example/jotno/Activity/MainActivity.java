package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import io.paperdb.Paper;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Fragment.ChangePassFragment;
import com.example.jotno.Fragment.ContuctUsFragment;
import com.example.jotno.Fragment.BMICalculatorFragment;
import com.example.jotno.Fragment.DashboardFragment;
import com.example.jotno.Fragment.ProfileSettingsFragment;
import com.example.jotno.Models.CustomiseEventModel;
import com.example.jotno.Models.EventModel;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private NavigationView drawerNavView;
    private Fragment fragment;
    private DrawerLayout drawer;
    private ImageView drawerBtn;
    private Toolbar toolbar;
    private TextView toolTitle;
    private TextView drawerPatientNameTxt;
    private ImageView drawerPatientImageView;
    private ImageButton toolCallusBtn;
    private RelativeLayout toolCallBtnBack, toolCallBtnBackBack;
    private String phone = "01715050507";
    private Dialog dialog, exitDialog;
    private String imageUrl;
    public static Context contextOfApplication;


    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextOfApplication = getApplicationContext();

        Paper.init(this);
        dialog = new Dialog(this);
        exitDialog = new Dialog(this);

        drawerBtn = findViewById(R.id.drawer_btn);
        toolbar = findViewById(R.id.main_toolbar_layout);
        toolTitle = findViewById(R.id.toolbar_title_txt);
        toolCallusBtn = findViewById(R.id.toolbar_call_btn);
        toolCallBtnBack = findViewById(R.id.tool_call_btn_back);
        toolCallBtnBackBack = findViewById(R.id.tool_call_btn_back_back);
        toolTitle.setHint("Dashboard");
        setSupportActionBar(toolbar);


        if(toolTitle.getHint().equals("Dashboard")){

            toolTitle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    toolTitle.setHintTextColor(getResources().getColor(R.color.light_red));

                    return false;
                }
            });
            toolTitle.setOnClickListener(view -> {

                startActivity(new Intent(MainActivity.this,MainActivity.class));

            });

        }

        drawerNavView = findViewById(R.id.drawer_nav_view_id);
        drawerNavView.bringToFront();
        drawerNavView.setCheckedItem(R.id.drawer_nav_dashboard);
        drawerNavView.setItemIconTintList(null);

        View header = drawerNavView.getHeaderView(0);
        drawerPatientNameTxt = (TextView) header.findViewById(R.id.nav_header_user_name_id);
        drawerPatientImageView = header.findViewById(R.id.nav_header_pro_pic_img);
        drawerPatientNameTxt.setText(Paper.book().read(PermanentPatient.userNameString));
        imageUrl = Paper.book().read(PermanentPatient.userImageString);

        //Log.d("imageUrl",Paper.book().read(PermanentPatient.userImageString));

         Picasso.get().load(imageUrl).placeholder(R.drawable.rehi).into(drawerPatientImageView);

        drawerBtn.setOnClickListener(v -> {
                drawer = findViewById(R.id.drawer_layout_id);
                if(!drawer.isDrawerOpen(Gravity.LEFT)) drawer.openDrawer(Gravity.LEFT);
                else drawer.closeDrawer(Gravity.RIGHT);

        });

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        toolCallBtnBack.startAnimation(animation);
        toolCallBtnBackBack.startAnimation(animation);

        toolCallusBtn.setOnClickListener(view -> {

            view.clearAnimation();

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
            }else{
                String s = "tel:+88"+phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }


        });

        TooltipCompat.setTooltipText(toolCallusBtn, "Contact Us");

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,new DashboardFragment()).commit();

        }

        drawerNavView.setNavigationItemSelectedListener(item -> {



            if(item.getItemId()==R.id.drawer_nav_dashboard){
                toolTitle.setHint("Dashboard");
                fragment = null;
                fragment = new DashboardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_medicines){
                Intent medicineIntent = new Intent(MainActivity.this, MedicinesActivity.class);
                startActivity(medicineIntent);

            }

            if(item.getItemId()==R.id.drawer_nav_profile_settings){
                toolTitle.setHint("Settings");
                fragment = null;
                fragment = new ProfileSettingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_change_password){
                toolTitle.setHint("Password");
                fragment = null;
                fragment = new ChangePassFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_bmi_calculator){

                toolTitle.setHint("BMI");
                fragment = null;
                fragment = new BMICalculatorFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }


            if(item.getItemId()==R.id.drawer_nav_blog){
                toolTitle.setHint("Blog");
                Toast.makeText(this, "Blog fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);


            }

            if(item.getItemId()==R.id.drawer_nav_contact_us){

                toolTitle.setHint("Contact");
                fragment = null;
                fragment = new ContuctUsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_about_us){
                toolTitle.setHint("About");
                Toast.makeText(this, "About us fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }
            if(item.getItemId()==R.id.drawer_nav_terms_and_conditions){

                toolTitle.setHint("Terms & Conditions");
                Toast.makeText(this, "Terms and Conditions fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_policies){

                toolTitle.setHint("Policies");
                Toast.makeText(this, "Our Policies fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_logout){

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView message = (TextView) dialog.findViewById(R.id.alertDialogMessageId);
                message.setText("Do you want to logout?\nIf you logout you will not be eligible for our services!");

                TextView yesBtn = dialog.findViewById(R.id.alert_positive_btn_id);
                TextView noBtn = dialog.findViewById(R.id.alert_negative_btn_id);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Paper.book().destroy();
                        Intent welcomeIntent = new Intent(v.getContext(),WelcomeActivity.class);
                        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(welcomeIntent);

                    }
                });
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

            return true;


        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {

        if(event.isTagMatchWith("nameSendToMain")){

            drawerPatientNameTxt.setText(event.getMessage());

        }

        if(event.isTagMatchWith("imageSendToMain")){

            Picasso.get().load(event.getMessage()).placeholder(R.drawable.rehi).into(drawerPatientImageView);
            //startActivity(new Intent(MainActivity.this,MainActivity.class));

        }

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Override
    public void onBackPressed() {

        exitDialog.setCancelable(false);
        exitDialog.setContentView(R.layout.dialog_layout);
        exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView message = (TextView) exitDialog.findViewById(R.id.alertDialogMessageId);
        message.setText("Do you want to exit?");

        TextView yesBtn = exitDialog.findViewById(R.id.alert_positive_btn_id);
        TextView noBtn = exitDialog.findViewById(R.id.alert_negative_btn_id);



        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAndRemoveTask();

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exitDialog.cancel();

            }
        });

        exitDialog.show();



    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( exitDialog!=null && exitDialog.isShowing() ){
            exitDialog.dismiss();
        }
    }




}
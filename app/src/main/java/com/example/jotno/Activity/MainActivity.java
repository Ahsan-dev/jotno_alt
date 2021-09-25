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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
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
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private NavigationView drawerNavView;
    private Fragment fragment;
    private DrawerLayout drawer;
    private ImageView drawerBtn;
    private Toolbar toolbar;
    private TextView toolTitle;
    private TextView drawerPatientNameTxt;
    private ImageButton toolCallusBtn;
    private RelativeLayout toolCallBtnBack, toolCallBtnBackBack;
    private String phone = "01715050507";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        drawerBtn = findViewById(R.id.drawer_btn);
        toolbar = findViewById(R.id.main_toolbar_layout);
        toolTitle = findViewById(R.id.toolbar_title_txt);
        toolCallusBtn = findViewById(R.id.toolbar_call_btn);
        toolCallBtnBack = findViewById(R.id.tool_call_btn_back);
        toolCallBtnBackBack = findViewById(R.id.tool_call_btn_back_back);
        toolTitle.setText("Dashboard");
        setSupportActionBar(toolbar);

        drawerNavView = findViewById(R.id.drawer_nav_view_id);
        drawerNavView.bringToFront();
        drawerNavView.setCheckedItem(R.id.drawer_nav_dashboard);
        drawerNavView.setItemIconTintList(null);

        View header = drawerNavView.getHeaderView(0);
        drawerPatientNameTxt = (TextView) header.findViewById(R.id.nav_header_user_name_id);
        drawerPatientNameTxt.setText(Paper.book().read(PermanentPatient.userNameString));

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
                toolTitle.setText("Dashboard");
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
                toolTitle.setText("Settings");
                fragment = null;
                fragment = new ProfileSettingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_change_password){
                toolTitle.setText("Password");
                fragment = null;
                fragment = new ChangePassFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_bmi_calculator){

                toolTitle.setText("BMI");
                fragment = null;
                fragment = new BMICalculatorFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }


            if(item.getItemId()==R.id.drawer_nav_blog){
                toolTitle.setText("Blog");
                Toast.makeText(this, "Blog fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);


            }

            if(item.getItemId()==R.id.drawer_nav_contact_us){

                toolTitle.setText("Contact");
                fragment = null;
                fragment = new ContuctUsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_about_us){
                toolTitle.setText("About");
                Toast.makeText(this, "About us fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }
            if(item.getItemId()==R.id.drawer_nav_terms_and_conditions){

                toolTitle.setText("Terms & Conditions");
                Toast.makeText(this, "Terms and Conditions fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_policies){

                toolTitle.setText("Policies");
                Toast.makeText(this, "Our Policies fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_logout){

                final Dialog dialog = new Dialog(this);
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
}
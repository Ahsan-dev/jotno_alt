package com.example.jotno.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import io.paperdb.Paper;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.Fragment.ContuctUsFragment;
import com.example.jotno.Fragment.BMICalculatorFragment;
import com.example.jotno.Fragment.DashboardFragment;
import com.example.jotno.Fragment.ProfileSettingsFragment;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.R;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        drawerBtn = findViewById(R.id.drawer_btn);
        toolbar = findViewById(R.id.main_toolbar_layout);
        toolTitle = findViewById(R.id.toolbar_title_txt);
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
                toolTitle.setText("Profile Settings");
                fragment = null;
                fragment = new ProfileSettingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_change_password){
                toolTitle.setText("Change your Password");
                Toast.makeText(this, "Change Password fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_bmi_calculator){

                toolTitle.setText("Body Mass Index");
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

                toolTitle.setText("Contact Us");
                fragment = null;
                fragment = new ContuctUsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_relative_layout,fragment).commit();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_about_us){
                toolTitle.setText("About Us");
                Toast.makeText(this, "About us fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }
            if(item.getItemId()==R.id.drawer_nav_terms_and_conditions){

                toolTitle.setText("Terms and Conditions");
                Toast.makeText(this, "Terms and Conditions fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_policies){

                toolTitle.setText("Our Policies");
                Toast.makeText(this, "Our Policies fragment", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);

            }

            if(item.getItemId()==R.id.drawer_nav_our_logout){

                final Dialog dialog = new Dialog(this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
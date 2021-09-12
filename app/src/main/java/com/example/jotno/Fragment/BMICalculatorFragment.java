package com.example.jotno.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotno.R;

import java.text.NumberFormat;
import java.util.Locale;

public class BMICalculatorFragment extends Fragment {

    private EditText heightEdt, weightEdt;
    private Button calculateBtn;
    private double height, weight;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_b_m_i_calculator, container, false);

        heightEdt = view.findViewById(R.id.bmi_height_edt);
        weightEdt = view.findViewById(R.id.bmi_weight_edt);
        calculateBtn = view.findViewById(R.id.bmi_calculate_btn);



        calculateBtn.setOnClickListener(v -> {

            if(heightEdt.getText().toString().equals("") || weightEdt.getText().toString().equals("")){
                Toast.makeText(v.getContext(), "Enter height weight", Toast.LENGTH_SHORT).show();
            }else{
                height = Double.parseDouble(heightEdt.getText().toString());
                weight = Double.parseDouble(weightEdt.getText().toString());


                showInAlertDialog(calculateBmi(height,weight));
                heightEdt.setText("");
                weightEdt.setText("");
            }

        });

        return view;

    }

    private double calculateBmi(double height, double weight){

        double result = weight / ((height*.0254) * (height*.0254));
        return result;

    }

    private void showInAlertDialog(double value) {

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.calculation_result_layout);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        RelativeLayout mainLayout = dialog.findViewById(R.id.result_dialog_layout);
        TextView bmiText = dialog.findViewById(R.id.bmi_dialog_your_bmi_txt);
        ImageView dcsionSymbol = dialog.findViewById(R.id.bmi_result_sign_img);
        TextView dcsionTxt =  dialog.findViewById(R.id.bmi_dialog_result_text);
        ImageView firstAdviceImg = dialog.findViewById(R.id.bmi_dialog_first_advice_image);
        TextView firstAdviceTxt =  dialog.findViewById(R.id.bmi_dialog_first_advice_text);
        ImageView secondAdviceImg = dialog.findViewById(R.id.bmi_dialog_second_advice_image);
        TextView secondAdviceTxt =  dialog.findViewById(R.id.bmi_dialog_second_advice_text);
        ImageView thirdAdviceImg =  dialog.findViewById(R.id.bmi_dialog_third_advice_image);
        TextView thirdAdviceTxt = dialog.findViewById(R.id.bmi_dialog_third_advice_text);
        Button okBtn = dialog.findViewById(R.id.bmi_dialog_ok_btn);

        String sd = String.format("%.2f",value);
        bmiText.setText(sd);

        if(value<=18.5){

            mainLayout.setBackgroundColor(getResources().getColor(R.color.yello));

            dcsionSymbol.setImageResource(R.drawable.exclamation);
            dcsionTxt.setText(R.string.low_bmi_string);
            firstAdviceImg.setImageResource(R.drawable.nowater);
            firstAdviceTxt.setText(R.string.low_bmi_first_advice_string);
            secondAdviceImg.setImageResource(R.drawable.bigmeal);
            secondAdviceTxt.setText(R.string.low_bmi_second_advice_string);
            thirdAdviceImg.setImageResource(R.drawable.sleep);
            firstAdviceTxt.setText(R.string.low_bmi_third_advice_string);

        }else if(value>18.5 && value<25.1){

            mainLayout.setBackgroundColor(getResources().getColor(R.color.green));
            dcsionSymbol.setImageResource(R.drawable.correct);
            dcsionTxt.setText(R.string.normal_bmi_string);
            firstAdviceImg.setImageResource(R.drawable.active);
            firstAdviceTxt.setText(R.string.normal_bmi_first_advice_string);
            secondAdviceImg.setImageResource(R.drawable.salad);
            secondAdviceTxt.setText(R.string.normal_bmi_second_advice_string);
            thirdAdviceImg.setImageResource(R.drawable.sleep);
            firstAdviceTxt.setText(R.string.normal_bmi_third_advice_string);

        }else{

            mainLayout.setBackgroundColor(getResources().getColor(R.color.red));
            dcsionSymbol.setImageResource(R.drawable.warning);
            dcsionTxt.setText(R.string.high_bmi_string);
            firstAdviceImg.setImageResource(R.drawable.water);
            firstAdviceTxt.setText(R.string.high_bmi_first_advice_string);
            secondAdviceImg.setImageResource(R.drawable.twoeggs);
            secondAdviceTxt.setText(R.string.high_bmi_second_advice_string);
            thirdAdviceImg.setImageResource(R.drawable.nosugar);
            firstAdviceTxt.setText(R.string.high_bmi_third_advice_string);

        }


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }


}
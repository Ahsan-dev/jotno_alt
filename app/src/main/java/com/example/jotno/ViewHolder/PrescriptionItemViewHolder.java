package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

public class PrescriptionItemViewHolder extends RecyclerView.ViewHolder {

    public TextView prescriptionNoTxt, dateTxt, appointmentNoTxt, doctorsNameTxt, noteTxt;
    public Button viewPresBtn;
    public LinearLayout showLinear;
    public ImageView prescriptExpandImg;

    public PrescriptionItemViewHolder(@NonNull View itemView) {
        super(itemView);

       prescriptionNoTxt = itemView.findViewById(R.id.prescription_item_prescription_no_id);
       dateTxt = itemView.findViewById(R.id.prescription_item_prescription_date_id);
       appointmentNoTxt = itemView.findViewById(R.id.prescription_item_appointment_no_txt_id);
       doctorsNameTxt = itemView.findViewById(R.id.prescription_item_doctor_name_txt_id);
       noteTxt = itemView.findViewById(R.id.prescription_item_note_txt_id);
       viewPresBtn = itemView.findViewById(R.id.prescription_item_view_btn_id);
       showLinear = itemView.findViewById(R.id.prescription_item_second_row_id);
       prescriptExpandImg = itemView.findViewById(R.id.prescription_item_prescription_expand_btn_id);



    }
}

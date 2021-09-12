package com.example.jotno;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentViewHolder extends RecyclerView.ViewHolder {

    public TextView appointmentNoTxt, appointmentStatusTxt, appointmentDateTxt;
    public Button viewBtn;
    public LinearLayout secondLayout;

    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);

        appointmentNoTxt = itemView.findViewById(R.id.appointment_item_appointment_no_id);
        appointmentStatusTxt = itemView.findViewById(R.id.appointment_item_appointment_status_id);
        appointmentDateTxt = itemView.findViewById(R.id.appointment_item_date_txt_id);
        viewBtn = itemView.findViewById(R.id.appointment_item_view_btn_id);
        secondLayout = itemView.findViewById(R.id.appointment_item_second_row_id);


    }
}

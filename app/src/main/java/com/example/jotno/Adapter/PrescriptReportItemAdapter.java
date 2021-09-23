package com.example.jotno.Adapter;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.Activity.WelcomeActivity;
import com.example.jotno.Models.ReportDatum;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.PrescriptReportItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.paperdb.Paper;

public class PrescriptReportItemAdapter extends RecyclerView.Adapter<PrescriptReportItemViewHolder> {

    private List<ReportDatum> prescriptReportList;
    private int s = 0;

    public PrescriptReportItemAdapter(List<ReportDatum> prescriptReportList) {
        this.prescriptReportList = prescriptReportList;

    }

    @NonNull
    @Override
    public PrescriptReportItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptReportItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescript_report_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptReportItemViewHolder holder, int position) {

        ReportDatum report = prescriptReportList.get(position);

        holder.tesNameTxt.setText(report.getName());
        Picasso.get().load(report.getImage()).placeholder(R.drawable.exclamation).into(holder.reportImage);



        holder.itemView.setOnClickListener(view -> {

            if(s==0){

                holder.toggleLayout.setVisibility(View.VISIBLE);
                s=1;

            }else{

                holder.toggleLayout.setVisibility(View.GONE);
                s=0;

            }

        });


        holder.reportImage.setOnClickListener(view -> {


            Dialog dialog = new Dialog(view.getContext());
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.image_view_dialog_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            ImageView imageView = dialog.findViewById(R.id.image_view_dialog_image_view_id);
            ImageButton cancelBtn = dialog.findViewById(R.id.image_view_dialog_cancel_image_btn_id);
            Picasso.get().load(report.getImage()).placeholder(R.drawable.exclamation).into(imageView);
            dialog.setCanceledOnTouchOutside(true);

            cancelBtn.setOnClickListener(view1 -> {

                dialog.dismiss();

            });

            dialog.show();

        });



    }

    @Override
    public int getItemCount() {

        return prescriptReportList.size();

    }
}

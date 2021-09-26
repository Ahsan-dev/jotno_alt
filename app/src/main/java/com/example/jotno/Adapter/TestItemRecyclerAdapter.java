package com.example.jotno.Adapter;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jotno.Models.Tests;
import com.example.jotno.Models.TestsDatum;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.TestItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestItemRecyclerAdapter extends RecyclerView.Adapter<TestItemViewHolder> {



    private List<TestsDatum> testsList;
    private int st = 0;


    public TestItemRecyclerAdapter(List<TestsDatum> testsList) {
        this.testsList = testsList;
    }

    @NonNull
    @Override
    public TestItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout,parent,false);

        return new TestItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestItemViewHolder holder, int position) {

        TestsDatum tests = testsList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(st == 0){
                holder.hideLayout.setVisibility(View.GONE);
                st = 1;
            }else{
                holder.hideLayout.setVisibility(View.VISIBLE);
                st = 0;
            }


        });

        holder.prescriptionNo.setText(tests.getPrescriptionNo());
        holder.testItemName.setText(tests.getName());
        holder.testDate.setText("Test Date:\n"+tests.getDate());



        holder.showReportBtn.setOnClickListener(v -> {

            Dialog dialog = new Dialog(v.getContext());
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.image_view_dialog_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            ImageView imageView = dialog.findViewById(R.id.image_view_dialog_image_view_id);
            ImageButton cancelBtn = dialog.findViewById(R.id.image_view_dialog_cancel_image_btn_id);
            Picasso.get().load(tests.getImage()).placeholder(R.drawable.exclamation).into(imageView);
            dialog.setCanceledOnTouchOutside(true);

            cancelBtn.setOnClickListener(view1 -> {

                dialog.dismiss();

            });

            dialog.show();

        });


    }

    @Override
    public int getItemCount() {
        return testsList.size();
    }
}

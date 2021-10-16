package com.example.jotno.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlogItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView blogItemImg;
    public CircleImageView blogProPicImg;
    public TextView blogTitleTxt, blogDetailsTxt, blogDateTxt;

    public BlogItemViewHolder(@NonNull View itemView) {
        super(itemView);

        blogItemImg = itemView.findViewById(R.id.blog_recycler_item_image_id);
        blogProPicImg = itemView.findViewById(R.id.blog_recycler_item_profile_img_id);
        blogTitleTxt = itemView.findViewById(R.id.blog_recycler_item_title_txt_id);
        blogDetailsTxt = itemView.findViewById(R.id.blog_recycler_item_post_details_id);
        blogDateTxt = itemView.findViewById(R.id.blog_recycler_item_date_id);

    }
}

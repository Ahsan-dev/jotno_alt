package com.example.jotno.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotno.BlogDetailsFragmentFragment;
import com.example.jotno.Fragment.PrescriptAppointFragment;
import com.example.jotno.Models.BlogDatum;
import com.example.jotno.R;
import com.example.jotno.ViewHolder.BlogItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogItemRecyclerAdapter extends RecyclerView.Adapter<BlogItemViewHolder> {

    private List<BlogDatum> blogList;

    public BlogItemRecyclerAdapter(List<BlogDatum> blogList) {
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlogItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_recycler_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlogItemViewHolder holder, int position) {

        BlogDatum blog = blogList.get(position);

        Picasso.get().load(blog.getImage()).placeholder(R.drawable.jotno_logo).into(holder.blogItemImg);

        holder.blogTitleTxt.setText(blog.getTitle());
        holder.blogCreatedByName.setText(blog.getCreatedBy());
        holder.blogDateTxt.setText(blog.getCreatedAt());
        holder.blogDetailsTxt.setText(blog.getDescription());


        holder.itemView.setOnClickListener(view -> {


            BlogDetailsFragmentFragment fragment = new BlogDetailsFragmentFragment();
            FragmentManager fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundles = new Bundle();
            bundles.putInt("position",position);
            fragment.setArguments(bundles);
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });


    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
}

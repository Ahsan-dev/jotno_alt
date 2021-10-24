package com.example.jotno;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jotno.Fragment.BlogFragment;
import com.example.jotno.Models.BlogDatum;
import com.example.jotno.PaperDB.PermanentBlog;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;


public class BlogDetailsFragmentFragment extends Fragment {

    private TextView titleTxt, nameTxt, dateTxt, detailsTxt;
    private CircleImageView proPicImage;
    private ImageView blogImage, backBtn;
    private String name, proPicUrl, imageUrl, date, details, title;
    private List<BlogDatum> blogList;
    private View view;
    private int position = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blog_details_fragment, container, false);

        titleTxt = view.findViewById(R.id.blog_details_title_txt_id);
        nameTxt = view.findViewById(R.id.blog_details_profile_name_id);
        dateTxt = view.findViewById(R.id.blog_details_date_id);
        detailsTxt = view.findViewById(R.id.blog_details_post_details_id);
        proPicImage = view.findViewById(R.id.blog_details_profile_img_id);
        blogImage = view.findViewById(R.id.blog_details_image_id);
        backBtn = view.findViewById(R.id.blog_details_back_btn);

        Paper.init(view.getContext());
        position = getArguments().getInt("position",0);

        blogList = Paper.book().read(PermanentBlog.blogListString);

        Picasso.get().load(blogList.get(position).getImage()).placeholder(R.drawable.jotno_logo).into(blogImage);
        titleTxt.setText(blogList.get(position).getTitle());
        nameTxt.setText(blogList.get(position).getCreatedBy());
        Picasso.get().load(blogList.get(position).getProfileImage()).placeholder(R.drawable.person_icon_black).into(proPicImage);
        dateTxt.setText(blogList.get(position).getCreatedAt());
        detailsTxt.setText(blogList.get(position).getDescription());

        backBtn.setOnClickListener(view1 -> {

            BlogFragment fragment = new BlogFragment();
            FragmentManager fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        return view;
    }
}
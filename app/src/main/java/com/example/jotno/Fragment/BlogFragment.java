package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Adapter.BlogItemRecyclerAdapter;
import com.example.jotno.Models.BlogDatum;
import com.example.jotno.Models.BlogResponse;
import com.example.jotno.PaperDB.PermanentBlog;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogFragment extends Fragment {

    private RecyclerView blogRecycler;
    private View view;
    private Api api;
    private BlogItemRecyclerAdapter blogAdapter;
    private List<BlogDatum> blogList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blog, container, false);

        blogRecycler = view.findViewById(R.id.blog_recycler_view);

        api = RetroClient.getClient().create(Api.class);
        Paper.init(view.getContext());

        blogList = new ArrayList<>();

        api.getAllBlogs()
                .enqueue(new Callback<BlogResponse>() {
                    @Override
                    public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                        if(response.isSuccessful()){

                            blogList = response.body().getBody().getData();
                            Paper.book().write(PermanentBlog.blogListString,blogList);
                            blogAdapter = new BlogItemRecyclerAdapter(blogList);
                            blogRecycler.hasFixedSize();
                            blogRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            blogRecycler.setAdapter(blogAdapter);
                            blogAdapter.notifyDataSetChanged();

                        }else {

                            Toast.makeText(view.getContext(), "Response not found!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BlogResponse> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        return view;
    }
}
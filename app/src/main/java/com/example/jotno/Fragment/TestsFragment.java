package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jotno.Adapter.TestItemRecyclerAdapter;
import com.example.jotno.Models.Tests;
import com.example.jotno.Models.TestsDatum;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PaperDB.TestsPermanent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestsFragment extends Fragment {

    private View view;
    private RecyclerView testsRecycler;
    private List<TestsDatum> testList;
    private TestItemRecyclerAdapter testItemRecyclerAdapter;
    private Api api;
    private ImageView backBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tests, container, false);

        api = RetroClient.getClient().create(Api.class);
        testsRecycler = view.findViewById(R.id.tests_fragment_tests_recycler_id);
        backBtn = view.findViewById(R.id.tests_back_btn);

        testList = new ArrayList<>();

        api.getAllTests(Paper.book().read(PermanentPatient.userIdString))
                .enqueue(new Callback<Tests>() {
                    @Override
                    public void onResponse(Call<Tests> call, Response<Tests> response) {
                        if(response.isSuccessful()){

                            if(response.body().getStatus().equals("success")){

                                testList = response.body().getBody().getData();
                                Paper.book().write(TestsPermanent.testsListString,testList);
                                testsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                testItemRecyclerAdapter = new TestItemRecyclerAdapter(testList);
                                testsRecycler.hasFixedSize();
                                testItemRecyclerAdapter.notifyDataSetChanged();
                                testsRecycler.setAdapter(testItemRecyclerAdapter);

                            }else{

                                Toast.makeText(view.getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                            }

                        }else{

                            Toast.makeText(view.getContext(), "Response null!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Tests> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        backBtn.setOnClickListener(view1 -> {

            DashboardFragment fragment = new DashboardFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_relative_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;

    }
}
package com.example.jotno.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotno.Adapter.BillItemRecyclerAdapter;
import com.example.jotno.Adapter.PrescriptionRecyclerAdapter;
import com.example.jotno.Models.Bills;
import com.example.jotno.Models.Prescriptions;
import com.example.jotno.Models.PrescriptionsDatum;
import com.example.jotno.PaperDB.PermanentPatient;
import com.example.jotno.PaperDB.PrescriptionsPermanent;
import com.example.jotno.R;
import com.example.jotno.Retrofit.Api;
import com.example.jotno.Retrofit.RetroClient;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BillsFragment extends Fragment {

    private View view;
    private RecyclerView billsRecycler;
    private List<PrescriptionsDatum> billList;
    private BillItemRecyclerAdapter billItemRecyclerAdapter;
    private Api api;
    private int patientId = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bills, container, false);

        billsRecycler = view.findViewById(R.id.bill_fragment_bill_recycler_id);

        Paper.init(view.getContext());
        patientId = Paper.book().read(PermanentPatient.userIdString);
        api = RetroClient.getClient().create(Api.class);

        billList = new ArrayList<>();


        api.getAllPrescriptions(patientId)
                .enqueue(new Callback<Prescriptions>() {
                    @Override
                    public void onResponse(Call<Prescriptions> call, Response<Prescriptions> response) {
                        if(response.isSuccessful()){

                            if(response.body().getStatus().equals("error")){

                                Toast.makeText(view.getContext(), "No bill found!!!", Toast.LENGTH_SHORT).show();

                            }else{

                                billList = response.body().getBody().getData();
                                Paper.book().write(PrescriptionsPermanent.prescriptionsListString,billList);
                                billsRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                billItemRecyclerAdapter = new BillItemRecyclerAdapter(billList);
                                billsRecycler.hasFixedSize();
                                billItemRecyclerAdapter.notifyDataSetChanged();
                                billsRecycler.setAdapter(billItemRecyclerAdapter);

                            }




                        }else{

                            Toast.makeText(view.getContext(), "Response null!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Prescriptions> call, Throwable t) {

                        Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



        return view;
    }
}
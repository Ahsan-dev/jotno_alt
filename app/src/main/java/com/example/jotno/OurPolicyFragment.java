package com.example.jotno;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OurPolicyFragment extends Fragment {

    private TextView detailsTxt;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_our_policy, container, false);

        detailsTxt = view.findViewById(R.id.our_policy_details_txt_id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            detailsTxt.setText(Html.fromHtml(, Html.FROM_HTML_MODE_COMPACT));
        } else {
            detailsTxt.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p> <br/> <img src=\"https://jotno.beautyclinicbd.com/images/home/1632993292QWfWyq7VP1pz.jpg\" alt=\"\">"));
        }

        return view;
    }
}
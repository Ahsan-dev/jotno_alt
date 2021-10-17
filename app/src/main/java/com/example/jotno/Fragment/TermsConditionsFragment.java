package com.example.jotno.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jotno.R;

public class TermsConditionsFragment extends Fragment {

    private View view;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_terms_conditions, container, false);

        webView = view.findViewById(R.id.terms_conditions_webviewid);

        final MyWebClient myWebViewClient = new MyWebClient();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.setWebViewClient(myWebViewClient);
        webView.loadUrl("https://jotno.beautyclinicbd.com/information/terms-and-condition");

        return view;
    }

    public class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            view.loadUrl("javascript:(function(){ document.getElementsByClassName('footer')[0].style.display = 'none'; document.getElementsByClassName('navbar')[0].style.display = 'none' })()");

        }
    }


}
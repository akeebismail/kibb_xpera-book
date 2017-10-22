package com.example.kibb.xpera.damiz.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kibb.xpera.R;
import com.example.kibb.xpera.damiz.config.Constants;
import com.example.kibb.xpera.damiz.widget.ProgressWebView;

/**
 * Created by KIBB on 8/30/2017.
 */

public class BlogFragment extends Fragment {
    private ProgressWebView dWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View root = inflater.inflate(R.layout.fragment_blog, container, false);
        dWebView = (ProgressWebView) root.findViewById(R.id.id_pwv_web_view);
        dWebView.loadUrl(Constants.BLOG_URL);
        dWebView.getSettings().setJavaScriptEnabled(true);
        return root;
    }
}

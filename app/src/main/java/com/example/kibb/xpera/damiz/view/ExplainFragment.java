package com.example.kibb.xpera.damiz.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kibb.xpera.R;

/**
 * Created by KIBB on 8/30/2017.
 */

public class ExplainFragment extends Fragment {
    @Nullable
    //@Override
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle saveState){
        return
                inflater.inflate(R.layout.fragment_explain,container,false);
    }
}

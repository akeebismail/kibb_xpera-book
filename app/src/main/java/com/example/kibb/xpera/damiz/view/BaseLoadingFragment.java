package com.example.kibb.xpera.damiz.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by KIBB on 8/30/2017.
 */
import com.example.kibb.xpera.R;

public abstract class BaseLoadingFragment extends Fragment {
    protected RelativeLayout dRLRoot;
    private ProgressBar dPBLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        dRLRoot= (RelativeLayout) inflater.inflate(R.layout.content_main,container, false);
        dPBLoading = new ProgressBar(getActivity());
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(200,200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        dPBLoading.setLayoutParams(params);
        dRLRoot.addView(dPBLoading);
        return dRLRoot;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (loadOk()){
            switchContent();
        }else {
            switchToError();
        }
        super.onCreate(savedInstanceState);
    }

    private void switchContent(){
        dPBLoading.setVisibility(View.GONE);
        View content = createErrorView();
        dRLRoot.addView(content);
    }
    private void switchToError(){
        dPBLoading.setVisibility(View.GONE);
        View error = createContentView();
        dRLRoot.addView(error);
    }
    protected abstract View createContentView();
    protected abstract boolean loadOk();
    protected abstract View createErrorView();
}

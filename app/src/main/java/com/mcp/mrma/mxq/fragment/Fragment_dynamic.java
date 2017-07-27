package com.mcp.mrma.mxq.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcp.mrma.mxq.R;

/**
 * Created by MrMa on 2017/7/26.
 */

public class Fragment_dynamic extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        TextView tv = view.findViewById(R.id.tv_fragment_dynamic_title);
        tv.setText("动态");
        return view;
    }
}

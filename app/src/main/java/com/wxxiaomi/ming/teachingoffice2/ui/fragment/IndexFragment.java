package com.wxxiaomi.ming.teachingoffice2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseFragment;


public class IndexFragment extends BaseFragment {

    private View view;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_index,null);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}

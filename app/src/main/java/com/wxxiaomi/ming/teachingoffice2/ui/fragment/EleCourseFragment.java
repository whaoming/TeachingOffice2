package com.wxxiaomi.ming.teachingoffice2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.EleCoursePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.EleCoursePreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.view.EleCourseView;

/**
 * Created by 12262 on 2016/5/19.
 */
public class EleCourseFragment extends BaseFragment implements EleCourseView {

    private View view;
    private EleCoursePre eleCoursePre;
    private LayoutInflater inflater;
    private ExpandableListView lv_lv;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_ele_course,null);
        this.inflater = inflater;
        lv_lv = (ExpandableListView) view.findViewById(R.id.lv_lv);
        lv_lv.setGroupIndicator(null);
        eleCoursePre = new EleCoursePreImpl(this);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initListView();
    }

    @Override
    public void initListView() {
        eleCoursePre.initListView(lv_lv,inflater);
    }

    @Override
    public void setNoDataView() {

    }

    @Override
    public void setRefersh(Boolean tag) {

    }


}

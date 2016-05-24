package com.wxxiaomi.ming.teachingoffice2.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.ScorePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.ScorePreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.view.ScoreView;

/**
 * Created by 12262 on 2016/5/18.
 */
public class ScoreFragment extends BaseFragment implements ScoreView{
    private View view;
    private ScorePre scorePre;
    private ExpandableListView lv_lv;
    private LayoutInflater inflater;
    private SwipeRefreshLayout view_refersh;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_score,null);
        lv_lv = (ExpandableListView) view.findViewById(R.id.lv_lv);
        lv_lv.setGroupIndicator(null);
        view_refersh = (SwipeRefreshLayout) view.findViewById(R.id.view_refersh);
        view_refersh.setColorSchemeResources(R.color.color1,
                R.color.color2, R.color.color3, R.color.color4);
        view_refersh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        view_refersh.setRefreshing(true);
        this.inflater = inflater;
        scorePre = new ScorePreImpl(this);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initListView();
    }

    @Override
    public void initListView() {
        scorePre.initListView(lv_lv,inflater);
    }

    @Override
    public void setNoDataView() {

    }

    @Override
    public void setRefersh(Boolean tag) {
        view_refersh.setRefreshing(tag);
    }
}

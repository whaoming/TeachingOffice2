package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.SearchPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.SearchPerImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.view.SearchView;

/**
 * Created by 12262 on 2016/5/20.
 * 搜索书本页面
 */
public class SearchActivity extends BaseActivity implements SearchView{

    private EditText et_serach;
    private RecyclerView listView;
    private SearchPre searchPre;
    private Button btn_ok;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_library_search);
        listView = (RecyclerView) findViewById(R.id.listView);
        et_serach = (EditText) findViewById(R.id.et_serach);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void initData() {
        searchPre = new SearchPerImpl(this);
        searchPre.init();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPre.search(et_serach.getText().toString().trim());
            }
        });
    }

    @Override
    public void initListView(RecyclerView.Adapter adapter) {
        listView.setAdapter(adapter);

    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goBookDetail(Bundle data) {
        Intent intent = new Intent(ct,BookDetailActivity.class);
        intent.putExtras(data);
        startActivity(intent);
    }


}

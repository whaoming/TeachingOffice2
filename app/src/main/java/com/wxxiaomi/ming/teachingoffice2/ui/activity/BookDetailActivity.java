package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.BookDetailPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.BookDetailPreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.view.BookDetailView;

/**
 * Created by 12262 on 2016/5/21.
 */
public class BookDetailActivity extends BaseActivity implements BookDetailView {

    private TextView tv_tv;
    private LinearLayout ll_ll;
    private BookDetailPre bookDetailPre;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_library_bookinfo);
        tv_tv = (TextView) findViewById(R.id.tv_tv);
        ll_ll = (LinearLayout) findViewById(R.id.ll_ll);
    }

    @Override
    public void initData() {
        bookDetailPre = new BookDetailPreImpl(this);
        bookDetailPre.getBookInfo(getIntent().getExtras());
    }

    @Override
    public void setBookInfo(String info) {
        tv_tv.setText(info);
    }

    @Override
    public LinearLayout getContentLayout() {
        return ll_ll;
    }

    @Override
    public Context getContext() {
        return this;
    }
}

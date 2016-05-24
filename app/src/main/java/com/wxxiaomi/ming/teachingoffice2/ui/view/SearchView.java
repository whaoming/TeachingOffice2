package com.wxxiaomi.ming.teachingoffice2.ui.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 12262 on 2016/5/20.
 */
public interface SearchView {
    void initListView(RecyclerView.Adapter adapter);



    void showLoading(String content);

    void hideLoading();

    Context getContext();

    void goBookDetail(Bundle data);
}

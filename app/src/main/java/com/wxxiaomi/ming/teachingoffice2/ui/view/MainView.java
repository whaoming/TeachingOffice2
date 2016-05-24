package com.wxxiaomi.ming.teachingoffice2.ui.view;

import android.view.MenuItem;

/**
 * Created by 12262 on 2016/5/18.
 */
public interface MainView {
    /**
     *弹出未登录提示框
     */
    void showUnLoginDialog();

    /**
     * 跳转到登录页面
     */
    void runLoginAct();

    void onMenuItemChange(final MenuItem menuItem);

    boolean changFragmentById(MenuItem menuItem);
}

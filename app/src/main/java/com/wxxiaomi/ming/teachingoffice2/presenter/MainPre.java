package com.wxxiaomi.ming.teachingoffice2.presenter;

import android.view.MenuItem;

/**
 * Created by 12262 on 2016/5/18.
 */
public interface MainPre {
    /**
     * 检查是否登录
     */
    boolean checkLogin();

    /**
     * 点击了未登录对话框的确定按钮
     */
    void onUnLoginDialogOkClick();

    /**
     * 点击了未登录对话的取消按钮
     */
    void onUnLoginDialogCancelClick();

    void switchFragment(MenuItem menuItem);

}

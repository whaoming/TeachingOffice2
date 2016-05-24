package com.wxxiaomi.ming.teachingoffice2.ui.view;

/**
 * Created by 12262 on 2016/5/17.
 * 登录页面的方法
 */
public interface LoginView {
    /**
     * 弹出一个错误提示对话框
     * @param content
     */
    void showErrorDialog(String content);

    /**
     * 弹出一个加载中的对话框
     */
    void showLoadingDialog();

    /**
     * 隐藏加载对话框
     */
    void hideLoadingDialog();

    /**
     * 跳到主页面
     */
    void runMainAct();

}

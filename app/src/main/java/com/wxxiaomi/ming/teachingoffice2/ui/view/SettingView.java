package com.wxxiaomi.ming.teachingoffice2.ui.view;

import android.content.Context;

/**
 * Created by 12262 on 2016/5/23.
 */
public interface SettingView {

    void initEditText(String username,String password);

    Context getContext();

    void showMsgDialog(String content);
}

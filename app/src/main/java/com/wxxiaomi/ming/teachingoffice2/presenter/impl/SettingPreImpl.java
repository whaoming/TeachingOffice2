package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import com.wxxiaomi.ming.teachingoffice2.presenter.SettingPre;
import com.wxxiaomi.ming.teachingoffice2.ui.view.SettingView;
import com.wxxiaomi.ming.teachingoffice2.util.SharePrefUtil;

/**
 * Created by 12262 on 2016/5/23.
 */
public class SettingPreImpl implements SettingPre {

    private SettingView view;

    public SettingPreImpl(SettingView view) {
        this.view = view;
    }

    @Override
    public void initData() {
        String libUsername = SharePrefUtil.getString(view.getContext(), "LibUsername", "");
        String libPassword =  SharePrefUtil.getString(view.getContext(), "LibPassword", "");
        view.initEditText(libUsername,libPassword);
    }

    @Override
    public void saveAccout(String username, String password) {
        SharePrefUtil.saveString(view.getContext(), "LibUsername",username);
        SharePrefUtil.saveString(view.getContext(), "LibPassword",password);
        SharePrefUtil.saveBoolean(view.getContext(),"isRemLib",true);
        view.showMsgDialog("保存成功");
    }
}

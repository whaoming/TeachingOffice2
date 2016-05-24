package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.view.MenuItem;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.presenter.MainPre;
import com.wxxiaomi.ming.teachingoffice2.ui.view.MainView;

/**
 * Created by 12262 on 2016/5/18.
 */
public class MainPreImpl implements MainPre {

    private MainView mainView;

    public MainPreImpl(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void onUnLoginDialogOkClick() {
        mainView.runLoginAct();
    }

    @Override
    public void onUnLoginDialogCancelClick() {

    }

    @Override
    public void switchFragment(MenuItem menuItem) {
        if(checkLogin()){
            mainView.onMenuItemChange(menuItem);
        }else{
            mainView.showUnLoginDialog();
        }
    }

    @Override
    public boolean checkLogin() {
        return ConstantValue.isOfficeLogin;
    }
}

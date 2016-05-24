package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.content.Context;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.Glo;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;
import com.wxxiaomi.ming.teachingoffice2.exception.MyException;
import com.wxxiaomi.ming.teachingoffice2.model.OfficeModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.OfficeModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.LoginPre;
import com.wxxiaomi.ming.teachingoffice2.ui.view.LoginView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/17.
 * 登录页面大脑的实现类
 */
public class LoginPreImpl implements LoginPre {

    OfficeModel model;
    LoginView view;

    public LoginPreImpl(LoginView view) {
        this.view = view;
        model = new OfficeModelImpl();
    }

    @Override
    public void login(String username, String password) {
        view.showLoadingDialog();
        model.Login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Html_Main>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorDialog(((MyException) e).getViewErrorInfo());
                    }

                    @Override
                    public void onNext(Html_Main html_main) {
                        Glo.html_main = html_main;
                        ConstantValue.isOfficeLogin = true;
                        view.hideLoadingDialog();
                        view.runMainAct();
                    }
                })
        ;
    }

    public void catchError(Throwable e) {

    }
}

package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.LoginPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.LoginPreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.view.LoginView;
import com.wxxiaomi.ming.teachingoffice2.ui.wedigt.LoadingDialog;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class LoginActivity extends BaseActivity implements LoginView{

    private TextInputLayout til_username;
    private TextInputLayout til_password;
    private Button btn_ok;
    private LoginPre loginPre;
    LoadingDialog loadingDialog;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        til_password = (TextInputLayout) findViewById(R.id.til_password);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        loginPre = new LoginPreImpl(this);
        btn_ok.setOnClickListener(this);
    }



    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                String username = til_username.getEditText().getText().toString().trim();
                String password = til_password.getEditText().getText().toString().trim();
                loginPre.login(username,password);
                break;
        }
    }

    @Override
    public void showErrorDialog(String content) {
        showMsgDialog(content,null,null);
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog = LoadingDialog.create(ct)
                .setTitle("正在登录中");
               loadingDialog .show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void runMainAct() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

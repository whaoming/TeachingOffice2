package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.SettingPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.SettingPreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.view.SettingView;

public class SettingActivity extends BaseActivity implements SettingView {

    private EditText lib_username;
    private EditText lib_password;
    private Button btn;
    private SettingPre settingPre;
    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        lib_username = (EditText) findViewById(R.id.lib_username);
        lib_password = (EditText) findViewById(R.id.lib_password);
        btn = (Button) findViewById(R.id.btn);
    }

    @Override
    public void initData() {
        settingPre = new SettingPreImpl(this);
        settingPre.initData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = lib_username.getText().toString().trim();
                String password = lib_password.getText().toString().trim();
                settingPre.saveAccout(username,password);
            }
        });
    }

    @Override
    public void initEditText(String username, String password) {
        lib_username.setText(username);
        lib_password.setText(password);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMsgDialog(String content) {
        showMsgDialog(content,null,null);
    }
}

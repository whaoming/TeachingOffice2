package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;


public class SplashActivity extends BaseActivity {


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(ct,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void initData() {

    }
}

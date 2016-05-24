package com.wxxiaomi.ming.teachingoffice2.ui.common;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.wxxiaomi.ming.teachingoffice2.R;


public abstract class BaseActivity extends AppCompatActivity implements
		OnClickListener {

	protected Context ct;
	protected Toolbar toolbar;
	AlertDialog msgDialog ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ct = this;
		toolbar = (Toolbar) this.findViewById(R.id.toolbar);
		initToolBar();
		initView(savedInstanceState);
		initData();
	}

	private void initToolBar() {
		if(toolbar != null){
			setSupportActionBar(toolbar);
			getSupportActionBar().setHomeButtonEnabled(true); // 设置返回键可用
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
	}
	@Override

	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
	}


	protected void showMsgDialog(final String content,DialogInterface.OnClickListener okLis,DialogInterface.OnClickListener canelLis){

		msgDialog = new AlertDialog.Builder(ct,R.style.MingDialog)
				.setTitle("测试标题")
						.setMessage(content)
						.setPositiveButton("确定", okLis)
						.create();
				msgDialog.show();
	}

	public abstract void initView(Bundle savedInstanceState);
	public abstract void initData();
}

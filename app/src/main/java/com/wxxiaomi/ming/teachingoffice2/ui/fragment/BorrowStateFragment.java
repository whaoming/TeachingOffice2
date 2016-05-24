package com.wxxiaomi.ming.teachingoffice2.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.BorrowStatePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.LibBorrowStateColumnAdapter;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.BorrowStatePreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.activity.SettingActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.view.BorrowStateView;
import com.wxxiaomi.ming.teachingoffice2.ui.wedigt.ImageCodeDialog;


public class BorrowStateFragment extends BaseFragment implements BorrowStateView{

    private View view;
    private ImageCodeDialog codeDialog;
    private ListView listView;
    private BorrowStatePre borrowStatePre;
    private SwipeRefreshLayout view_refersh;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_borrow_state,null);
        listView = (ListView) view.findViewById(R.id.lv_lv);
        view_refersh = (SwipeRefreshLayout) view.findViewById(R.id.view_refersh);
        view_refersh = (SwipeRefreshLayout) view.findViewById(R.id.view_refersh);
        view_refersh.setColorSchemeResources(R.color.color1,
                R.color.color2, R.color.color3, R.color.color4);
        view_refersh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        borrowStatePre = new BorrowStatePreImpl(this);
        codeDialog = ImageCodeDialog.getInstance().create(ct, new ImageCodeDialog.onPositionListener() {
            @Override
            public void onClick(String code) {
                borrowStatePre.OnCodeDialogOKListener(code);
            }
        });
        borrowStatePre.initListData();
    }

    @Override
    public void showImageCodeDialog() {
        codeDialog.show();
    }

    @Override
    public void setImageCodeDialogBitmap(Bitmap bitmap) {
        codeDialog.setImage(bitmap);
    }

    @Override
    public Context getContext(){
        return ct;
    }

    @Override
    public void initList(BaseAdapter adapter) {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setRefresh(Boolean flag) {
        view_refersh.setRefreshing(flag);
    }

    @Override
    public void showNoAccountDialog() {
        AlertDialog dialog = new AlertDialog.Builder(ct)
                .setTitle("当前未记录图书馆账号")
                .setMessage("是否前往图书馆登录页面")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrowStatePre.noAccoutPosition();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void runRemAccount() {
        Intent intent = new Intent(ct, SettingActivity.class);
        startActivity(intent);
    }


}

package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ListView;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.Glo;
import com.wxxiaomi.ming.teachingoffice2.bean.BookBorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;
import com.wxxiaomi.ming.teachingoffice2.model.LibModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.LibModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.BorrowStatePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.LibBorrowStateColumnAdapter;
import com.wxxiaomi.ming.teachingoffice2.ui.view.BorrowStateView;
import com.wxxiaomi.ming.teachingoffice2.util.SharePrefUtil;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/19.
 */
public class BorrowStatePreImpl implements BorrowStatePre {

    private BorrowStateView view;
    private LibModel model;
    private LibBorrowStateColumnAdapter adapter;
    private Html_lib_Login tempLogin;


    public BorrowStatePreImpl(BorrowStateView view) {
        this.view = view;
        model = new LibModelImpl();
    }

    @Override
    public void initListData() {
        view.setRefresh(true);
        if(ConstantValue.isLibLogin){
            model.getBorrowState(Glo.html_lib_main.getCookie(),Glo.html_lib_main.getBookBorrowedUrl(),"http://210.38.162.2/OPAC/login.aspx?ReturnUrl=%2fopac%2fuser%2fuserinfo.aspx")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<BookBorrowedState>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<BookBorrowedState> bookBorrowedStates) {
                            adapter = new LibBorrowStateColumnAdapter(view.getContext(),bookBorrowedStates);
                            view.initList(adapter);
                            view.setRefresh(false);
                        }
                    });
        }else{
//
            boolean isRemLib = SharePrefUtil.getBoolean(view.getContext(), "isRemLib", false);
            if(isRemLib){
                login();
            }else{
                view.showNoAccountDialog();
            }
        }

    }

    private void login() {
        view.showImageCodeDialog();
        model.getLibLogin(ConstantValue.LIBURL)
                .flatMap(new Func1<Html_lib_Login, Observable<Bitmap>>() {
                    @Override
                    public Observable<Bitmap> call(Html_lib_Login html_lib_login) {
                        tempLogin = html_lib_login;
                        return Observable.just(html_lib_login.getPicCode());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        view.setImageCodeDialogBitmap(bitmap);
                    }
                });
    }

    @Override
    public void OnCodeDialogOKListener(String content) {
        String username = SharePrefUtil.getString(view.getContext(),"LibUsername","");
        String password = SharePrefUtil.getString(view.getContext(),"LibPassword","");
        model.getLibMain(tempLogin,username,password,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Html_Lib_Main>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Html_Lib_Main html_lib_main) {
                        ConstantValue.isLibLogin = true;
                        Glo.html_lib_main = html_lib_main;
//                        Log.i("wang",html_lib_main.getBookBorrowedUrl());
                        initListData();
                    }
                });
    }

    @Override
    public void OnCodeDialogCancelListener() {

    }

    @Override
    public void noAccoutPosition() {
        view.runRemAccount();
    }

    @Override
    public void noAcountCancle() {

    }
}

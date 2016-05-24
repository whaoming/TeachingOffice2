package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.os.Bundle;
import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BookInfoDetail;
import com.wxxiaomi.ming.teachingoffice2.model.LibModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.LibModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.BookDetailPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.viewadapter.CollectLocatAdapter;
import com.wxxiaomi.ming.teachingoffice2.ui.view.BookDetailView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/21.
 */
public class BookDetailPreImpl implements BookDetailPre {

    private BookDetailView view;
    private String bookUrl;
    private LibModel model;
    private CollectLocatAdapter adapter;

    public BookDetailPreImpl(BookDetailView view) {
        this.view = view;
        model = new LibModelImpl();
    }

    @Override
    public void getBookInfo(Bundle bundle) {
        bookUrl = new String(bundle.getByteArray("bookurl"));
        model.getBookInfo(bookUrl)
                .flatMap(new Func1<Html_Lib_BookInfoDetail, Observable<BookInfo>>() {
                    @Override
                    public Observable<BookInfo> call(Html_Lib_BookInfoDetail html_lib_bookInfoDetail) {
                        return Observable.just(html_lib_bookInfoDetail.getBookInfo());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookInfo bookInfo) {
//                        Log.i("wang","bookInfo.getDetail()="+bookInfo.getDetail());
                        view.setBookInfo(bookInfo.getDetail());
                        adapter = new CollectLocatAdapter(view.getContext());
                        adapter.injectView(view.getContentLayout(),bookInfo.getCollecLocations());
                    }
                });
    }
}

package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.os.Bundle;
import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Result;
import com.wxxiaomi.ming.teachingoffice2.model.LibModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.LibModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.SearchPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.BookSearchAdapter;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.base.BaseRecyclerViewAdapter;
import com.wxxiaomi.ming.teachingoffice2.ui.view.SearchView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/20.
 */
public class SearchPerImpl implements SearchPre{

    private SearchView view;
    private LibModel model;
    private Html_Lib_Search_Main main;
    private BookSearchAdapter adapter;

    public SearchPerImpl(SearchView view) {
        this.view = view;
        model = new LibModelImpl();
    }

    @Override
    public void init() {
        view.showLoading("正在初始化中...");
        adapter = new BookSearchAdapter(view.getContext());
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<BookInfo>() {
            @Override
            public void onItemClick(BookInfo bookInfo) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("bookurl",bookInfo.getUrl().getBytes());
                view.goBookDetail(bundle);
            }
        });
        view.initListView(adapter);

        model.getSearchMain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Html_Lib_Search_Main>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Html_Lib_Search_Main html_lib_search_main) {
                        main = html_lib_search_main;
                        view.hideLoading();
                    }
                });
    }

    @Override
    public void search(String content) {
        model.getsearchResult(main,content)
                .flatMap(new Func1<Html_Lib_Search_Result, Observable<List<BookInfo>>>() {
                    @Override
                    public Observable<List<BookInfo>> call(Html_Lib_Search_Result html_lib_search_result) {
                        return Observable.just(html_lib_search_result.getColumns());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BookInfo> bookInfos) {
                        adapter.updateList(bookInfos);
                        adapter.setLoadingComplete();
                    }
                });
    }
}

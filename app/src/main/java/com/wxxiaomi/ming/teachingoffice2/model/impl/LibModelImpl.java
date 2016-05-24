package com.wxxiaomi.ming.teachingoffice2.model.impl;

import com.wxxiaomi.ming.teachingoffice2.bean.BookBorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BookInfoDetail;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Result;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;
import com.wxxiaomi.ming.teachingoffice2.model.LibModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.base.BaseImpl;
import com.wxxiaomi.ming.teachingoffice2.serviceapi.LibService;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 12262 on 2016/5/19.
 */
public class LibModelImpl extends BaseImpl implements LibModel{


    @Override
    public Observable<Html_Lib_BookInfoDetail> getBookInfo(String bookUrl) {
        return makeObservable(LibService.getBookInfo(bookUrl));
    }

    @Override
    public Observable<Html_Lib_Search_Result> getsearchResult(Html_Lib_Search_Main main, String content) {
        return makeObservable(LibService.getSearchResult(main,content));
    }

    @Override
    public Observable<Html_Lib_Search_Main> getSearchMain() {
        return makeObservable(LibService.getSearchMain("http://210.38.162.2/OPAC/search.aspx"));
    }

    @Override
    public Observable<List<BookBorrowedState>> getBorrowState(String cookie, String url, String refererUrl) {
        return makeObservable(LibService.getBorrowedState(cookie,url,refererUrl))
                .flatMap(new Func1<Html_Lib_BorrowedState, Observable<List<BookBorrowedState>>>() {
                    @Override
                    public Observable<List<BookBorrowedState>> call(Html_Lib_BorrowedState html_lib_borrowedState) {
                        return Observable.just(html_lib_borrowedState.getColumns());
                    }
                })
                ;
    }

    @Override
    public Observable<Html_lib_Login> getPicCode(String liburl) {
        return makeObservable(LibService.getLibLoginPageAndCodePic(liburl));
    }

    @Override
    public Observable<Html_Lib_Main> getLibMain(Html_lib_Login login,String username,String password,String code) {
        return makeObservable(LibService.getLibMain(login,username,password,code));
    }

    @Override
    public Observable<Html_lib_Login> getLibLogin(String liburl) {
        return makeObservable(LibService.getLibLoginPageAndCodePic(liburl));
    }


}

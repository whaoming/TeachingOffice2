package com.wxxiaomi.ming.teachingoffice2.model;

import com.wxxiaomi.ming.teachingoffice2.bean.BookBorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BookInfoDetail;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Result;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;

import java.util.List;

import rx.Observable;

/**
 * Created by 12262 on 2016/5/19.
 */
public interface LibModel {

    /**
     * 获取某本书
     * @param bookUrl
     * @return
     */
    Observable<Html_Lib_BookInfoDetail> getBookInfo(String bookUrl);

    /**
     * 根据关键字查找结果
     * @param main  搜索页面的bean
     * @param content
     * @return
     */
    Observable<Html_Lib_Search_Result> getsearchResult(Html_Lib_Search_Main main,String content);

    /**
     * 获取搜索页面
     * @return
     */
    Observable<Html_Lib_Search_Main> getSearchMain();

    /**
     * 获取借阅情况
     * @param cookie
     * @param url
     * @param refererUrl
     * @return
     */
    Observable<List<BookBorrowedState>> getBorrowState(final String cookie, final String url, final String refererUrl);

    /**
     * 获取图片验证码
     * @param liburl
     * @return
     */
    Observable<Html_lib_Login> getPicCode(final String liburl);

    /**
     * 获取图书馆主页面
     * @param login
     * @param username
     * @param password
     * @param code
     * @return
     */
    Observable<Html_Lib_Main> getLibMain(Html_lib_Login login,String username,String password,String code);

    Observable<Html_lib_Login> getLibLogin(String liburl);
}

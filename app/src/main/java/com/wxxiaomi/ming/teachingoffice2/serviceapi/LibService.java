package com.wxxiaomi.ming.teachingoffice2.serviceapi;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BookInfoDetail;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Result;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetReceiverData;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetSendData;
import com.wxxiaomi.ming.teachingoffice2.exception.MyException;
import com.wxxiaomi.ming.teachingoffice2.net.MyHttpWebUtil;
import com.wxxiaomi.ming.teachingoffice2.util.CommUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by 12262 on 2016/5/19.
 */
public class LibService {

    public static Callable<Html_Lib_BookInfoDetail> getBookInfo(final String url) {
        return new Callable<Html_Lib_BookInfoDetail>() {
            @Override
            public Html_Lib_BookInfoDetail call() throws Exception {
                Html_Lib_BookInfoDetail bookInfo2Bean = null;
                try {
                    NetSendData sendData = new NetSendData();
                    sendData.setUrl(url);
                    NetReceiverData data = MyHttpWebUtil.sendGet(sendData);
                    bookInfo2Bean = HtmlService.getBookInfo(new String(data.getContent())).call();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("getNextPage",MyException.PARSE_HTML_ERROR);
                }
                return bookInfo2Bean;
            }
        };
    }

    public static Callable<Html_Lib_Search_Result> getNextPage(final String nexPageUrl,final int currentPage, final String refererUrl) {
        return new Callable<Html_Lib_Search_Result>() {
            @Override
            public Html_Lib_Search_Result call() throws Exception {
                Html_Lib_Search_Result result = null;
                try {
                    String url = nexPageUrl+ CommUtils.getEncodeUrl("&page="+(currentPage+1))+"=";
                    NetSendData sendData = new NetSendData();
                    sendData.setUrl(url);
                    sendData.getHeaders().put("Referer", refererUrl);
                    NetReceiverData sendGet = MyHttpWebUtil.sendGet(sendData);
                    result = HtmlService.getSearchResult(new String(sendGet.getContent()),currentPage).call();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("getNextPage",MyException.PARSE_HTML_ERROR);
                }
                return result;
            }
        };
    }

    public static Callable<Html_Lib_Search_Result> getSearchResult(final Html_Lib_Search_Main main,final String content) {
        return new Callable<Html_Lib_Search_Result>() {
            @Override
            public Html_Lib_Search_Result call() throws Exception {
                Html_Lib_Search_Result result = null;
                try {
                    NetSendData sendData = new NetSendData();
                    sendData.setUrl(main.getSearchUrl());
                    main.getSearchPars().put("ctl00$ContentPlaceHolder1$keywordstb", content);
                    main.getSearchPars().put("ctl00$ContentPlaceHolder1$splb", "TITLEFORWARD");
                    main.getSearchPars().put("ctl00$ContentPlaceHolder1$deptddl", "ALL");
                    main.getSearchPars().put("ctl00$ContentPlaceHolder1$depthf", "ALL");
                    sendData.setParmars(main.getSearchPars());
                    NetReceiverData receivePost = MyHttpWebUtil.sendPost(sendData);
                    result = HtmlService.getSearchResult(new String(receivePost.getContent()),0).call();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("getLibMain",MyException.PARSE_HTML_ERROR);
                }
                return result;
            }
        };
    }

    public static Callable<Html_Lib_Search_Main> getSearchMain(final String searchUrl) {
        return new Callable<Html_Lib_Search_Main>() {
            @Override
            public Html_Lib_Search_Main call() throws Exception {
                Html_Lib_Search_Main result = null;
                try {
                    NetSendData sendData = new NetSendData();
                    sendData.setUrl(searchUrl);
                    NetReceiverData sendGet = MyHttpWebUtil.sendGet(sendData);
                    result = HtmlService.getSearchMain(new String(sendGet.getContent())).call();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("getLibMain",MyException.PARSE_HTML_ERROR);
                }
                return result;
            }
        };
    }

    public static Callable<Html_Lib_Main> getLibMain(final Html_lib_Login login, final String username, final String password, final String code) {
        return new Callable<Html_Lib_Main>() {
            @Override
            public Html_Lib_Main call() throws Exception {
                Log.i("wang","getLibMain");
                Html_Lib_Main loginSuccess = null;
                try {
                    NetSendData loginToSendData = new NetSendData();
                    loginToSendData.setUrl(login.getLoginUrl());
                    loginToSendData.getHeaders().put("Referer", login.getLoginUrl());
                    Map<String, String> libLoginParmars = login.getLoginPars();
                    libLoginParmars.put("ctl00$ContentPlaceHolder1$txtUsername_Lib", username);
                    libLoginParmars.put("ctl00$ContentPlaceHolder1$txtPas_Lib", password);
                    libLoginParmars.put("ctl00$ContentPlaceHolder1$txtCode", code);
                    loginToSendData.setParmars(libLoginParmars);
                    String[] split = login.getCookie().split(";");
                    loginToSendData.getHeaders().put("Cookie",split[0] );
                    NetReceiverData receiverData = MyHttpWebUtil.sendPost(loginToSendData);
                    loginSuccess = HtmlService.getLibMainBean(new String(receiverData.getContent())).call();
                    loginSuccess.setCookie(receiverData.getHeaders().get("Cookie"));
                    loginSuccess.setUsername(username);
                    loginSuccess.setPassword(password);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("getLibMain",MyException.PARSE_HTML_ERROR);
                }
                return loginSuccess;
            }
        };
    }

    public static Callable<Html_Lib_BorrowedState> getBorrowedState(final String cookie, final String url, final String refererUrl) {
        return new Callable<Html_Lib_BorrowedState>() {
            @Override
            public Html_Lib_BorrowedState call() throws Exception {
                Html_Lib_BorrowedState borrowStateBean = null;
                try {
                    NetSendData sendData = new NetSendData();
                    sendData.setUrl(url);
                    sendData.getHeaders().put("Cookie", cookie);
                    sendData.getHeaders().put("Referer", refererUrl);
                    NetReceiverData receiverData = MyHttpWebUtil.sendGet(sendData);
                    borrowStateBean = HtmlService.getBorrowStateBean(new String(receiverData.getContent())).call();
                } catch (Exception e) {
                    throw e;
                }
                return borrowStateBean;
            }
        };
    }


    public static Callable<Html_lib_Login> getLibLoginPageAndCodePic(final String liburl) {
        return new Callable<Html_lib_Login>() {
            @Override
            public Html_lib_Login call() throws Exception {
                Html_lib_Login loginHtml2Bean = null;
                try {
                    NetSendData send = new NetSendData();
                    send.setUrl(liburl);
                    NetReceiverData data = MyHttpWebUtil.sendGet(send);
                    loginHtml2Bean = HtmlService.getLibLoginHtmlBean(data.getContent2String()).call();
                    String temlCookie = data.getHeaders().get("Cookie");
                    loginHtml2Bean.setCookie(temlCookie);
//                Log.i("wang", "temlCookie="+temlCookie);
                    NetSendData getCode = new NetSendData();
                    getCode.setUrl(loginHtml2Bean.getCodePicUrl());
                    getCode.getHeaders().put("Cookie", temlCookie);
                    NetReceiverData receiverData = MyHttpWebUtil.sendGet(getCode);
                    InputStream is = null;
                    is = new ByteArrayInputStream(receiverData.getContent());
                    loginHtml2Bean.setPicCode(BitmapFactory.decodeStream(is));
                    is.close();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw e;
                }
                return loginHtml2Bean;
            }
        };
    }
}

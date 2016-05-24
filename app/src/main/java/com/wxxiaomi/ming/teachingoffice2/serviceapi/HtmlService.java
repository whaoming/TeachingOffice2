package com.wxxiaomi.ming.teachingoffice2.serviceapi;

import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.bean.BookBorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;
import com.wxxiaomi.ming.teachingoffice2.bean.LibUserInfo;
import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BookInfoDetail;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_BorrowedState;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Lib_Search_Result;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Login;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;
import com.wxxiaomi.ming.teachingoffice2.exception.MyException;
import com.wxxiaomi.ming.teachingoffice2.util.CommUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.concurrent.Callable;


/**
 * Created by 12262 on 2016/5/11.
 */
public class HtmlService {

    public static Callable<Html_Lib_BookInfoDetail> getBookInfo(final String html) {
        return new Callable<Html_Lib_BookInfoDetail>() {
            @Override
            public Html_Lib_BookInfoDetail call() throws Exception {
                Html_Lib_BookInfoDetail result = new Html_Lib_BookInfoDetail();
                BookInfo info = new BookInfo();
                Document doc = Jsoup.parse(html);
                Element span = doc.getElementById("ctl00_ContentPlaceHolder1_bookcardinfolbl");
                if(span != null){
                    info.setDetail(span.text());
                    Element tbody = doc.select("table.tb tbody").first();
                    if(tbody != null){
                        for(Element tr : tbody.children()){
                            BookInfo.CollectState location = new BookInfo.CollectState(tr.child(0).child(0).text()
                                    , tr.child(1).text(), tr.child(2).text(), tr.child(3).text()
                                    , tr.child(4).text(), tr.child(5).text(), tr.child(6).text()
                            );
                            info.getCollecLocations().add(location);
                        }
                    }
                    result.setBookInfo(info);
                }else{
                    Log.i("wang", "span == null");
                    throw new MyException("HtmlService,getBookInfo",MyException.PARSE_HTML_ERROR);
                }

                return result;
            }
        };
    }

    public static Callable<Html_Lib_Search_Result> getSearchResult(final String html, final int page) {
        return new Callable<Html_Lib_Search_Result>() {
            @Override
            public Html_Lib_Search_Result call() throws Exception {
                Log.i("wang",html);
                Html_Lib_Search_Result result = new Html_Lib_Search_Result();
                Document doc = Jsoup.parse(html);
                Element table = doc.select("table.tb").first();
                if (table != null) {
                    Element tbody = table.select("tbody").first();
                    if (tbody.children().size() != 0) {
                        for (int i = 0; i < tbody.children().size(); i++) {
                            Element tr = tbody.child(i);
                            BookInfo column = new BookInfo(tr.child(1).child(0).child(0).text()
                                    , tr.child(2).text(), tr.child(3).text(), tr.child(4).text()
                                    , tr.child(5).text(), tr.child(6).text(), tr.child(7).text());
                            column.setUrl(ConstantValue.LIBMAIN + tr.child(1).child(0).child(0).attr("href"));
                            result.getColumns().add(column);
                        }
                        if (page == 0) {//说明是加载第一页结果
                            String pageUrl = ConstantValue.LIBMAIN + ConstantValue.LIBASEARCHRESULTAJAX + "?" + CommUtils.getEncodeUrl(doc.getElementById("ctl00_ContentPlaceHolder1_thissearchhf").attr("value"));
                            result.setPageUrl(pageUrl);
                            result.setPageCount(doc.getElementById("ctl00_ContentPlaceHolder1_gplblfl1").text());
                            result.setCurrentPage(1 + "");
                        }
                    }
                } else {
                    result.setMsg("无数据");
                }
//		        String value = ;
                /**
                 * 下一页的地址，就要图书馆服务器地址+ajax地址+参数+page
                 */

                return result;
            }
        };
    }

    public static Callable<Html_Lib_Search_Main> getSearchMain(final String html) {
        return new Callable<Html_Lib_Search_Main>() {
            @Override
            public Html_Lib_Search_Main call() throws Exception {
                Html_Lib_Search_Main result = new Html_Lib_Search_Main();
                Document doc = Jsoup.parse(html);
                result.getSearchPars().put("__VIEWSTATE", URLEncoder.encode(doc.getElementById("__VIEWSTATE").attr("value"),"gb2312"));
                result.getSearchPars().put("__EVENTVALIDATION", URLEncoder.encode(doc.getElementById("__EVENTVALIDATION").attr("value"),"gb2312"));
                result.getSearchPars().put("ctl00$ContentPlaceHolder1$searchbtn", CommUtils.getGBKUrl(doc.getElementsByAttributeValue("name", "ctl00$ContentPlaceHolder1$searchbtn").first().attr("value")));
                result.setSearchUrl(ConstantValue.LIBSEARCHURL);
                return result;
            }
        };
    }

    public static Callable<Html_Lib_Main> getLibMainBean(final String html) {
        return new Callable<Html_Lib_Main>() {
            @Override
            public Html_Lib_Main call() throws Exception {
                Html_Lib_Main main = new Html_Lib_Main();
                Document doc = Jsoup.parse(html);
                Element userInfoContent = doc.getElementById("userInfoContent");
                if (userInfoContent == null) {
                    //验证失败
//                    LibException libException = new LibException(LibException.OTHERERROR);
//                    libException.setOtherError(doc.getElementById("ctl00_ContentPlaceHolder1_lblErr_Lib").child(0).text());
//                    throw libException;
                    Log.i("wang", "图书馆账号密码出错");
                    throw new MyException("getLibLoginBean", MyException.LIB_LOGIN_ERROR);
                } else {
                    main.setBorrowHistoryUrl(ConstantValue.LibHost + doc.select(":containsOwn(我的借书历史)").first().attr("href"));
                    LibUserInfo userInfo = new LibUserInfo(userInfoContent.child(0).child(1).text(), userInfoContent.child(1).child(1).text()
                            , userInfoContent.child(2).child(1).text(), userInfoContent.child(3).child(1).text()
                            , userInfoContent.child(4).child(1).text(), userInfoContent.child(5).child(1).text()
                            , userInfoContent.child(6).child(1).text(), userInfoContent.child(7).child(1).text()
                            , userInfoContent.child(8).child(1).text(), userInfoContent.child(9).child(1).text());
                    main.setUserInfo(userInfo);
                    main.setBookBorrowedUrl(ConstantValue.LibHost + doc.select(":containsOwn(当前借阅情况和续借)").first().attr("href"));
                }
                return main;
            }
        };
    }

    public static Callable<Html_Lib_BorrowedState> getBorrowStateBean(final String html) {
        return new Callable<Html_Lib_BorrowedState>() {
            @Override
            public Html_Lib_BorrowedState call() throws Exception {
                Html_Lib_BorrowedState result = new Html_Lib_BorrowedState();
                Document doc = Jsoup.parse(html);
                Element div = doc.getElementById("borrowedcontent");
                if (div == null) {
//                    throw new LibException(LibException.OUTOF_LOGIN);
                    throw new MyException("getBorrowStateBean", MyException.LIB_OUT_LOGIN);
                } else {
                    Element tbody = div.select("tbody").first();
                    for (int i = 0; i < tbody.children().size(); i++) {
                        Element tr = tbody.child(i);
                        BookBorrowedState column = new BookBorrowedState(tr.child(1).text()
                                , tr.child(2).child(0).text()
                                , tr.child(3).text()
                                , tr.child(4).text()
                                , tr.child(5).text()
                                , tr.child(6).text()
                                , ConstantValue.LibHost + tr.child(2).child(0).attr("href")
                        );
                        result.getColumns().add(column);
                    }
                }
                return result;
            }
        };
    }


    public static Callable<Html_lib_Login> getLibLoginHtmlBean(final String html) {
        return new Callable<Html_lib_Login>() {
            @Override
            public Html_lib_Login call() throws Exception {
                Log.i("wang", "getLibLoginHtmlBean");
                Html_lib_Login result = new Html_lib_Login();
                Document doc = Jsoup.parse(html);
                result.setLoginUrl("http://210.38.162.2/OPAC/login.aspx?ReturnUrl=/opac/user/userinfo.aspx");
                result.setCodePicUrl("http://210.38.162.2/OPAC/" + doc.getElementById("ccodeimg").attr("src") + "?rd=" + Math.random());
                String __VIEWSTATE = doc.getElementById("__VIEWSTATE").attr("value");
                String ctl00_ContentPlaceHolder1_txtlogintype = doc.getElementById("ctl00_ContentPlaceHolder1_txtlogintype").attr("value");
                String ctl00_ContentPlaceHolder1_btnLogin_Lib = "%E7%99%BB%E5%BD%95";
                String __EVENTVALIDATION = doc.getElementById("__EVENTVALIDATION").attr("value") == "" ? "" : URLEncoder.encode(doc.getElementById("__EVENTVALIDATION").attr("value"), "gb2312");
                result.getLoginPars().put("__VIEWSTATE", URLEncoder.encode(__VIEWSTATE, "gb2312"));
                Log.i("wang", "__VIEWSTATE=" + URLEncoder.encode(__VIEWSTATE, "gb2312"));
                result.getLoginPars().put("__EVENTTARGET", "");
                result.getLoginPars().put("__EVENTARGUMENT", "");
                result.getLoginPars().put("ctl00$ContentPlaceHolder1$txtlogintype", ctl00_ContentPlaceHolder1_txtlogintype);
                result.getLoginPars().put("ctl00$ContentPlaceHolder1$btnLogin_Lib", ctl00_ContentPlaceHolder1_btnLogin_Lib);
                result.getLoginPars().put("__EVENTVALIDATION", __EVENTVALIDATION);
                return result;
            }
        };
    }


    public static Callable<OfficeElectiveCourse> getEleCourseHtmlBean(final String html) {
        return new Callable<OfficeElectiveCourse>() {
            @Override
            public OfficeElectiveCourse call() throws Exception {
                OfficeElectiveCourse result = new OfficeElectiveCourse();
                Document doc = Jsoup.parse(html);
                Element tag = doc.getElementById("DBGrid");
                if (tag == null) {
                    Log.i("wang", "officeHistoryScoreHtml2Bean捕获到登陆超时，因为未找到标签");
//                    throw new OfficeException(OfficeException.OUTOF_LOGIN);
                } else {
                    try {
                        Elements trs = doc.getElementById("DBGrid").select("tr");
                        for (int i = 1; i < trs.size(); i++) {
                            Element tr = trs.get(i);
                            OfficeElectiveCourse.ElectiveCourseColumn column = new OfficeElectiveCourse.ElectiveCourseColumn(tr
                                    .child(0).text(), tr.child(1).text(), tr.child(2)
                                    .child(0).text(), tr.child(3).text(), tr.child(4)
                                    .text(), tr.child(5).child(0).text(), tr.child(6)
                                    .text(), tr.child(7).text(), tr.child(8).child(0)
                                    .text(), tr.child(9).text(), tr.child(10).text(), tr
                                    .child(11).text(), tr.child(12).text(), tr.child(13)
                                    .text(), tr.child(14).text());
                            result.getColumns().add(column);
                        }
                    } catch (Exception e) {
//                        throw new OfficeException(OfficeException.PARSE_HTML_ERROR);
                        throw new MyException("getEleCourseHtmlBean", MyException.PARSE_HTML_ERROR);
                    }
                }
                return result;
            }
        };
    }

    public static Callable<Score> officeHistoryScoreHtml2Bean(final Score score, final String html) {
        return new Callable<Score>() {
            @Override
            public Score call() throws Exception {
                Log.i("wang", "html=" + html);
                Document doc = Jsoup.parse(html);
                Element loginTag = doc.getElementById("Datagrid1");
//                Log.i("wang","loginTag="+loginTag.toString());
                if (loginTag == null) {
                    Log.i("wang", "officeHistoryScoreHtml2Bean捕获到登陆超时，因为未找到标签");
//                    throw new OfficeException(OfficeException.OUTOF_LOGIN);
                    throw new MyException("officeHistoryScoreHtml2Bean", MyException.OFFICE_OUT_LOGIN);
                } else {
                    try {
                        Elements trs = doc.getElementById("Datagrid1").select("tr");
                        if (trs != null) {
                            Log.i("wang", "trs.zize=" + trs.size());
                            for (int i = 1; i < trs.size(); i++) {

                                Element element = trs.get(i);
                                Score.ScoreColumn column = new Score.ScoreColumn(element.child(0)
                                        .text(), element.child(1).text(), element.child(2)
                                        .text(), element.child(3).text(), element.child(4)
                                        .text(), element.child(5).text(), element.child(6)
                                        .text(), element.child(7).text(), element.child(8)
                                        .text(), element.child(9).text(), element.child(10)
                                        .text(), element.child(11).text(), element
                                        .child(12).text(), element.child(13).text(),
                                        element.child(12).text());
                                score.getColumns().add(column);
                            }
                        }
                    } catch (Exception e) {
//                        throw new OfficeException(OfficeException.PARSE_HTML_ERROR);
                        throw new MyException("officeHistoryScoreHtml2Bean", MyException.PARSE_HTML_ERROR);
                    }
                }
                return score;
            }
        };
    }

    /**
     * 得到可以点击获取成绩页面的第一个页面，取得参数都封装在Score里面
     *
     * @param html
     * @return
     */
    public static Callable<Score> officeScoreHtmlToGetScore2Bean(final String html) {
        return new Callable<Score>() {
            @Override
            public Score call() throws Exception {
                Score result = new Score();
                Document doc = Jsoup.parse(html);
                /**
                 * __EVENTTARGET __EVENTARGUMENT __VIEWSTATE hidLanguage ddlXN ddlXQ
                 * ddl_kcxz btn_zcj
                 */
                Element first = doc.getElementsByAttributeValue("name", "__EVENTTARGET")
                        .first();
                if (first == null) {
                    Log.i("wang", "officeScoreHtmlToGetScore2Bean中检测到登陆超时");
//                    throw new OfficeException(OfficeException.OUTOF_LOGIN);
                    throw new MyException("officeScoreHtmlToGetScore2Bean方法中", MyException.OFFICE_OUT_LOGIN);
                } else {
                    try {
                        result.getGetHistoryScorePars().put(
                                "__EVENTTARGET",
                                doc.getElementsByAttributeValue("name", "__EVENTTARGET")
                                        .first().attr("value"));
                        result.getGetHistoryScorePars().put(
                                "__EVENTARGUMENT",
                                doc.getElementsByAttributeValue("name", "__EVENTARGUMENT")
                                        .first().attr("value"));
                        result.getGetHistoryScorePars().put("__VIEWSTATE",
                                URLEncoder.encode(doc.select("[name=__VIEWSTATE]").first().attr("value"), "gb2312"));
                        result.getGetHistoryScorePars().put("hidLanguage", "");
                        result.getGetHistoryScorePars().put("ddlXN", "");
                        result.getGetHistoryScorePars().put("ddlXQ", "");
                        result.getGetHistoryScorePars().put("ddl_kcxz", "");
//                        result.getGetHistoryScorePars()

//                                .put("btn_zcj",
//                                        CommUtils.getGBKUrl(doc.getElementById("btn_zcj").attr(
//                                                "value")));
                        result.getGetHistoryScorePars()
                                .put("btn_zcj",
                                        CommUtils.getGBKUrl("历年成绩"));
                    } catch (Exception e) {
//                        throw new OfficeException(OfficeException.PARSE_HTML_ERROR);
                        throw new MyException("officeScoreHtmlToGetScore2Bean方法中", MyException.PARSE_HTML_ERROR);
                    }


                }

                return result;
            }
        };
    }

    /**
     * 访问登录页面，并把html解析成初始bean
     *
     * @param html
     * @return
     */
    public static Callable<Html_Login> officeLoginHtml2Bean(final String html) {
        return new Callable<Html_Login>() {
            @Override
            public Html_Login call() throws Exception {
                Html_Login result = new Html_Login();
                try {
                    Log.i("wang", "登录页面的html：" + html);
                    Document doc = Jsoup.parse(html);
                    result.setLoginUrl(doc.select("[name=form1]").first().attr("action"));
                    String __VIEWSTATE = doc.select("[name=__VIEWSTATE]").first()
                            .attr("value");
                    result.getLoginPars().put("__VIEWSTATE", __VIEWSTATE);
                    result.getLoginPars().put("RadioButtonList1", "%D1%A7%C9%FA");
                    result.getLoginPars().put("Button1", "");
                    result.getLoginPars().put("lbLanguage", "");
                    result.getLoginPars().put("hidPdrs", "");
                    result.getLoginPars().put("hidsc", "");
                    result.getLoginPars().put("txtSecretCode", "");
                } catch (Exception e) {
//                    throw new OfficeException(OfficeException.PARSE_HTML_ERROR);
                    throw new MyException("officeLoginHtml2Bean方法中:", MyException.PARSE_HTML_ERROR);
                }

                return result;
            }
        };
    }

    /**
     * 登陆操作后返回的html
     * 解析是否成功
     * 如果成功返回html对应的bean
     *
     * @param html
     * @return
     */
    public static Callable<Html_Main> officeMainHtml2Bean(final String html) {
        return new Callable<Html_Main>() {
            @Override
            public Html_Main call() throws Exception {
                Html_Main main = new Html_Main();
                Document doc = Jsoup.parse(html);
                if (doc.getElementById("xhxm") == null) {
                    //throw new LoginException("登录失败");
//                    throw new OfficeException(OfficeException.LOGINE_FAIL);
                    // throw new Exception("登录失败，账号出错");
                    throw new MyException("HtmlService", MyException.OFFICE_USER_ERROR);
                } else {
                    // 设置获取课表的url
                    main.setClassFormUrl(ConstantValue.tempOfficeUrl
                            + CommUtils.getGBKUrl(doc.select(":containsOwn(专业推荐课表查询)")
                            .first().attr("href")));

                    // 设置获取个人信息的url
                    main.setPersonalInfoUrl(ConstantValue.tempOfficeUrl
                            + CommUtils.getGBKUrl(doc.select(":containsOwn(个人信息)")
                            .first().attr("href")));

                    // 设置获取选课情况的url
                    main.setElectiveCourseUrl(ConstantValue.tempOfficeUrl
                            + CommUtils.getGBKUrl(doc.select(":containsOwn(学生选课情况查询)")
                            .first().attr("href")));

                    // 设置获取成绩的url
                    main.setScoreUrl(ConstantValue.tempOfficeUrl
                            + (doc.select(":containsOwn(成绩查询)")
                            .first().attr("href")));

                    // 获取main首页的信息
                    main.setNumberAndName(doc.getElementById("xhxm").text());
                }

                return main;
            }
        };
    }
}

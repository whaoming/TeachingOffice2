package com.wxxiaomi.ming.teachingoffice2.serviceapi;

import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Login;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetReceiverData;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetSendData;
import com.wxxiaomi.ming.teachingoffice2.net.MyHttpUtil;

import java.util.concurrent.Callable;

/**
 * Created by 12262 on 2016/5/17.
 */
public class OfficeService {

    public static Callable<OfficeElectiveCourse> getElectiveCourse(final String url, final String refererUrl){
        return new Callable<OfficeElectiveCourse>() {
            @Override
            public OfficeElectiveCourse call() throws Exception {
                OfficeElectiveCourse course = null;
                try {
                    NetSendData firstData = new NetSendData();
                    firstData.setUrl(url);
                    firstData.getHeaders().put("Referer", refererUrl);
                    NetReceiverData sendGet = MyHttpUtil.sendGet(firstData);
                    course = HtmlService.getEleCourseHtmlBean(sendGet.getContent2String()).call();
                }catch (Exception e){
                    throw e;
                }
                return course;
            }
        };
    }

    /**
     * 获取成绩
     * @param url
     * @param refererUrl
     * @return
     */
    public static Callable<Score> getScore(final String url, final String refererUrl){
        Log.i("wang","获取成绩的服务中：url="+url);
        return new Callable<Score>() {
            @Override
            public Score call() throws Exception {
                Score score;
                try {
                    NetSendData firstData = new NetSendData();
                    //访问可以点击获取成绩的页面
                    firstData.setUrl(url);
                    firstData.getHeaders().put("Referer", refererUrl);
                    NetReceiverData sendGet = MyHttpUtil.sendGet(firstData);
                    //封装了参数，模拟点击了获取成绩
                    score =  HtmlService.officeScoreHtmlToGetScore2Bean(sendGet.getContent2String()).call();
                    NetSendData sencondData = new NetSendData();
                    sencondData.setHost(ConstantValue.Host);
                    sencondData.setUrl(url);
                    sencondData.setParmars(score.getGetHistoryScorePars());
                    sencondData.getHeaders().put("Referer", url);
//                    sencondData.getHeaders().put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//                    sencondData.getHeaders().put("Connection", "keep-alive");
                    sencondData.getHeaders().put("Content-Type", "application/x-www-form-urlencoded");
                    NetReceiverData sendPost = MyHttpUtil.sendPost(sencondData);
                    score = HtmlService.officeHistoryScoreHtml2Bean(score,sendPost.getContent2String()).call();
                }catch (Exception e){
                    throw e;
                }
                return score;
            }
        };
    }

    public static Callable<Html_Main> login(final String username, final String password){
        // Log.i("wang","OfficeService中的login方法");
        return new Callable<Html_Main>() {
            @Override
            public Html_Main call() throws Exception{
                Html_Login html_login = getOfficeLoginHtml2Bean().call();
                NetSendData firstData = new NetSendData();
                firstData.setUrl(html_login.getLoginUrl());
                firstData.setHost(ConstantValue.Host);
                html_login.getLoginPars().put("txtUserName", username);
                html_login.getLoginPars().put("TextBox2", password);
                firstData.setParmars(html_login.getLoginPars());
                firstData.getHeaders().put("Referer", html_login.getUrl());
                NetReceiverData sendPost = MyHttpUtil.sendPost(firstData);
                Log.i("wang","sendPost.getContent2String()="+sendPost.getContent2String());
                Html_Main officeMainHtml2Bean = null;
                try{
                    officeMainHtml2Bean = HtmlService.officeMainHtml2Bean(sendPost.getContent2String()).call();
                    officeMainHtml2Bean.setFromUrl(firstData.getUrl());
                    officeMainHtml2Bean.setUsername(username);
                    officeMainHtml2Bean.setPassword(password);
                }catch (Exception e){
                    Log.i("wang","OfficeService has found error");
                    throw e;
                }
                return officeMainHtml2Bean;

            }
        };
    }

    /**
     * 获取登录页面的bean
     * @return
     */
    public static Callable<Html_Login> getOfficeLoginHtml2Bean(){
        return new Callable<Html_Login>() {
            @Override
            public Html_Login call() throws Exception {

                NetSendData firstData = new NetSendData();
                firstData.setUrl(ConstantValue.Host);
                firstData.setHost(ConstantValue.Host);
                NetReceiverData sendPost = MyHttpUtil.sendPost(firstData);
                Log.i("wang","返回的html为"+new String(sendPost.getContent2String()));
                Html_Login item = null;
                try {
                    item = HtmlService.officeLoginHtml2Bean(new String(sendPost.getContent2String())).call();
                    item.setTempUrl(sendPost.getFromUrl().replaceAll(item.getLoginUrl(), ""));
                    item.setLoginUrl(item.getTempUrl()+item.getLoginUrl());
                    ConstantValue.tempOfficeUrl = item.getTempUrl();
                    if(item!=null){
                        item.setUrl(sendPost.getFromUrl());
                    }
                }catch (Exception e){
                    throw e;
                }

                return item;

            }
        };
    }
}

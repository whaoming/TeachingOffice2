package com.wxxiaomi.ming.teachingoffice2.model;

import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;

import rx.Observable;

/**
 * Created by 12262 on 2016/5/17.
 */
public interface OfficeModel {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Observable<Html_Main> Login(String username, String password);

    /**
     * 获取成绩
     * @param scoreUrl
     * @param referUrl
     * @return
     */
    Observable<Score> getScore(String scoreUrl,String referUrl);

    /**
     * 获取修选课
     * @param courseUrl
     * @param referUrl
     * @return
     */
    Observable<OfficeElectiveCourse> getCourse(String courseUrl,String referUrl);
}

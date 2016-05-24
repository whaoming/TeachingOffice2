package com.wxxiaomi.ming.teachingoffice2.model.impl;

import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;
import com.wxxiaomi.ming.teachingoffice2.model.OfficeModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.base.BaseImpl;
import com.wxxiaomi.ming.teachingoffice2.serviceapi.OfficeService;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 12262 on 2016/5/17.
 */
public class OfficeModelImpl extends BaseImpl implements OfficeModel{

    @Override
    public Observable<Html_Main> Login(String username, String password) {
        return makeObservable(OfficeService.login(username, password));
    }

    @Override
    public Observable<Score> getScore(String scoreUrl, String referUrl) {
        return  makeObservable(OfficeService.getScore(scoreUrl, referUrl));
    }

    @Override
    public Observable<OfficeElectiveCourse> getCourse(String courseUrl, String referUrl) {
        return makeObservable(OfficeService.getElectiveCourse(courseUrl,referUrl));
    }



}

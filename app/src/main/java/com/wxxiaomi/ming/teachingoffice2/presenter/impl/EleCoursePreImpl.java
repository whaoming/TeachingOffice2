package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import com.wxxiaomi.ming.teachingoffice2.Glo;
import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;
import com.wxxiaomi.ming.teachingoffice2.model.OfficeModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.OfficeModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.EleCoursePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.OfficeElectiveCourseAdapter;
import com.wxxiaomi.ming.teachingoffice2.ui.view.EleCourseView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/19.
 */
public class EleCoursePreImpl implements EleCoursePre {

    private OfficeModel officeModel;
    private EleCourseView view;
    private OfficeElectiveCourseAdapter adapter;

    public EleCoursePreImpl(EleCourseView view){
        this.view = view;
    }

    @Override
    public void initListView(final ExpandableListView lv, final LayoutInflater inflater) {
        officeModel = new OfficeModelImpl();
        officeModel.getCourse(Glo.html_main.getElectiveCourseUrl(),Glo.html_main.getFromUrl())
                .flatMap(new Func1<OfficeElectiveCourse, Observable<List<OfficeElectiveCourse.ElectiveCourseColumn>>>() {
                    @Override
                    public Observable<List<OfficeElectiveCourse.ElectiveCourseColumn>> call(OfficeElectiveCourse officeElectiveCourse) {
                        return Observable.just(officeElectiveCourse.getColumns());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<OfficeElectiveCourse.ElectiveCourseColumn>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<OfficeElectiveCourse.ElectiveCourseColumn> electiveCourseColumns) {
                        adapter = new OfficeElectiveCourseAdapter(null,inflater,electiveCourseColumns);
                        lv.setAdapter(adapter);
                        view.setRefersh(false);
                    }
                });
    }
}

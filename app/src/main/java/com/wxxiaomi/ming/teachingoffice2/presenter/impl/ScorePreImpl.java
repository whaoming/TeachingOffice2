package com.wxxiaomi.ming.teachingoffice2.presenter.impl;

import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import com.wxxiaomi.ming.teachingoffice2.Glo;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;
import com.wxxiaomi.ming.teachingoffice2.model.OfficeModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.OfficeModelImpl;
import com.wxxiaomi.ming.teachingoffice2.presenter.ScorePre;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.OfficeScoreAdapter;
import com.wxxiaomi.ming.teachingoffice2.ui.view.ScoreView;

import java.util.List;
import java.util.zip.Inflater;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 12262 on 2016/5/18.
 */
public class ScorePreImpl implements ScorePre {
    private OfficeModel officeModel;
    private ScoreView scoreView;
    private OfficeScoreAdapter adapter;

    public ScorePreImpl(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void initListView(final ExpandableListView lv, final LayoutInflater inflater) {
        officeModel = new OfficeModelImpl();
        officeModel.getScore(Glo.html_main.getScoreUrl(), Glo.html_main.getFromUrl())
                .flatMap(new Func1<Score, Observable<List<Score.ScoreColumn>>>() {
                    @Override
                    public Observable<List<Score.ScoreColumn>> call(Score score) {
                        return Observable.just(score.getColumns());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Score.ScoreColumn>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Score.ScoreColumn> scoreColumns) {
                        adapter = new OfficeScoreAdapter(scoreColumns, inflater);
                        lv.setAdapter(adapter);
                        scoreView.setRefersh(false);
                    }
                })
        ;
    }
}

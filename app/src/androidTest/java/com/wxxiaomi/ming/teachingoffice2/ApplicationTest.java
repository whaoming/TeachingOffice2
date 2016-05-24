package com.wxxiaomi.ming.teachingoffice2;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_Main;
import com.wxxiaomi.ming.teachingoffice2.bean.html.Html_lib_Login;
import com.wxxiaomi.ming.teachingoffice2.model.LibModel;
import com.wxxiaomi.ming.teachingoffice2.model.OfficeModel;
import com.wxxiaomi.ming.teachingoffice2.model.impl.LibModelImpl;
import com.wxxiaomi.ming.teachingoffice2.model.impl.OfficeModelImpl;

import rx.Observer;
import rx.functions.Func1;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testLibLogin(){
        OfficeModel model = new OfficeModelImpl();
        model.Login("username","password")
                .subscribe(new Observer<Html_Main>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Html_Main html_main) {

                    }
                });
    }
}
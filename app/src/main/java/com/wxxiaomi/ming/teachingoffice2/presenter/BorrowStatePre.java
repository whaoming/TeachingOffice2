package com.wxxiaomi.ming.teachingoffice2.presenter;

import android.widget.ListView;

/**
 * Created by 12262 on 2016/5/19.
 */
public interface BorrowStatePre {
    void initListData();

    void OnCodeDialogOKListener(String content);

    void OnCodeDialogCancelListener();

    void noAccoutPosition();

    void noAcountCancle();

}

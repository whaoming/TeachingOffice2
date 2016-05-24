package com.wxxiaomi.ming.teachingoffice2.ui.view;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by 12262 on 2016/5/21.
 */
public interface BookDetailView {
    void setBookInfo(String info);

    LinearLayout getContentLayout();

    Context getContext();
}

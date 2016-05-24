package com.wxxiaomi.ming.teachingoffice2.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.BaseAdapter;


/**
 * Created by 12262 on 2016/5/19.
 */
public interface BorrowStateView {
    void showImageCodeDialog();
    void setImageCodeDialogBitmap(Bitmap bitmap);
    Context getContext();
    void initList(BaseAdapter adapter);
    void setRefresh(Boolean flag);
    void showNoAccountDialog();
    void runRemAccount();

}

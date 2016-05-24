package com.wxxiaomi.ming.teachingoffice2.presenter.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;

import java.util.List;

/**
 * Created by 12262 on 2016/5/21.
 */
public class CollectLocatAdapter {
    private Context ct;
    private View view;
    List<BookInfo.CollectState> collecLocations;

    public CollectLocatAdapter(Context ct) {
        this.ct = ct;
        view = LayoutInflater.from(ct).inflate(R.layout.item_lib_bookinfo_collectionlocation,null);
    }

    public void injectView(LinearLayout content, List<BookInfo.CollectState> collecLocations){
        for (BookInfo.CollectState cl : collecLocations) {
            TextView tv_location = (TextView) view
                    .findViewById(R.id.tv_location);
            TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
            TextView tv_loginnumber = (TextView) view
                    .findViewById(R.id.tv_loginnumber);
            TextView tv_state = (TextView) view.findViewById(R.id.tv_state);
            TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_location.setText(cl.getCollectionLocation());
            tv_number.setText(cl.getNumber());
            tv_loginnumber.setText(cl.getLoginNumber());
            tv_state.setText(cl.getState());
            tv_type.setText(cl.getType());
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            content.addView(view);
        }
    }
}

package com.wxxiaomi.ming.teachingoffice2.ui.wedigt;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.wxxiaomi.ming.teachingoffice2.R;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 12262 on 2016/5/15.
 * 验证码的dialog
 */
public class ImageCodeDialog {
    private AlertDialog dialog;
    private Context ct;
    private EditText et;
    private ImageView iv;
    private ProgressBar pb;
    private onPositionListener lis;
    private FrameLayout fl_iv;
    private FrameLayout fl_pb;
    DialogInterface.OnClickListener onClickListener;



    private ImageCodeDialog() {

    }

    public static ImageCodeDialog getInstance() {
        return new ImageCodeDialog();
    }

    public ImageCodeDialog create(Context ct,final onPositionListener liss) {
        this.ct = ct;
        View view = LayoutInflater.from(ct).inflate(R.layout.dialog_image_code,
                null);
        et = (EditText) view.findViewById(R.id.et);
        iv = (ImageView) view.findViewById(R.id.iv_iv);
        pb = (ProgressBar) view.findViewById(R.id.pb_pb);
        fl_iv = (FrameLayout) view.findViewById(R.id.fl_iv);
        fl_pb = (FrameLayout) view.findViewById(R.id.fl_pb);
       onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    liss.onClick(et.getText().toString().trim());

            }
        };
        dialog = new AlertDialog.Builder(ct, R.style.MingDialog)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("确定",onClickListener)
                .create();
        return this;
    }

    public ImageCodeDialog show() {
        dialog.show();
        return this;
    }


    public void demo() {
        Log.i("wang", "demo");
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public ImageCodeDialog setPositionListener(onPositionListener liss) {
        this.lis = liss;


        return this;
    }

    public void setImage(Bitmap b) {
        try {
            Log.i("wang", "setImage,pb=" + pb + ",iv=" + iv);
            fl_pb.setVisibility(View.INVISIBLE);
            fl_iv.setVisibility(View.VISIBLE);
            Log.i("wang", "1");
//        fl_pb.setVisibility(View.INVISIBLE);
            Log.i("wang", "2");

            Log.i("wang", "3");

            iv.setImageBitmap(b);

            iv.setImageBitmap(b);
            Log.i("wang", "setImage,pb=" + pb + ",iv=" + iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface onPositionListener {
        void onClick(String code);
    }

    public Observable<ImageCodeDialog> getDialogObservable(final Context ct,final onPositionListener lis) {
        return Observable.create(new Observable.OnSubscribe<ImageCodeDialog>() {
            @Override
            public void call(Subscriber<? super ImageCodeDialog> subscriber) {
                subscriber.onNext(getInstance().create(ct,lis));
            }
        });
    }

    public Observable<String> getContentObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                setPositionListener(new onPositionListener() {
                    @Override
                    public void onClick(String code) {
                        subscriber.onNext(code);
                    }
                });
            }
        });
    }
}

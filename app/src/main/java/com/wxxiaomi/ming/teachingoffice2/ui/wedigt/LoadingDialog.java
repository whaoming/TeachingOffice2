package com.wxxiaomi.ming.teachingoffice2.ui.wedigt;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.wxxiaomi.ming.teachingoffice2.R;

/**
 * Created by 12262 on 2016/5/23.
 */
public class LoadingDialog{
    /**
     * 是否可取消
     */
    private boolean cancle;

    AlertDialog alertDialog;


    public static LoadingDialog create(Context context){
        LoadingDialog loading = new LoadingDialog();
        loading.creatBaseDialog(context);
        return loading;
    }

   private void creatBaseDialog(Context context){
       View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
       alertDialog = new AlertDialog.Builder(context, R.style.MingDialog)
               .setView(view)
               .create();
   }

    public void show(){
        alertDialog.show();
    }

    public LoadingDialog setTitle(String content){
        alertDialog.setTitle(content);
        return this;
    }

    public LoadingDialog dismiss(){
        alertDialog.dismiss();
        return this;
    }

}

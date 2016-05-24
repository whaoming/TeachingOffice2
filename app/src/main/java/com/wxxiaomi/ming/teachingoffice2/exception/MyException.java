package com.wxxiaomi.ming.teachingoffice2.exception;

/**
 * Created by 12262 on 2016/5/18.
 */
public class MyException extends Exception{

    /**
     * office登录超时
     */
    public static final int OFFICE_OUT_LOGIN = 1;
    /**
     * office登录过程账户名或者密码出错
     */
    public static final int OFFICE_USER_ERROR = 2;
    /**
     * 在解析html的过程发生错误
     */
    public static final int PARSE_HTML_ERROR = 3;
    /**
     * 图书馆登录超时
     */
    public static final int LIB_OUT_LOGIN = 5;
    /**
     * 图书馆账户用户名或者密码出错
     */
    public static final int LIB_LOGIN_ERROR = 6;
    /**
     * 访问网络异常
     */
    public static final int CONNECT_ERROR = 4;

    private int errorCode;
    /**
     * 错误信息
     */
    private String info;
    /**
     * 用于view展示的错误信息
     */
    private String viewInfo;
    private String TAG = "MyException";

    public MyException(String TAG,int errorCode){
        this.errorCode = errorCode;
        this.TAG = TAG;
    }

    /**
     * 获取view展示的错误信息
     * @return
     */
    public String getViewErrorInfo(){
        String result = "";
        switch (errorCode){
            case OFFICE_OUT_LOGIN:
                result = "登录超时";
                break;
            case OFFICE_USER_ERROR:
                result = "账号或密码出错";
                break;
            case PARSE_HTML_ERROR:
                result = "网络出错";
                break;
            case CONNECT_ERROR:
                result = "连接网络异常";
                break;
        }
        return result;
    }

    /**
     * 获取底层错误信息
     * @return
     */
    public String getBasicErrorInfo(){
        String result = "";
        switch (errorCode){
            case OFFICE_OUT_LOGIN:
                result = ":第二次登录失败";
                break;
            case OFFICE_USER_ERROR:
                result = "账号或密码出错";
                break;
            case PARSE_HTML_ERROR:
                result = "html解析出错";
                break;
            case CONNECT_ERROR:
                result = "网络异常";
                break;
        }
        return TAG+result;
    }
}

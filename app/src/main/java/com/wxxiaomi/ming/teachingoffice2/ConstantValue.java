package com.wxxiaomi.ming.teachingoffice2;

public class ConstantValue {

	public static String ENCODING = "UTF-8";
	public static String Host = "http://210.38.162.116/";
	public static String tempOfficeUrl = "";

	public static String LibHost = "http://210.38.162.2/opac/user/";

	public static boolean isLibLogin = false;
	public static boolean isOfficeLogin = false;
	public static boolean isRemLibLogin = false;
	public static boolean isRemOfficeLogin = false;
	public static String LIBURL = "http://210.38.162.2/OPAC/login.aspx?ReturnUrl=/opac/user/userinfo.aspx";
	public static String LIBHOST = "http://210.38.162.2";
	public static String LIBSEARCHURL = "http://210.38.162.2/OPAC/search.aspx";
	
	
	public static String LIBMAIN = "http://210.38.162.2/OPAC/";
	
	/**
	 * 图书馆cookie过期
	 */
	public final static int STATE_LIBOUTTIME = 10;
	
	/**
	 * 图书馆记住密码，第一次点击未登录的情况
	 */
	public final static int LIBFIRSTNOLOGIN = 11;
	
	/**
	 * 图书馆搜索页面ajax地址
	 */
	public static String LIBASEARCHRESULTAJAX = "showpageforlucenesearchAjax.aspx";
	public static int CLICKTHESAMEFRAGMENT = 12;
	
}

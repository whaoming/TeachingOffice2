package com.wxxiaomi.ming.teachingoffice2.bean.html;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class Html_lib_Login {

	private String url;
	private String formUrl;
	private String cookie;
	private String codePicUrl;
	private String loginUrl;
	private Bitmap picCode;
	private Map<String,String> loginPars = new HashMap<String, String>();
	
	
	
	
	public Bitmap getPicCode() {
		return picCode;
	}
	public void setPicCode(Bitmap picCode) {
		this.picCode = picCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getCodePicUrl() {
		return codePicUrl;
	}
	public void setCodePicUrl(String codePicUrl) {
		this.codePicUrl = codePicUrl;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public Map<String, String> getLoginPars() {
		return loginPars;
	}
	public void setLoginPars(Map<String, String> loginPars) {
		this.loginPars = loginPars;
	}
	
}

package com.wxxiaomi.ming.teachingoffice2.bean.html;

import java.util.HashMap;
import java.util.Map;

public class Html_Login {

	private String loginUrl;
	private String url;
	private Map<String,String> loginPars = new HashMap<String,String>();
	
	private String tempUrl;
	public String getTempUrl() {
		return tempUrl;
	}

	public void setTempUrl(String tempUrl) {
		this.tempUrl = tempUrl;
	}
	
	public Map<String, String> getLoginPars() {
		return loginPars;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public String toString() {
		return "Html_Login [loginUrl=" + loginUrl + ", url=" + url
				+ ", loginPars=" + loginPars + "]";
	}
	
}

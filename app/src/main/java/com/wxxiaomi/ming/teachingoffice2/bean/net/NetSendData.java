package com.wxxiaomi.ming.teachingoffice2.bean.net;


import java.util.HashMap;
import java.util.Map;

public class NetSendData {

	private String url;
	private String content;
	private Map<String,String> headers = new HashMap<String,String>();
	private Map<String,String> parmars = new HashMap<String,String>();
	private String pars;
	private String host;
	
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPars() {
		return pars;
	}
	public void setPars(String pars) {
		this.pars = pars;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public Map<String, String> getParmars() {
		return parmars;
	}
	public void setParmars(Map<String, String> parmars) {
		this.parmars = parmars;
	}

	@Override
	public String toString() {
		return "NetSendData{" +
				"url='" + url + '\'' +
				", content='" + content + '\'' +
				", headers=" + headers +
				", parmars=" + parmars +
				", pars='" + pars + '\'' +
				", host='" + host + '\'' +
				'}';
	}
}

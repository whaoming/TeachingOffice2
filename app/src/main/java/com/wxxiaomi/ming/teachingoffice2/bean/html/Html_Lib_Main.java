package com.wxxiaomi.ming.teachingoffice2.bean.html;


import com.wxxiaomi.ming.teachingoffice2.bean.LibUserInfo;

import java.io.Serializable;


public class Html_Lib_Main implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookBorrowedUrl;
	private String cookie;
	private LibUserInfo userInfo;
	private String borrowHistoryUrl;
	
	private String username;
	private String password;
	
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBorrowHistoryUrl() {
		return borrowHistoryUrl;
	}
	public void setBorrowHistoryUrl(String borrowHistoryUrl) {
		this.borrowHistoryUrl = borrowHistoryUrl;
	}
	public LibUserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(LibUserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getBookBorrowedUrl() {
		return bookBorrowedUrl;
	}
	public void setBookBorrowedUrl(String bookBorrowedUrl) {
		this.bookBorrowedUrl = bookBorrowedUrl;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	
}

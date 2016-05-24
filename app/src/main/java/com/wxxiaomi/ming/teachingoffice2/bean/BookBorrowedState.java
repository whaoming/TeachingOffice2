package com.wxxiaomi.ming.teachingoffice2.bean;

public class BookBorrowedState {

	/**
	 * <td width="5%">续借</td>
                  <td width="10%">最迟应还期</td>
                  <td width="35%">题名／著者</td>
                  <td width="5%">卷期</td>
                  <td width="8%">图书类型</td>
                  <td width="7%">登录号</td>
                  <td width="10%">借期</td>
	 */
	/**
	 * 最迟应还期
	 */
	private String latestReturn;
	/**
	 * 题名／著者
	 */
	private String name;
	/**
	 * 卷期
	 */
	private String juanqi;
	/**
	 * 图书类型
	 */
	private String type;
	/**
	 * 登录号
	 */
	private String loginNumber;
	/**
	 * 借期
	 */
	private String borrowedTime;
	/**
	 * 图书地址
	 */
	private String bookInfoUrl;
	
	
	public String getBookInfoUrl() {
		return bookInfoUrl;
	}
	public void setBookInfoUrl(String bookInfoUrl) {
		this.bookInfoUrl = bookInfoUrl;
	}
	public String getLatestReturn() {
		return latestReturn;
	}
	public void setLatestReturn(String latestReturn) {
		this.latestReturn = latestReturn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJuanqi() {
		return juanqi;
	}
	public void setJuanqi(String juanqi) {
		this.juanqi = juanqi;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoginNumber() {
		return loginNumber;
	}
	public void setLoginNumber(String loginNumber) {
		this.loginNumber = loginNumber;
	}
	public String getBorrowedTime() {
		return borrowedTime;
	}
	public void setBorrowedTime(String borrowedTime) {
		this.borrowedTime = borrowedTime;
	}
//	public BookBorrowedState(String latestReturn, String name, String juanqi,
//			String type, String loginNumber, String borrowedTime,int i) {
//		super();
//		this.latestReturn = latestReturn;
//		this.name = name;
//		this.juanqi = juanqi;
//		this.type = type;
//		this.loginNumber = loginNumber;
//		this.borrowedTime = borrowedTime;
//	}
	//历史的
	public BookBorrowedState(String borrowedTime,String latestReturn, String bookInfoUrl ,
				String name, String type,String loginNumber) {
		super();
		this.latestReturn = latestReturn;
		this.name = name;
		this.type = type;
		this.loginNumber = loginNumber;
		this.borrowedTime = borrowedTime;
		this.bookInfoUrl = bookInfoUrl;
	}
	
	//借阅情况的
	public BookBorrowedState(String latestReturn,String name,String juanqi,String type,String loginNumber,String borrowedTime,String bookInfoUrl){
		super();
		this.juanqi = juanqi;
		this.latestReturn = latestReturn;
		this.name = name;
		this.type = type;
		this.loginNumber = loginNumber;
		this.borrowedTime = borrowedTime;
		this.bookInfoUrl = bookInfoUrl;
	}
}

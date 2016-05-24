package com.wxxiaomi.ming.teachingoffice2.bean.html;

import java.io.Serializable;

public class Html_Main implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fromUrl;
	
	

	private String numberAndName;
	/**
	 * 获取课表的url
	 */
	public String classFormUrl;
	/**
	 * 获取成绩的url
	 */
	private String scoreUrl;
	/**
	 * 选课情况url
	 */
	private String electiveCourseUrl;
	

	/**
	 * 个人信息url
	 */
	private String personalInfoUrl;
	
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

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}
	
	
	public String getPersonalInfoUrl() {
		return personalInfoUrl;
	}

	public void setPersonalInfoUrl(String personalInfoUrl) {
		this.personalInfoUrl = personalInfoUrl;
	}

	public String getElectiveCourseUrl() {
		return electiveCourseUrl;
	}

	public void setElectiveCourseUrl(String electiveCourseUrl) {
		this.electiveCourseUrl = electiveCourseUrl;
	}

	public String getScoreUrl() {
		return scoreUrl;
	}

	public void setScoreUrl(String scoreUrl) {
		this.scoreUrl = scoreUrl;
	}

	public String getNumberAndName() {
		return numberAndName;
	}

	public void setNumberAndName(String numberAndName) {
		this.numberAndName = numberAndName;
	}

	public String getClassFormUrl() {
		return classFormUrl;
	}

	public void setClassFormUrl(String classFormUrl) {
		this.classFormUrl = classFormUrl;
	}
	
	
}

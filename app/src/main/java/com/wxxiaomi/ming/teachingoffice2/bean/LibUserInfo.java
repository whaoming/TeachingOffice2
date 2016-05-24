package com.wxxiaomi.ming.teachingoffice2.bean;

import java.io.Serializable;

public class LibUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 证 号： 131110199 
	 * 姓 名： 王浩明
	 *  类 型： 学生 
	 *  单 位： 计算机1305班 
	 *  当前状态 :正常 
	 *  电 话： 
	 *  手 机： 
	 *  备 注：
	 * Email地址： 
	 * 预约取书地点： 
	 * 您的预存金额为： 0.00 元
	 */
	/**
	 * 证 号
	 */
	private String number;
	/**
	 * 姓 名
	 */
	private String name;
	/**
	 * 类 型
	 */
	private String type;
	/**
	 * 单 位
	 */
	private String unit;
	/**
	 * 当前状态
	 */
	private String state;
	/**
	 * 电 话
	 */
	private String phone;
	/**
	 * 手 机
	 */
	private String mobile;
	/**
	 * Email地址
	 */
	private String email;
	/**
	 * 预约取书地点
	 */
	private String location;
	/**
	 * 您的预存金额
	 */
	private String money;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public LibUserInfo(String number, String name, String type, String unit,
			String state, String phone, String mobile, String email,
			String location, String money) {
		super();
		this.number = number;
		this.name = name;
		this.type = type;
		this.unit = unit;
		this.state = state;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.location = location;
		// this.money = Double.valueOf(money);
		this.money = money;
	}

	public LibUserInfo() {
		super();
	}

	@Override
	public String toString() {
		return "LibUserInfo [number=" + number + ", name=" + name + ", type="
				+ type + ", unit=" + unit + ", state=" + state + ", phone="
				+ phone + ", mobile=" + mobile + ", email=" + email
				+ ", location=" + location + ", money=" + money + "]";
	}
}

package com.wxxiaomi.ming.teachingoffice2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称 作者 出版者 出版年 索取号 馆藏 可借 详情
	 */

	public BookInfo() {
		super();
	}

	/**
	 * bookinfourl
	 */
	private String url;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版者
	 */
	private String publishPer;
	/**
	 * 出版年
	 */
	private String publishYear;
	/**
	 * 索取号
	 */
	private String number;
	/**
	 * 馆藏
	 */
	private String collectionCount;
	/**
	 * 可借
	 */
	private String isBorrow;

	/**
	 * 详情
	 */
	private String detail;

	private List<CollectState> collecLocations = new ArrayList<CollectState>();

	public List<CollectState> getCollecLocations() {
		return collecLocations;
	}

	public void setCollecLocations(List<CollectState> collecLocations) {
		this.collecLocations = collecLocations;
	}

	/**
	 * 馆藏状态
	 * 
	 * @author Administrator
	 * 
	 */
	public static class CollectState implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 卷期
		 */
		private String juanqi;

		/**
		 * 年代
		 */
		private String year;

		private String pointState;

		/**
		 * 索取号
		 */
		private String number;

		/**
		 * 类型
		 */
		private String type;

		/**
		 * 状态
		 */
		private String state;

		/**
		 * 登录号
		 */
		private String loginNumber;

		/**
		 * 馆藏地
		 */
		private String collectionLocation;

		public String getJuanqi() {
			return juanqi;
		}

		public void setJuanqi(String juanqi) {
			this.juanqi = juanqi;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getPointState() {
			return pointState;
		}

		public void setPointState(String pointState) {
			this.pointState = pointState;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getLoginNumber() {
			return loginNumber;
		}

		public void setLoginNumber(String loginNumber) {
			this.loginNumber = loginNumber;
		}

		public String getCollectionLocation() {
			return collectionLocation;
		}

		public void setCollectionLocation(String collectionLocation) {
			this.collectionLocation = collectionLocation;
		}

		public CollectState(String collectionLocation,String number, String loginNumber,String juanqi, String year, String state, 
				 String type
				) {
			super();
			this.juanqi = juanqi;
			this.year = year;
			this.number = number;
			this.type = type;
			this.state = state;
			this.loginNumber = loginNumber;
			this.collectionLocation = collectionLocation;
		}
		
		

	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishPer() {
		return publishPer;
	}

	public void setPublishPer(String publishPer) {
		this.publishPer = publishPer;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(String collectionCount) {
		this.collectionCount = collectionCount;
	}

	public String getIsBorrow() {
		return isBorrow;
	}

	public void setIsBorrow(String isBorrow) {
		this.isBorrow = isBorrow;
	}

	public BookInfo(String name, String author, String publishPer,
			String publishYear, String number, String collection,
			String isBorrow) {
		super();
		this.name = name;
		this.author = author;
		this.publishPer = publishPer;
		this.publishYear = publishYear;
		this.number = number;
		this.collectionCount = collection;
		this.isBorrow = isBorrow;
	}

}

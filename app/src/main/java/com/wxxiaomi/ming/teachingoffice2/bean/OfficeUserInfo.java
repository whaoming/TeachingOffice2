package com.wxxiaomi.ming.teachingoffice2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OfficeUserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String __VIEWSTATE;
	private List<UserInfoColumn> columns = new ArrayList<UserInfoColumn>();

	public String get__VIEWSTATE() {
		return __VIEWSTATE;
	}

	public void set__VIEWSTATE(String __VIEWSTATE) {
		this.__VIEWSTATE = __VIEWSTATE;
	}

	
	public List<UserInfoColumn> getEditColumn(List<UserInfoColumn> columns){
		List<UserInfoColumn> columnss = new ArrayList<UserInfoColumn>();
		for(UserInfoColumn u : columns){
			if(u.isEdit){
				columnss.add(u);
			}
		}
		return columnss;
		
	}
	
	public List<UserInfoColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<UserInfoColumn> columns) {
		this.columns = columns;
	}

	public static class UserInfoColumn implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public String toString() {
			return "UserInfoColumn [isEdit=" + isEdit + ", key=" + key
					+ ", name=" + name + ", value=" + value + "]";
		}
		private boolean isEdit;
		private String key;
		private String name;
		private String value;
		public boolean isEdit() {
			return isEdit;
		}
		public void setEdit(boolean isEdit) {
			this.isEdit = isEdit;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
	}
}

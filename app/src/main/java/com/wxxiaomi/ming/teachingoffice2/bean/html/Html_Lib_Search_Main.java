package com.wxxiaomi.ming.teachingoffice2.bean.html;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Html_Lib_Search_Main {

	List<CollectionLocation> locationColumns = new ArrayList<CollectionLocation>();
	List<ComeFrom> comefromColumns = new ArrayList<ComeFrom>();
	private String searchUrl;
//	private String __VIEWSTATE;
//	private String __EVENTVALIDATION;
	Map<String,String> searchPars = new HashMap<String, String>();
	private String fromUrl;
	
	
	
	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public List<CollectionLocation> getLocationColumns() {
		return locationColumns;
	}

	public void setLocationColumns(List<CollectionLocation> locationColumns) {
		this.locationColumns = locationColumns;
	}

	public List<ComeFrom> getComefromColumns() {
		return comefromColumns;
	}

	public void setComefromColumns(List<ComeFrom> comefromColumns) {
		this.comefromColumns = comefromColumns;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public Map<String, String> getSearchPars() {
		return searchPars;
	}

	public void setSearchPars(Map<String, String> searchPars) {
		this.searchPars = searchPars;
	}

	public  class CollectionLocation{
		private String value;
		private String name;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public class ComeFrom{
		private String value;
		private String name;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
}

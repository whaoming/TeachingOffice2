package com.wxxiaomi.ming.teachingoffice2.bean.html;



import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;

import java.util.ArrayList;
import java.util.List;


public class Html_Lib_Search_Result {

	private List<BookInfo> columns = new ArrayList<BookInfo>();
	private String pageUrl;
	private int pageCount;
	private int currentPage;
	private String url;
	private String msg;
	
	
	
	
	
	
	
	
	
	
	
	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(String currentPage) {
		this.currentPage = Integer.valueOf(currentPage);
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(String pageCount) {
		this.pageCount = Integer.valueOf(pageCount);
	}


	public String getPageUrl() {
		return pageUrl;
	}


	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}


	public List<BookInfo> getColumns() {
		return columns;
	}


	public void setColumns(List<BookInfo> columns) {
		this.columns = columns;
	}


	
}

package com.wxxiaomi.ming.teachingoffice2.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Score {

	//参数们
//	private String __EVENTTARGET;
//	private String __EVENTARGUMENT;
//	private String __VIEWSTATE;
//	private String hidLanguage;
//	private String ddlXN;
//	private String ddlXQ;
//	private String ddl_kcxz;
//	private String btn_zcj;
	
	private HashMap<String,String> getHistoryScorePars = new LinkedHashMap<String, String>();
	
	
	public HashMap<String, String> getGetHistoryScorePars() {
		return getHistoryScorePars;
	}

	public void setGetHistoryScorePars(HashMap<String, String> getHistoryScorePars) {
		this.getHistoryScorePars = getHistoryScorePars;
	}

	public List<ScoreColumn> columns = new ArrayList<ScoreColumn>();
	
	
	

	public List<ScoreColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ScoreColumn> columns) {
		this.columns = columns;
	}

	public static class ScoreColumn{
		/**
		 * 学年:schoolyear
		 * 学期:term
		 * 课程代码：classcode
		 * 课程名称：classname
		 * 课程性质：classnature
		 * 课程归属：belong
		 * 学分：credit
		 * 绩点：point/gpa
		 * 成绩：score
		 * 辅修标记：minor
		 * 补考成绩：makeupscore
		 * 重修成绩：rebuilescore
		 * 开课学院：begins
		 * 备注：remarks
		 * 重修标记：rebuiletab
		 */
		/**
		 * 学年
		 */
		private String schoolyear;
		/**
		 * 课程归属
		 */
		private String belong;
		/**
		 * 课程性质
		 */
		private String classnature;
		/**
		 * 学期
		 */
		private int term;
		/**
		 * 课程代码
		 */
		private int classcode;
		/**
		 * 课程名称
		 */
		private String classname;
		/**
		 * 学分
		 */
		private double credit;
		/**
		 * 绩点
		 */
		private double point;
		/**
		 * 成绩
		 */
		private double score;
		/**
		 * 辅修标记
		 */
		private int minor;
		/**
		 * 补考成绩
		 */
		private double makeupscore;
		/**
		 * 重修成绩
		 */
		private double rebuilescore;
		/**
		 * 开课学院
		 */
		private String begins;
		/**
		 * 备注
		 */
		private String remarks;
		/**
		 * 重修标记
		 */
		private int rebuiletab;
		
		public int getRebuiletab() {
			return rebuiletab;
		}
		public void setRebuiletab(int rebuiletab) {
			this.rebuiletab = rebuiletab;
		}
		public String getSchoolyear() {
			return schoolyear;
		}
		public void setSchoolyear(String schoolyear) {
			this.schoolyear = schoolyear;
		}
		public String getBelong() {
			return belong;
		}
		public void setBelong(String belong) {
			this.belong = belong;
		}
		public String getClassnature() {
			return classnature;
		}
		public void setClassnature(String classnature) {
			this.classnature = classnature;
		}
		public int getTerm() {
			return term;
		}
		public void setTerm(int term) {
			this.term = term;
		}
		public int getClasscode() {
			return classcode;
		}
		public void setClasscode(int classcode) {
			this.classcode = classcode;
		}
		public String getClassname() {
			return classname;
		}
		public void setClassname(String classname) {
			this.classname = classname;
		}
		public double getCredit() {
			return credit;
		}
		public void setCredit(double credit) {
			this.credit = credit;
		}
		public double getPoint() {
			return point;
		}
		public void setPoint(double point) {
			this.point = point;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public int getMinor() {
			return minor;
		}
		public void setMinor(int minor) {
			this.minor = minor;
		}
		public double getMakeupscore() {
			return makeupscore;
		}
		public void setMakeupscore(double makeupscore) {
			this.makeupscore = makeupscore;
		}
		public double getRebuilescore() {
			return rebuilescore;
		}
		public void setRebuilescore(double rebuilescore) {
			this.rebuilescore = rebuilescore;
		}
		public String getBegins() {
			return begins;
		}
		public void setBegins(String begins) {
			this.begins = begins;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
		
		public ScoreColumn(String schoolyear, String term, String classcode,String classname,
				String classnature, String belong,
				  String credit,String point,
				  String score, String minor, String makeupscore,
				  String rebuilescore, String begins, String remarks, String rebuiletab) {
			super();
			this.schoolyear = schoolyear;
			this.belong = belong;
			this.classnature = classnature;
			this.term = getInt(term);
			this.classcode = getInt(classcode);
			this.classname = classname;
			this.credit = getDouble(credit);
			this.point = getDouble(point);
			this.score = getDouble(score);
			this.minor = getInt(minor);
			this.makeupscore = getDouble(makeupscore);
			this.rebuilescore = getDouble(rebuilescore);
			this.begins = begins;
			this.remarks = remarks;
			this.rebuiletab = getInt(rebuiletab);
		}
		public Double getDouble(String a){
			try{
				 double b = Double.valueOf(a);
				 return b;
				}catch(Exception e)
				{
				   return 0.0;
				}
		}
		public int getInt(String a){
			try{
				 int b = Integer.valueOf(a);
//				 double b = Double.parseDouble(s);
				 return b;
				}catch(Exception e)
				{
				   return 0;
				}
		}
		
	}
}

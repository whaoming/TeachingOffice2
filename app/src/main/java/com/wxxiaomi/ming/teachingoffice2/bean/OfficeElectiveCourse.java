package com.wxxiaomi.ming.teachingoffice2.bean;

import java.util.ArrayList;
import java.util.List;

public class OfficeElectiveCourse {
	
	public List<ElectiveCourseColumn> columns = new ArrayList<ElectiveCourseColumn>(); 

	public List<ElectiveCourseColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ElectiveCourseColumn> columns) {
		this.columns = columns;
	}

	public static class ElectiveCourseColumn{
		/**
		 * 
    <td>选课课号</td>
    <td>课程代码</td>
    <td>课程名称</td>
    <td>课程性质</td>
    <td>是否选课</td>
    <td>教师姓名</td>
    <td>学分</td>
    <td>周学时</td>
    <td>上课时间</td>
    <td>上课地点</td>
    <td>教材</td>
    <td>修读标记</td>
    <td>授课计划上传次数</td>
    <td>授课计划最近上传时间</td>
    <td>授课计划上传文件名</td>
    <td>授课计划下载</td
		 */
		/**
		 * 选课课号
		 */
		private String number;
		/**
		 * 上课时间
		 */
		private String classTime;
		/**
		 * 课程代码
		 */
		private String code;
		/**
		 * 课程名称
		 */
		private String name;
		/**
		 * 课程性质
		 */
		private String nature;
		/**
		 * 是否选课
		 */
		private String isChoose;
		/**
		 * 教师姓名
		 */
		private String teaName;
		/**
		 * 学分
		 */
		private String creait;
		/**
		 * 周学时
		 */
		private String weekTime;
		/**
		 * 上课地点
		 */
		private String location;
		/**
		 * 教材
		 */
		private String teach;
		/**
		 * 修读标记
		 */
		private String tag;
		/**
		 * 授课计划上传次数
		 */
		private String upCount;
		/**
		 * 授课计划最近上传时间
		 */
		private String nearUpTime;
		/**
		 * 授课计划上传文件名
		 */
		private String upName;


		@Override
		public String toString() {
			return "ElectiveCourseColumn{" +
					"number='" + number + '\'' +
					", classTime='" + classTime + '\'' +
					", code='" + code + '\'' +
					", name='" + name + '\'' +
					", nature='" + nature + '\'' +
					", isChoose='" + isChoose + '\'' +
					", teaName='" + teaName + '\'' +
					", creait='" + creait + '\'' +
					", weekTime='" + weekTime + '\'' +
					", location='" + location + '\'' +
					", teach='" + teach + '\'' +
					", tag='" + tag + '\'' +
					", upCount='" + upCount + '\'' +
					", nearUpTime='" + nearUpTime + '\'' +
					", upName='" + upName + '\'' +
					'}';
		}

		public String getClassTime() {
			return classTime;
		}
		public void setClassTime(String classTime) {
			this.classTime = classTime;
		}
		public ElectiveCourseColumn(String number, String code, String name,
				String nature, String isChoose, String teaName, String creait,
				String weekTime, String classTime,String location, String teach, String tag,
				String upCount, String nearUpTime, String upName) {
			super();
			this.number = number;
			this.code = code;
			this.name = name;
			this.nature = nature;
			this.isChoose = isChoose;
			this.teaName = teaName;
			this.creait = creait;
			this.weekTime = weekTime;
			this.classTime = classTime;
			this.location = location;
			this.teach = teach;
			this.tag = tag;
			this.upCount = upCount;
			this.nearUpTime = nearUpTime;
			this.upName = upName;
		}
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNature() {
			return nature;
		}
		public void setNature(String nature) {
			this.nature = nature;
		}
		public String getIsChoose() {
			return isChoose;
		}
		public void setIsChoose(String isChoose) {
			this.isChoose = isChoose;
		}
		public String getTeaName() {
			return teaName;
		}
		public void setTeaName(String teaName) {
			this.teaName = teaName;
		}
		public String getCreait() {
			return creait;
		}
		public void setCreait(String creait) {
			this.creait = creait;
		}
		public String getWeekTime() {
			return weekTime;
		}
		public void setWeekTime(String weekTime) {
			this.weekTime = weekTime;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getTeach() {
			return teach;
		}
		public void setTeach(String teach) {
			this.teach = teach;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public String getUpCount() {
			return upCount;
		}
		public void setUpCount(String upCount) {
			this.upCount = upCount;
		}
		public String getNearUpTime() {
			return nearUpTime;
		}
		public void setNearUpTime(String nearUpTime) {
			this.nearUpTime = nearUpTime;
		}
		public String getUpName() {
			return upName;
		}
		public void setUpName(String upName) {
			this.upName = upName;
		}
		
		
	}
}

package com.wxxiaomi.ming.teachingoffice2.presenter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.bean.OfficeElectiveCourse;

import java.util.List;


public class OfficeElectiveCourseAdapter extends BaseExpandableListAdapter {

	/**
	 * expandGroup (int groupPos) ;//在分组列表视图中 展开一组， setSelectedGroup (int
	 * groupPosition) ;//设置选择指定的组。
	 * 
	 * setSelectedChild (int groupPosition, int childPosition, boolean
	 * shouldExpandGroup);//设置选择指定的子项。
	 * 
	 * getPackedPositionGroup (long packedPosition);//返回所选择的组
	 * 
	 * getPackedPositionForChild (int groupPosition, int childPosition)
	 * ;//返回所选择的子项
	 * 
	 * getPackedPositionType (long packedPosition);//返回所选择项的类型（Child,Group）
	 * 
	 * isGroupExpanded (int groupPosition);//判断此组是否展开
	 */

//	private Context context;
	private List<OfficeElectiveCourse.ElectiveCourseColumn> list;
	private LayoutInflater inflater;

	public OfficeElectiveCourseAdapter(Context ct, LayoutInflater inflater,
									   List<OfficeElectiveCourse.ElectiveCourseColumn> list) {
//		this.context = ct;
		this.list = list;
		this.inflater = inflater;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ParentViewHolder holder = null;
		OfficeElectiveCourse.ElectiveCourseColumn column = list.get(groupPosition);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_office_electivecourse, null);
			holder = new ParentViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
			convertView.setTag(holder);
		}else{
			holder = (ParentViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(column.getName());
		holder.tv_time.setText("上课时间："+column.getClassTime());
		holder.tv_location.setText("上课地点："+column.getLocation());
		if (isExpanded) {
			holder.iv.setBackgroundResource(R.mipmap.group_up);
		} else {
			holder.iv.setBackgroundResource(R.mipmap.group_down);
		}
		return convertView;
	}
	public class ParentViewHolder{
		TextView tv_name;
		TextView tv_time;
		TextView tv_location;
		ImageView iv;
		
		
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder holder = null;
		OfficeElectiveCourse.ElectiveCourseColumn column = list.get(groupPosition);
		if(convertView == null){
			holder = new ChildViewHolder();
			convertView = inflater.inflate(R.layout.item_office_elective_course_child, null);
			holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
			holder.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
			holder.tv_teaName = (TextView) convertView.findViewById(R.id.tv_teaName);
			holder.tv_creait = (TextView) convertView.findViewById(R.id.tv_creait);
			holder.tv_weekTime = (TextView) convertView.findViewById(R.id.tv_weekTime);
			convertView.setTag(holder);
		}else{
			holder = (ChildViewHolder) convertView.getTag();
		}
		holder.tv_number.setText("选课课号："+column.getNumber());
		holder.tv_code.setText("课程代码："+column.getCode());
		holder.tv_teaName.setText("教师姓名："+column.getTeaName());
		holder.tv_creait.setText("学分："+column.getCreait());
		holder.tv_weekTime.setText("周学时："+column.getWeekTime());
		return convertView;
	}
	
	public class ChildViewHolder {
		TextView tv_number;
		TextView tv_code;
		TextView tv_teaName;
		TextView tv_creait;
		TextView tv_weekTime;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}

package com.wxxiaomi.ming.teachingoffice2.presenter.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.bean.Score;

import java.util.List;


public class OfficeScoreAdapter extends BaseExpandableListAdapter {

	private List<Score.ScoreColumn> list;
	private LayoutInflater inflater;
	
	
	
	public OfficeScoreAdapter(List<Score.ScoreColumn> list, LayoutInflater inflater) {
		super();
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
		Score.ScoreColumn column = list.get(groupPosition);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_office_score_column, null);
			holder = new ParentViewHolder();
			holder.tv_score = (TextView) convertView.findViewById(R.id.tv_score);
			holder.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
			convertView.setTag(holder);
		}else{
			holder = (ParentViewHolder) convertView.getTag();
		}
		holder.tv_subject.setText(column.getClassname());
		holder.tv_score.setText(column.getScore()+"");
		return convertView;
	}
	public class ParentViewHolder{
		TextView tv_subject;
		TextView tv_score;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChirdViewHolder holder = null;
		Score.ScoreColumn column = list.get(groupPosition);
		if(convertView == null){
			holder = new ChirdViewHolder();
			convertView = inflater.inflate(R.layout.item_office_score_column_child, null);
			holder.tv_begins = (TextView) convertView.findViewById(R.id.tv_begins);
			holder.tv_term = (TextView) convertView.findViewById(R.id.tv_term);
			holder.tv_classcode = (TextView) convertView.findViewById(R.id.tv_classcode);
			holder.tv_classnature = (TextView) convertView.findViewById(R.id.tv_classnature);
			holder.tv_credit = (TextView) convertView.findViewById(R.id.tv_credit);
			holder.tv_point = (TextView) convertView.findViewById(R.id.tv_point);
			holder.tv_schoolyear = (TextView) convertView.findViewById(R.id.tv_schoolyear);
			convertView.setTag(holder);
		}else{
			holder = (ChirdViewHolder) convertView.getTag();
		}
		holder.tv_begins.setText("开课学院："+column.getBegins());
		holder.tv_term.setText("学期："+column.getTerm());
		holder.tv_classcode.setText("课程代码："+column.getClasscode());
		holder.tv_classnature.setText("课程性质："+column.getClassnature());
		holder.tv_credit.setText("学分："+column.getCredit());
		holder.tv_point.setText("绩点："+column.getPoint());
		holder.tv_schoolyear.setText("学年："+column.getSchoolyear());
		return convertView;
	}
	public class ChirdViewHolder{
		TextView tv_schoolyear;
		TextView tv_term;
		TextView tv_classcode;
		TextView tv_classnature;
		TextView tv_credit;
		TextView tv_point;
		TextView tv_begins;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}


}

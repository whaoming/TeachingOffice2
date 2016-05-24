package com.wxxiaomi.ming.teachingoffice2.presenter.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.bean.BookBorrowedState;


public class LibBorrowStateColumnAdapter extends BaseAdapter {

	private Context context;
	private List<BookBorrowedState> list;

	public LibBorrowStateColumnAdapter(Context ct,List<BookBorrowedState> list) {
		this.context = ct;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
//		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		BookBorrowedState column = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.item_lib_borrow_state, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_latesttime = (TextView) convertView.findViewById(R.id.tv_latesttime);
			holder.tv_borrowtime = (TextView) convertView.findViewById(R.id.tv_borrowtime);
			holder.tv_loginnumber = (TextView) convertView.findViewById(R.id.tv_loginnumber);
			holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(column.getName());
		holder.tv_latesttime.setText("最迟应还期："+column.getLatestReturn());
		holder.tv_borrowtime.setText("借期："+column.getBorrowedTime());
		holder.tv_loginnumber.setText("登录号："+column.getLoginNumber());
		holder.tv_type.setText("图书类型："+column.getType());
		return convertView;
	}

	public class ViewHolder {
		TextView tv_name;
		TextView tv_latesttime;
		TextView tv_borrowtime;
		TextView tv_loginnumber;
		TextView tv_type;
	}

}

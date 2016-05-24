package com.wxxiaomi.ming.teachingoffice2.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.bean.BookInfo;
import com.wxxiaomi.ming.teachingoffice2.presenter.adapter.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BookSearchAdapter extends BaseRecyclerViewAdapter {

    private Context ct;
    private List<BookInfo> list;

    public BookSearchAdapter(Context context) {
        super(context);
        this.ct = context;
        list = new ArrayList<BookInfo>();
    }

    public void updateList(List<BookInfo> ls){
        list.addAll(ls);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderOnChild(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ct).inflate(R.layout.item_lib_searchresult,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolderOnChild(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            final BookInfo column = list.get(position);
            holder.tv_name.setText(column.getName());
            holder.tv_info.setText("信息："+column.getAuthor());
            holder.tv_collectCount.setText("馆藏："+column.getCollectionCount());
            holder.tv_number.setText("索取号："+column.getNumber());
            holder.tv_borrow.setText("可借："+column.getIsBorrow());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(column);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCountOnChild() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public TextView tv_info;
        public TextView tv_collectCount;
        public TextView tv_number;
        public TextView tv_borrow;
        public View view;

        public ItemViewHolder(View view) {
            super(view);
            this.view = view;
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_info = (TextView) view.findViewById(R.id.tv_info);
            tv_collectCount = (TextView) view.findViewById(R.id.tv_collectCount);
            tv_number = (TextView) view.findViewById(R.id.tv_number);
            tv_borrow = (TextView) view.findViewById(R.id.tv_borrow);
        }

    }
}

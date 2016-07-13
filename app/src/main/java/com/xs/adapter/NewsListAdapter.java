package com.xs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xs.app.R;
import com.xs.model.NewsItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsListAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<NewsItem> NewsItems = new ArrayList<>();
	
	
	public NewsListAdapter(Context mContext, List<NewsItem> NewsItems) {
		super();
		this.mContext = mContext;
		this.NewsItems = NewsItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NewsItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return NewsItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (convertView==null) {			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item,null);
			
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView.findViewById(R.id.textView1);
			viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
			viewHolder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
			convertView.setTag(viewHolder);
						
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.textView.setText(NewsItems.get(position).getTitle());
		viewHolder.textView2.setText(NewsItems.get(position).getSource());
		viewHolder.textView3.setText(new Date(NewsItems.get(position).getBehot_time()).toLocaleString());
		
		return convertView;
	}

	protected static class ViewHolder{
		TextView textView;
		TextView textView2;
		TextView textView3;
	}
}

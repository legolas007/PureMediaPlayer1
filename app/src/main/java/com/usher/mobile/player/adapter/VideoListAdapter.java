package com.usher.mobile.player.adapter;

import com.usher.mobile.player.R;
import com.usher.mobile.player.bean.VideoItem;
import com.usher.mobile.player.util.Utils;

import android.content.Context;
import android.database.Cursor;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VideoListAdapter extends CursorAdapter {

	public VideoListAdapter(Context context, Cursor c) {
		super(context, c);
	}

	// ����һ��View
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// ����һ��View
		View view = View.inflate(context, R.layout.adapter_video_list, null);
		
		// ��һ��ViewHolder����View�е��ӿؼ�
		ViewHolder holder = new ViewHolder();
		holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
		holder.tv_duration = (TextView) view.findViewById(R.id.tv_duration);
		holder.tv_size = (TextView) view.findViewById(R.id.tv_size);
		
		// ��ViewHolder���浽View��
		view.setTag(holder);
		return view;
	}

	// ��cursor�е����ݰ󶨵�View�Ͻ�����ʾ
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		
		VideoItem item = VideoItem.fromCursor(cursor);
		holder.tv_title.setText(item.getTitle());
		holder.tv_size.setText(Formatter.formatFileSize(context, item.getSize()));
		CharSequence time = Utils.formatMillis(item.getDuration());
		holder.tv_duration.setText(time);
	}
	
	class ViewHolder {
		TextView tv_title;
		TextView tv_duration;
		TextView tv_size;
	}

}

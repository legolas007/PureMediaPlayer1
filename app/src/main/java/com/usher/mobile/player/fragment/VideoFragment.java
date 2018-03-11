package com.usher.mobile.player.fragment;

import java.util.ArrayList;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Video.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.usher.mobile.player.R;
import com.usher.mobile.player.activity.VideoPlayerActivity;
import com.usher.mobile.player.adapter.VideoListAdapter;
import com.usher.mobile.player.bean.VideoItem;
import com.usher.mobile.player.interfaces.Keys;

public class VideoFragment extends BaseFragment {

	private ListView listView;

	@Override
	public int getLayoutResID() {
		return R.layout.fragment_media_list;
	}

	@Override
	public void initView() {
		listView = (ListView) rootView;
	}

	@Override
	public void initListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			/**
			 * parent��������ListView
			 * view �����item��View
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				ArrayList<VideoItem> videos = getVideoItems(cursor);
				enterVideoPlayerActivity(videos, position);
			}
		});
	}

	/***
	 * ��Cursor������������ݷ�װ��һ��ArrayList��
	 * @param cursor
	 * @return
	 */
	protected ArrayList<VideoItem> getVideoItems(Cursor cursor) {
		ArrayList<VideoItem> videos = new ArrayList<VideoItem>();
		cursor.moveToFirst();
		do {
			videos.add(VideoItem.fromCursor(cursor));
		} while (cursor.moveToNext());
		
		return videos;
	}

	/**
	 * ������Ƶ���Ž���
	 * @param videos
	 * @param position
	 */
	protected void enterVideoPlayerActivity(ArrayList<VideoItem> videos, int position) {
		Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
		intent.putExtra(Keys.ITEMS, videos);
		intent.putExtra(Keys.CURRENT_POSITION, position);
		startActivity(intent);
	}

	@Override
	public void initData() {
		// �����ѯ���������������߳�
		// getActivity().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder)
		AsyncQueryHandler queryHandler = new AsyncQueryHandler(getActivity().getContentResolver()) {
			
			// ��ѯ�����ݵĻص�������������������������߳�
			@Override
			protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
				// Utils.printCursor(cursor);
				VideoListAdapter adapter = new VideoListAdapter(getActivity(), cursor);
				listView.setAdapter(adapter);
			}
		};
		
		int token = 0;			// �൱��Message.what
		Object cookie = null;	// �൱��Message.obj
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		String[] projection = {	// ָ��Ҫ��ѯ��Щ��
				Media._ID, Media.TITLE, Media.DURATION, Media.SIZE, Media.DATA,
		};
		String selection = null;				// ָ����ѯ����
		String[] selectionArgs = null;			// ָ����ѯ�����еĲ���
		String orderBy = Media.TITLE + " ASC";	//  ָ��Ϊ����
		// �����ѯ���������������߳�
		queryHandler.startQuery(token, cookie, uri, projection, selection, selectionArgs, orderBy);
	}

	@Override
	public void onClick(View v, int id) {
		
	}

}











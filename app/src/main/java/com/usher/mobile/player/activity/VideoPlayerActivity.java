package com.usher.mobile.player.activity;

import java.util.ArrayList;

import com.usher.mobile.player.R;
import com.usher.mobile.player.bean.VideoItem;
import com.usher.mobile.player.interfaces.Keys;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.View;
import android.widget.VideoView;

public class VideoPlayerActivity extends BaseActivity {

	private VideoView videoView;

	@Override
	public int getLayoutResID() {
		return R.layout.activity_video_player;
	}

	@Override
	public void initView() {
		videoView = findView(R.id.video_view);
	}

	@Override
	public void initListener() {
		videoView.setOnPreparedListener(mOnPreparedListener);
	}

	@Override
	public void initData() {
		ArrayList<VideoItem> videos = (ArrayList<VideoItem>) getIntent().getSerializableExtra(Keys.ITEMS);
		int currentPosition = getIntent().getIntExtra(Keys.CURRENT_POSITION, -1);
		if (videos == null || videos.isEmpty() || currentPosition == -1) {
			return;
		}
		
 		VideoItem videoItem = videos.get(currentPosition);
		String path = videoItem.getData();
		videoView.setVideoPath(path);
		// videoView.setMediaController(new MediaController(this));
	}

	@Override
	public void onClick(View v, int id) {
		switch (id) {
		case R.id.btn_voice:	// ������ť
			showToast("����");
			break;
		case R.id.btn_exit:		// �˳���ť
			showToast("�˳�");
			break;
		case R.id.btn_pre:		// ��һ�װ�ť
			showToast("��һ��");
			break;
		case R.id.btn_play:		// ���Ű�ť
			showToast("����");
			break;
		case R.id.btn_next:		// ��һ�װ�ť
			showToast("��һ��");
			break;
		case R.id.btn_fullscreen:		// ȫ����ť
			showToast("ȫ��");
			break;

		default:
			break;
		}
	}
	
	
	OnPreparedListener mOnPreparedListener = new OnPreparedListener() {
		
		@Override
		public void onPrepared(MediaPlayer mp) {
			videoView.start();
		}
	};

}

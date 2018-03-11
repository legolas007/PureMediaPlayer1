package com.usher.mobile.player.activity;

import com.usher.mobile.player.R;

import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class SplashActivity extends BaseActivity {

	private Handler handler;

	@Override
	public int getLayoutResID() {
		return R.layout.activity_splash;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initListener() {
		
	}

	@Override
	public void initData() {
		delayEnterHome();
	}
	
	/**
	 * 延迟3秒种之后进入首页
	 */
	private void delayEnterHome() {
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				enterHome();
			}
		}, 1000);
	}

	/**
	 * 进入首页
	 */
	protected void enterHome() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v, int id) {

	}
	
	@Override
	public void onBackPressed() {
		 // super.onBackPressed();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			handler.removeCallbacksAndMessages(null);
			enterHome();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}

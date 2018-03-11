package com.usher.mobile.player.activity;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.usher.mobile.player.R;
import com.usher.mobile.player.adapter.MainAdapter;
import com.usher.mobile.player.fragment.AudioFragment;
import com.usher.mobile.player.fragment.VideoFragment;
import com.usher.mobile.player.util.Utils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class MainActivity extends BaseActivity {

	private TextView tv_video;
	private TextView tv_audio;
	private View view_indicator;
	private ViewPager view_pager;
	private int indicatorWidth;

	@Override
	public int getLayoutResID() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		tv_video = findView(R.id.tv_video);
		tv_audio = findView(R.id.tv_audio);
		view_indicator = findView(R.id.view_indicator);
		view_pager = findView(R.id.view_pager);
		initIndicator();
	}

	/** 初始化指示器 */
	private void initIndicator() {
		int screenWidth = Utils.getScreenWidth(this);
		indicatorWidth = screenWidth / 2;
		view_indicator.getLayoutParams().width = indicatorWidth;
		view_indicator.requestLayout();	// 通知这个View去更新它的布局参数
	}

	@Override
	public void initListener() {
		tv_video.setOnClickListener(this);
		tv_audio.setOnClickListener(this);
		view_pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			// 当某一页被选择的时候
			@Override
			public void onPageSelected(int position) {
				changeTitleTextState(position == 0);
			}
			
			// 当页面滚动的时候
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				scrollIndicator(position, positionOffset);
			}
			
			// 当页面滚动状态发生改变的时候
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}

	/**
	 * 滚动指示线
	 * @param position
	 * @param positionOffset
	 */
	protected void scrollIndicator(int position, float positionOffset) {
		float translationX = indicatorWidth * position + indicatorWidth * positionOffset;
		ViewHelper.setTranslationX(view_indicator, translationX);
	}

	@Override
	public void initData() {
		changeTitleTextState(true);
		initViewPager();
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new VideoFragment());
		fragments.add(new AudioFragment());
		MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), fragments);
		view_pager.setAdapter(adapter);
	}

	@Override
	public void onClick(View v, int id) {
		switch (id) {
		case R.id.tv_video:
			view_pager.setCurrentItem(0);
			break;
		case R.id.tv_audio:
			view_pager.setCurrentItem(1);
			break;

		default:
			break;
		}
	}

	/**
	 * 改变标题状态
	 * @param isSelectVideo 是否选择了视频
	 */
	private void changeTitleTextState(boolean isSelectVideo) {
		// 改变文本颜色
		tv_video.setSelected(isSelectVideo);
		tv_audio.setSelected(!isSelectVideo);
		
		// 改变文本大小
		scaleTitle(isSelectVideo ? 1.2f : 1.0f, tv_video);
		scaleTitle(!isSelectVideo ? 1.2f : 1.0f, tv_audio);
	}

	/**
	 * 缩放标题
	 * @param scale 缩放的比例
	 * @param textView
	 */
	private void scaleTitle(float scale, TextView textView) {
		ViewPropertyAnimator.animate(textView).scaleX(scale).scaleY(scale);
	}


}

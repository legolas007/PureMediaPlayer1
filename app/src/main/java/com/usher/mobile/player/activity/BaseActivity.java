package com.usher.mobile.player.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.usher.mobile.player.R;
import com.usher.mobile.player.interfaces.UiOperation;
import com.usher.mobile.player.util.Utils;

/**
 * activity�Ļ��࣬������ActivityӦ�ü̳������
 *
 */
public abstract class BaseActivity extends FragmentActivity implements UiOperation {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getLayoutResID());	// ��̬
		View rootView = findViewById(android.R.id.content);	// android.R.id.content ���id���Ի�ȡ��Activity�ĸ�View
		Utils.setButtonOnClickListener(rootView, this);
		initView();
		initListener();
		initData();
	}
	
	/**
	 * ����View�������������ʡȥ���ǵ�ǿת����
	 * @param id
	 * @return
	 */
	public <T> T findView(int id) {
		@SuppressWarnings("unchecked")
		T view = (T) findViewById(id);
		return view;
	}
	
	/**
	 * ����Ļ�м���ʾһ��Toast
	 * @param text
	 */
	public void showToast(String text) {
		Utils.showToast(this, text);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:	// ����ͬ����
			finish();
			break;
		default:
			// ��������Ĳ��Ƿ��ذ�ť������������ȥ������
			onClick(v, v.getId());
			break;
		}
	}
	
}

package com.usher.mobile.player.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usher.mobile.player.R;
import com.usher.mobile.player.interfaces.UiOperation;
import com.usher.mobile.player.util.Utils;

/***
 * Fragment�Ļ��࣬����FragmentӦ�ü̳������
 * @author dzl
 *
 */
public abstract class BaseFragment extends Fragment implements UiOperation {

	protected View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(getLayoutResID(), null);
		Utils.setButtonOnClickListener(rootView, this);
		initView();
		initListener();
		initData();
		return rootView;
	}
	
	/**
	 * ����View�������������ʡȥ���ǵ�ǿת����
	 * @param id
	 * @return
	 */
	public <T> T findView(int id) {
		@SuppressWarnings("unchecked")
		T view = (T) rootView.findViewById(id);
		return view;
	}
	
	/**
	 * ����Ļ�м���ʾһ��Toast
	 * @param text
	 */
	public void showToast(String text) {
		Utils.showToast(getActivity(), text);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:	// ����ͬ����
			getActivity().finish();
			break;
		default:
			// ��������Ĳ��Ƿ��ذ�ť������������ȥ������
			onClick(v, v.getId());
			break;
		}
	}
}

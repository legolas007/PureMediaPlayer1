package com.usher.mobile.player.interfaces;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Ui�����ӿ�
 *
 */
public interface UiOperation extends OnClickListener {
	
	/** ����һ��������ʾ����Ĳ���id */
	int getLayoutResID();
	
	/** ��ʼ��View��findViewById�Ĵ���д������������� */
	void initView();
	
	/** ��ʼ�������� */
	void initListener();
	
	/** ��ʼ�����ݣ�����ʾ�������� */
	void initData();
	
	/**
	 * �����¼�����������д���
	 * @param v �����Ŀؼ�
	 * @param id �����ؼ���id
	 */
	void onClick(View v, int id);
}

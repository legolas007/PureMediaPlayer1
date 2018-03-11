package com.usher.mobile.player.util;

import android.util.Log;

public class Logger {

	public static boolean isShowLog = true;
	
	/**
	 * ��ʾ��Ϣ������ռ�
	 */
	public static void i(Object objTag, String msg) {
		if (!isShowLog) {
			return;
		}
		
		String tag;
		// �����String����ֱ��ʹ�ã����������������ʹ��������������
		if (objTag instanceof String) {
			tag = (String) objTag;
		} else if (objTag instanceof Class) {
			tag = ((Class) objTag).getSimpleName();
		} else {
			tag = objTag.getClass().getSimpleName();
		}
		
		
		Log.i(tag, msg);
	}

}

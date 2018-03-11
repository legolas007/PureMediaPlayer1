package com.usher.mobile.player.util;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.usher.mobile.player.interfaces.Constants;

public class Utils {

	/**
	 * ����Button��ImageButton�����õ���������
	 * @param view
	 * @param listener ����������
	 */
	public static void setButtonOnClickListener(View view, OnClickListener listener) {
		// ����view�������е�Button��ImageButton
		if (view instanceof Button || view instanceof ImageButton) {
			view.setOnClickListener(listener);
		} else if (view instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) view;
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				View child = viewGroup.getChildAt(i);
				setButtonOnClickListener(child, listener);
			}
		}
	}
	
	/**
	 * ����Ļ�м���ʾһ��Toast
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * ��ȡ��Ļ���
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		return screenWidth;
	}
	
	/**
	* ��ȡ��Ļ���
	* @param context
	* @return
	*/
	public static int getScreenHeight(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		return screenHeight;
	}

	
	/**
	 * ����Cursor�����еļ�¼
	 * @param cursor
	 */
	public static void printCursor(Cursor cursor) {
		if (cursor == null) {
			return ;
		}
		
		Logger.i(Utils.class, "����" + cursor.getCount() + "������");
		// �������е���
		while (cursor.moveToNext()) {
			
			// �������е���
			Logger.i(Utils.class, "---------------------");
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				String columnName = cursor.getColumnName(i);
				String value = cursor.getString(i);
				Logger.i(Utils.class, columnName + "=" + value);
			}
		}
	}

	/**
	 * ��ʽ��һ������ֵ�������Сʱ�����ʽ��Ϊʱ���룬�磺02:30:59�����û��Сʱ���ʽ��Ϊ���룬�磺30:59
	 * @param duration
	 * @return
	 */
	public static CharSequence formatMillis(long duration) {
		// ��durationת��Ϊһ������
		Calendar calendar = Calendar.getInstance();
		calendar.clear();	// ���ʱ��
		calendar.add(Calendar.MILLISECOND, (int) duration);
		CharSequence inFormat = duration / Constants.HOUR_MILLIS > 0 ? "kk:mm:ss" : "mm:ss";
		return DateFormat.format(inFormat, calendar.getTime());
	}
}
















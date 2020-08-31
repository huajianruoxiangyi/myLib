package com.uerbeautybusiness.uersbeauty.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uerbeautybusiness.uersbeauty.application.MyApplication;
import com.uerbeautybusiness.uersbeauty.constants.URLs;
import com.uerbeautybusiness.uersbeauty.utils.DensityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 跟App相关的辅助类
 * 
 * @author zhy
 * 
 */
public class AppUtils
{

	private AppUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * EditText获取焦点并显示软键盘
	 */
	public static void showSoftInputFromWindow(Context activity, EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.showSoftInput(editText, 0);
	}

	/**
	 * 隐藏软键盘.
	 */
	public static void invisibleKeyboard(Activity activity, Application appContext) {
		if (activity.getWindow().peekDecorView() != null) {
			InputMethodManager inputMethodManager =
					(InputMethodManager) appContext.getSystemService(
							Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(
					activity.getWindow().peekDecorView().getWindowToken(), 0);
		}
	}



	//图片加载路径
	public static String getPictureUrl(String imgurl) {
		if (imgurl == null || "".equals(imgurl)) {
			return imgurl;
		} else {
			if (imgurl.contains("http")) {
				return imgurl;
			} else {
				return URLs.BASE_URL + "ocean/" + imgurl;
			}
		}
	}

	/**
	 * 图片格式转换.
	 */
	public static File webpToJpg(String outputFile) {
		FileOutputStream fos = null;
		try {
			File fileOut = new File(outputFile + "_1.jpg");
			Bitmap bitmap = BitmapFactory.decodeFile(outputFile, null);
			if (bitmap == null) {
				return null;
			}
			fos = new FileOutputStream(fileOut);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			return fileOut;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	public static int floatToInt(float f){
		int i = 0;
		if(f>0) //正数
			i = (int) ((f*10 + 5)/10);
		else if(f<0) //负数
			i = (int) ((f*10 - 5)/10);
		else i = 0;

		return i;

	}

	public static void reflex(final TabLayout tabLayout , final int dip ){//可以设置字间的宽度，还有下划线的长度。
		//了解源码得知 线的宽度是根据 tabView的宽度来设置的
		tabLayout.post(new Runnable() {
			@Override
			public void run() {
				try {
					//拿到tabLayout的mTabStrip属性
					LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

					int dp10 = ((int) DensityUtils.dp2px(MyApplication.getInstance(),dip));

					for (int i = 0; i < mTabStrip.getChildCount(); i++) {
						View tabView = mTabStrip.getChildAt(i);

						//拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
						Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
						mTextViewField.setAccessible(true);

						TextView mTextView = (TextView) mTextViewField.get(tabView);

						tabView.setPadding(0, 0, 0, 0);

						//因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
						int width = 0;
						width = mTextView.getWidth();
						if (width == 0) {
							mTextView.measure(0, 0);
							width = mTextView.getMeasuredWidth();
						}

						//设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
						LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
						params.width = width ;
						params.leftMargin = dp10;
						params.rightMargin = dp10;
						tabView.setLayoutParams(params);

						tabView.invalidate();
					}

				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});

	}
}

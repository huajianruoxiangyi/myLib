package com.uerbeautybusiness.uersbeauty.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/**
 * 常用单位转换的辅助类
 * 
 * @author zhy
 * 
 */
public class DensityUtils
{
	private DensityUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * dp转px
	 * 
	 * @param context

	 * @return
	 */
	public static int dp2px(Context context, float dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * sp转px
	 * 
	 * @param context

	 * @return
	 */
	public static int sp2px(Context context, float spVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * px转dp
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float px2dp(Context context, float pxVal)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (pxVal / scale);
	}

	/**
	 * px转sp
	 * 

	 * @param pxVal
	 * @return
	 */
	public static float px2sp(Context context, float pxVal)
	{
		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
	}

	/**
	 * 计算两个view的距离
	 * @param v1
	 * @param v2
	 * @return 返回new int[2], [0]横坐标距离，[1]纵坐标的距离
	 */
	public static int[] calculateWidgetsDistance(View v1, View v2){
		int[] location1 = new int[2];
		int[] location2 = new int[2];
		int[] ret = new int[2];

		v1.getLocationOnScreen(location1);
		v2.getLocationOnScreen(location2);

		ret[0] = Math.abs(location1[0] - location2[0]);
		ret[1] = Math.abs(location1[1] - location2[1]);
		return ret;
	}
}

package com.uerbeautybusiness.uersbeauty.common;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by work on 17/5/12.
 */

public class CommonMethod {

    /**
     * 下拉刷新配置信息
     */
    public static void SwipeConfig(SwipeRefreshLayout mSwipe) {
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipe.setProgressBackgroundColor(android.R.color.white); // 设定下拉圆圈的背景
        mSwipe.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小
    }

    /**
     * 设置时间
     * 如:39'33''
     *
     * @param takingTime  时间戳
     * @param mTvTakeTime 要显示的控件
     */
    public static void setTime(long takingTime, TextView mTvTakeTime) {
        if (takingTime < 60) {
            mTvTakeTime.setText(takingTime + "''");
        } else if (takingTime >= 60 && takingTime < 3600) {
            long min = takingTime / 60;
            long second = takingTime - min * 60;
            mTvTakeTime.setText(min + "'" + second + "''");
        } else {
            long hour = takingTime / 3600;
            long min = (takingTime - hour * 3600) / 60;
            long second = takingTime - hour * 3600 - min * 60;
            mTvTakeTime.setText(hour + ":" + min + "'" + second + "''");
        }
    }


//
//    /**
//     * 大地图打开动画
//     *
//     * @param in1          大地图根布局
//     * @param view         要执行动画的view
//     * @param centerX      中心点X
//     * @param centerY      中心点Y
//     * @param startRadius  起始半径
//     * @param endRadius    终止半径
//     * @param ll_bottom
//     * @param head
//     */
//    public static void openAnim(final FrameLayout in1, View view, int centerX, int centerY, float startRadius,
//                                float endRadius, final LinearLayout ll_bottom, final RelativeLayout head) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Animator animator = ViewAnimationUtils.createCircularReveal(
//                    view, centerX, centerY, startRadius, endRadius);
//            animator.setInterpolator(new LinearInterpolator());
//            animator.setDuration(300);
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    in1.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    ll_bottom.setVisibility(View.VISIBLE);
//                    head.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                }
//            });
//            animator.start();
//        } else {
//            in1.setVisibility(View.VISIBLE);
//        }
//    }
//
//    /**
//     * 大地图关闭动画
//     *
//     * @param handler
//     * @param r
//     * @param in1         大地图根布局
//     * @param view        要执行动画的view
//     * @param centerX     中心点X
//     * @param centerY     中心点Y
//     * @param startRadius 起始半径
//     * @param endRadius   终止半径
//     */
//    public static void closeAnimation(final Handler handler, final Runnable r, final FrameLayout in1,
//                                      View view, int centerX, int centerY, float startRadius, float endRadius,
//                                      final LinearLayout ll_bottom, final RelativeLayout head) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Animator animator = ViewAnimationUtils.createCircularReveal(
//                    view, centerX, centerY, startRadius, endRadius);
//            animator.setInterpolator(new LinearInterpolator());
//            animator.setDuration(300);
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    ll_bottom.setVisibility(View.GONE);
//                    head.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    in1.setVisibility(View.INVISIBLE);
//                    handler.removeCallbacks(r);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                }
//            });
//            animator.start();
//        } else {
//            in1.setVisibility(View.INVISIBLE);
//        }
//    }

    private static HashMap<String, String> hashMapInstance = null;

    private static HashMap<String, String> getMapInstance() {
        if (null == hashMapInstance) {
            synchronized (CommonMethod.class) {
                if (null == hashMapInstance) {
                    hashMapInstance = new HashMap<>();
                }
            }
        }
        return hashMapInstance;
    }




}

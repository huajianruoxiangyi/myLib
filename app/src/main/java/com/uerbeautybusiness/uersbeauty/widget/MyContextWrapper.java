package com.uerbeautybusiness.uersbeauty.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;


/**
 * @author 王兴岭
 * @date 2017-12-06 16:49
 * @desc 继承 ContextWrapper 重新配置 Configuration
 */
public class MyContextWrapper extends ContextWrapper {

    public MyContextWrapper(Context base) {
        super(base);
    }

    @NonNull
    public static ContextWrapper wrap(Context context) {
        Resources resources = context.getResources();
        Configuration newConfig = new Configuration();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        newConfig.setToDefaults();
        //如果没有设置densityDpi, createConfigurationContext对字体大小设置限制无效
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            newConfig.densityDpi = metrics.densityDpi;
            context = context.createConfigurationContext(newConfig);
        } else {
            resources.updateConfiguration(newConfig, resources.getDisplayMetrics());
        }

        return new MyContextWrapper(context);
    }

}

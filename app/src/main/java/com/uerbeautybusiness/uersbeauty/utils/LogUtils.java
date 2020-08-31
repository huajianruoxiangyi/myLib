/**
 * Copyright 2014 Zhenguo Jin
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uerbeautybusiness.uersbeauty.utils;

import android.util.Log;

/**
 * This class can replace android.util.Log.
 *
 * @author jingle1267@163.com
 * @description And you can turn off the log by set DEBUG_LEVEL = Log.ASSERT.
 */
//Log工具类
public final class LogUtils {
    public static final boolean DEBUG = true;

    private LogUtils() {
    }

    public static void d(String tag, String desc) {
        if (DEBUG)
            Log.d(tag, desc);
    }

    public static void d(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.d(tag, desc, tr);
    }

    public static void v(String tag, String desc) {
        if (DEBUG)
            Log.v(tag, desc);
    }
    public static void v(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.v(tag, desc);
    }

    public static void w(String tag, String desc) {
        if (DEBUG)
            Log.w(tag, desc);
    }

    public static void w(String tag, Throwable ioe) {
        if (DEBUG)
            Log.w(tag, ioe);
    }

    public static void w(String tag, String desc, Throwable e) {
        if (DEBUG)
            Log.w(tag, desc, e);
    }

    public static void i(String tag, String desc) {
        if (DEBUG)
            Log.i(tag, desc);
    }

    public static void i(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.i(tag, desc, tr);
    }

    public static void e(String tag, String desc) {
        if (DEBUG)
            Log.e(tag, desc);
    }

    public static void e(String tag, String desc, Throwable tr) {
        if (DEBUG)
            Log.e(tag, desc, tr);
    }
}
package com.uerbeautybusiness.uersbeauty.net;


/**
 * Api Factory
 * Created by Wangj on 16/2/22.
 */
public class MyFactory {

    protected static final Object monitor = new Object();
    public static SharedApi sharedSingleton = null;
    /**
     * 获得SharedAPI对象
     */
    public static SharedApi getSharedSingleton() {
        //一次只能有一个线程进入
        synchronized (monitor) {
            if (sharedSingleton == null) {
                sharedSingleton = MyRetrofit.createService(SharedApi.class);
            }
            return sharedSingleton;
        }
    }
}

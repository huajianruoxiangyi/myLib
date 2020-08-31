package com.uerbeautybusiness.uersbeauty.net;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * <p>文件描述：<p>
 * <p>作者：guyao<p>
 * <p>创建时间：2018/4/16<p>
 */

public class Transformer {

    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
//                    context.mStateLayout.showLoadingView();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

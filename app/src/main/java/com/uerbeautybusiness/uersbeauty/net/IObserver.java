package com.uerbeautybusiness.uersbeauty.net;


import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

/**
 * <p>文件描述：<p>
 * <p>作者：guyao<p>
 * <p>创建时间：2018/4/20<p>
 */

public interface IObserver<T> {

    void doOnSubscribe(@NonNull Disposable d);
    void doOnComplete();
    void doOnError(Throwable e);
    void doOnNext(T t);

}

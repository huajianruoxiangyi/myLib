package com.uerbeautybusiness.uersbeauty.net;


import android.support.annotation.NonNull;

import com.uerbeautybusiness.uersbeauty.base.BaseActivity;
import com.uerbeautybusiness.uersbeauty.widget.StateLayout;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <p>文件描述：<p>
 * <p>作者：guyao<p>
 * <p>创建时间：2018/4/20<p>
 */

public abstract class CommonObserver<T> implements Observer<T>, IObserver<T> {

    private final StateLayout mStateLayout;
    private final BaseActivity mContext;

    public CommonObserver(StateLayout stateLayout,BaseActivity context) {
        this.mStateLayout = stateLayout;
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void doOnSubscribe(@NonNull Disposable d) {
        mContext.addSubscription(d);
    }

    @Override
    public void onNext(@NonNull T t) {
        mStateLayout.showSuccessView();
        doOnNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mStateLayout.showErrorView(); //不能显示。用户也看不懂。
        doOnError(e);
    }

    @Override
    public void onComplete() {
        doOnComplete();
    }

    @Override
    public void doOnComplete() {
        //这个时候隐藏loadding太晚了。跳转页面的时候会造成loading卡顿。
//        mContext.hideWaitDialog();
    }

    @Override
    public void doOnError(Throwable e) {
    }
}

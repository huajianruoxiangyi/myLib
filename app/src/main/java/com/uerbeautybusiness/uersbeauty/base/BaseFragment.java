package com.uerbeautybusiness.uersbeauty.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.application.MyApplication;
import com.uerbeautybusiness.uersbeauty.dialog.DialogControl;
import com.uerbeautybusiness.uersbeauty.net.MyFactory;
import com.uerbeautybusiness.uersbeauty.net.SharedApi;
import com.uerbeautybusiness.uersbeauty.widget.StateLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 碎片基类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:18:46
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentInterface, StateLayout.OnReloadListener {
    private View view;
    protected LayoutInflater mInflater;
    private Unbinder mUnbinder;
    public StateLayout mStateLayout;
    //实例化一个单例接口
    public static final SharedApi sSharedApi = MyFactory.getSharedSingleton();
    public Context mContext;
    public BaseActivity mActivity;


    public MyApplication getApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        mStateLayout = new StateLayout(getContext());
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            mStateLayout.bindSuccessView(view);
        }
        mUnbinder = ButterKnife.bind(this, view);
        //首次展示loading
        mStateLayout.showLoadingView();
        mStateLayout.setOnReloadListener(this);
        initView(view);
        initData();
        initListener();
        return mStateLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarTitle();
    }

    private void setActionBarTitle() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            int title = getActionBarTitle();
            ((BaseActivity) activity).setActionBarTitle(title);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (BaseActivity) getActivity();
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void hideWaitDialog() {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

    protected ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    protected ProgressDialog showWaitDialog(int resid) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(resid);
        }
        return null;
    }

    protected ProgressDialog showWaitDialog(String str) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(str);
        }
        return null;
    }

    protected void showToast(int message) {
        MyApplication.showToast(message);
    }

    protected void showToast(String message) {
        MyApplication.showToast(message);
    }

    protected void showShortToast(int message) {
        MyApplication.showToastShort(message);
    }

    protected void showShortToast(String message) {
        MyApplication.showToastShort(message);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public int getActionBarTitle() {
        return R.string.app_name;
    }

    @Override
    public void onReload() {
        initData();
    }


}

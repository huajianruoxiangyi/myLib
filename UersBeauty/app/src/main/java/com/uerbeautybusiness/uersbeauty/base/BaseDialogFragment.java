package com.uerbeautybusiness.uersbeauty.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.uerbeautybusiness.uersbeauty.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by leimo on 2017/5/9.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    private Unbinder mUnbinder;
    //实例化一个单例接口

    public Context mContext;
    public BaseActivity mActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        if (isCenter()) {
            attributes.gravity = Gravity.CENTER;
        }
        if (isTop()) {
            attributes.gravity = Gravity.TOP;
        }
        dialog.setContentView(getLayoutId());

        mUnbinder = ButterKnife.bind(this, dialog);
        initViews();
        return dialog;
    }

    protected void initViews() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected abstract int getLayoutId();

    public boolean isCenter() {
        return false;
    }

    public boolean isTop() {
        return false;
    }

    public boolean isCanceledOnTouchOutside() {
        return false;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        if (getActivity() instanceof BaseActivity) {
            mActivity = (BaseActivity) getActivity();
        }
    }
}

package com.uerbeautybusiness.uersbeauty.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.gson.JsonSyntaxException;
import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.application.MyApplication;
import com.uerbeautybusiness.uersbeauty.dialog.DialogControl;
import com.uerbeautybusiness.uersbeauty.dialog.DialogHelper;
import com.uerbeautybusiness.uersbeauty.net.MyFactory;
import com.uerbeautybusiness.uersbeauty.net.SharedApi;
import com.uerbeautybusiness.uersbeauty.utils.AppManager;
import com.uerbeautybusiness.uersbeauty.utils.TDevice;
import com.uerbeautybusiness.uersbeauty.utils.UiUtil;
import com.uerbeautybusiness.uersbeauty.widget.MyContextWrapper;
import com.uerbeautybusiness.uersbeauty.widget.StateLayout;

import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

/**
 * baseActionBar Activity
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:30:15 引用自：tonlin
 */
public abstract class BaseActivity extends AppCompatActivity implements
        DialogControl, BaseViewInterface, StateLayout.OnReloadListener {
    private boolean mIsVisible;
    public StateLayout mStateLayout;
    private ProgressDialog mWaitDialog;
    private static final String TAG = "BaseActivity";
    //实例化一个单例接口
    public static final SharedApi sSharedApi = MyFactory.getSharedSingleton();
    protected CompositeDisposable mCompositeSubscription;

    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeSubscription = new CompositeDisposable();
        if (mStateLayout == null) {
            mStateLayout = new StateLayout(this);
        }

        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            Log.e("className", getClass().getName());
            mStateLayout.bindSuccessView(LayoutInflater.from(this).inflate(getLayoutId(), null, false));
            setContentView(mStateLayout);
        }
        //首先展示正在加载的view
        mStateLayout.showLoadingView();
        mStateLayout.setOnReloadListener(this);
        AppManager.getAppManager().addActivity(this);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mInflater = getLayoutInflater();
        if (hasToolbar()) {
            initToolbar(toolbar);
        }

        // 通过注解绑定控件
        ButterKnife.bind(this);

        init(savedInstanceState);
        mIsVisible = true;
        initView();
        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.isFinishing()) {
            TDevice.hideSoftKeyboard(getCurrentFocus());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.clear();
        }
        AppManager.getAppManager().removeActivity(this);
    }

    protected void onBeforeSetContentLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!hasToolbar()) {
                int statusBarHeight = UiUtil.getStatusBarHeight(this);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
                ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
                View statusBarTintView = new View(this);
                statusBarTintView.setLayoutParams(params);
                statusBarTintView.setBackgroundColor(
                        TDevice.getColor(getResources(), R.color.colorPrimary));
                decorView.addView(statusBarTintView);
            }
        }
    }

    public void addSubscription(Disposable s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeDisposable();
        }
        this.mCompositeSubscription.add(s);
    }

    protected boolean hasToolbar() {
        return true;
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected void initToolbar(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        toolbar.setPadding(0, UiUtil.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (hasBackButton()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
        }
        int titleRes = getActionBarTitle();
        if (titleRes != 0) {
            actionBar.setTitle(titleRes);
        }
    }

    /**
     * 设置toolbar上的标题.
     */
    public void setActionBarTitle(int resId) {
        if (!hasToolbar()) {
            return;
        }
        int titleId = resId;
        if (titleId == 0) {
            titleId = R.string.app_name;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(titleId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public ProgressDialog showWaitDialog() {
        return showWaitDialog("加载中...");
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (mIsVisible) {
            if (mWaitDialog == null) {
                mWaitDialog = DialogHelper.getProgressDialog(this, message);
            }
            if (mWaitDialog != null && !isFinishing() && Build.VERSION.SDK_INT >= 17 && !isDestroyed()) {
                mWaitDialog.setMessage(message);
                mWaitDialog.show();
            }
            return mWaitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (mIsVisible && mWaitDialog != null) {
            try {
                if (!isFinishing() && Build.VERSION.SDK_INT >= 17 && !isDestroyed()) {
                    mWaitDialog.dismiss();
                }
                mWaitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
    }

    //设置app内字体不受系统字体影响
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    public void showOnError(Throwable e) {
        //ToastUtils.showShort(e.getMessage());//--请求错误
        if (e instanceof JsonSyntaxException) {
            showShortToast("数据量过大，请修改查询条件!");
        } else if (e instanceof TimeoutException) {
            showShortToast("请求超时，请稍后重试!");
        } else {
            showShortToast("请求失败!");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        @SuppressLint("RestrictedApi")
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if (fragmentList != null) {
            for (int indext = 0; indext < fragmentList.size(); indext++) {
                Fragment fragment = fragmentList.get(indext); //找到第一层Fragment
                if (fragment == null) {
                    Log.w(TAG, "Activity result no fragment exists for index: 0x"
                            + Integer.toHexString(requestCode));
                } else {
                    handleResult(fragment, requestCode, resultCode, data);
                }
            }
        }
    }

    /**
     * 递归调用，对所有的子Fragment生效.
     */
    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        Log.e(TAG, "MyBaseFragmentActivity");
        try {
            @SuppressLint("RestrictedApi")
            List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
            if (childFragment != null) {
                for (Fragment f : childFragment) {
                    if (f != null) {
                        handleResult(f, requestCode, resultCode, data);
                    }
                }
            }
            if (childFragment == null) {
                Log.e(TAG, "MyBaseFragmentActivity1111");
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
        }

    }

    @Override
    public void onReload() {
        initData();
    }

}

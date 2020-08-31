package com.uerbeautybusiness.uersbeauty.module.fragment;

import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.base.BaseFragment;
import com.uerbeautybusiness.uersbeauty.net.CommonObserver;
import com.uerbeautybusiness.uersbeauty.net.Transformer;

import butterknife.BindView;

/**
 * @ClassName HomeFragment
 * @Description 首页
 * @Author gy
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.content)
    TextView mContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void initData() {
        sSharedApi.requestSportRecord("357")
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<JsonObject>(mStateLayout, mActivity) {
                    @Override
                    public void doOnNext(JsonObject s) {
                        mContent.setText(s.toString());
                    }
                });

    }
}

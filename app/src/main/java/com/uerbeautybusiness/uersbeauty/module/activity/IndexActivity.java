package com.uerbeautybusiness.uersbeauty.module.activity;

import android.support.v4.app.Fragment;

import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.adapter.IndexPagerAdapter;
import com.uerbeautybusiness.uersbeauty.base.BaseActivity;
import com.uerbeautybusiness.uersbeauty.module.fragment.HomeFragment;
import com.uerbeautybusiness.uersbeauty.widget.NoTouchViewPager;
import com.uerbeautybusiness.uersbeauty.widget.SpecialTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class IndexActivity extends BaseActivity {

    @BindView(R.id.main_vp_layout)
    NoTouchViewPager mMainVpLayout;
    @BindView(R.id.main_tab_view)
    PageNavigationView mMainTabView;
    private List<Fragment> mFragmentList;
    private NavigationController mNavigationController;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        mStateLayout.showSuccessView();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mNavigationController = mMainTabView.custom()
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "首页"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "项目"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "财富"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "礼包"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "我的"))
                .build();

        mMainVpLayout.setAdapter(new IndexPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(getResources().getColor(R.color.color_99));
        mainTab.setTextCheckedColor(getResources().getColor(R.color.color_33));
        return mainTab;
    }

}

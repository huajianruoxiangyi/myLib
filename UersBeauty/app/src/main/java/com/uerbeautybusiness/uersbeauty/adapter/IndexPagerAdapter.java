package com.uerbeautybusiness.uersbeauty.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ClassName IndexPagerAdapter
 * @Description
 * @Author gy
 */
public class IndexPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mList;

    public IndexPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}

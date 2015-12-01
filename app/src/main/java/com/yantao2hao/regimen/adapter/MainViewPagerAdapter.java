package com.yantao2hao.regimen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yantao2hao.regimen.AllFragment;
import com.yantao2hao.regimen.ClassesFragment;
import com.yantao2hao.regimen.NewestFragment;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private NewestFragment newestFragment ;
    private AllFragment allFragment ;
    private ClassesFragment classesFragment ;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        newestFragment = new NewestFragment();
        allFragment = new AllFragment();
        classesFragment = new ClassesFragment() ;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return newestFragment;
            case 1:
                return allFragment;
            case 2:
                return classesFragment;
            default:
                return newestFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "最新";
            case 1:
                return "全部";
            case 2:
                return "类别";
            default:
                return "最新";
        }
    }
}

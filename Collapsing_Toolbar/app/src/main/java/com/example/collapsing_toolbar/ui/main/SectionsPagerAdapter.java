package com.example.collapsing_toolbar.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.collapsing_toolbar.R;
import com.example.collapsing_toolbar.frag1;
import com.example.collapsing_toolbar.frag2;
import com.example.collapsing_toolbar.frag3;
import com.example.collapsing_toolbar.frag4;
import com.example.collapsing_toolbar.frag5;
import com.example.collapsing_toolbar.frag6;
import com.example.collapsing_toolbar.frag7;
import com.example.collapsing_toolbar.frag8;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //edited here
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment =new frag1();
                break;
            case 1:
                fragment =new frag2();
                break;
            case 2:
                fragment =new frag3();
                break;
            case 3:
                fragment =new frag4();
                break;
            case 4:
                fragment =new frag5();
                break;
            case 5:
                fragment =new frag5();
                break;
            case 6:
                fragment =new frag6();
                break;
            case 7:
                fragment =new frag7();
                break;
            case 8:
                fragment =new frag8();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //edited here
        switch (position){
            case 0:
                return "Tab 1";
            case 1:
                return "Tab 2";
            case 2:
                return "Tab 3";
            case 3:
                return "Tab 4";
            case 4:
                return "Tab 5";
            case 5:
                return "Tab 6";
            case 6:
                return "Tab 7";
            case 7:
                return "Tab 8";

        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 8;
    }
}
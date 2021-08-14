package com.example.korarmy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.korarmy.frag.FragViewPager;

public class Adapter extends FragmentPagerAdapter {
    public Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return FragViewPager.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}

package com.quypn.truyenngan.PNQ18101997;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by QuyPN on 3/23/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private  Fragment[] screens;
    private Context context;

    public FragmentAdapter(FragmentManager fm, Fragment[] screens, Context context) {
        super(fm);
        this.screens = screens;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return screens[position];
    }

    @Override
    public int getCount() {
        return screens.length;
    }

   /* public  CharSequence getPageTitle(int i)
    {
        String title="";
        switch (i)
        {
            case 0:
                title = "Lịch sử đọc";
                break;
            case 1:
                title = "Truyện yêu thích";
                break;
            case 2:
                title="Thể loại";
                break;
        }
        return title;
    }*/
}

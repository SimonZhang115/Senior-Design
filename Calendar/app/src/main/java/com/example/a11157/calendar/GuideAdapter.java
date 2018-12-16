package com.example.a11157.calendar;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class GuideAdapter extends FragmentPagerAdapter {
    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                Events events = new Events();
                return events;
            case 1:
                Accounts accounts = new Accounts();
                return accounts;
            case 2:
                CalendarFragment calendarFragment = new CalendarFragment();
                return calendarFragment;
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Courses";
            case 1:
                return "Accounts";
            case 2:
                return "Calendar";
            default:
                return null;
        }
    }



}

package com.example.a11157.calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DayActivity extends AppCompatActivity implements CalendarFragment.OnFragmentInteractionListener, Events.OnFragmentInteractionListener, Accounts.OnFragmentInteractionListener{
    DatabaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        final GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        viewPager.setCurrentItem(position);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        myDb = new DatabaseHelper(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

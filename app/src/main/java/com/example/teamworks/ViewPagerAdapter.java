package com.example.teamworks;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ContactFragment();
        }
        else if (position == 1)
        {
            fragment = new ImageFragment();
        }
        else if (position == 2)
        {
            fragment = new ImageViewFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Contact us";
        }
        else if (position == 1)
        {
            title = "Images";
        }
        else if (position == 2)
        {
            title = "View Images";
        }
        return title;
    }
}

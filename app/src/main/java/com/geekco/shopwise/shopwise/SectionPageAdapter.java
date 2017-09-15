package com.geekco.shopwise.shopwise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by abhin on 16-09-2017.
 */

class SectionPageAdapter extends FragmentPagerAdapter {

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                     DealsFragment deals = new DealsFragment();
                     return  deals;
            case 1 :
                    NearbyFragment nearby = new NearbyFragment();
                    return nearby;
            case 2 :
                    ShopFragment shops=new ShopFragment();
                    return  shops;
            default :
                     return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                     return "Deals";
            case 1 :
                     return  "Nearby";
            case 2 :
                      return "Shops";
            default:
                      return "Null";

        }

    }
}


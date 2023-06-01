package com.media.nsofttask.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.media.nsofttask.ContributorFragment;
import com.media.nsofttask.FavoriteFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //@StringRes
    //private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);

        switch (position) {

            case 0:
                return new FavoriteFragment();
            case 1:
                return new ContributorFragment();
            default:
                return null;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Lista repozitorija";
            case 1:
                return "Lista suradnika";

        }
        return null;
    }

    @Override
    public int getCount() {

        // Show 2 total pages.
        return 2;
    }
}
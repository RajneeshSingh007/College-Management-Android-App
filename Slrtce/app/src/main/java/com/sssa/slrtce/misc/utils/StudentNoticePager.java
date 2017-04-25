package com.sssa.slrtce.misc.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sssa.slrtce.R;
import com.sssa.slrtce.ui.fragments.GeneralFragment;
import com.sssa.slrtce.ui.fragments.NoticeFragment;

import java.util.Locale;

/**
 * Created by Coolalien on 3/2/2017.
 */

public class StudentNoticePager extends FragmentStatePagerAdapter {

    private Context context;

    public StudentNoticePager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    /**
     * Return Fragment acc. to position
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NoticeFragment.getInstance(true, false, "upload");
            case 1:
                return GeneralFragment.getInstance(true, false);
        }
        return null;
    }

    /**
     * No of fragment
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Title for Tab
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();
        switch (position){
            case 0:
                return context.getString(R.string.notices).toUpperCase(locale);
            case 1:
                return context.getString(R.string.general).toUpperCase(locale);

        }
        return super.getPageTitle(position);
    }
}

package com.sssa.slrtce.ui.fragments.year;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.ui.activities.MainActivity;

/**
 * Created by Coolalien on 2/26/2017.
 */

public class SecondYearFragment extends BaseFragment {

    private Toolbar toolbar;
    private TextView syallbus_oopm;
    private static String toolbarTitle;

    /**
     * Instance of this class
     *
     */

    public static SecondYearFragment getInstances(String toolbarTitle){
        setToolbarTitle(toolbarTitle);
        return new SecondYearFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_secondyear;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
    }

    @Override
    protected void function() {
        toolbar.setTitle(getToolbarTitle());
    }


    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }


    public static String getToolbarTitle() {
        return toolbarTitle;
    }

    public static void setToolbarTitle(String toolbarTitles) {
        toolbarTitle = toolbarTitles;
    }

    private void fragmentLoader(Fragment fragment){
        getFragmentManager().beginTransaction().replace(setContainerId(), fragment).addToBackStack(null).commit();
    }
}

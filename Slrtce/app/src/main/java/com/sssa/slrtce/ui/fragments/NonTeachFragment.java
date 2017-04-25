package com.sssa.slrtce.ui.fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.utils.NonTeachPager;
import com.sssa.slrtce.ui.activities.AboutActivity;
import com.sssa.slrtce.ui.activities.MainActivity;

/**
 * Created by Coolalien on 2/23/2017.
 */

public class NonTeachFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Extras preferences;
    private TabLayout tabLayout;
    private NonTeachPager nonTeachPager;
    private ViewPager viewPager;
    private ActionBarDrawerToggle barDrawerToggle;

    /**
     * Instance of this class
     *
     * @return
     */
    public static NonTeachFragment getInstance() {
        return new NonTeachFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_nonteach;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        navigationView = (NavigationView) rootview.findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) rootview.findViewById(R.id.drawerLayout);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.pager);
    }

    @Override
    protected void function() {
        toolbar.setTitle(getString(R.string.nteachers));
        barDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setHasOptionsMenu(true);
        MainActivity mainActivity = ((MainActivity) getActivity());
        if (toolbar != null) {
            mainActivity.setSupportActionBar(toolbar);
            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mainActivity.getSupportActionBar().setHomeButtonEnabled(true);
                mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
        preferences = new Extras(getContext());
        nonTeachPager = new NonTeachPager(getChildFragmentManager(), getContext());
        //Adapted is set in viewpager
        viewPager.setAdapter(nonTeachPager);
        //tablayout is set work with viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected Fragment setfragment() {
        return LoginProcess.getInstance();
    }

    @Override
    protected int setContainerId() {
        return ((MainActivity) getActivity()).setContainerId();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawers();
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (item.getItemId()) {
                    case R.id.upload:
                        if (NonTeachFragment.getInstance().isAdded()) {
                            getFragmentManager().beginTransaction().detach(NonTeachFragment.getInstance()).attach(NonTeachFragment.getInstance()).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.about:
                        Intent intent = new Intent(getActivity(), AboutActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        }, 75);
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void FrgamentLoader() {
        super.FrgamentLoader();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                preferences.getUserSessionEdit().clear();
                preferences.getUserSessionEdit().commit();
                FrgamentLoader();
                break;
        }
        return true;
    }
}
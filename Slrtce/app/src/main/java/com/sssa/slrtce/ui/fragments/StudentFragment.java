package com.sssa.slrtce.ui.fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.ui.activities.AboutActivity;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.fragments.year.FourthYearFragment;

/**
 * Created by Coolalien on 2/22/2017.
 */

public class StudentFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Extras preferences;
    private ActionBarDrawerToggle barDrawerToggle;
    private TextView userName;
    private View navigationHeader;

    /**
     * Instance of this class
     * @return
     */
    public static StudentFragment getInstance(){
      return new StudentFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_student;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        navigationView = (NavigationView) rootview.findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) rootview.findViewById(R.id.drawerLayout);
        navigationHeader = navigationView.inflateHeaderView(R.layout.navigation_headerview);
        userName = (TextView) navigationHeader.findViewById(R.id.userName);
    }

    @Override
    protected void function() {
        toolbar.setTitle(getString(R.string.students));
        barDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        if (userName != null){
            userName.setText(preferences.getUserName());
        }
        if (preferences.getfourthYear()){
            getFragmentManager().beginTransaction().replace(R.id.data_container, FourthYearFragment.getInstances("")).commit();
        }
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
                switch (item.getItemId()){
                    case R.id.syllabus:
                        getFragmentManager().beginTransaction().detach(StudentFragment.getInstance()).attach(StudentFragment.getInstance()).addToBackStack(null).commit();
                        break;
                    case R.id.notices:
                        getFragmentManager().beginTransaction().replace(setContainerId(), SNoticesFragment.getInstance()).addToBackStack(null).commit();
                        break;
                    case R.id.about:
                        Intent intents = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intents);
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
        switch (item.getItemId()){
            case R.id.logout:
                preferences.getUserSessionEdit().clear();
                preferences.getUserSessionEdit().commit();
                FrgamentLoader();
                break;
        }
        return true;
    }

}

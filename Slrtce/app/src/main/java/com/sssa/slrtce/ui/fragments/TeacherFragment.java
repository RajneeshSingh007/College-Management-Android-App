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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseFragment;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.ui.activities.AboutActivity;
import com.sssa.slrtce.ui.activities.MainActivity;
import com.sssa.slrtce.ui.fragments.common.CommonYrFragment;

/**
 * Created by Coolalien on 2/23/2017.
 */

public class TeacherFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Extras preferences;
    private ImageView cmpn, it, etrx, extc, mech, civil;
    private ActionBarDrawerToggle barDrawerToggle;
    private TextView userName;
    private View navigationHeader;

    /**
     * Instance of this class
     *
     * @return
     */
    public static TeacherFragment getInstance() {
        return new TeacherFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_teacher;
    }

    @Override
    protected void ui(View rootview) {
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        navigationView = (NavigationView) rootview.findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) rootview.findViewById(R.id.drawerLayout);
        cmpn = (ImageView) rootview.findViewById(R.id.branch_cmpn);
        it = (ImageView) rootview.findViewById(R.id.branch_it);
        etrx = (ImageView) rootview.findViewById(R.id.branch_etrx);
        extc = (ImageView) rootview.findViewById(R.id.branch_extc);
        mech = (ImageView) rootview.findViewById(R.id.branch_mech);
        civil = (ImageView) rootview.findViewById(R.id.branch_civil);
        navigationHeader = navigationView.inflateHeaderView(R.layout.navigation_headerview);
        userName = (TextView) navigationHeader.findViewById(R.id.userName);
    }

    @Override
    protected void function() {
        toolbar.setTitle(getString(R.string.teachers));
        MainActivity mainActivity = ((MainActivity) getActivity());
        barDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();
        if (toolbar != null) {
            mainActivity.setSupportActionBar(toolbar);
            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mainActivity.getSupportActionBar().setHomeButtonEnabled(true);
                mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
        navigationView.setNavigationItemSelectedListener(this);
        setHasOptionsMenu(true);
        preferences = new Extras(getContext());
        it.setOnClickListener(onClick);
        cmpn.setOnClickListener(onClick);
        mech.setOnClickListener(onClick);
        civil.setOnClickListener(onClick);
        etrx.setOnClickListener(onClick);
        extc.setOnClickListener(onClick);
        if (userName != null){
            userName.setText(preferences.getUserName());
        }

        Glide.with(getContext())
                .load(R.drawable.comps)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(cmpn);

        Glide.with(getContext())
                .load(R.drawable.it)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(it);

        Glide.with(getContext())
                .load(R.drawable.civil)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(civil);

        Glide.with(getContext())
                .load(R.drawable.mech)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(mech);

        Glide.with(getContext())
                .load(R.drawable.etrx)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(etrx);

        Glide.with(getContext())
                .load(R.drawable.extc)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(300,300)
                .into(extc);
    }

    /**
     * OnclickListerner
     */
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.branch_cmpn:
                    preferences.branchCmpn("cmpn");
                    fragmentLoaders(CommonYrFragment.getInstance("CMPN"));
                    break;

                case R.id.branch_it:
                    preferences.branchCmpn("it");
                    fragmentLoaders(CommonYrFragment.getInstance("IT"));
                    break;

                case R.id.branch_etrx:
                    preferences.branchCmpn("etrx");
                    fragmentLoaders(CommonYrFragment.getInstance("ETRX"));
                    break;

                case R.id.branch_extc:
                    preferences.branchCmpn("extc");
                    fragmentLoaders(CommonYrFragment.getInstance("EXTC"));
                    break;
                case R.id.branch_mech:
                    preferences.branchCmpn("mech");
                    fragmentLoaders(CommonYrFragment.getInstance("MECh"));
                    break;
                case R.id.branch_civil:
                    preferences.branchCmpn("civil");
                    fragmentLoaders(CommonYrFragment.getInstance("CIVIL"));
                    break;
            }

        }
    };

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
                    case R.id.dept:
                        if (TeacherFragment.getInstance().isAdded()){
                            getFragmentManager().beginTransaction().detach(TeacherFragment.getInstance()).attach(TeacherFragment.getInstance()).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.general:
                        fragmentLoaders(GeneralFragment.getInstance(false, true));
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
        switch (item.getItemId()) {
            case R.id.logout:
                preferences.getUserSessionEdit().clear();
                preferences.getUserSessionEdit().commit();
                FrgamentLoader();
                break;
        }
        return true;
    }

    /**
     * Load Fragments
     * @param fragment
     */
    private void fragmentLoaders(Fragment fragment){
        getFragmentManager().beginTransaction().replace(setContainerId(), fragment).addToBackStack(null).commit();
    }
}


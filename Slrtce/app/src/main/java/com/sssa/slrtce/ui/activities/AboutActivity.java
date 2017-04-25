package com.sssa.slrtce.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseActivity;

/**
 * Created by Coolalien on 2/27/2017.
 */

public class AboutActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected int layoutID() {
        return R.layout.activity_about;
    }

    @Override
    protected void ui() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void function() {
        toolbar.setTitle(R.string.About);
    }

    @Override
    protected Fragment setfragment() {
        return null;
    }

    @Override
    protected int setContainerId() {
        return 0;
    }
}

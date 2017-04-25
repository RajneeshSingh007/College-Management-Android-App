package com.sssa.slrtce.ui.activities;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sssa.slrtce.R;
import com.sssa.slrtce.base.BaseActivity;
import com.sssa.slrtce.misc.utils.Extras;
import com.sssa.slrtce.misc.utils.PermissionManagers;
import com.sssa.slrtce.ui.fragments.LoginProcess;
import com.sssa.slrtce.ui.fragments.NonTeachFragment;
import com.sssa.slrtce.ui.fragments.StudentFragment;
import com.sssa.slrtce.ui.fragments.TeacherFragment;

import static com.sssa.slrtce.misc.utils.Constants.PERMISSIONS_REQ;

/**
 * Created by Coolalien on 2/16/2017.
 */


public class MainActivity extends BaseActivity {

    private Extras prefernces;

    @Override
    protected int layoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void ui() {
    }

    @Override
    protected void function() {
        prefernces = new Extras(MainActivity.this);
        PermissionManagers.checkPermission(this);
        FrgamentLoader();
    }

    @Override
    protected Fragment setfragment() {
        if (!TextUtils.isEmpty(prefernces.isStudent())){
            if (prefernces.isLogged() && prefernces.isStudent().equals("1") && prefernces.studentTrack()){
                return StudentFragment.getInstance();
            }
        }else {
            Log.d("MainActivity", "failed to check login status");
        }
        if (!TextUtils.isEmpty(prefernces.isTeacher())){
            if (prefernces.isLogged() && prefernces.isTeacher().equals("4") && prefernces.teacherTrack()){
                return TeacherFragment.getInstance();
            }
        }else {
            Log.d("MainActivity", "failed to check login status");
        }
        if (!TextUtils.isEmpty(prefernces.isNTeacher())){
            if (prefernces.isLogged() && prefernces.isNTeacher().equals("7") && prefernces.nteacherTrack()){
                return NonTeachFragment.getInstance();
            }
        }else {
            Log.d("MainActivity", "failed to check login status");
        }
        return LoginProcess.getInstance();
    }

    @Override
    public int setContainerId() {
        return R.id.container;
    }

    @Override
    public void FrgamentLoader() {
        super.FrgamentLoader();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                } else {
                    FrgamentLoader();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSIONS_REQ: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Granted", "hurray");
                }else {
                    PermissionManagers.checkPermission(this);
                }
            }
        }
    }

}

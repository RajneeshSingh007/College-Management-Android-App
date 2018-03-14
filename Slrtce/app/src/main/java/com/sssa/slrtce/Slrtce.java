package com.sssa.slrtce;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;


/**
 * Created by Coolalien on 2/20/2017.
 */

public class Slrtce extends MultiDexApplication {

    private static Slrtce istance;

    @Override
    public void onCreate() {
        super.onCreate();
        istance = this;
        AndroidNetworking.initialize(this);
        MultiDex.install(this);
    }

    /**
     * Instance of this class
     * @return
     */
    public static Slrtce getIstance() {
        return istance;
    }

}


package com.sssa.slrtce;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;


/**
 * Created by Coolalien on 2/20/2017.
 */

public class Slrtce extends Application {

    private static Slrtce istance;

    @Override
    public void onCreate() {
        super.onCreate();
        istance = this;
        AndroidNetworking.initialize(this);
    }

    /**
     * Instance of this class
     * @return
     */
    public static Slrtce getIstance() {
        return istance;
    }

}


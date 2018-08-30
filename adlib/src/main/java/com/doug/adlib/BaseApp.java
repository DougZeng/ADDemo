/*
 * Copyright (c) 2018. doug.
 */

package com.doug.adlib;

import android.app.Application;
import android.util.DisplayMetrics;

import com.doug.adlib.util.DisplayUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDisplayOpinion();

        Fresco.initialize(this);
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
}

package com.aplication.material.sortumanen.application;

import android.app.Application;

import com.aplication.material.sortumanen.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SortumanenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Abel-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}

package com.example.sunnylin.sypreferenceutils;

import android.app.Application;


/**
 * Created by SunnyLin on 2017/09/16.
 */

public class ExampleApplication extends Application {

    private static ExampleApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }




    public static ExampleApplication getInstance() {
        return instance;
    }
}

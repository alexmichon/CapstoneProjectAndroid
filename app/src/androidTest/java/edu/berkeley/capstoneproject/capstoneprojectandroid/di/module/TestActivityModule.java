package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;

/**
 * Created by Alex on 25/11/2017.
 */

@Module
public class TestActivityModule extends ActivityModule {

    public TestActivityModule(AppCompatActivity activity) {
        super(activity);
    }
}

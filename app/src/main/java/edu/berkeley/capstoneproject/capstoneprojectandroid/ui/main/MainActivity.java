package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

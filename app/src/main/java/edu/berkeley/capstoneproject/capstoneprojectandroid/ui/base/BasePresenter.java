package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.support.annotation.NonNull;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BasePresenter<V> {

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }
}

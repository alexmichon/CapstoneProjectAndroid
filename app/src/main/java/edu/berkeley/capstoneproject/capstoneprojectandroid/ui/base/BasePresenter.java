package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BasePresenter<V> {

    protected V mView;

    private Scheduler mSubscribingScheduler;
    private Scheduler mObservingScheduler;

    public BasePresenter(V view) {
        mView = view;
    }

    public Scheduler getObservingScheduler() {
        if (mObservingScheduler != null) {
            return mObservingScheduler;
        }
        return AndroidSchedulers.mainThread();
    }

    public void setObservingScheduler(Scheduler scheduler) {
        mObservingScheduler = scheduler;
    }

    public Scheduler getSubscribingScheduler() {
        if (mSubscribingScheduler != null) {
            return mSubscribingScheduler;
        }
        return Schedulers.io();
    }

    public void setSubscribingScheduler(Scheduler scheduler) {
        mSubscribingScheduler = scheduler;
    }
}

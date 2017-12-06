package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import com.androidnetworking.error.ANError;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by Alex on 09/11/2017.
 */

public interface IBasePresenter<V extends IBaseView, I extends IBaseInteractor> extends MvpPresenter<V> {

    boolean isViewAttached();

    I getInteractor();

    void handleApiError(Throwable throwable);
}

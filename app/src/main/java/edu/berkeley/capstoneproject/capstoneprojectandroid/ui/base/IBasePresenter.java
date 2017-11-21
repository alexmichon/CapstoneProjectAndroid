package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import com.androidnetworking.error.ANError;

/**
 * Created by Alex on 09/11/2017.
 */

public interface IBasePresenter<V extends IBaseView, I extends IBaseInteractor> {

    public V getView();
    public void onAttach(V view);
    public void onDetach();
    public boolean isViewAttached();

    public I getInteractor();

    void handleApiError(Throwable throwable);
}

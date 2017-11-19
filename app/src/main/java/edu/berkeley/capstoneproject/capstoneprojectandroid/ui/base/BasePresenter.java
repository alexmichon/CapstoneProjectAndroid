package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IBaseInteractor> implements IBasePresenter<V, I> {

    private final ISchedulerProvider mSchedulerProvider;

    private V mView;
    private I mInteractor;

    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        mInteractor = interactor;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
        mInteractor = null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mInteractor;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    public ISchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}

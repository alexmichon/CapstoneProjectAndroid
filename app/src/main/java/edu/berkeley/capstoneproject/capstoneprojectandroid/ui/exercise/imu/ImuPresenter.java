package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.imu;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 17/11/2017.
 */

public class ImuPresenter<V extends ImuContract.View, I extends ImuContract.Interactor> extends BasePresenter<V, I> implements ImuContract.Presenter<V, I> {

    private static final String TAG = ImuPresenter.class.getSimpleName();

    public ImuPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }
}

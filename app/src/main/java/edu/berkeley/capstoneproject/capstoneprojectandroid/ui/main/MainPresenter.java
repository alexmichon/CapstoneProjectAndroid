package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainPresenter<V extends MainContract.View, I extends MainContract.Interactor>
        extends BasePresenter<V, I> implements MainContract.Presenter<V, I> {

    private static final String TAG = MainPresenter.class.getSimpleName();


    public MainPresenter(I interactor,
                         ISchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onStartExerciseClick() {
        getView().startBluetoothListActivity();
    }

    @Override
    public void onViewResultsClick() {
        getView().showError("Not implemented yet !");
    }
}

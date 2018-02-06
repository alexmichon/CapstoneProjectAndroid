package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingPresenter<V extends TrainingContract.View, I extends TrainingContract.Interactor>
        extends BasePresenter<V,I> implements TrainingContract.Presenter<V, I> {

    @Inject
    public TrainingPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onBluetoothDeviceSelect() {
        if (isViewAttached()) {
            getView().showExerciseBuilderFragment();
        }
    }

    @Override
    public void onExerciseSummaryStart() {
        if (isViewAttached()) {
            getView().showExerciseFragment();
        }
    }

    @Override
    public void onExerciseSummaryBack() {
        if (isViewAttached()) {
            getView().showExerciseBuilderFragment();
        }
    }

    @Override
    public void onExerciseDone() {
        if (isViewAttached()) {
            getView().showExerciseResultFragment();
        }
    }

    @Override
    public void onExerciseBuilt() {
        if (isViewAttached()) {
            getView().showExerciseSummaryFragment();
        }
    }

    @Override
    public void onExerciseResultMenu() {
        if (isViewAttached()) {
            getView().moveToMainActivity();
        }
    }

    @Override
    public void onExerciseResultRetry() {
        if (isViewAttached()) {
            getView().showExerciseFragment();
        }
    }
}

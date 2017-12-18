package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.support.design.widget.NavigationView;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 17/11/2017.
 */

public interface TrainingContract {

    interface View extends IBaseView {
        void showBluetoothListFragment();
        void showExerciseTypesFragment();
        void showExerciseGoalFragment();
        void showExerciseSummaryFragment();
        void showExerciseFragment();

        void showExerciseTypeDialog(ExerciseType exerciseType);

        void onDeviceConnected();
    }

    interface Interactor extends IBaseInteractor {
        void doSelectDevice(Rx2BleDevice device);

        Single<Rx2BleConnection> doConnect(Rx2BleDevice device);
        Completable doValidateDevice();
        void doDisconnect();
        void doSelectExerciseType(ExerciseType exerciseType);
        void doSelectExerciseGoal(ExerciseGoal exerciseGoal);

        void doClearExerciseSession();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V,I> {
        void onDeviceSelect(Rx2BleDevice device);
        void onExerciseGoalSelect(ExerciseGoal exerciseGoal);
        void onExerciseTypeMore(ExerciseType exerciseType);
        void onExerciseTypeSelect(ExerciseType exerciseType);
        void onExerciseSummaryStart();

        void onDestroy();
    }
}

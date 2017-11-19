package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.AccMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.EncoderMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.ImuMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 10/11/2017.
 */

public interface ExerciseContract {

    interface View extends IBaseView {
        void onExerciseStart();
        void onExerciseStop();

        void addEncoderValue(Measurement measurement);

        void addAccMeasurement(Measurement measurement);

        void addGyrMeasurement(Measurement measurement);
    }

    interface Interactor extends IBaseInteractor {

        Single doStartExercise();

        Observable<Measurement> doListenEncoder();

        Observable<Measurement> doListenImu();

        void doStopExercise();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        boolean isStarted();

        void onStartClick();

        void onStopClick();

        void setExerciseType(ExerciseType exerciseType);

        void onPause();
    }
}

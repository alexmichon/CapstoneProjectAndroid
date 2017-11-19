package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.EncoderMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.ImuMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseInteractor extends BaseInteractor implements ExerciseContract.Interactor {

    private final IExerciseService mExerciseService;
    private ISensorService mSensorService;
    private Disposable mNotificationDisposable;

    @Inject
    public ExerciseInteractor(IDataManager dataManager) {
        super(dataManager);
        mExerciseService = getDataManager().getBluetoothHelper().getExerciseService();
    }

    @Override
    public Single doStartExercise() {
        return Single.create(new SingleOnSubscribe() {
            @Override
            public void subscribe(@NonNull final SingleEmitter e) throws Exception {
                mNotificationDisposable = mExerciseService.startExercise()
                        .subscribe(new Consumer<ISensorService>() {
                            @Override
                            public void accept(ISensorService iSensorService) throws Exception {
                                mSensorService = iSensorService;
                                e.onSuccess(true);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                e.onError(throwable);
                            }
                        });
            }
        });
    }

    @Override
    public Observable<Measurement> doListenEncoder() {
        return mSensorService.getEncoderObservable();
    }

    @Override
    public Observable<Measurement> doListenImu() {
        return mSensorService.getImuObservable();
    }

    @Override
    public void doStopExercise() {
        mNotificationDisposable.dispose();
    }
}

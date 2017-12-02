package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Timed;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Z;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder.ID_ANGLE;

/**
 * Created by Alex on 28/11/2017.
 */

public class MeasurementService extends BaseService implements IMeasurementService, SensorEventListener {

    private final SensorManager mSensorManager;
    private final Sensor mSensor;

    private float mAccValues[];
    private float mAngleValues[];

    private final long mStartTime;

    @Inject
    public MeasurementService(SensorManager sensorManager) {
        mSensorManager = sensorManager;
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, 100000);

        mStartTime = new Date().getTime();
    }

    @Override
    public Observable<Measurement> getEncoderObservable() {
        return Observable.interval(100, TimeUnit.MILLISECONDS).timestamp(TimeUnit.MILLISECONDS)
            .flatMap(new Function<Timed<?>, ObservableSource<Measurement>>() {
                @Override
                public ObservableSource<Measurement> apply(@NonNull Timed<?> timed) throws Exception {
                    return Observable.just(new Measurement(
                            edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_ENCODER).getMetric(ID_ANGLE),
                            timed.time() - mStartTime,
                            0
                    ));
                }
            });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {

    }

    @Override
    public Observable<Measurement> getImuObservable() {
        return Observable.interval(100, TimeUnit.MILLISECONDS).timestamp(TimeUnit.MILLISECONDS)
                .flatMap(new Function<Timed<?>, ObservableSource<Measurement>>() {
                    @Override
                    public ObservableSource<Measurement> apply(@NonNull Timed<?> longTimed) throws Exception {
                        Measurement measurements[] = new Measurement[]{
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_X),
                                        longTimed.time() - mStartTime,
                                        mAccValues[0] / 10
                                ),
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Y),
                                        longTimed.time() - mStartTime,
                                        mAccValues[1] / 10
                                ),
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Z),
                                        longTimed.time() - mStartTime,
                                        mAccValues[2] / 10
                                )
                        };
                        return Observable.fromArray(measurements);
                    }
                });
    }

    @Override
    public void setImuObservable(Observable<byte[]> imuObservable) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        mAccValues = sensorEvent.values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

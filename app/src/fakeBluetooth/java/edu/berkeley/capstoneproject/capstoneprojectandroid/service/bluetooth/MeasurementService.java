package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

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
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Z;

/**
 * Created by Alex on 28/11/2017.
 */

public class MeasurementService extends BaseService implements IMeasurementService, SensorEventListener {

    private final SensorManager mSensorManager;
    private final Sensor mAccSensor, mGyrSensor;

    private float mAccValues[] = new float[3];
    private float mGyrValues[] = new float[3];
    private float mAngleValues[] = new float[1];

    private final long mStartTime;

    @Inject
    public MeasurementService(SensorManager sensorManager) {
        mSensorManager = sensorManager;
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyrSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorManager.registerListener(this, mAccSensor, 100000);
        mSensorManager.registerListener(this, mGyrSensor, 100000);

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
                            mAngleValues[0]
                    ));
                }
            });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {

    }

    @Override
    public Observable<Measurement> getImuObservable() {
        return Observable.merge(getAccObservable(), getGyrObservable());
    }

    private Observable<Measurement> getAccObservable() {
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

    private Observable<Measurement> getGyrObservable() {
        return Observable.interval(100, TimeUnit.MILLISECONDS).timestamp(TimeUnit.MILLISECONDS)
                .flatMap(new Function<Timed<?>, ObservableSource<Measurement>>() {
                    @Override
                    public ObservableSource<Measurement> apply(@NonNull Timed<?> longTimed) throws Exception {
                        Measurement measurements[] = new Measurement[]{
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_X),
                                        longTimed.time() - mStartTime,
                                        mGyrValues[0]
                                ),
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_Y),
                                        longTimed.time() - mStartTime,
                                        mGyrValues[1]
                                ),
                                new Measurement(
                                        edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.find(edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_Z),
                                        longTimed.time() - mStartTime,
                                        mGyrValues[2]
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
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccValues = sensorEvent.values;
                mAngleValues[0] = (float) Math.atan2(mAccValues[2], mAccValues[0]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                mGyrValues = sensorEvent.values;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

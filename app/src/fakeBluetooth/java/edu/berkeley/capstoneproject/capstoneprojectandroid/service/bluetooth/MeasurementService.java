package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BytesFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Timed;

/**
 * Created by Alex on 28/11/2017.
 */

public class MeasurementService extends BaseService implements IMeasurementService, SensorEventListener {

    private static final int FREQUENCY_HZ = 10;
    private static final int PERIOD_US = 1000000 / FREQUENCY_HZ;

    private final SensorManager mSensorManager;
    private final Sensor mAccSensor, mGyrSensor;

    private float mAccValues[] = new float[3];
    private float mGyrValues[] = new float[3];

    private long mAccStart, mGyrStart, mEncoderStart;

    @Inject
    public MeasurementService(SensorManager sensorManager) {
        mSensorManager = sensorManager;
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyrSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public Observable<byte[]> getEncoderObservable() {
        return Observable.interval(PERIOD_US, TimeUnit.MICROSECONDS).timestamp(TimeUnit.MILLISECONDS)
            .flatMap(new Function<Timed<?>, ObservableSource<byte[]>>() {
                @Override
                public ObservableSource<byte[]> apply(@NonNull Timed<?> timed) throws Exception {
                    return Observable.just(BytesFactory.encoderBuilder()
                            .withTimestamp((int) (timed.time() - mEncoderStart))
                            .withAngle((float) ((Math.atan2(mAccValues[2], mAccValues[0]) * 180 % 180 + 180) % 180))
                            .build()
                    );
                }
            }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mEncoderStart = new Date().getTime();
                    }
            });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {

    }

    @Override
    public Observable<byte[]> getImuObservable() {
        return Observable.merge(getAccObservable(), getGyrObservable());
    }

    private Observable<byte[]> getAccObservable() {
        return Observable.interval(PERIOD_US, TimeUnit.MICROSECONDS).timestamp(TimeUnit.MILLISECONDS)
                .flatMap(new Function<Timed<?>, ObservableSource<byte[]>>() {
                    @Override
                    public ObservableSource<byte[]> apply(@NonNull Timed<?> longTimed) throws Exception {
                        return Observable.just(BytesFactory.accBuilder()
                                .withTimestamp((int) (longTimed.time() - mGyrStart))
                                .withX(mAccValues[0]/10)
                                .withY(mAccValues[0]/10)
                                .withZ(mAccValues[0]/10)
                                .build()
                        );
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mSensorManager.registerListener(MeasurementService.this, mAccSensor, PERIOD_US);
                        mAccStart = new Date().getTime();
                    }
                }).doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        mSensorManager.unregisterListener(MeasurementService.this, mAccSensor);
                    }
                });
    }

    private Observable<byte[]> getGyrObservable() {
        return Observable.interval(PERIOD_US, TimeUnit.MICROSECONDS).timestamp(TimeUnit.MILLISECONDS)
                .flatMap(new Function<Timed<?>, ObservableSource<byte[]>>() {
                    @Override
                    public ObservableSource<byte[]> apply(@NonNull Timed<?> longTimed) throws Exception {
                        return Observable.just(BytesFactory.gyrBuilder()
                                .withTimestamp((int) (longTimed.time() - mGyrStart))
                                .withX(mGyrValues[0]/10)
                                .withY(mGyrValues[1]/10)
                                .withZ(mGyrValues[2]/10)
                                .build()
                        );
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mSensorManager.registerListener(MeasurementService.this, mGyrSensor, PERIOD_US);
                        mGyrStart = new Date().getTime();
                    }
                }).doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        mSensorManager.unregisterListener(MeasurementService.this, mGyrSensor);
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

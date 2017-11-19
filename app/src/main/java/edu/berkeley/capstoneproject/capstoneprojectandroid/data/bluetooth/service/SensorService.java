package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.EncoderMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.ImuMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 18/11/2017.
 */

public class SensorService implements ISensorService {

    private static final String TAG = SensorService.class.getSimpleName();

    private final IBluetoothHelper mBluetoothHelper;

    private Observable<byte[]> mEncoderObservable;
    private Observable<byte[]> mImuObservable;

    public SensorService(IBluetoothHelper helper) {
        mBluetoothHelper = helper;
    }

    @Override
    public Observable<Measurement> getEncoderObservable() {
        return mEncoderObservable.map(new Function<byte[], Measurement>() {
            @Override
            public Measurement apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeEncoder(bytes);
            }
        });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {
        mEncoderObservable = encoderObservable;
    }

    @Override
    public Observable<Measurement> getImuObservable() {
        return mImuObservable.map(new Function<byte[], Measurement>() {
            @Override
            public Measurement apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeImu(bytes);
            }
        });
    }

    @Override
    public void setImuObservable(Observable<byte[]> imuObservable) {
        mImuObservable = imuObservable;
    }
}

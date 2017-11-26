package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
        return mEncoderObservable.map(new Function<byte[], List<Measurement>>() {
            @Override
            public List<Measurement> apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeEncoder(bytes);
            }
        }).flatMap(new Function<List<Measurement>, ObservableSource<Measurement>>() {
            @Override
            public ObservableSource<Measurement> apply(@NonNull List<Measurement> measurements) throws Exception {
                return Observable.fromIterable(measurements);
            }
        });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {
        mEncoderObservable = encoderObservable;
    }

    @Override
    public Observable<Measurement> getImuObservable() {
        return mImuObservable.map(new Function<byte[], List<Measurement>>() {
            @Override
            public List<Measurement> apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeImu(bytes);
            }
        }).flatMap(new Function<List<Measurement>, ObservableSource<Measurement>>() {
            @Override
            public ObservableSource<Measurement> apply(@NonNull List<Measurement> measurements) throws Exception {
                return Observable.fromIterable(measurements);
            }
        });
    }

    @Override
    public void setImuObservable(Observable<byte[]> imuObservable) {
        mImuObservable = imuObservable;
    }
}

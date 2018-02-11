package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public class MeasurementService extends BaseService implements IMeasurementService {

    private Observable<byte[]> mEncoderObservable;
    private Observable<byte[]> mImuObservable;

    @Inject
    public MeasurementService() {
        // Empty constructor
    }

    @Override
    public Observable<byte[]> getEncoderObservable() {
        return mEncoderObservable;
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {
        mEncoderObservable = encoderObservable;
    }

    @Override
    public Observable<byte[]> getImuObservable() {
        return mImuObservable;
    }

    @Override
    public void setImuObservable(Observable<byte[]> imuObservable) {
        mImuObservable = imuObservable;
    }
}

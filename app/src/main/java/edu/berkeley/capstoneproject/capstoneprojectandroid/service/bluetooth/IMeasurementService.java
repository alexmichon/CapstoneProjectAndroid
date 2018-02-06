package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public interface IMeasurementService extends IBaseService {
    Observable<byte[]> getEncoderObservable();
    void setEncoderObservable(Observable<byte[]> encoderObservable);

    Observable<byte[]> getImuObservable();
    void setImuObservable(Observable<byte[]> imuObservable);

}

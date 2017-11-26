package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public interface ISensorService {
    Observable<Measurement> getEncoderObservable();
    void setEncoderObservable(Observable<byte[]> encoderObservable);

    Observable<Measurement> getImuObservable();
    void setImuObservable(Observable<byte[]> imuObservable);

}

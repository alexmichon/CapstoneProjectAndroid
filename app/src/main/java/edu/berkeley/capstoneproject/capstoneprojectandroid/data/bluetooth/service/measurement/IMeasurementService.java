package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.measurement;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.base.IBaseService;
import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public interface IMeasurementService extends IBaseService {
    Observable<Measurement> getEncoderObservable();
    void setEncoderObservable(Observable<byte[]> encoderObservable);

    Observable<Measurement> getImuObservable();
    void setImuObservable(Observable<byte[]> imuObservable);

}

package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public interface IExerciseService extends IBaseService {

    String ENCODER_OBSERVABLE = "EncoderObservable";
    String IMU_OBSERVABLE = "ImuObservable";

    void setConnection(Rx2BleConnection connection);

    Observable<Map<String, Observable<byte[]>>> doStartExercise();
}

package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService extends BaseService implements IExerciseService {

    public static final String ENCODER_OBSERVABLE = "EncoderObservable";
    public static final String IMU_OBSERVABLE = "ImuObservable";

    @Inject
    public ExerciseService() {

    }

    @Override
    public Observable<Map<String, Observable<byte[]>>> startExercise() {
        Map<String, Observable<byte[]>> map = new HashMap<String, Observable<byte[]>>();
        map.put(IMU_OBSERVABLE, null);
        map.put(ENCODER_OBSERVABLE, null);
        return Observable.just(map);
    }
}

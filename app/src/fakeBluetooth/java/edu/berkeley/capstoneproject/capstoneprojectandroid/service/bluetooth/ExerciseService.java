package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService extends BaseService implements IExerciseService {

    @Inject
    public ExerciseService() {

    }

    @Override
    public Single<Map<String, Observable<byte[]>>> doStartExercise() {
        Map<String, Observable<byte[]>> map = new HashMap<String, Observable<byte[]>>();
        map.put(IMU_OBSERVABLE, null);
        map.put(ENCODER_OBSERVABLE, null);
        return Single.just(map);
    }
}

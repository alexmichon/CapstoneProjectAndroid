package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service;

import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

public interface IExerciseService {

    Observable<ISensorService> startExercise();
}

package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services;

import com.rx2androidnetworking.Rx2ANRequest;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.MeasurementResponse;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IExerciseService {

    Single<ExerciseResponse> doCreateExercise(ExerciseRequest request);
    Single<MeasurementResponse> doCreateMeasurement(int exerciseId, MeasurementRequest request);
}

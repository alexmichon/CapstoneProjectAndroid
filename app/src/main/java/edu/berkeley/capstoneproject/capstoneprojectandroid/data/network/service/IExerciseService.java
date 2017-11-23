package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementResponse;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IExerciseService {

    Single<ExerciseResponse> doCreateExercise(ExerciseRequest request);
    Single<MeasurementResponse> doCreateMeasurement(int exerciseId, MeasurementRequest request);
}
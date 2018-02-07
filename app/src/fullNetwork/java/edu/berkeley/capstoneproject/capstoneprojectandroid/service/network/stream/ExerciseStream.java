package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Alex on 06/02/2018.
 */

public class ExerciseStream extends RxActionCable implements IExerciseStream {

    private static final String EXERCISE_CHANNEL = "ExerciseChannel";


    public ExerciseStream(OkHttpClient client, Request request, Exercise exercise) {
        super(client, request, EXERCISE_CHANNEL, buildParams(exercise));
    }


    private static String buildParams(Exercise exercise) {
        return new StringBuilder()
                .append("\"exercise_id\":")
                .append(exercise.getId())
                .toString();
    }

    @Override
    public void doSendMeasurement(Measurement measurement) {
        send(new MeasurementRequest(measurement).toJson().toString());;
    }
}

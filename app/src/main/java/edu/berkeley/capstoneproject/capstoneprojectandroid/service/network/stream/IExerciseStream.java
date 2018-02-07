package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;

/**
 * Created by Alex on 06/02/2018.
 */

public interface IExerciseStream extends IStream {

    void doSendMeasurement(Measurement measurement);
}

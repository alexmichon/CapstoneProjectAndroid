package edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.IMU;

/**
 * Created by Alex on 27/10/2017.
 */

public class TestExercise extends Exercise {

    private static final String TAG = TestExercise.class.getSimpleName();

    private static final String TEST_EXERCISE_NAME = "TEST_EXERCISE";

    public TestExercise() {
        super(TEST_EXERCISE_NAME);
        mMetrics.put(Encoder.LABEL_ENCODER_ANGLE, new Metric(Encoder.LABEL_ENCODER_ANGLE));
        mMetrics.put(IMU.LABEL_IMU_ACC_X, new Metric(IMU.LABEL_IMU_ACC_X));
        mMetrics.put(IMU.LABEL_IMU_ACC_Y, new Metric(IMU.LABEL_IMU_ACC_Y));
        mMetrics.put(IMU.LABEL_IMU_ACC_Z, new Metric(IMU.LABEL_IMU_ACC_Z));
    }

}

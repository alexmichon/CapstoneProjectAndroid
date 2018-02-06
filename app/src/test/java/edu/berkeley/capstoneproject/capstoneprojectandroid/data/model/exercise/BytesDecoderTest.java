package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.BytesFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes.BytesDecoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Alex on 25/11/2017.
 */

public class BytesDecoderTest {

    private BytesDecoder mBytesDecoder;

    @Before
    public void setup() {
        mBytesDecoder = new BytesDecoder();
    }


    @Test
    public void decodeAccShouldReturnCorrectMeasurement() {
        // given
        Exercise exercise = ExerciseFactory.create();
        int timestamp = (int) new Date().getTime();
        float x = (float) Math.random();
        float y = (float) Math.random();
        float z = (float) Math.random();
        byte[] bytes = BytesFactory.accBytes(timestamp, x, y, z);

        // when
        List<Measurement> measurements = mBytesDecoder.decodeImu(exercise, bytes);

        // then
        assertThat(measurements, hasSize(3));

        for (int i = 0; i < measurements.size(); i++) {
            assertEquals(measurements.get(i).getExercise(), exercise);
            assertEquals(measurements.get(i).getTimestamp(), timestamp);
        }

        assertEquals(Accelerometer.ID_ACC_X, measurements.get(0).getMetric().getId());
        assertEquals(x, measurements.get(0).getValue());
        assertEquals(Accelerometer.ID_ACC_Y, measurements.get(1).getMetric().getId());
        assertEquals(y, measurements.get(1).getValue());
        assertEquals(Accelerometer.ID_ACC_Z, measurements.get(2).getMetric().getId());
        assertEquals(z, measurements.get(2).getValue());
    }

    @Test
    public void decodeGyrShouldReturnCorrectMeasurement() {
        // given
        Exercise exercise = ExerciseFactory.create();

        int timestamp = (int) new Date().getTime();
        float x = (float) Math.random();
        float y = (float) Math.random();
        float z = (float) Math.random();

        byte[] bytes = BytesFactory.gyrBytes(timestamp, x, y, z);

        // when
        List<Measurement> measurements = mBytesDecoder.decodeImu(exercise, bytes);

        // then
        assertThat(measurements, hasSize(3));

        for (int i = 0; i < measurements.size(); i++) {
            assertEquals(measurements.get(i).getExercise(), exercise);
            assertEquals(measurements.get(i).getTimestamp(), timestamp);
        }

        assertEquals(Gyroscope.ID_GYR_X, measurements.get(0).getMetric().getId());
        assertEquals(x, measurements.get(0).getValue());
        assertEquals(Gyroscope.ID_GYR_Y, measurements.get(1).getMetric().getId());
        assertEquals(y, measurements.get(1).getValue());
        assertEquals(Gyroscope.ID_GYR_Z, measurements.get(2).getMetric().getId());
        assertEquals(z, measurements.get(2).getValue());
    }

    @Test
    public void decodeEncoderShouldReturnCorrectMeasurement() {
        // given
        Exercise exercise = ExerciseFactory.create();

        int timestamp = (int) new Date().getTime();
        float angle = (float) (Math.random() * 180);
        byte[] bytes = BytesFactory.encoderBytes(timestamp, angle);

        // when
        List<Measurement> measurements = mBytesDecoder.decodeEncoder(exercise, bytes);

        // then
        assertThat(measurements, hasSize(1));

        assertEquals(Encoder.ID_ANGLE, measurements.get(0).getMetric().getId());
        assertEquals(timestamp, measurements.get(0).getTimestamp());
        assertEquals(angle, measurements.get(0).getValue());
    }
}

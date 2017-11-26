package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.util.collections.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.TestBytes;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement.*;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Z;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder.ID_ANGLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Z;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;

/**
 * Created by Alex on 25/11/2017.
 */

public class MeasurementTest {

    private static final long DEFAULT_TIMESTAMP = 999;
    private static final float DEFAULT_VALUE = 9.99f;

    @Mock
    private Metric mMetric;

    private Measurement mMeasurement;

    @Before
    public void setup() {
        mMeasurement = new Measurement(mMetric, DEFAULT_TIMESTAMP, DEFAULT_VALUE);
    }

    @Test
    public void measurementHasMetric() {
        assertEquals(mMetric, mMeasurement.getMetric());
    }

    @Test
    public void measurementHasTimestamp() {
        assertEquals(DEFAULT_TIMESTAMP, mMeasurement.getTimestamp());
    }

    @Test
    public void measurementHasValue() {
        assertEquals(DEFAULT_VALUE, mMeasurement.getValue());
    }




    @Test
    public void decodeAccShouldReturnCorrectMeasurement() {
        // given
        byte[] bytes = TestBytes.accBytes();
        List<Measurement> expectedMeasurements = TestBytes.accMeasurements();

        // when
        List<Measurement> measurements = Measurement.decodeImu(bytes);

        // then
        assertThat(measurements, hasSize(expectedMeasurements.size()));

        for (int i = 0; i < expectedMeasurements.size(); i++) {
            assertEquals(measurements.get(i).getMetric(), expectedMeasurements.get(i).getMetric());
            assertEquals(measurements.get(i).getTimestamp(), expectedMeasurements.get(i).getTimestamp());
            assertEquals(measurements.get(i).getValue(), expectedMeasurements.get(i).getValue());
        }
    }

    @Test
    public void decodeGyrShouldReturnCorrectMeasurement() {
        // given
        byte[] bytes = TestBytes.gyrBytes();
        List<Measurement> expectedMeasurements = TestBytes.gyrMeasurements();

        // when
        List<Measurement> measurements = Measurement.decodeImu(bytes);

        // then
        assertThat(measurements, hasSize(expectedMeasurements.size()));

        for (int i = 0; i < expectedMeasurements.size(); i++) {
            assertEquals(measurements.get(i).getMetric(), expectedMeasurements.get(i).getMetric());
            assertEquals(measurements.get(i).getTimestamp(), expectedMeasurements.get(i).getTimestamp());
            assertEquals(measurements.get(i).getValue(), expectedMeasurements.get(i).getValue());
        }
    }

    @Test
    public void decodeEncoderShouldReturnCorrectMeasurement() {
        // given
        byte[] bytes = TestBytes.encoderBytes();
        List<Measurement> expectedMeasurements = TestBytes.encoderMeasurements();

        // when
        List<Measurement> measurements = Measurement.decodeEncoder(bytes);

        // then
        assertThat(measurements, hasSize(expectedMeasurements.size()));

        for (int i = 0; i < expectedMeasurements.size(); i++) {
            assertEquals(measurements.get(i).getMetric(), expectedMeasurements.get(i).getMetric());
            assertEquals(measurements.get(i).getTimestamp(), expectedMeasurements.get(i).getTimestamp());
            assertEquals(measurements.get(i).getValue(), expectedMeasurements.get(i).getValue());
        }
    }
}

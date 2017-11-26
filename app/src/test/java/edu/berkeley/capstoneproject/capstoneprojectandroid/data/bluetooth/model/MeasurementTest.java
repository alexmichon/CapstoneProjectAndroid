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
        short sensorId = SensorManager.ID_ACCELEROMETER;
        short type = IMU_DATA_ACC;
        long timestamp = 999;
        float valX = 0.1f;
        float valY = 0.2f;
        float valZ = 0.3f;

        byte bytes[] = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
                .putShort(0, sensorId)
                .putShort(2, type)
                .putLong(4, timestamp)
                .putFloat(8, valX)
                .putFloat(12, valY)
                .putFloat(16, valZ)
                .array();

        // when
        List<Measurement> measurements = Measurement.decodeImu(bytes);

        // then
        assertThat(measurements, hasSize(3));

        assertThat(measurements.get(0).getMetric().getId(), is(ID_ACC_X));
        assertThat(measurements.get(0).getTimestamp(), is(timestamp));
        assertThat(measurements.get(0).getValue(), is(valX));

        assertThat(measurements.get(1).getMetric().getId(), is(ID_ACC_Y));
        assertThat(measurements.get(1).getTimestamp(), is(timestamp));
        assertThat(measurements.get(1).getValue(), is(valY));

        assertThat(measurements.get(2).getMetric().getId(), is(ID_ACC_Z));
        assertThat(measurements.get(2).getTimestamp(), is(timestamp));
        assertThat(measurements.get(2).getValue(), is(valZ));
    }

    @Test
    public void decodeGyrShouldReturnCorrectMeasurement() {
        // given
        short sensorId = SensorManager.ID_GYROSCOPE;
        short type = IMU_DATA_GYR;
        long timestamp = 999;
        float valX = 0.1f;
        float valY = 0.2f;
        float valZ = 0.3f;

        byte bytes[] = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
                .putShort(0, sensorId)
                .putShort(2, type)
                .putLong(4, timestamp)
                .putFloat(8, valX)
                .putFloat(12, valY)
                .putFloat(16, valZ)
                .array();

        // when
        List<Measurement> measurements = Measurement.decodeImu(bytes);

        // then
        assertThat(measurements, hasSize(3));

        assertThat(measurements.get(0).getMetric().getId(), is(ID_GYR_X));
        assertThat(measurements.get(0).getTimestamp(), is(timestamp));
        assertThat(measurements.get(0).getValue(), is(valX));

        assertThat(measurements.get(1).getMetric().getId(), is(ID_GYR_Y));
        assertThat(measurements.get(1).getTimestamp(), is(timestamp));
        assertThat(measurements.get(1).getValue(), is(valY));

        assertThat(measurements.get(2).getMetric().getId(), is(ID_GYR_Z));
        assertThat(measurements.get(2).getTimestamp(), is(timestamp));
        assertThat(measurements.get(2).getValue(), is(valZ));
    }

    @Test
    public void decodeEncoderShouldReturnCorrectMeasurement() {
        // given
        short sensorId = SensorManager.ID_ENCODER;
        long timestamp = 999;
        float angle = 0.1f;

        byte bytes[] = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                .putLong(0, timestamp)
                .putFloat(4, angle)
                .array();

        // when
        List<Measurement> measurements = Measurement.decodeEncoder(bytes);

        // then
        assertThat(measurements, hasSize(1));

        assertThat(measurements.get(0).getMetric().getId(), is(ID_ANGLE));
        assertThat(measurements.get(0).getTimestamp(), is(timestamp));
        assertThat(measurements.get(0).getValue(), is(angle));
    }
}

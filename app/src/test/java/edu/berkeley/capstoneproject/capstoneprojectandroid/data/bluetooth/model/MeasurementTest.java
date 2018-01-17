package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.TestBytes;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
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
        //mMeasurement = new Measurement(mMetric, DEFAULT_TIMESTAMP, DEFAULT_VALUE);
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

package edu.berkeley.capstoneproject.capstoneprojectandroid.service;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.TestBytes;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.MeasurementService;
import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 26/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class SensorServiceTest {

    private MeasurementService mMeasurementService;

    @Before
    public void setup() {
        mMeasurementService = new MeasurementService();
    }

    @Test
    public void getEncoderObservableShouldDecodeBytes() {
        byte[] bytes = TestBytes.encoderBytes();
        List<Measurement> expectedMeasurements = TestBytes.encoderMeasurements();

        Observable<byte[]> encoderObservable = Observable.just(bytes);
        mMeasurementService.setEncoderObservable(encoderObservable);

        // when
        Observable<Measurement> observable = mMeasurementService.getEncoderObservable();
        List<Measurement> measurements = observable.test().values();

        // then
        for (int i = 0; i < expectedMeasurements.size(); i++) {
            assertEquals(measurements.get(i).getMetric(), expectedMeasurements.get(i).getMetric());
            assertEquals(measurements.get(i).getTimestamp(), expectedMeasurements.get(i).getTimestamp());
            assertEquals(measurements.get(i).getValue(), expectedMeasurements.get(i).getValue());
        }
    }

    @Test
    public void getImuObservableShouldDecodeBytes() {
        byte[] bytes = TestBytes.accBytes();
        List<Measurement> expectedMeasurements = TestBytes.accMeasurements();

        Observable<byte[]> imuObservable = Observable.just(bytes);
        mMeasurementService.setImuObservable(imuObservable);

        // when
        Observable<Measurement> observable = mMeasurementService.getImuObservable();
        TestSubscriber<Measurement> testSubscriber = TestSubscriber.create();
        List<Measurement> measurements = observable.test().values();

        // then
        for (int i = 0; i < expectedMeasurements.size(); i++) {
            assertEquals(measurements.get(i).getMetric(), expectedMeasurements.get(i).getMetric());
            assertEquals(measurements.get(i).getTimestamp(), expectedMeasurements.get(i).getTimestamp());
            assertEquals(measurements.get(i).getValue(), expectedMeasurements.get(i).getValue());
        }
    }
}

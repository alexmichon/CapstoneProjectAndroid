package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 25/10/2017.
 */

public class Encoder extends Sensor {

    private static final String TAG = Encoder.class.getSimpleName();

    private static final String DEFAULT_NAME = "Encoder";

    public static final String LABEL_ENCODER_ANGLE = "Angle";

    public Encoder() {
        super(SensorType.ENCODER, DEFAULT_NAME);
    }

    public Encoder(String name) {
        super(SensorType.ENCODER, name);
    }


    public static Map<String, Measurement> decodeMeasurement(byte[] bytes) {
        Log.d(TAG, "Decoding measurement");

        Map<String, Measurement> measurements = new HashMap<>(1);

        long tookAt = BytesUtils.bytesToDate(bytes);
        int angle = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP);

        measurements.put(LABEL_ENCODER_ANGLE, new Measurement<Integer>(tookAt, angle));

        return measurements;
    }
}

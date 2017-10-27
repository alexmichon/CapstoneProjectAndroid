package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import android.util.Log;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.EncoderData;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 25/10/2017.
 */

public class Encoder extends Sensor<EncoderData> {

    private static final String TAG = Encoder.class.getSimpleName();

    private static final String DEFAULT_NAME = "Encoder";

    public Encoder() {
        super(SensorType.ENCODER, DEFAULT_NAME);
    }

    public Encoder(String name) {
        super(SensorType.ENCODER, name);
    }


    public static Measurement<EncoderData> decodeMeasurement(byte[] bytes) {
        Log.d(TAG, "Decoding measurement");

        long tookAt = BytesUtils.bytesToDate(bytes);
        int angle = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP);
        return new Measurement<>(tookAt, new EncoderData(angle));
    }
}

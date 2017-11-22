package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import android.util.Log;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 18/11/2017.
 */

public class EncoderMeasurement {

    private static final String TAG = EncoderMeasurement.class.getSimpleName();

    private final long mTimestamp;
    private final float mAngle;

    public EncoderMeasurement(long timestamp, float angle) {
        mTimestamp = timestamp;
        mAngle = angle;
    }

    public static EncoderMeasurement decode(byte[] bytes) {
        long timestamp = BytesUtils.bytesToDate(bytes);
        float angle = BytesUtils.bytesToFloat(bytes, BytesUtils.BYTES_TIMESTAMP);

        return new EncoderMeasurement(timestamp, angle);
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public float getAngle() {
        return mAngle;
    }
}

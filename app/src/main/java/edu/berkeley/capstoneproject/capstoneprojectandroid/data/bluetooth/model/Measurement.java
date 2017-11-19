package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 18/11/2017.
 */

public class Measurement {

    private static final String TAG = Measurement.class.getSimpleName();

    public static final String LABEL_ACC = "Acceleration";
    public static final String LABEL_GYR = "Gyroscope";
    public static final String LABEL_ENC = "Encoder angle";

    private static final String LABEL_ANGLE = "Angle";
    private static final String LABEL_ACC_X = "Acc X";
    private static final String LABEL_ACC_Y = "Acc Y";
    private static final String LABEL_ACC_Z = "Acc Z";
    private static final String LABEL_GYR_X = "Gyr X";
    private static final String LABEL_GYR_Y = "Gyr Y";
    private static final String LABEL_GYR_Z = "Gyr Z";


    private static final byte IMU_DATA_ACC = 0;
    private static final byte IMU_DATA_GYR = 1;
    private static final byte IMU_DATA_MAG = 2;


    private final String mLabel;
    private final long mTimestamp;
    private final List<Value> mValues;

    public Measurement(String label, long timestamp, List<Value> values) {
        mLabel = label;
        mTimestamp = timestamp;
        mValues = values;
    }


    public long getTimestamp() {
        return mTimestamp;
    }

    public List<Value> getValues() {
        return mValues;
    }

    public String getLabel() {
        return mLabel;
    }

    public static Measurement decodeEncoder(byte[] bytes) {
        Log.d(TAG, "Decoding encoder measurement");

        List<Value> values = new ArrayList<>();

        long timestamp = BytesUtils.bytesToDate(bytes);
        float angle = BytesUtils.bytesToFloat(bytes, BytesUtils.BYTES_TIMESTAMP);
        values.add(new Value(LABEL_ANGLE, angle));

        return new Measurement(LABEL_ENC, timestamp, values);
    }

    public static Measurement decodeImu(byte[] bytes) {
        Log.d(TAG, "Decoding IMU measurement");

        List<Value> values = new ArrayList<>();

        int type = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_INT16);
        long timestamp = BytesUtils.bytesToDate(bytes, 2 * BytesUtils.BYTES_INT16);
        int offset = 2 * BytesUtils.BYTES_INT16 + BytesUtils.BYTES_TIMESTAMP;

        switch(type) {
            case IMU_DATA_ACC:
                float accX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
                float accY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
                float accZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

                values.add(new Value(LABEL_ACC_X, accX));
                values.add(new Value(LABEL_ACC_Y, accY));
                values.add(new Value(LABEL_ACC_Z, accZ));

                return new Measurement(LABEL_ACC, timestamp, values);

            case IMU_DATA_GYR:
                float gyrX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
                float gyrY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
                float gyrZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

                values.add(new Value(LABEL_GYR_X, gyrX));
                values.add(new Value(LABEL_GYR_Y, gyrY));
                values.add(new Value(LABEL_GYR_Z, gyrZ));

                return new Measurement(LABEL_GYR, timestamp, values);
        }

        return null;
    }
}

package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
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

    private final long mTimestamp;
    private final float mValue;

    private final Metric mMetric;

    public Measurement(Metric metric, long timestamp, float value) {
        mMetric = metric;
        mTimestamp = timestamp;
        mValue = value;
    }


    public long getTimestamp() {
        return mTimestamp;
    }
    public float getValue() { return mValue; }

    public static List<Measurement> decodeEncoder(byte[] bytes) {

        List<Measurement> measurements = new ArrayList<>(1);

        Sensor encoder = SensorManager.find(SensorManager.ID_ENCODER);

        long timestamp = BytesUtils.bytesToDate(bytes);
        float angle = BytesUtils.bytesToFloat(bytes, BytesUtils.BYTES_TIMESTAMP);

        measurements.add(new Measurement(encoder.getMetric(Encoder.ID_ANGLE), timestamp, angle));

        return measurements;
    }

    public static List<Measurement> decodeImu(byte[] bytes) {
        List<Measurement> measurements = new ArrayList<>(3);

        int type = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_INT16);
        long timestamp = BytesUtils.bytesToDate(bytes, 2 * BytesUtils.BYTES_INT16);
        int offset = 2 * BytesUtils.BYTES_INT16 + BytesUtils.BYTES_TIMESTAMP;

        float valX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
        float valY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
        float valZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

        Sensor sensor = null;

        switch(type) {
            case IMU_DATA_ACC:
                sensor = SensorManager.find(SensorManager.ID_ACCELEROMETER);
                measurements.add(new Measurement(sensor.getMetric(Accelerometer.ID_ACC_X), timestamp, valX));
                measurements.add(new Measurement(sensor.getMetric(Accelerometer.ID_ACC_Y), timestamp, valY));
                measurements.add(new Measurement(sensor.getMetric(Accelerometer.ID_ACC_Z), timestamp, valZ));
                break;

            case IMU_DATA_GYR:
                sensor = SensorManager.find(SensorManager.ID_GYROSCOPE);
                measurements.add(new Measurement(sensor.getMetric(Gyroscope.ID_GYR_X), timestamp, valX));
                measurements.add(new Measurement(sensor.getMetric(Gyroscope.ID_GYR_Y), timestamp, valY));
                measurements.add(new Measurement(sensor.getMetric(Gyroscope.ID_GYR_Z), timestamp, valZ));
                break;
        }

        return measurements;
    }

    public Metric getMetric() {
        return mMetric;
    }
}

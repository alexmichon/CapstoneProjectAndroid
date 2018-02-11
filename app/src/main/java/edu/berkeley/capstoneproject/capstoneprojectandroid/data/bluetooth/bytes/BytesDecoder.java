package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 21/01/2018.
 */

public class BytesDecoder {

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

    public static final byte IMU_DATA_ACC = 0;
    public static final byte IMU_DATA_GYR = 1;
    public static final byte IMU_DATA_MAG = 2;


    @Inject
    public BytesDecoder() {

    }


    public List<Measurement> decodeEncoder(Exercise exercise, byte[] bytes) {

        List<Measurement> measurements = new ArrayList<>(1);

        Sensor encoder = SensorManager.find(SensorManager.ID_ENCODER);

        long timestamp = BytesUtils.bytesToTimestamp(bytes);
        float angle = BytesUtils.bytesToFloat(bytes, BytesUtils.BYTES_TIMESTAMP);

        measurements.add(new Measurement(exercise, encoder.getMetric(Encoder.ID_ANGLE), 0, timestamp, angle));

        return measurements;
    }

    public List<Measurement> decodeImu(Exercise exercise, byte[] bytes) {
        List<Measurement> measurements = new ArrayList<>(3);

        int type = BytesUtils.bytesToUInt8(bytes, BytesUtils.BYTES_UINT8);
        int batch = BytesUtils.bytesToUInt16(bytes, 2 * BytesUtils.BYTES_UINT8);
        long timestamp = BytesUtils.bytesToTimestamp(bytes, 2 * BytesUtils.BYTES_UINT8 + BytesUtils.BYTES_UINT16);
        int offset = 2 * BytesUtils.BYTES_UINT16 + BytesUtils.BYTES_TIMESTAMP;

        float valX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
        float valY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
        float valZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

        Sensor sensor = null;

        switch(type) {
            case IMU_DATA_ACC:
                sensor = SensorManager.find(SensorManager.ID_ACCELEROMETER);
                measurements.add(new Measurement(exercise, sensor.getMetric(Accelerometer.ID_ACC_X), batch, timestamp, valX));
                measurements.add(new Measurement(exercise, sensor.getMetric(Accelerometer.ID_ACC_Y), batch, timestamp, valY));
                measurements.add(new Measurement(exercise, sensor.getMetric(Accelerometer.ID_ACC_Z), batch, timestamp, valZ));
                break;

            case IMU_DATA_GYR:
                sensor = SensorManager.find(SensorManager.ID_GYROSCOPE);
                measurements.add(new Measurement(exercise, sensor.getMetric(Gyroscope.ID_GYR_X), batch, timestamp, valX));
                measurements.add(new Measurement(exercise, sensor.getMetric(Gyroscope.ID_GYR_Y), batch, timestamp, valY));
                measurements.add(new Measurement(exercise, sensor.getMetric(Gyroscope.ID_GYR_Z), batch, timestamp, valZ));
                break;
        }

        return measurements;
    }
}

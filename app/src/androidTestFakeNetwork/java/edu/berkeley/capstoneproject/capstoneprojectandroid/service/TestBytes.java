package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement.IMU_DATA_ACC;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement.IMU_DATA_GYR;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Z;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder.ID_ANGLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope.ID_GYR_Z;

/**
 * Created by Alex on 26/11/2017.
 */

public class TestBytes {

    public static byte[] encoderBytes() {
        short sensorId = SensorManager.ID_ENCODER;
        long timestamp = 999;
        float angle = 0.1f;

        return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                .putLong(0, timestamp)
                .putFloat(4, angle)
                .array();
    }

    public static List<Measurement> encoderMeasurements() {
        List<Measurement> list = new ArrayList<>(1);
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_ENCODER).getMetric(ID_ANGLE),
                999,
                0.1f
        ));
        return list;
    }

    public static byte[] accBytes() {
        short sensorId = SensorManager.ID_ACCELEROMETER;
        short type = IMU_DATA_ACC;
        long timestamp = 999;
        float valX = 0.1f;
        float valY = 0.2f;
        float valZ = 0.3f;

        return ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
                .putShort(0, sensorId)
                .putShort(2, type)
                .putLong(4, timestamp)
                .putFloat(8, valX)
                .putFloat(12, valY)
                .putFloat(16, valZ)
                .array();
    }

    public static List<Measurement> accMeasurements() {
        List<Measurement> list = new ArrayList<>(3);
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_X),
                999,
                0.1f
        ));
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Y),
                999,
                0.2f
        ));
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Z),
                999,
                0.3f
        ));
        return list;
    }

    public static byte[] gyrBytes() {
        short sensorId = SensorManager.ID_GYROSCOPE;
        short type = IMU_DATA_GYR;
        long timestamp = 999;
        float valX = 0.1f;
        float valY = 0.2f;
        float valZ = 0.3f;

        return ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
                .putShort(0, sensorId)
                .putShort(2, type)
                .putLong(4, timestamp)
                .putFloat(8, valX)
                .putFloat(12, valY)
                .putFloat(16, valZ)
                .array();
    }

    public static List<Measurement> gyrMeasurements() {
        List<Measurement> list = new ArrayList<>(3);
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_X),
                999,
                0.1f
        ));
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_Y),
                999,
                0.2f
        ));
        list.add(new Measurement(
                SensorManager.find(SensorManager.ID_GYROSCOPE).getMetric(ID_GYR_Z),
                999,
                0.3f
        ));
        return list;
    }
}

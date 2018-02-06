package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes.BytesDecoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

/**
 * Created by Alex on 26/11/2017.
 */

public class BytesFactory {

    public static EncoderBuilder encoderBuilder() {
        return new EncoderBuilder();
    }

    public static byte[] encoderBytes(int timestamp, float angle) {
        return encoderBuilder()
                .withTimestamp(timestamp)
                .withAngle(angle)
                .build();
    }





    public static ImuBuilder accBuilder() {
        return new ImuBuilder()
                .withSensorId(SensorManager.ID_ACCELEROMETER)
                .withType(BytesDecoder.IMU_DATA_ACC);
    }

    public static byte[] accBytes(int timestamp, float x, float y, float z) {
        return accBuilder()
                .withTimestamp(timestamp)
                .withX(x)
                .withY(y)
                .withZ(z)
                .build();
    }


    public static ImuBuilder gyrBuilder() {
        return new ImuBuilder()
                .withSensorId(SensorManager.ID_GYROSCOPE)
                .withType(BytesDecoder.IMU_DATA_GYR);
    }

    public static byte[] gyrBytes(int timestamp, float x, float y, float z) {
        return gyrBuilder()
                .withTimestamp(timestamp)
                .withX(x)
                .withY(y)
                .withZ(z)
                .build();
    }


    public static class EncoderBuilder {

        private int mTimestamp;
        private float mAngle;

        public EncoderBuilder withTimestamp(int timestamp) {
            mTimestamp = timestamp;
            return this;
        }

        public EncoderBuilder withAngle(float angle) {
            mAngle = angle;
            return this;
        }

        public byte[] build() {
            return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                    .putInt(0, mTimestamp)
                    .putFloat(4, mAngle)
                    .array();
        }
    }

    public static class ImuBuilder {

        private short mSensorId;
        private short mType;

        private int mTimestamp;
        private float mX;
        private float mY;
        private float mZ;

        public ImuBuilder withSensorId(short sensorId) {
            mSensorId = sensorId;
            return this;
        }

        public ImuBuilder withType(short type) {
            mType = type;
            return this;
        }

        public ImuBuilder withTimestamp(int timestamp) {
            mTimestamp = timestamp;
            return this;
        }

        public ImuBuilder withX(float x) {
            mX = x;
            return this;
        }

        public ImuBuilder withY(float y) {
            mY = y;
            return this;
        }

        public ImuBuilder withZ(float z) {
            mZ = z;
            return this;
        }

        public byte[] build() {
            return ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN)
                    .putShort(0, mSensorId)
                    .putShort(2, mType)
                    .putInt(4, mTimestamp)
                    .putFloat(8, mX)
                    .putFloat(12, mY)
                    .putFloat(16, mZ)
                    .array();
        }
    }
}

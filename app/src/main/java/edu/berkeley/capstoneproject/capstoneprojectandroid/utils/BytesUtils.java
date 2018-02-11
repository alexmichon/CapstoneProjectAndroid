package edu.berkeley.capstoneproject.capstoneprojectandroid.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Alex on 26/10/2017.
 */

public final class BytesUtils {

    public static final int BYTES_UINT8 = 1;
    public static final int BYTES_UINT16 = 2;
    public static final int BYTES_TIMESTAMP = 4;
    public static final int BYTES_FLOAT     = 4;

    public static short bytesToUInt8(byte[] bytes) {
        return bytesToUInt8(bytes, 0);
    }

    public static short bytesToUInt8(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, BYTES_UINT8).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.get();
    }

    public static short bytesToUInt16(byte[] bytes) {
        return bytesToUInt16(bytes, 0);
    }

    public static short bytesToUInt16(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, BYTES_UINT16).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getShort();
    }

    public static float bytesToFloat(byte[] bytes) { return bytesToFloat(bytes, 0); }

    public static float bytesToFloat(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, BYTES_FLOAT).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getFloat();
    }


    public static long bytesToTimestamp(byte[] bytes) {
        return bytesToTimestamp(bytes, 0);
    }

    public static long bytesToTimestamp(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, BYTES_TIMESTAMP).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt() & 0xFFFFFFFFL;
    }
}

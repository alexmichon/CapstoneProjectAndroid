package edu.berkeley.capstoneproject.capstoneprojectandroid.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Alex on 26/10/2017.
 */

public final class BytesUtils {

    public static final int BYTES_INT16     = 2;
    public static final int BYTES_TIMESTAMP = 4;
    public static final int BYTES_FLOAT     = 4;

    public static short bytesToInt16(byte[] bytes) {
        return bytesToInt16(bytes, 0);
    }

    public static short bytesToInt16(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, BYTES_INT16).order(ByteOrder.LITTLE_ENDIAN);
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

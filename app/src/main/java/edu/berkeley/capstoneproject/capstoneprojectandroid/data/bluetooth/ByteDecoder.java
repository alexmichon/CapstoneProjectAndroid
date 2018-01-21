package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

/**
 * Created by Alex on 14/01/2018.
 */

public abstract class ByteDecoder<T> {

    private final byte[] mBytes;

    public ByteDecoder(byte[] bytes) {
        mBytes = bytes;
    }

    public abstract T decode();
}

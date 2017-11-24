package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx;

import org.junit.Test;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alex on 23/11/2017.
 */

public class BytesUtilsTest {

    @Test
    public void bytesToInt16ShouldReturnCorrectValue() {
        // given
        byte bytes[] = {(byte) 0x01, (byte) 0x00};

        // when
        int value = BytesUtils.bytesToInt16(bytes);

        // then
        assertEquals(1, value);
    }

    @Test
    public void bytesToInt16ShouldWorkWithSignedInt() {
        // given
        byte bytes[] = {(byte) 0xFF, (byte) 0xFF};

        // when
        short value = BytesUtils.bytesToInt16(bytes);

        // then
        assertEquals(-1, value);
    }

    @Test
    public void bytesToInt16ShouldWorkWithOffset() {
        // given
        byte bytes[] = {(byte) 0x00, (byte) 0x01, (byte) 0x00};

        // when
        short value = BytesUtils.bytesToInt16(bytes, 1);

        // then
        assertEquals(1, value);
    }

    @Test
    public void bytesToFloatShouldReturnCorrectValue() {
        // given
        byte bytes[] = {(byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x3F};

        // when
        float value = BytesUtils.bytesToFloat(bytes);

        // then
        assertEquals(1.0, value, 0.0001);
    }

    @Test
    public void bytesToFloatShouldWorkWithOffset() {
        // given
        byte bytes[] = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x3F};

        // when
        float value = BytesUtils.bytesToFloat(bytes, 1);

        // then
        assertEquals(1.0, value, 0.0001);
    }

    @Test
    public void bytesToDateShouldReturnCorrectValue() {
        // given
        byte bytes[] = {(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        // when
        long value = BytesUtils.bytesToTimestamp(bytes);

        // then
        assertEquals(1, value);
    }

    @Test
    public void bytesToDateShouldWorkWithOffset() {
        // given
        byte bytes[] = {(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        // when
        long value = BytesUtils.bytesToTimestamp(bytes, 1);

        // then
        assertEquals(1, value);
    }
}

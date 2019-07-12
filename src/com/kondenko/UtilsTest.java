package com.kondenko;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void areEqual() {
        assertTrue(ArrayUtils.areEqual(1.0, 1.0, 1.0, 1.0));
    }

    @Test
    public void areNotEqual() {
        assertFalse(ArrayUtils.areEqual(1.0, 1.0, 1.0, 2.0));
    }

}
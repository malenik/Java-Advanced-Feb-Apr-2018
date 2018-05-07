package com.flowergarden.properties;

import org.junit.Assert;
import org.junit.Test;

public class FreshnessIntegerTest {

    private int sampleFreshness = 40;
    private Freshness<Integer> freshness = new FreshnessInteger(sampleFreshness);

    @Test
    public void getFreshness() {
        Assert.assertEquals((int) freshness.getFreshness(), sampleFreshness);
    }

    @Test
    public void compareTo() {
        int higherFreshness = 80;
        Freshness<Integer> moreFreshness = new FreshnessInteger(higherFreshness);
        Assert.assertEquals(((FreshnessInteger) freshness).compareTo((FreshnessInteger) moreFreshness), -1);
    }
}
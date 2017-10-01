package com.papyrus.statistics;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class ErrorCountingTest {
    private final Measurement defaultMeasurement = new Measurement("Picking", "Duration");

    @Test
    public void countsSingleError() {
        final Collector collector = new Collector();

        collector.reportError(defaultMeasurement);

        assertEquals(1, collector.errorCount(defaultMeasurement));
    }

    @Test
    public void countsMultipleErrors() {
        final Collector collector = new Collector();

        collector.reportError(defaultMeasurement);
        collector.reportError(defaultMeasurement);

        assertEquals(2, collector.errorCount(defaultMeasurement));
    }

    @Test
    public void onlyAddsErrorsWithSameStepAndMeasure() {
        final Collector collector = new Collector();

        collector.reportError(defaultMeasurement);
        collector.reportError(new Measurement("Other", defaultMeasurement.measure));
        collector.reportError(new Measurement(defaultMeasurement.step, "Other"));

        assertEquals(1, collector.errorCount(defaultMeasurement));
    }
}

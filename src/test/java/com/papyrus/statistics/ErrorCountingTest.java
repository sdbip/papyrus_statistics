package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class ErrorCountingTest {
    private final Measurement defaultMeasurement = new Measurement("Picking", "Duration");
    private final TestSource testSource = new TestSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void countsSingleError() {
        testSource.entries = Collections.singletonList(Entry.error(defaultMeasurement));

        collector.collect();

        assertEquals(1, collector.errorCount(defaultMeasurement));
    }

    @Test
    public void countsMultipleErrors() {
        testSource.entries = Arrays.asList(
                Entry.error(defaultMeasurement),
                Entry.error(defaultMeasurement));

        collector.collect();

        assertEquals(2, collector.errorCount(defaultMeasurement));
    }

    @Test
    public void onlyAddsErrorsWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                Entry.error(defaultMeasurement),
                Entry.error(new Measurement("Other", defaultMeasurement.measure)),
                Entry.error(new Measurement(defaultMeasurement.step, "Other")));

        collector.collect();

        assertEquals(1, collector.errorCount(defaultMeasurement));
    }
}
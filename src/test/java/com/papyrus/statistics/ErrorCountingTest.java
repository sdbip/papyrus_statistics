package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class ErrorCountingTest {
    private final Measurement defaultMeasurement = new Measurement(TestSteps.picking, TestMeasures.duration);
    private final TestSource testSource = new TestSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void countsSingleError() {
        testSource.entries = Collections.singletonList(CollectedEntry.error(defaultMeasurement));

        final CollectedData collectedData = collector.collect();

        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void countsMultipleErrors() {
        testSource.entries = Arrays.asList(
                CollectedEntry.error(defaultMeasurement),
                CollectedEntry.error(defaultMeasurement));

        final CollectedData collectedData = collector.collect();

        assertEquals(2, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void onlyAddsErrorsWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                CollectedEntry.error(defaultMeasurement),
                CollectedEntry.error(new Measurement(TestSteps.other, defaultMeasurement.measure)),
                CollectedEntry.error(new Measurement(defaultMeasurement.step, TestMeasures.other)));

        final CollectedData collectedData = collector.collect();

        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void calculatesTotalNumberOfErrorsPerMeasure() {
        testSource.entries = Arrays.asList(
                CollectedEntry.error(defaultMeasurement),
                CollectedEntry.error(new Measurement(TestSteps.other, defaultMeasurement.measure)));

        final CollectedData collectedData = collector.collect();

        assertEquals(2, collectedData.totalErrorsByMeasure.get(TestMeasures.duration).intValue());
    }
}

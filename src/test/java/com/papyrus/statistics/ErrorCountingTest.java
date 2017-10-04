package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class ErrorCountingTest {
    private final TestInputSource testSource = new TestInputSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void countsSingleError() {
        testSource.entries = Collections.singletonList(InputEntry.error(TestSteps.picking, TestMeasures.duration));

        final CollectedData collectedData = collector.collect();

        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void countsMultipleErrors() {
        testSource.entries = Arrays.asList(
                InputEntry.error(TestSteps.picking, TestMeasures.duration),
                InputEntry.error(TestSteps.picking, TestMeasures.duration));

        final CollectedData collectedData = collector.collect();

        assertEquals(2, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void onlyAddsErrorsWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                InputEntry.error(TestSteps.picking, TestMeasures.duration),
                InputEntry.error(TestSteps.other, TestMeasures.duration),
                InputEntry.error(TestSteps.picking, TestMeasures.other));

        final CollectedData collectedData = collector.collect();

        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).errors);
    }

    @Test
    public void calculatesTotalNumberOfErrorsPerMeasure() {
        testSource.entries = Arrays.asList(
                InputEntry.error(TestSteps.picking, TestMeasures.duration),
                InputEntry.error(TestSteps.other, TestMeasures.duration));

        final CollectedData collectedData = collector.collect();

        assertEquals(2, collectedData.totalErrorsByMeasure.get(TestMeasures.duration).intValue());
    }
}

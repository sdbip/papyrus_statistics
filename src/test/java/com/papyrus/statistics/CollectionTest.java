package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class CollectionTest {
    private final Measurement defaultMeasurement = new Measurement(TestSteps.picking, TestMeasures.duration);
    private final TestSource testSource = new TestSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void collectsSingleValue() {
        testSource.entries = Collections.singletonList(new CollectedEntry(defaultMeasurement, 9.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(9.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }

    @Test
    public void collectsMultipleValues() {
        testSource.entries = Arrays.asList(
                new CollectedEntry(defaultMeasurement, 9.0),
                new CollectedEntry(defaultMeasurement, 11.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(20.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(2, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }

    @Test
    public void onlyAddsValuesWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                new CollectedEntry(defaultMeasurement, 9.0),
                new CollectedEntry(new Measurement(TestSteps.other, defaultMeasurement.measure), 11.0),
                new CollectedEntry(new Measurement(defaultMeasurement.step, TestMeasures.other), 11.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(9.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }
}

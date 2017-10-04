package com.papyrus.statistics.input;

import com.papyrus.statistics.CollectedData;
import com.papyrus.statistics.TestMeasures;
import com.papyrus.statistics.TestSteps;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class CollectionTest {
    private final TestInputSource testSource = new TestInputSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void collectsSingleValue() {
        testSource.entries = Collections.singletonList(new InputEntry(TestSteps.picking, TestMeasures.duration, 9.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(9.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }

    @Test
    public void collectsMultipleValues() {
        testSource.entries = Arrays.asList(
                new InputEntry(TestSteps.picking, TestMeasures.duration, 9.0),
                new InputEntry(TestSteps.picking, TestMeasures.duration, 11.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(20.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(2, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }

    @Test
    public void onlyAddsValuesWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                new InputEntry(TestSteps.picking, TestMeasures.duration, 9.0),
                new InputEntry(TestSteps.other, TestMeasures.duration, 11.0),
                new InputEntry(TestSteps.picking, TestMeasures.other, 11.0));

        final CollectedData collectedData = collector.collect();

        assertEquals(9.0, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).total, 0.001);
        assertEquals(1, collectedData.entries.get(TestSteps.picking).get(TestMeasures.duration).count);
    }
}

package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class CollectionTest {
    private final Measurement defaultMeasurement = new Measurement("Picking", "Duration");
    private final TestSource testSource = new TestSource();
    private final Collector collector = new Collector(testSource);

    @Test
    public void collectsSingleValue() {
        testSource.entries = Collections.singletonList(new Entry(defaultMeasurement, 9.0));

        collector.collect();

        assertEquals(Collections.singletonList(9.0), collector.getValues(defaultMeasurement));
    }

    @Test
    public void collectsMultipleValues() {
        testSource.entries = Arrays.asList(
                new Entry(defaultMeasurement, 9.0),
                new Entry(defaultMeasurement, 11.0));

        collector.collect();

        assertEquals(Arrays.asList(9.0, 11.0), collector.getValues(defaultMeasurement));
    }

    @Test
    public void onlyAddsValuesWithSameStepAndMeasure() {
        testSource.entries = Arrays.asList(
                new Entry(defaultMeasurement, 9.0),
                new Entry(new Measurement("Other", defaultMeasurement.measure), 11.0),
                new Entry(new Measurement(defaultMeasurement.step, "Other"), 11.0));

        collector.collect();

        assertEquals(Collections.singletonList(9.0), collector.getValues(defaultMeasurement));
    }

}

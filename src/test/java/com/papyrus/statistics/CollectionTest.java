package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class CollectionTest {
    private final Measurement defaultMeasurement = new Measurement("Picking", "Duration");

    @Test
    public void collectsSingleValue() {
        final Collector collector = new Collector();

        collector.add(defaultMeasurement, 9.0);

        assertEquals(Collections.singletonList(9.0), collector.getValues(defaultMeasurement));
    }

    @Test
    public void collectsMultipleValues() {
        final Collector collector = new Collector();

        collector.add(defaultMeasurement, 9.0);
        collector.add(defaultMeasurement, 11.0);

        assertEquals(Arrays.asList(9.0, 11.0), collector.getValues(defaultMeasurement));
    }

    @Test
    public void onlyAddsValuesWithSameStepAndMeasure() {
        final Collector collector = new Collector();

        collector.add(defaultMeasurement, 9.0);
        collector.add(new Measurement("Other", defaultMeasurement.measure), 11.0);
        collector.add(new Measurement(defaultMeasurement.step, "Other"), 11.0);

        assertEquals(Collections.singletonList(9.0), collector.getValues(defaultMeasurement));
    }
}

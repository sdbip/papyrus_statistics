package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculationTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void calculatesAverage() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement(new Step("Picking"), "Duration"),
                Arrays.asList(9.0, 11.0)
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get(new Step("Picking")).get("Duration");
        assertEquals(10.0, entry.average, 0.01);
    }

    @Test
    public void remembersErrors() {
        final CollectedData collectedData = new CollectedData();
        collectedData.errors.put(
                new Measurement(new Step("Picking"), "Duration"),
                3
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get(new Step("Picking")).get("Duration");
        assertEquals(3, entry.errors);
    }

    @Test
    public void combinesAverageAndErrors() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement(new Step("Picking"), "Duration"),
                Arrays.asList(9.0, 11.0)
        );
        collectedData.errors.put(
                new Measurement(new Step("Picking"), "Duration"),
                3
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get(new Step("Picking")).get("Duration");
        assertEquals(10.0, entry.average, 0.01);
        assertEquals(3, entry.errors);
    }

    @Test
    public void collatesMultipleMeasuresForSameStep() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement(new Step("Picking"), "Duration"),
                Collections.singletonList(1.0)
        );
        collectedData.measurements.put(
                new Measurement(new Step("Picking"), "Fuel"),
                Collections.singletonList(1.0)
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final Map<String, CalculatedEntry> entry = calculatedData.entries.get(new Step("Picking"));
        assertTrue(entry.containsKey("Fuel"));
        assertTrue(entry.containsKey("Duration"));
    }

    @Test
    public void calculatesTotalNumberOfErrorsPerMeasure() {
        final CollectedData collectedData = new CollectedData();
        collectedData.errors.put(
                new Measurement(new Step("Picking"), "Duration"),
                3
        );
        collectedData.errors.put(
                new Measurement(new Step("Loading"), "Duration"),
                3
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final int errors = calculatedData.totalErrorsByMeasure.get("Duration");
        assertEquals(6, errors);
    }
}

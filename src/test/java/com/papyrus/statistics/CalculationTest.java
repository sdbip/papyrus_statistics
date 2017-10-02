package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CalculationTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void calculatesAverage() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement("Picking", "Duration"),
                Arrays.asList(9.0, 11.0)
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get("Picking");
        assertEquals(10.0, entry.average, 0.01);
    }

    @Test
    public void remembersErrors() {
        final CollectedData collectedData = new CollectedData();
        collectedData.errors.put(
                new Measurement("Picking", "Duration"),
                3
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get("Picking");
        assertEquals(3, entry.errors);
    }

    @Test
    public void combinesAverageAndErrors() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement("Picking", "Duration"),
                Arrays.asList(9.0, 11.0)
        );
        collectedData.errors.put(
                new Measurement("Picking", "Duration"),
                3
        );

        final CalculatedData calculatedData = calculator.calculate(collectedData);

        final CalculatedEntry entry = calculatedData.entries.get("Picking");
        assertEquals(10.0, entry.average, 0.01);
        assertEquals(3, entry.errors);
    }
}

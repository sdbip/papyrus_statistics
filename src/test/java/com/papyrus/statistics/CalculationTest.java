package com.papyrus.statistics;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CalculationTest {
    @Test
    public void calculatesAverage() {
        final CollectedData collectedData = new CollectedData();
        collectedData.measurements.put(
                new Measurement("Picking", "Duration"),
                Arrays.asList(9.0, 11.0)
        );
        final Calculator calculator = new Calculator(collectedData);

        double average = calculator.average(new Measurement("Picking", "Duration"));

        assertEquals(10.0, average, 0.01);
    }
}

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

        calculator.calculate(collectedData);
    
        double average = calculator.average(new Measurement("Picking", "Duration"));
        assertEquals(10.0, average, 0.01);
    }
}

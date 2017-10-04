package com.papyrus.statistics;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class OutputTest {
    private final TestTarget testTarget = new TestTarget();

    @Test
    public void outputsHeaders() throws IOException {
        final CalculatedData calculatedData = new CalculatedData();
        calculatedData.totalErrorsByMeasure.put(TestMeasures.duration, 30);

        final Output output = new Output(testTarget);
        output.output(calculatedData);

        assertEquals(1, testTarget.writtenHeaders.length);
        assertEquals(TestMeasures.duration, testTarget.writtenHeaders[0]);
    }

    @Test
    public void outputsStatisticsPerStep() throws IOException {
        final CalculatedData calculatedData = new CalculatedData();
        calculatedData.totalErrorsByMeasure.put(TestMeasures.duration, 30);
        calculatedData.entries.put(TestSteps.picking, new HashMap<>());
        calculatedData.entries.get(TestSteps.picking).put(
                TestMeasures.duration,
                new CalculatedEntry(9.0, 30));

        final Output output = new Output(testTarget);
        output.output(calculatedData);

        assertEquals(1, testTarget.lastWrittenEntries.length);
        assertEquals(9.0, testTarget.lastWrittenEntries[0].average, 0.001);
        assertEquals(30, testTarget.lastWrittenEntries[0].errors);
    }
}

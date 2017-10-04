package com.papyrus.statistics;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class OutputTest {
    private final TestTarget testTarget = new TestTarget();

    @Test
    public void outputsHeaders() throws IOException {
        final CalculatedData calculatedData = new CalculatedData();
        calculatedData.totalErrorsByMeasure.put(TestMeasures.duration, 0);

        final Output output = new Output(testTarget);
        output.output(calculatedData);

        assertEquals(Collections.singletonList(TestMeasures.duration), testTarget.writtenHeaders);
    }

    @Test
    public void outputsHeadersForMultipleMeasures() throws IOException {
        final CalculatedData calculatedData = new CalculatedData();
        calculatedData.totalErrorsByMeasure.put(TestMeasures.duration, 0);
        calculatedData.totalErrorsByMeasure.put(TestMeasures.fuel, 0);

        final Output output = new Output(testTarget);
        output.output(calculatedData);

        final List<Measure> headers = new ArrayList<>();
        testTarget.writtenHeaders.forEach(headers::add);

        assertEquals(2, headers.size());
    }

    @Test
    public void sortsHeadersByTotalErrors() throws IOException {
        final CalculatedData calculatedData = new CalculatedData();
        calculatedData.totalErrorsByMeasure.put(TestMeasures.duration, 30);
        calculatedData.totalErrorsByMeasure.put(TestMeasures.fuel, 10);

        final Output output = new Output(testTarget);
        output.output(calculatedData);

        assertEquals(Arrays.asList(TestMeasures.duration, TestMeasures.fuel), testTarget.writtenHeaders);
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

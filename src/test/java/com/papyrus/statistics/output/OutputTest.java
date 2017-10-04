package com.papyrus.statistics.output;

import com.papyrus.statistics.*;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class OutputTest {
    private final TestTarget testTarget = new TestTarget();

    @Test
    public void outputsHeaders() throws IOException {
        final CollectedData collectedData = new CollectedData();
        collectedData.totalErrorsByMeasure.put(TestMeasures.duration, 0);

        final Output output = new Output(testTarget);
        output.output(collectedData);

        assertEquals(Collections.singletonList(TestMeasures.duration), testTarget.writtenHeaders);
    }

    @Test
    public void outputsHeadersForMultipleMeasures() throws IOException {
        final CollectedData collectedData = new CollectedData();
        collectedData.totalErrorsByMeasure.put(TestMeasures.duration, 0);
        collectedData.totalErrorsByMeasure.put(TestMeasures.fuel, 0);

        final Output output = new Output(testTarget);
        output.output(collectedData);

        final List<Measure> headers = new ArrayList<>();
        testTarget.writtenHeaders.forEach(headers::add);

        assertEquals(2, headers.size());
    }

    @Test
    public void sortsHeadersByTotalErrors() throws IOException {
        final CollectedData collectedData = new CollectedData();
        collectedData.totalErrorsByMeasure.put(TestMeasures.duration, 30);
        collectedData.totalErrorsByMeasure.put(TestMeasures.fuel, 10);

        final Output output = new Output(testTarget);
        output.output(collectedData);

        assertEquals(Arrays.asList(TestMeasures.duration, TestMeasures.fuel), testTarget.writtenHeaders);
    }

    @Test
    public void outputsStatisticsPerStep() throws IOException {
        final CollectedData collectedData = new CollectedData();
        collectedData.totalErrorsByMeasure.put(TestMeasures.duration, 30);
        collectedData.entries.put(TestSteps.picking, new HashMap<>());
        collectedData.entries.get(TestSteps.picking).put(
                TestMeasures.duration,
                new CollectedEntry(9.0, 1, 30));

        final Output output = new Output(testTarget);
        output.output(collectedData);

        final List<CollectedEntry> entries = new ArrayList<>();
        testTarget.lastWrittenEntries.forEach(entries::add);

        assertEquals(1, entries.size());
        assertEquals(9.0, entries.get(0).total, 0.001);
        assertEquals(1, entries.get(0).count);
        assertEquals(30, entries.get(0).errors);
    }

    @Test
    public void outputsRowsForMultipleMeasures() throws IOException {
        final CollectedData collectedData = new CollectedData();
        collectedData.totalErrorsByMeasure.put(TestMeasures.duration, 0);
        collectedData.totalErrorsByMeasure.put(TestMeasures.fuel, 0);
        collectedData.entries.put(TestSteps.picking, new HashMap<>());
        collectedData.entries.get(TestSteps.picking).put(
                TestMeasures.duration,
                new CollectedEntry(9.0, 1, 30));
        collectedData.entries.get(TestSteps.picking).put(
                TestMeasures.fuel,
                new CollectedEntry(8.7, 1, 10));

        final Output output = new Output(testTarget);
        output.output(collectedData);

        final List<CollectedEntry> entries = new ArrayList<>();
        testTarget.lastWrittenEntries.forEach(entries::add);

        assertEquals(2, entries.size());
    }
}

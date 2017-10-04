package com.papyrus.statistics;

import org.junit.Test;

import java.io.IOException;

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
}

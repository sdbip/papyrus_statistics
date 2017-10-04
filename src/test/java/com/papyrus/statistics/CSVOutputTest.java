package com.papyrus.statistics;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class CSVOutputTest {
    @Test
    public void outputsDataInAUsefulFormat() throws IOException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final CSVOutput output = new CSVOutput(outputStream, TestMeasures.duration);

        output.writeHeaders();
        output.write(TestSteps.picking, new CalculatedEntry(8.7, 11));
        output.write(TestSteps.loading, new CalculatedEntry(5.6, 23));
        output.close();

        assertEquals(
                "Process step;Average Duration;Errors Duration\r\n" +
                "Picking;8.7;11\r\n" +
                "Loading;5.6;23\r\n", outputStream.toString());
    }
}

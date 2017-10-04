package com.papyrus.statistics;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CSVOutputTest {
    @Test
    public void outputsDataInAUsefulFormat() throws IOException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final CSVOutputTaget output = new CSVOutputTaget(outputStream);

        output.writeHeaders(Collections.singletonList(TestMeasures.duration));
        output.write(TestSteps.picking, Collections.singletonList(new CalculatedEntry(8.7, 11)));
        output.write(TestSteps.loading, Collections.singletonList(new CalculatedEntry(5.6, 23)));
        output.close();

        assertEquals(
                "Process step;Average Duration;Errors Duration\r\n" +
                "Picking;8.7;11\r\n" +
                "Loading;5.6;23\r\n", outputStream.toString());
    }
}

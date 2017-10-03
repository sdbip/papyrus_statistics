package com.papyrus.statistics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVOutputTest {
    @Test
    public void again() throws IOException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final CSVFormat format = CSVFormat.EXCEL.withDelimiter(';');
        final CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(outputStream), format);
        printer.printRecord("Process step", "Average Duration", "Errors Duration");
        printer.printRecord("Picking", "8.7", "11");
        printer.printRecord("Loading", "5.6", "23");
        printer.flush();
        printer.close();

        assertEquals(
                "Process step;Average Duration;Errors Duration\r\n" +
                "Picking;8.7;11\r\n" +
                "Loading;5.6;23\r\n", outputStream.toString());
    }
}

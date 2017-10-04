package com.papyrus.statistics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class CSVOutput {
    private final static CSVFormat EXCEL_FORMAT = CSVFormat.EXCEL.withDelimiter(';');

    private final CSVPrinter printer;

    CSVOutput(final OutputStream stream) throws IOException {
        printer = new CSVPrinter(new OutputStreamWriter(stream), EXCEL_FORMAT);
    }

    void writeHeaders(Measure... measures) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add("Process step");
        for (final Measure measure : measures) {
            values.add("Average " + measure.name);
            values.add("Errors " + measure.name);
        }
        printer.printRecord(values);
    }

    void write(Step step, CalculatedEntry... entries) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add(step.name);
        for (final CalculatedEntry entry : entries) {
            values.add(Double.toString(entry.average));
            values.add(Integer.toString(entry.errors));
        }
        printer.printRecord(values);
    }

    void close() throws IOException {
        printer.flush();
        printer.close();
    }
}
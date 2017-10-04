package com.papyrus.statistics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class CSVOutputTaget implements OutputTarget {
    private final static CSVFormat EXCEL_FORMAT = CSVFormat.EXCEL.withDelimiter(';');

    private final CSVPrinter printer;

    CSVOutputTaget(final OutputStream stream) throws IOException {
        printer = new CSVPrinter(new OutputStreamWriter(stream), EXCEL_FORMAT);
    }

    @Override
    public void writeHeaders(final Iterable<Measure> measures) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add("Process step");
        for (final Measure measure : measures) {
            values.add("Average " + measure.name);
            values.add("Errors " + measure.name);
        }
        printer.printRecord(values);
    }

    @Override
    public void write(final Step step, final Iterable<CalculatedEntry> entries) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add(step.name);
        for (final CalculatedEntry entry : entries) {
            values.add(Double.toString(entry.total / entry.count));
            values.add(Integer.toString(entry.errors));
        }
        printer.printRecord(values);
    }

    @Override
    public void close() throws IOException {
        printer.flush();
        printer.close();
    }
}

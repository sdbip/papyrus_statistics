package com.papyrus.statistics.csv;

import com.papyrus.statistics.CollectedEntry;
import com.papyrus.statistics.Measure;
import com.papyrus.statistics.output.OutputTarget;
import com.papyrus.statistics.Step;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVOutputTarget implements OutputTarget {
    private final static CSVFormat EXCEL_FORMAT = CSVFormat.EXCEL.withDelimiter(';');

    private final CSVPrinter printer;

    public CSVOutputTarget(final OutputStream stream) throws IOException {
        printer = new CSVPrinter(new OutputStreamWriter(stream), EXCEL_FORMAT);
    }

    @Override
    public void writeHeaders(final Iterable<Measure> measures) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add("Process step");
        for (final Measure measure : measures) {
            values.add("Average " + measure.toString());
            values.add("Errors " + measure.toString());
        }
        printer.printRecord(values);
    }

    @Override
    public void write(final Step step, final Iterable<CollectedEntry> entries) throws IOException {
        final List<String> values = new ArrayList<>();
        values.add(step.toString());
        for (final CollectedEntry entry : entries) {
            values.add(Double.toString(getAverage(entry)));
            values.add(Integer.toString(entry.errors));
        }
        printer.printRecord(values);
    }

    private double getAverage(final CollectedEntry entry) {
        return entry.count == 0 ? 0 : entry.total / entry.count;
    }

    @Override
    public void close() throws IOException {
        printer.flush();
        printer.close();
    }
}

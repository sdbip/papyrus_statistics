package com.papyrus.statistics.csv;

import com.papyrus.statistics.input.InputEntry;
import com.papyrus.statistics.input.InputSource;
import com.papyrus.statistics.Measure;
import com.papyrus.statistics.Step;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class CSVInputSource implements InputSource {
    private static final CSVFormat EXCEL_FORMAT = CSVFormat.EXCEL.withHeader().withDelimiter(';');
    private final CSVParser parser;

    public CSVInputSource(final InputStream stream) throws IOException {
        final InputStreamReader reader = new InputStreamReader(stream);
        parser = EXCEL_FORMAT.parse(reader);
    }

    @Override
    public Iterable<InputEntry> entries() {
        final Iterator<CSVRecord> recordIterator = parser.iterator();
        return () -> new Iterator<InputEntry>() {
            @Override
            public boolean hasNext() {
                return recordIterator.hasNext();
            }

            @Override
            public InputEntry next() {
                final CSVRecord record = recordIterator.next();
                final Step step = new Step(record.get(0));
                final Measure measure = new Measure(record.get(1));
                final Double value = parseDouble(record.get(2));
                if (value == null) {
                    return InputEntry.error(step, measure);
                } else {
                    return new InputEntry(step, measure, value);
                }
            }
        };
    }

    private Double parseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

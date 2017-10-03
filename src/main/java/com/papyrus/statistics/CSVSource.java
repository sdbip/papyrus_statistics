package com.papyrus.statistics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

class CSVSource implements Source {
    private static final CSVFormat EXCEL_FORMAT = CSVFormat.EXCEL.withHeader().withDelimiter(';');
    private final CSVParser parser;

    CSVSource(final InputStream stream) throws IOException {
        final InputStreamReader reader = new InputStreamReader(stream);
        parser = EXCEL_FORMAT.parse(reader);
    }

    @Override
    public Iterable<CollectedEntry> entries() {
        final Iterator<CSVRecord> recordIterator = parser.iterator();
        return () -> new Iterator<CollectedEntry>() {
            @Override
            public boolean hasNext() {
                return recordIterator.hasNext();
            }

            @Override
            public CollectedEntry next() {
                final CSVRecord record = recordIterator.next();
                final Measurement measurement = new Measurement(
                        new Step(record.get(0)),
                        new Measure(record.get(1))
                );
                final Double value = parseDouble(record.get(2));
                if (value == null) {
                    return CollectedEntry.error(measurement);
                } else {
                    return new CollectedEntry(measurement, value);
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

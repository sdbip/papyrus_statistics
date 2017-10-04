package com.papyrus.statistics;

import java.io.IOException;

class TestTarget implements OutputTarget {
    boolean isClosed;
    Iterable<Measure> writtenHeaders;
    Step lastWrittenStep;
    Iterable<CalculatedEntry> lastWrittenEntries;

    @Override
    public void writeHeaders(final Iterable<Measure> measures) throws IOException {
        writtenHeaders = measures;
    }

    @Override
    public void write(final Step step, final Iterable<CalculatedEntry> entries) throws IOException {
        lastWrittenStep = step;
        lastWrittenEntries = entries;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
    }
}

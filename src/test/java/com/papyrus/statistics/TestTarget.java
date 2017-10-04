package com.papyrus.statistics;

import java.io.IOException;

class TestTarget implements OutputTarget {
    boolean isClosed;
    Measure[] writtenHeaders;
    Step lastWrittenStep;
    CalculatedEntry[] lastWrittenEntries;

    @Override
    public void writeHeaders(final Measure... measures) throws IOException {
        writtenHeaders = measures;
    }

    @Override
    public void write(final Step step, final CalculatedEntry... entries) throws IOException {
        lastWrittenStep = step;
        lastWrittenEntries = entries;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
    }
}

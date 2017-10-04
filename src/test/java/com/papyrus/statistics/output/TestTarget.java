package com.papyrus.statistics.output;

import com.papyrus.statistics.CollectedEntry;
import com.papyrus.statistics.Measure;
import com.papyrus.statistics.Step;
import com.papyrus.statistics.output.OutputTarget;

import java.io.IOException;

public final class TestTarget implements OutputTarget {
    boolean isClosed;
    Iterable<Measure> writtenHeaders;
    Step lastWrittenStep;
    Iterable<CollectedEntry> lastWrittenEntries;

    @Override
    public void writeHeaders(final Iterable<Measure> measures) throws IOException {
        writtenHeaders = measures;
    }

    @Override
    public void write(final Step step, final Iterable<CollectedEntry> entries) throws IOException {
        lastWrittenStep = step;
        lastWrittenEntries = entries;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
    }
}

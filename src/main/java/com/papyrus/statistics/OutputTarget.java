package com.papyrus.statistics;

import java.io.IOException;

public interface OutputTarget {
    void writeHeaders(Iterable<Measure> measures) throws IOException;
    void write(Step step, Iterable<CollectedEntry> entries) throws IOException;
    void close() throws IOException;
}

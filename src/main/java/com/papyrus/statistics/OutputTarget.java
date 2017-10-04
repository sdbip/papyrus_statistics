package com.papyrus.statistics;

import java.io.IOException;

interface OutputTarget {
    void writeHeaders(final Measure... measures) throws IOException;
    void write(final Step step, CalculatedEntry... entries) throws IOException;
    void close() throws IOException;
}

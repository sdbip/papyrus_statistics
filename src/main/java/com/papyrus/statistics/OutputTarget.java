package com.papyrus.statistics;

import java.io.IOException;
import java.util.List;

interface OutputTarget {
    void writeHeaders(Iterable<Measure> measures) throws IOException;
    void write(Step step, CalculatedEntry... entries) throws IOException;
    void close() throws IOException;
}

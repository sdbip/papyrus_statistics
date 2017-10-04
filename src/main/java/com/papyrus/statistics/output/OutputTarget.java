package com.papyrus.statistics.output;

import com.papyrus.statistics.CollectedEntry;
import com.papyrus.statistics.Measure;
import com.papyrus.statistics.Step;

import java.io.IOException;

public interface OutputTarget {
    void writeHeaders(Iterable<Measure> measures) throws IOException;
    void write(Step step, Iterable<CollectedEntry> entries) throws IOException;
    void close() throws IOException;
}

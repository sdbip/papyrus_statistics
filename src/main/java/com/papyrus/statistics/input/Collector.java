package com.papyrus.statistics.input;

import com.papyrus.statistics.*;

import java.util.HashMap;
import java.util.Map;

public class Collector {
    private final InputSource source;

    public Collector(final InputSource source) {
        this.source = source;
    }

    public CollectedData collect() {
        final CollectedData collectedData = new CollectedData();

        for (final InputEntry entry : source.entries()) {
            final Map<Measure, CollectedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.step, k -> new HashMap<>());
            CollectedEntry collectedEntry = entriesForStep.get(entry.measure);
            if (collectedEntry == null) {
                collectedEntry = new CollectedEntry(0, 0, 0);
            }
            final CollectedEntry changedEntry = entry.isError ?
                    new CollectedEntry(collectedEntry.total, collectedEntry.count, collectedEntry.errors + 1) :
                    new CollectedEntry(collectedEntry.total + entry.value, collectedEntry.count + 1, 0);
            entriesForStep.put(entry.measure, changedEntry);

            if (entry.isError) {
                final Integer errorsBefore = collectedData.totalErrorsByMeasure.get(entry.measure);
                collectedData.totalErrorsByMeasure.put(entry.measure, errorsBefore == null ? 1 : errorsBefore + 1);
            }
        }

        return collectedData;
    }
}

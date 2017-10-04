package com.papyrus.statistics.input;

import com.papyrus.statistics.*;

import java.util.HashMap;
import java.util.Map;

public final class Collector {
    private final CollectedData collectedData = new CollectedData();
    private final InputSource source;

    public Collector(final InputSource source) {
        this.source = source;
    }

    public CollectedData collect() {
        for (final InputEntry entry : source.entries()) {
            addToStepInfo(entry);
            if (entry.isError)
                incrementTotalErrors(entry.measure);
        }

        return collectedData;
    }

    private void addToStepInfo(InputEntry entry) {
        final Map<Measure, CollectedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.step, k -> new HashMap<>());
        final CollectedEntry oldEntry = getCollectedEntry(entry.measure, entriesForStep);
        final CollectedEntry changedEntry = entry.isError ?
                new CollectedEntry(oldEntry.total, oldEntry.count, oldEntry.errors + 1) :
                new CollectedEntry(oldEntry.total + entry.value, oldEntry.count + 1, 0);
        entriesForStep.put(entry.measure, changedEntry);
    }

    private void incrementTotalErrors(final Measure measure) {
        final Integer errorsBefore = collectedData.totalErrorsByMeasure.get(measure);
        collectedData.totalErrorsByMeasure.put(measure, errorsBefore == null ? 1 : errorsBefore + 1);
    }

    private CollectedEntry getCollectedEntry(final Measure measure, final Map<Measure, CollectedEntry> entries) {
        final CollectedEntry collectedEntry = entries.get(measure);
        return collectedEntry == null ? new CollectedEntry(0, 0, 0) : collectedEntry;
    }
}

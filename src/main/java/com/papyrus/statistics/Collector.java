package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

class Collector {
    private final InputSource source;

    Collector(final InputSource source) {
        this.source = source;
    }

    CollectedData collect() {
        final CollectedData collectedData = new CollectedData();

        for (final InputEntry entry : source.entries()) {
            if (entry.isError) {
                final Integer errorsBefore = collectedData.totalErrorsByMeasure.get(entry.measure);
                collectedData.totalErrorsByMeasure.put(entry.measure, errorsBefore == null ? 1 : errorsBefore + 1);

                final Map<Measure, CollectedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.step, k -> new HashMap<>());
                CollectedEntry collectedEntry = entriesForStep.get(entry.measure);
                if (collectedEntry == null) {
                    collectedEntry = new CollectedEntry(0, 0, 1);
                    entriesForStep.put(entry.measure, collectedEntry);
                } else {
                    collectedEntry = new CollectedEntry(collectedEntry.total, collectedEntry.count, collectedEntry.errors + 1);
                    entriesForStep.put(entry.measure, collectedEntry);
                }
            }
            else {
                final Map<Measure, CollectedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.step, k -> new HashMap<>());
                CollectedEntry collectedEntry = entriesForStep.get(entry.measure);
                if (collectedEntry == null) {
                    collectedEntry = new CollectedEntry(entry.value, 1, 0);
                    entriesForStep.put(entry.measure, collectedEntry);
                } else {
                    collectedEntry = new CollectedEntry(collectedEntry.total + entry.value, collectedEntry.count + 1, 0);
                    entriesForStep.put(entry.measure, collectedEntry);
                }
            }
        }

        return collectedData;
    }
}

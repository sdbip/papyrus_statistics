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
                final Integer errorsBefore = collectedData.totalErrorsByMeasure.get(entry.measurement.measure);
                collectedData.totalErrorsByMeasure.put(entry.measurement.measure, errorsBefore == null ? 1 : errorsBefore + 1);

                final Map<Measure, CalculatedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.measurement.step, k -> new HashMap<>());
                CalculatedEntry calculatedEntry = entriesForStep.get(entry.measurement.measure);
                if (calculatedEntry == null) {
                    calculatedEntry = new CalculatedEntry(0, 0, 1);
                    entriesForStep.put(entry.measurement.measure, calculatedEntry);
                } else {
                    calculatedEntry = new CalculatedEntry(calculatedEntry.total, calculatedEntry.count, calculatedEntry.errors + 1);
                    entriesForStep.put(entry.measurement.measure, calculatedEntry);
                }
            }
            else {
                final Map<Measure, CalculatedEntry> entriesForStep = collectedData.entries.computeIfAbsent(entry.measurement.step, k -> new HashMap<>());
                CalculatedEntry calculatedEntry = entriesForStep.get(entry.measurement.measure);
                if (calculatedEntry == null) {
                    calculatedEntry = new CalculatedEntry(entry.value, 1, 0);
                    entriesForStep.put(entry.measurement.measure, calculatedEntry);
                } else {
                    calculatedEntry = new CalculatedEntry(calculatedEntry.total + entry.value, calculatedEntry.count + 1, 0);
                    entriesForStep.put(entry.measurement.measure, calculatedEntry);
                }
            }
        }

        return collectedData;
    }
}

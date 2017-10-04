package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.List;

class Collector {
    private final CollectedData collectedData = new CollectedData();
    private final Source source;

    Collector(final Source source) {
        this.source = source;
    }

    CalculatedData collect() {
        for (final CollectedEntry entry : source.entries()) {
            if (entry.isError)
                reportError(entry.measurement);
            else
                add(entry.measurement, entry.value);
        }
        return new Calculator().calculate(collectedData);
    }

    private void add(Measurement measurement, double value) {
        final List<Double> values = collectedData.measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    private void reportError(Measurement measurement) {
        final int before = errorCount(measurement);
        collectedData.errors.put(measurement, before + 1);
    }

    private int errorCount(Measurement measurement) {
        final Integer storedValue = collectedData.errors.get(measurement);
        return storedValue == null ? 0 : storedValue;
    }
}

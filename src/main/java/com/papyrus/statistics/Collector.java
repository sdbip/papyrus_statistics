package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.List;

final class Collector {
    private final CollectedData collectedData = new CollectedData();

    void add(Measurement measurement, double value) {
        final List<Double> values = collectedData.measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    List<Double> getValues(Measurement measurement) {
        return collectedData.measurements.get(measurement);
    }

    void reportError(Measurement measurement) {
        final int before = errorCount(measurement);
        collectedData.errors.put(measurement, before + 1);
    }

    int errorCount(Measurement measurement) {
        final Integer storedValue = collectedData.errors.get(measurement);
        return storedValue == null ? 0 : storedValue;
    }
}

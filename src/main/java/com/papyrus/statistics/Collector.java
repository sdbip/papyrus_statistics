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
        Integer integer = collectedData.errors.get(measurement);
        if (integer == null) integer = 1;
        else integer++;
        collectedData.errors.put(measurement, integer);
    }

    int errorCount(Measurement measurement) {
        return collectedData.errors.get(measurement);
    }
}

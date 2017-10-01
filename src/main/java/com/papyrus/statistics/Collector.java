package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Collector {
    private final Map<Measurement, List<Double>> measurements = new HashMap<>();
    private final Map<Measurement, Integer> errors = new HashMap<>();

    void add(Measurement measurement, double value) {
        final List<Double> values = measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    List<Double> getValues(Measurement measurement) {
        return measurements.get(measurement);
    }

    void reportError(Measurement measurement) {
        Integer integer = errors.get(measurement);
        if (integer == null) integer = 1;
        else integer++;
        errors.put(measurement, integer);
    }

    int errorCount(Measurement measurement) {
        return errors.get(measurement);
    }
}

package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Collector {
    private final Map<Measurement, List<Double>> measurements = new HashMap<>();

    void add(Measurement measurement, double value) {
        final List<Double> values = measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    List<Double> getValues(Measurement measurement) {
        return measurements.get(measurement);
    }
}

package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Collector {
    private final Map<Measurement, List<Double>> measurements = new HashMap<>();

    void add(final String step, final String measure, final double value) {
        final Measurement measurement = new Measurement(step, measure);
        final List<Double> values = measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    List<Double> get(final String step, final String measure) {
        final Measurement measurement = new Measurement(step, measure);
        return measurements.get(measurement);
    }

    final static class Measurement {
        final String step;
        final String measure;

        Measurement(String step, String measure) {
            this.step = step;
            this.measure = measure;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Measurement)) return false;

            final Measurement other = (Measurement) obj;
            return other.step.equals(step) && other.measure.equals(measure);
        }

        @Override
        public int hashCode() {
            return step.hashCode() * 2^16 + measure.hashCode();
        }
    }
}

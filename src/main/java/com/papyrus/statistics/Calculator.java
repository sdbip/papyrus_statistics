package com.papyrus.statistics;

import java.util.Collections;
import java.util.List;

final class Calculator {
    CalculatedData calculate(final CollectedData collectedData) {
        final CalculatedData calculatedData = new CalculatedData();
        for (final Measurement measurement : collectedData.measurements.keySet()) {
            final CalculatedEntry entry = calculateEntry(collectedData, measurement);
            calculatedData.entries.put(measurement.step, Collections.singletonMap(measurement.measure, entry));
        }

        for (final Measurement measurement : collectedData.errors.keySet()) {
            final CalculatedEntry entry = calculateEntry(collectedData, measurement);
            calculatedData.entries.put(measurement.step, Collections.singletonMap(measurement.measure, entry));
        }

        return calculatedData;
    }

    private CalculatedEntry calculateEntry(final CollectedData collectedData, final Measurement measurement) {
        final List<Double> maybeValues = collectedData.measurements.get(measurement);
        final double average = maybeValues == null ? 0 : average(maybeValues);
        final Integer maybeErrors = collectedData.errors.get(measurement);
        final int errors = maybeErrors == null ? 0 : maybeErrors;
        return new CalculatedEntry(average, errors);
    }

    private double average(List<Double> values) {
        return sum(values) / values.size();
    }

    private double sum(Iterable<Double> values) {
        double total = 0;
        for (final double value : values)
            total += value;
        return total;
    }
}

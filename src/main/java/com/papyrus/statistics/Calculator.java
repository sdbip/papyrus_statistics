package com.papyrus.statistics;

import java.util.List;

final class Calculator {
    CalculatedData calculate(final CollectedData collectedData) {
        final CalculatedData calculatedData = new CalculatedData();
        for (final Measurement measurement : collectedData.measurements.keySet()) {
            final List<Double> values = collectedData.measurements.get(measurement);
            final double average = average(values);
            calculatedData.entries.put(measurement.step, new CalculatedEntry(average, 0));
        }

        for (final Measurement measurement : collectedData.errors.keySet()) {
            final int errors = collectedData.errors.get(measurement);
            calculatedData.entries.put(measurement.step, new CalculatedEntry(0, errors));
        }

        return calculatedData;
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

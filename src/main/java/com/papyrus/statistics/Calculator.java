package com.papyrus.statistics;

import java.util.List;

final class Calculator {
    CalculatedData calculate(final CollectedData collectedData) {
        final CalculatedData calculatedData = new CalculatedData();
        for (final Measurement measurement : collectedData.measurements.keySet()) {
            final List<Double> values = collectedData.measurements.get(measurement);
            final double average = average(values);
            calculatedData.averages.put(measurement, average);
        }
        return calculatedData;
    }

    double average(final Measurement measurement, final CalculatedData calculatedData) {
        return calculatedData.averages.get(measurement);
    }

    private double average(List<Double> values) {
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total / values.size();
    }
}

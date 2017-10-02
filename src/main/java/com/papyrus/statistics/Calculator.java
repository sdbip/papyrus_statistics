package com.papyrus.statistics;

import java.util.List;

final class Calculator {
    CalculatedData calculate(final CollectedData collectedData) {
        return new CalculatedData(collectedData);
    }

    double average(final Measurement measurement, final CalculatedData calculatedData) {
        final List<Double> values = calculatedData.collectedData.measurements.get(measurement);
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total / values.size();
    }
}

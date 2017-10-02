package com.papyrus.statistics;

import java.util.List;

final class Calculator {
    private CollectedData collectedData;

    void calculate(final CollectedData collectedData) {
        this.collectedData = collectedData;
    }

    double average(final Measurement measurement) {
        final List<Double> values = collectedData.measurements.get(measurement);
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total / values.size();
    }
}

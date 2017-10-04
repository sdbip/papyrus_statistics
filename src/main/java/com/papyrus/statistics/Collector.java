package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Collector {
    private final CollectedData collectedData = new CollectedData();
    private final Source source;

    Collector(final Source source) {
        this.source = source;
    }

    CalculatedData collect() {
        for (final CollectedEntry entry : source.entries()) {
            if (entry.isError)
                reportError(entry.measurement);
            else
                add(entry.measurement, entry.value);
        }
        return calculate(collectedData);
    }

    CalculatedData calculate(final CollectedData collectedData) {
        final CalculatedData calculatedData = new CalculatedData();
        for (final Measurement measurement : collectedData.measurements.keySet()) {
            final CalculatedEntry entry = calculateEntry(collectedData, measurement);
            addEntry(measurement, entry, calculatedData.entries);
        }

        for (final Measurement measurement : collectedData.errors.keySet()) {
            final CalculatedEntry entry = calculateEntry(collectedData, measurement);
            addEntry(measurement, entry, calculatedData.entries);

            final Integer errorsBefore = calculatedData.totalErrorsByMeasure.computeIfAbsent(measurement.measure, k -> 0);
            calculatedData.totalErrorsByMeasure.put(measurement.measure, errorsBefore + entry.errors);
        }

        return calculatedData;
    }

    private void addEntry(
            final Measurement measurement,
            final CalculatedEntry entry,
            final Map<Step, Map<Measure, CalculatedEntry>> target) {
        final Map<Measure, CalculatedEntry> entriesByMeasure =
                target.computeIfAbsent(measurement.step, k -> new HashMap<>());
        entriesByMeasure.put(measurement.measure, entry);
    }

    private CalculatedEntry calculateEntry(final CollectedData collectedData, final Measurement measurement) {
        final List<Double> maybeValues = collectedData.measurements.get(measurement);
        final double average = maybeValues == null ? 0 : average(maybeValues);
        final Integer maybeErrors = collectedData.errors.get(measurement);
        final int errors = maybeErrors == null ? 0 : maybeErrors;
        return new CalculatedEntry(average, errors);
    }

    private double average(final List<Double> values) {
        return sum(values) / values.size();
    }

    private double sum(final Iterable<Double> values) {
        double total = 0;
        for (final double value : values)
            total += value;
        return total;
    }

    private void add(Measurement measurement, double value) {
        final List<Double> values = collectedData.measurements.computeIfAbsent(measurement, k -> new ArrayList<>());
        values.add(value);
    }

    private void reportError(Measurement measurement) {
        final int before = errorCount(measurement);
        collectedData.errors.put(measurement, before + 1);
    }

    private int errorCount(Measurement measurement) {
        final Integer storedValue = collectedData.errors.get(measurement);
        return storedValue == null ? 0 : storedValue;
    }
}

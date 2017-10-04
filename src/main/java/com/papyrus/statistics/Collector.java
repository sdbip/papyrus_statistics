package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Collector {
    private final Source source;

    Collector(final Source source) {
        this.source = source;
    }

    CalculatedData collect() {
        final CollectedData collectedData = new CollectedData();
        for (final CollectedEntry entry : source.entries()) {
            if (entry.isError) {
                final Integer storedValue = collectedData.errors.get(entry.measurement);
                final int before = storedValue == null ? 0 : storedValue;
                collectedData.errors.put(entry.measurement, before + 1);
            }
            else {
                final List<Double> values = collectedData.measurements.computeIfAbsent(entry.measurement, k -> new ArrayList<>());
                values.add(entry.value);
            }
        }

        final CalculatedData calculatedData = new CalculatedData();
        for (final Measurement measurement : collectedData.measurements.keySet()) {
            final List<Double> maybeValues = collectedData.measurements.get(measurement);
            final double average = maybeValues == null ? 0 : average(maybeValues);
            final Integer maybeErrors = collectedData.errors.get(measurement);
            final int errors = maybeErrors == null ? 0 : maybeErrors;
            final CalculatedEntry entry = new CalculatedEntry(average, errors);
            addEntry(measurement, entry, calculatedData.entries);
        }

        for (final Measurement measurement : collectedData.errors.keySet()) {
            final List<Double> maybeValues = collectedData.measurements.get(measurement);
            final double average = maybeValues == null ? 0 : average(maybeValues);
            final Integer maybeErrors = collectedData.errors.get(measurement);
            final int errors = maybeErrors == null ? 0 : maybeErrors;
            final CalculatedEntry entry = new CalculatedEntry(average, errors);
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

    private double average(final List<Double> values) {
        return sum(values) / values.size();
    }

    private double sum(final Iterable<Double> values) {
        double total = 0;
        for (final double value : values)
            total += value;
        return total;
    }
}

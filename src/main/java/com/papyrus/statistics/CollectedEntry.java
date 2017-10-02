package com.papyrus.statistics;

final class CollectedEntry {
    final Measurement measurement;
    final boolean isError;
    final double value;

    static CollectedEntry error(Measurement measurement) {
        return new CollectedEntry(measurement, true, 0);
    }

    CollectedEntry(Measurement measurement, double value) {
        this(measurement, false, value);
    }

    private CollectedEntry(Measurement measurement, boolean isError, double value) {
        this.measurement = measurement;
        this.isError = isError;
        this.value = value;
    }
}

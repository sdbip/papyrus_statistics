package com.papyrus.statistics;

final class Entry {
    final Measurement measurement;
    final boolean isError;
    final double value;

    static Entry error(Measurement measurement) {
        return new Entry(measurement, true, 0);
    }

    Entry(Measurement measurement, double value) {
        this(measurement, false, value);
    }

    private Entry(Measurement measurement, boolean isError, double value) {
        this.measurement = measurement;
        this.isError = isError;
        this.value = value;
    }
}

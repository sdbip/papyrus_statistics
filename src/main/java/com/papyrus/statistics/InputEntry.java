package com.papyrus.statistics;

final class InputEntry {
    final Measurement measurement;
    final boolean isError;
    final double value;

    static InputEntry error(Measurement measurement) {
        return new InputEntry(measurement, true, 0);
    }

    InputEntry(Measurement measurement, double value) {
        this(measurement, false, value);
    }

    private InputEntry(Measurement measurement, boolean isError, double value) {
        this.measurement = measurement;
        this.isError = isError;
        this.value = value;
    }
}

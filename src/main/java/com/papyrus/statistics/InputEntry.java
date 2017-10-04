package com.papyrus.statistics;

final class InputEntry {
    final Step step;
    final Measure measure;
    final boolean isError;
    final double value;

    static InputEntry error(final Step step, final Measure measure) {
        return new InputEntry(step, measure, true, 0);
    }

    InputEntry(final Step step, final Measure measure, final double value) {
        this(step, measure, false, value);
    }

    private InputEntry(final Step step, final Measure measure, final boolean isError, final double value) {
        this.step = step;
        this.measure = measure;
        this.isError = isError;
        this.value = value;
    }
}

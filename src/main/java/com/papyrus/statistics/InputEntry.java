package com.papyrus.statistics;

public final class InputEntry {
    public final Step step;
    public final Measure measure;
    public final boolean isError;
    public final double value;

    public static InputEntry error(final Step step, final Measure measure) {
        return new InputEntry(step, measure, true, 0);
    }

    public InputEntry(final Step step, final Measure measure, final double value) {
        this(step, measure, false, value);
    }

    private InputEntry(final Step step, final Measure measure, final boolean isError, final double value) {
        this.step = step;
        this.measure = measure;
        this.isError = isError;
        this.value = value;
    }
}

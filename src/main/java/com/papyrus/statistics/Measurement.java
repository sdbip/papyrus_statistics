package com.papyrus.statistics;

final class Measurement {
    final Step step;
    final String measure;

    Measurement(Step step, String measure) {
        this.step = step;
        this.measure = measure;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Measurement)) return false;

        final Measurement other = (Measurement) obj;
        return other.step.equals(step) && other.measure.equals(measure);
    }

    @Override
    public int hashCode() {
        return step.hashCode() * 2^16 + measure.hashCode();
    }
}

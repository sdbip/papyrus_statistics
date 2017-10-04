package com.papyrus.statistics;

public final class CollectedEntry {
    public final double total;
    public final int count;
    public final int errors;

    public CollectedEntry(final double total, final int count, final int errors) {
        this.total = total;
        this.count = count;
        this.errors = errors;
    }
}

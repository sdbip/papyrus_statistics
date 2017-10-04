package com.papyrus.statistics;

class CollectedEntry {
    final double total;
    final int count;
    final int errors;

    CollectedEntry(final double total, final int count, final int errors) {
        this.total = total;
        this.count = count;
        this.errors = errors;
    }
}

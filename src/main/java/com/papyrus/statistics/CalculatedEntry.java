package com.papyrus.statistics;

class CalculatedEntry {
    final double total;
    final int count;
    final int errors;

    CalculatedEntry(final double total, final int count, final int errors) {
        this.total = total;
        this.count = count;
        this.errors = errors;
    }
}

package com.papyrus.statistics;

import java.util.Collections;
import java.util.List;

class Collector {
    double value;

    void add(final String step, final String measure, double value) {
        this.value = value;
    }

    List<Double> get(final String step, final String measure) {
        return Collections.singletonList(value);
    }
}

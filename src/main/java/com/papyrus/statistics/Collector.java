package com.papyrus.statistics;

import java.util.ArrayList;
import java.util.List;

final class Collector {
    final List<Double> values = new ArrayList<Double>();

    void add(final String step, final String measure, final double value) {
        values.add(value);
    }

    List<Double> get(final String step, final String measure) {
        return values;
    }
}

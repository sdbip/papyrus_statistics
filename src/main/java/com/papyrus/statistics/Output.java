package com.papyrus.statistics;

import java.io.IOException;
import java.util.Set;

class Output {
    private final OutputTarget target;

    Output(final OutputTarget target) {
        this.target = target;
    }

    void output(final CalculatedData calculatedData) throws IOException {
        final Set<Measure> measures = calculatedData.totalErrorsByMeasure.keySet();
        for (final Measure measure : measures)
            target.writeHeaders(measure);
        final Set<Step> steps = calculatedData.entries.keySet();
        for (final Step step : steps) {
            for (final Measure measure : measures)
                target.write(step, calculatedData.entries.get(step).get(measure));
        }
    }
}

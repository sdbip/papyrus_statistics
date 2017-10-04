package com.papyrus.statistics;

import java.io.IOException;
import java.util.*;

class Output {
    private final OutputTarget target;

    Output(final OutputTarget target) {
        this.target = target;
    }

    void output(final CalculatedData calculatedData) throws IOException {
        final Set<Measure> measures = calculatedData.totalErrorsByMeasure.keySet();
        target.writeHeaders(getMeasures(calculatedData.totalErrorsByMeasure));

        final Set<Step> steps = calculatedData.entries.keySet();
        for (final Step step : steps) {
            for (final Measure measure : measures)
                target.write(step, calculatedData.entries.get(step).get(measure));
        }
    }

    private List<Measure> getMeasures(final Map<Measure, Integer> map) {
        final List<Measure> result = new ArrayList<>();
        result.addAll(map.keySet());
        result.sort((measure1, measure2) -> map.get(measure2) - map.get(measure1));
        return result;
    }
}

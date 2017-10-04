package com.papyrus.statistics;

import java.io.IOException;
import java.util.*;

class Output {
    private final OutputTarget target;

    Output(final OutputTarget target) {
        this.target = target;
    }

    void output(final CollectedData collectedData) throws IOException {
        final List<Measure> measures = getMeasures(collectedData.totalErrorsByMeasure);
        target.writeHeaders(measures);

        final Set<Step> steps = collectedData.entries.keySet();
        for (final Step step : steps) {
            final List<CalculatedEntry> entries = new ArrayList<>();
            for (final Measure measure : measures) {
                entries.add(collectedData.entries.get(step).get(measure));
            }
            target.write(step, entries);
        }
    }

    private List<Measure> getMeasures(final Map<Measure, Integer> map) {
        final List<Measure> result = new ArrayList<>();
        result.addAll(map.keySet());
        result.sort((measure1, measure2) -> map.get(measure2) - map.get(measure1));
        return result;
    }
}

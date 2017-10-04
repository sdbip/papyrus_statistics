package com.papyrus.statistics.output;

import com.papyrus.statistics.CollectedData;
import com.papyrus.statistics.CollectedEntry;
import com.papyrus.statistics.Measure;
import com.papyrus.statistics.Step;

import java.io.IOException;
import java.util.*;

public final class Output {
    private final OutputTarget target;

    public Output(final OutputTarget target) {
        this.target = target;
    }

    public void output(final CollectedData collectedData) throws IOException {
        final List<Measure> measures = getMeasures(collectedData.totalErrorsByMeasure);
        target.writeHeaders(measures);

        final Set<Step> steps = collectedData.entries.keySet();
        for (final Step step : steps) {
            final List<CollectedEntry> entries = new ArrayList<>();
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

package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

public final class CollectedData {
    public final Map<Step, Map<Measure, CollectedEntry>> entries = new HashMap<>();
    public final Map<Measure, Integer> totalErrorsByMeasure = new HashMap<>();
}

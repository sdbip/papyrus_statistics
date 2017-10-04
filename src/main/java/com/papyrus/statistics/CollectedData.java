package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

final class CollectedData {
    final Map<Step, Map<Measure, CollectedEntry>> entries = new HashMap<>();
    final Map<Measure, Integer> totalErrorsByMeasure = new HashMap<>();
}

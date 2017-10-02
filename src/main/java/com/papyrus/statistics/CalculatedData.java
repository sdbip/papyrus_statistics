package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

final class CalculatedData {
    final Map<String, Map<String, CalculatedEntry>> entries = new HashMap<>();
    final Map<String, Integer> totalErrorsByMeasure = new HashMap<>();
}

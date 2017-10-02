package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

final class CalculatedData {
    final Map<Measurement, Integer> errors = new HashMap<>();
    final Map<String, CalculatedEntry> entries = new HashMap<>();
}

package com.papyrus.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class CollectedData {
    final Map<Measurement, List<Double>> measurements = new HashMap<>();
    final Map<Measurement, Integer> errors = new HashMap<>();
}

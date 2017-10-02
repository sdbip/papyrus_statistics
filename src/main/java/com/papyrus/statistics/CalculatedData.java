package com.papyrus.statistics;

import java.util.HashMap;
import java.util.Map;

final class CalculatedData {
    final Map<Measurement, Double> averages = new HashMap<>();
    final Map<Measurement, Integer> errors = new HashMap<>();
}

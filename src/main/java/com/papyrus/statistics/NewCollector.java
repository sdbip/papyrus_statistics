package com.papyrus.statistics;

class NewCollector {
    private final Collector collector;

    NewCollector(Source source) {
        collector = new Collector(source);
    }

    CalculatedData collect() {
        return new Calculator().calculate(collector.collect());
    }
}

package com.papyrus.statistics;

public class Program {
    public static void main(String[] args) {
        System.out.println("program running");

        final Source source = null; // = new FileSource(args[0]);
        final Collector collector = new Collector(source);
        final CollectedData collectedData = collector.collect();

        final Calculator calculator = new Calculator();
        final CalculatedData calculatedData = calculator.calculate(collectedData);

//        final Output output = new FileOutput(args[1]);
//        output.write(calculatedData);
    }
}

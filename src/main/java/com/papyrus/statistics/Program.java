package com.papyrus.statistics;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        System.out.println("program running");

        final InputStream inputStream = new FileInputStream(args[0]);
        final Source source = new CSVSource(inputStream);
        final Collector collector = new Collector(source);
        final CollectedData collectedData = collector.collect();

        final Calculator calculator = new Calculator();
        final CalculatedData calculatedData = calculator.calculate(collectedData);

//        final Output output = new FileOutput(args[1]);
//        output.write(calculatedData);
    }
}

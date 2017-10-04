package com.papyrus.statistics;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        System.out.println("program running");

        final InputStream inputStream = new FileInputStream(args[0]);
        final Source source = new CSVSource(inputStream);
        final Collector collector = new Collector(source);
        final CollectedData collectedData = collector.collect();

        final OutputStream outputStream = new FileOutputStream(args[1]);
        final OutputTarget target = new CSVOutputTarget(outputStream);
        final Output output = new Output(target);
        output.output(collectedData);
    }
}

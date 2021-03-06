package com.papyrus.statistics.program;

import com.papyrus.statistics.CollectedData;
import com.papyrus.statistics.csv.CSVInputSource;
import com.papyrus.statistics.csv.CSVOutputTarget;
import com.papyrus.statistics.input.Collector;
import com.papyrus.statistics.input.InputSource;
import com.papyrus.statistics.output.Output;
import com.papyrus.statistics.output.OutputTarget;

import java.io.*;

public final class Program {
    public static void main(String[] args) throws IOException {
        final InputStream inputStream = new FileInputStream(args[0]);
        final InputSource source = new CSVInputSource(inputStream);
        final Collector collector = new Collector(source);
        final CollectedData collectedData = collector.collect();

        final OutputStream outputStream = new FileOutputStream(args[1]);
        final OutputTarget target = new CSVOutputTarget(outputStream);
        final Output output = new Output(target);
        output.output(collectedData);
    }
}

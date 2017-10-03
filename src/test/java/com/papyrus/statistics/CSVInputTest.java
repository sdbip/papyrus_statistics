package com.papyrus.statistics;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CSVInputTest {
    @Test
    public void aTest() throws IOException {
        final String csv =
                "Process Step;Measure;Value\n" +
                "Picking;Duration;9.0\n";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(csv.getBytes());

        final CSVSource source = new CSVSource(inputStream);

        final List<CollectedEntry> entries = new ArrayList<>();
        source.entries().forEach(entries::add);

        assertEquals(1, entries.size());
        final CollectedEntry entry = entries.get(0);
        assertEquals(new Measurement(TestSteps.picking, TestMeasures.duration), entry.measurement);
        assertFalse(entry.isError);
        assertEquals(9.0, entry.value, 0.001);
    }
}

package com.papyrus.statistics;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CSVInputTest {
    @Test
    public void parsesExcelCSV() throws IOException {
        final CSVInputSource source = createCSVSource(
                "Process Step;Measure;Value\n" +
                "Picking;Duration;9.0\n");

        final List<InputEntry> entries = getCollectedEntries(source);

        assertEquals(1, entries.size());
        final InputEntry entry = entries.get(0);
        assertEquals(new Measurement(TestSteps.picking, TestMeasures.duration), entry.measurement);
        assertFalse(entry.isError);
        assertEquals(9.0, entry.value, 0.001);
    }

    @Test
    public void parsesErrors() throws IOException {
        final CSVInputSource source = createCSVSource(
                "Process Step;Measure;Value\n" +
                "Picking;Duration;ERROR\n");

        final List<InputEntry> entries = getCollectedEntries(source);

        assertEquals(1, entries.size());
        final InputEntry entry = entries.get(0);
        assertEquals(new Measurement(TestSteps.picking, TestMeasures.duration), entry.measurement);
        assertTrue(entry.isError);
    }

    @Test
    public void parsesSampleCSV() throws IOException {
        final InputStream inputStream = openResource("test_data_java_exercise.csv");
        final CSVInputSource source = new CSVInputSource(inputStream);

        final List<InputEntry> entries = getCollectedEntries(source);

        assertEquals(999, entries.size());
        final InputEntry entry = entries.get(0);
        assertEquals(new Measurement(new Step("Shipping"), new Measure("Stops")), entry.measurement);
        assertFalse(entry.isError);
        assertEquals(7.0, entry.value, 0.001);
    }

    @SuppressWarnings("SameParameterValue")
    private InputStream openResource(final String name) throws IOException {
        final URL resource = getClass().getClassLoader().getResource(name);
        return resource == null ? null : resource.openStream();
    }

    private CSVInputSource createCSVSource(String csv) throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(csv.getBytes());
        return new CSVInputSource(inputStream);
    }

    private List<InputEntry> getCollectedEntries(CSVInputSource source) {
        final List<InputEntry> entries = new ArrayList<>();
        source.entries().forEach(entries::add);
        return entries;
    }
}

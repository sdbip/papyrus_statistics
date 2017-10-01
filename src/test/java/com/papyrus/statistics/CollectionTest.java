package com.papyrus.statistics;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public final class CollectionTest {
    @Test
    public void collectsSingleValue() {
        final Collector collector = new Collector();

        collector.add("Picking", "Duration", 9.0);

        assertEquals(Collections.singletonList(9.0), collector.get("Picking", "Duration"));
    }
}

package com.papyrus.statistics;

import java.util.List;

final class TestSource implements Source {
    List<Entry> entries;

    @Override
    public Iterable<Entry> entries() {
        return entries;
    }
}

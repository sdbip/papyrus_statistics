package com.papyrus.statistics;

import java.util.List;

final class TestSource implements Source {
    List<CollectedEntry> entries;

    @Override
    public Iterable<CollectedEntry> entries() {
        return entries;
    }
}

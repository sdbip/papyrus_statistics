package com.papyrus.statistics;

public interface Source {
    Iterable<CollectedEntry> entries();
}

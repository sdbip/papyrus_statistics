package com.papyrus.statistics.input;

import java.util.List;

final class TestInputSource implements InputSource {
    List<InputEntry> entries;

    @Override
    public Iterable<InputEntry> entries() {
        return entries;
    }
}

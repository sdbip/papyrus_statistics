package com.papyrus.statistics;

public final class Measure {
    private final String name;

    public Measure(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Measure)) return false;
        final Measure other = (Measure) obj;
        return other.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}

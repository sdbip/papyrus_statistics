package com.papyrus.statistics;

public final class Step {
    public final String name;

    public Step(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Step)) return false;
        final Step other = (Step) obj;
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

package ua.khpi.oop.labs.mykhailiuk10.models.classes.abstractions;

import java.io.Serializable;

/**
 * Class that strands for single note in the book
 */
public abstract class StoringBox<V1, V2> implements Serializable {
    private final V1 value1;
    private final V2 value2;

    public StoringBox(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    protected V1 getValue1() {
        return value1;
    }

    protected V2 getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return "[" + value1 + ", " + value2 + "]";
    }
}

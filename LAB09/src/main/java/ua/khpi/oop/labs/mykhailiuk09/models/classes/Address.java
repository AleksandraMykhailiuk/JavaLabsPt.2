package ua.khpi.oop.labs.mykhailiuk09.models.classes;

import java.io.Serializable;

public class Address implements Serializable {
    private final String value;

    public Address(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}

package ua.khpi.oop.labs.mykhailiuk10.models.classes;

import java.io.Serializable;

/**
 * Address represents address as domain address object
 */
public class Address implements Serializable {
    private String value;

    public Address(String value) {
        this.value = value;
    }

    public Address() {
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}

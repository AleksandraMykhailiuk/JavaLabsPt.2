package ua.khpi.oop.labs.mykhailiuk10.models.classes;

import java.io.Serializable;

/**
 * Phone number represent serializable phone number as DTO object
 */
public class Phone implements Serializable {
    private final String value;

    public Phone(String value) {
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

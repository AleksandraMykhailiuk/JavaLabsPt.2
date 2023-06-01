package ua.khpi.oop.labs.mykhailiuk09.models.classes;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements Serializable {
    private final String value;

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}

package ua.khpi.opp.labs.mykhailiuk13.models.classes;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Address represents address as domain address object
 */
public class Address implements Serializable {
    private static final String PATTERN = "^st\\..*";
    private String value;

    private Address(String value) {
        this.value = value;
    }

    /**
     * Method for creation valid address
     * @param value value for the address
     * @return valid Address object
     * @throws IllegalArgumentException in case if Address value is not valid
     */
    public static Address createAddress(String value) {
        if(!validate(value)) {
            throw new IllegalArgumentException(
                    "Cannot create adress with invalid value!" +
                            " Add st. at the beginning of your value"
            );
        }
        return new Address(value);
    }

    private static boolean validate(String value) {
        return Pattern.compile(PATTERN).matcher(value).matches();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}

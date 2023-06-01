package ua.khpi.opp.labs.mykhailiuk16.models.classes;

import ua.khpi.opp.labs.mykhailiuk16.exceptions.DataNotValidException;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Phone number represent serializable phone number as DTO object
 */
public class Phone implements Serializable {
    private static final String PATTERN = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s" +
            "\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    private final String value;

    private Phone(String value) {
        this.value = value;
    }


    /**
     * Method for creating valid phone number
     * @param value Phone number value
     * @return valid phone number
     */
    public static Phone createPhone(String value) {
        if (!validate(value)) {
            throw new DataNotValidException(
                    "Phone number with this value id not valid! " +
                            "Try to type real phone number!"
            );
        }
        return new Phone(value);
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
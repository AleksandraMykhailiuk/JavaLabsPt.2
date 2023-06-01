package ua.khpi.opp.labs.mykhailiuk12.constants;

/**
 * Class that keeps regex for finding elements in address book
 */
public enum FindingRegex {
    PHONE_NUMBERS("^\\+380?.*"),
    STREET_KHARKIV("st\\..* Kharkiv");

    private final String value;

    private FindingRegex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

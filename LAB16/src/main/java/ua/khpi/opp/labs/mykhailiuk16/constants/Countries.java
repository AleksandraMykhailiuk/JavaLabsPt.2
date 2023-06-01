package ua.khpi.opp.labs.mykhailiuk16.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum class that contains countries
 */
public enum Countries {
    AFGHANISTAN("Afghanistan"),
    ALBANIA("Albania"),
    ALGERIA("Algeria"),
    ARGENTINA("Argentina"),
    AUSTRALIA("Australia"),
    AUSTRIA("Austria"),
    BANGLADESH("Bangladesh"),
    BELGIUM("Belgium"),
    BRAZIL("Brazil"),
    CANADA("Canada"),
    CHINA("China"),
    COLOMBIA("Colombia"),
    CROATIA("Croatia"),
    CUBA("Cuba"),
    CZECH_REPUBLIC("Czech Republic"),
    DENMARK("Denmark"),
    EGYPT("Egypt"),
    ETHIOPIA("Ethiopia"),
    FINLAND("Finland"),
    FRANCE("France"),
    GERMANY("Germany"),
    GREECE("Greece"),
    INDIA("India"),
    INDONESIA("Indonesia"),
    IRAN("Iran"),
    IRAQ("Iraq"),
    IRELAND("Ireland"),
    ISRAEL("Israel"),
    ITALY("Italy"),
    JAPAN("Japan"),
    KENYA("Kenya"),
    MALAYSIA("Malaysia"),
    MEXICO("Mexico"),
    NETHERLANDS("Netherlands"),
    NEW_ZEALAND("New Zealand"),
    NIGERIA("Nigeria"),
    NORWAY("Norway"),
    PAKISTAN("Pakistan"),
    PERU("Peru"),
    PHILIPPINES("Philippines"),
    POLAND("Poland"),
    UKRAINE("Ukraine"),
    SAUDI_ARABIA("Saudi Arabia"),
    SOUTH_AFRICA("South Africa"),
    SOUTH_KOREA("South Korea"),
    SPAIN("Spain"),
    SWEDEN("Sweden"),
    SWITZERLAND("Switzerland"),
    THAILAND("Thailand"),
    TURKEY("Turkey"),
    UNITED_KINGDOM("United Kingdom"),
    UNITED_STATES("United States");

    private final String value;

    Countries(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getAllContriesAdsList() {
        return Arrays.stream(values()).map(Countries::getValue).collect(Collectors.toList());
    }
}


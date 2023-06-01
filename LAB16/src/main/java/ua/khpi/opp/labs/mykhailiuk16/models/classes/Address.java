package ua.khpi.opp.labs.mykhailiuk16.models.classes;

import ua.khpi.opp.labs.mykhailiuk16.constants.Countries;
import ua.khpi.opp.labs.mykhailiuk16.exceptions.DataNotValidException;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Address represents address as domain address object
 */
public class Address implements Serializable {
    private static final int PARAMETERS_COUNT = 5;
    private static final String STREET_PATTERN = "^st\\..*";
    private static final String POSTAL_CODE_PATTERN = "^\\d{5}(?:[-\\s]\\d{4})?$";
    private String street;
    private String city;
    private String regionOrState;
    private String postalCode;
    private String country;

    private Address(String street, String city, String regionOrState, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.regionOrState = regionOrState;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * Method for creation valid address
     * @param value value for the address
     * value must contain items and keep them with the same order as here:
     * street, city, regionOrState, postalCode, country
     * @return valid Address object
     * @throws DataNotValidException in case cannot split the value into variables
     */
    public static Address createAddress(String value) {
        String[] values = value.split(", ");
        if(values.length < PARAMETERS_COUNT) {
            throw new DataNotValidException(
                    "Incorrect value of address line!"
            );
        }
        String street = values[0];
        String city = values[1];
        String regionOrState = values[2];
        String postalCode = values[3];
        String country = values[4];

        return createAddressWithDetails(street, city, regionOrState, postalCode, country);
    }

    /**
     * Method for creation valid address
     * with parameters
     * street, city, regionOrState, postalCode, country
     * @return valid Address object
     * @throws DataNotValidException in case cannot split the value into variables
     */
    public static Address createAddress(
            String street, String city, String regionOrState, String postalCode, String country
    ) {
        return createAddressWithDetails(street, city, regionOrState, postalCode, country);
    }

    private static Address createAddressWithDetails(
            String street, String city, String regionOrState, String postalCode, String country
    ) {
        validate(street, postalCode, country);
        return new Address(street, city, regionOrState, postalCode, country);
    }

    private static void validate(String street, String postalCode, String country) {
        if(!Pattern.compile(STREET_PATTERN).matcher(street).matches()) {
            throw new DataNotValidException(
                    "Street is not valid, it must start with 'st.': " + street
            );
        }
        if(!Countries.getAllContriesAdsList().contains(country)) {
            throw new DataNotValidException(
                    "Country is not valid, it must be one of the list:" +
                            Countries.getAllContriesAdsList()
            );
        }
        if (!Pattern.compile(POSTAL_CODE_PATTERN).matcher(postalCode).matches()) {
            throw new DataNotValidException(
                    "Postal code in incorrect, please type correct value!"
            );
        }

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegionOrState() {
        return regionOrState;
    }

    public void setRegionOrState(String regionOrState) {
        this.regionOrState = regionOrState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String combineAllFields() {
        return street + ", " + city + ", " + regionOrState + ", " + postalCode + ", " + country;
    }

    public String toAddressLine() {
        return this.combineAllFields();
    }

    @Override
    public String toString() {
        return "[" + this.combineAllFields() + "]";
    }
}

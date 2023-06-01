package models.classes.models;

import models.classes.models.abstractions.AddressTestInterface;
import org.junit.jupiter.api.Test;
import ua.khpi.opp.labs.mykhailiuk16.exceptions.DataNotValidException;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Address;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing address class
 */
public class AddressTest implements AddressTestInterface {
    @Override
    @Test
    public void createAddressWithOneValue_successExpected() {
        String correctAddress = "st. Pushkinska, Kharkiv, Kharkiv Region, 00896, Ukraine";
        Address address = Address.createAddress(correctAddress);

        assertEquals(correctAddress, address.toAddressLine());
    }

    @Override
    @Test
    public void createAddressWithOneValue_failExpected() {
        String incorrectAddress = "st. Pushkinska, Kharkiv";

        assertThrows(DataNotValidException.class,
                () -> Address.createAddress(incorrectAddress));
    }

    @Override
    @Test
    public void successCreation_returnObject() {
        String correctAddress = "st. Pushkinska, Kharkiv, Kharkiv Region, 00896, Ukraine";
        String[] values = correctAddress.split(", ");
        Address address = Address.createAddress(
                values[0],
                values[1],
                values[2],
                values[3],
                values[4]
        );

        assertEquals(correctAddress, address.toAddressLine());
    }

    @Override
    @Test
    public void failCreation_exceptionThrowing() {
        String correctAddress = "st. Pushkinska, Kharkiv, Kharkiv Region, 00896, Ukraine";
        String[] values = correctAddress.split(", ");
        assertThrows(DataNotValidException.class,
                () ->Address.createAddress(
                        "Not valid",
                        values[1],
                        values[2],
                        values[3],
                        values[4]
                ));
    }
}

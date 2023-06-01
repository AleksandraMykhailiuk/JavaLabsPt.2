package models.classes.containers;

import models.classes.containers.abstractions.CreateReadUpdateDeleteContainerTestInterface;
import org.junit.jupiter.api.Test;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk16.models.containers.AddressBook;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing address book container
 */
public class AddressBookTest implements CreateReadUpdateDeleteContainerTestInterface {
    private final String phoneValue = "+380999425174";
    private final String addressBookValue = "st. Pushkinska, Kharkiv, Khrakiv Region, 64000, Ukraine";

    @Override
    @Test
    public void createContainer_returnAddressBookContainer() {
        assertDoesNotThrow(() -> new AddressBook());
    }

    @Override
    @Test
    public void addElementToContainer_success() {
        AddressBook addressBook = new AddressBook();

        addressBook.add(new Note(
                Phone.createPhone(phoneValue),
                Address.createAddress(addressBookValue)
        ));

        assertEquals(phoneValue, addressBook.get(0).getPhoneNumber().getValue());
        assertEquals(addressBookValue, addressBook.get(0).getAddress().toAddressLine());
    }

    @Override
    @Test
    public void removeElementFromContainer_success() {
        AddressBook addressBook = new AddressBook();

        addressBook.add(new Note(
                Phone.createPhone(phoneValue),
                Address.createAddress(addressBookValue)
        ));

        addressBook.remove(0);

        assertEquals(0, addressBook.size());
    }

    @Override
    @Test
    public void removeElementFromContainer_fail() {
        AddressBook addressBook = new AddressBook();

        addressBook.add(new Note(
                Phone.createPhone(phoneValue),
                Address.createAddress(addressBookValue)
        ));

        addressBook.remove(0);

        assertThrows(IndexOutOfBoundsException.class,
                () -> addressBook.remove(0));
    }

    @Override
    @Test
    public void getElementFromContainer_success() {
        AddressBook addressBook = new AddressBook();
        Note note = new Note(
                Phone.createPhone(phoneValue),
                Address.createAddress(addressBookValue)
        );

        addressBook.add(note);

        assertEquals(note, addressBook.get(0));
    }

    @Override
    @Test
    public void getElementFromContainer_fail() {
        AddressBook addressBook = new AddressBook();

        assertThrows(IndexOutOfBoundsException.class,
                () -> addressBook.get(0)
        );
    }
}

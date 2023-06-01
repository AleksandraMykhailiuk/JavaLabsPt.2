import org.junit.jupiter.api.Test;
import ua.khpi.opp.labs.mykhailiuk16.AddressBookRegexFinder;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk16.models.containers.AddressBook;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Method for testing the address book regex finder
 */
public class AddressBookRegexFinderTest {
    private final AddressBook addressBook;
    public AddressBookRegexFinderTest() {
        addressBook = new AddressBook();
    }

    /**
     * Method for validating finding a vodafone number
     * one element expected
     */
    @Test
    public void findVodafone_returnOneElement() {
        Note note = new Note(
                Phone.createPhone("+380999425174"),
                Address.createAddress("st. Pushkinskaya, Kharkiv, Kharkiv Region, 64000, Ukraine")
        );
        addressBook.add(note);

        assertEquals(note,
                new AddressBookRegexFinder(addressBook).findVodafoneInKharkiv().get(0)
        );
    }

    /**
     * Method for validating finding a vodafone number
     * no elements expected
     */
    @Test
    public void findVodafone_returnEmpty() {
        addressBook.clear();

        assertEquals(
                0,
                new AddressBookRegexFinder(addressBook).findVodafoneInKharkiv().size()
        );
    }

    /**
     * Method for validating finding a kyivstar number
     * one element expected
     */
    @Test
    public void findKyivstar_returnOneElement() {
        Note note = new Note(
                Phone.createPhone("+380679425174"),
                Address.createAddress("st. Pushkinskaya, Kharkiv, Kharkiv Region, 64000, Ukraine")
        );
        addressBook.add(note);

        assertEquals(note,
                new AddressBookRegexFinder(addressBook).findKyivstarInKharkiv().get(0)
        );
    }

    /**
     * Method for validating finding a kyivstar number
     * no elements expected
     */
    @Test
    public void findKyivstar_returnEmpty() {
        Note note = new Note(
                Phone.createPhone("+380999425174"),
                Address.createAddress("st. Pushkinskaya, Kharkiv, Kharkiv Region, 64000, Ukraine")
        );
        addressBook.add(note);

        assertEquals(0,
                new AddressBookRegexFinder(addressBook).findKyivstarInKharkiv().size()
        );
    }

}

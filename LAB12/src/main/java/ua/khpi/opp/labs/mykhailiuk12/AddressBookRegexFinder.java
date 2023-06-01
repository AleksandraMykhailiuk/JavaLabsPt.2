package ua.khpi.opp.labs.mykhailiuk12;

import ua.khpi.opp.labs.mykhailiuk12.constants.FindingRegex;
import ua.khpi.opp.labs.mykhailiuk12.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk12.models.containers.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class for finding elements from address book
 */
public class AddressBookRegexFinder {
    private static final String[] kyivstarNumberStartings = {"39", "67", "98", "68"};
    private static final String[] vodafoneNumberStartings = {"50", "66", "95", "99"};
    private final AddressBook addressBook;

    public AddressBookRegexFinder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    private List<Note> findPhoneNumberInKharkiv(String... numberStartings) {
        List<Note> notes = new ArrayList<>();
        addressBook.forEach(
                (element) -> {
                    if(Pattern.compile(FindingRegex.STREET_KHARKIV.getValue())
                            .matcher(element.getAddress().getValue()).matches()) {
                        for (String numberStarting : numberStartings) {
                            Pattern pattern = Pattern
                                    .compile(
                                            FindingRegex.PHONE_NUMBERS.getValue()
                                                    .replace("?", numberStarting)
                                    );

                            if (pattern.matcher(element.getPhoneNumber().getValue()).matches()) {
                                notes.add(element);
                                break;
                            }
                        }
                    }
                }
        );
        return notes;
    }

    public List<Note> findKyivstarInKharkiv() {
        return findPhoneNumberInKharkiv(kyivstarNumberStartings);
    }

    public List<Note> findVodafoneInKharkiv() {
        return findPhoneNumberInKharkiv(vodafoneNumberStartings);
    }
}

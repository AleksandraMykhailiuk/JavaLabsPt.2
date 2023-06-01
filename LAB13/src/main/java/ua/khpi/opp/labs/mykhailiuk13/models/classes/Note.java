package ua.khpi.opp.labs.mykhailiuk13.models.classes;

import ua.khpi.opp.labs.mykhailiuk13.models.classes.abstractions.StoringBox;

/**
 * Note represents storage box in the address book
 */
public class Note extends StoringBox<Phone, Address> {
    public Note(Phone phone, Address address) {
        super(phone, address);
    }

    public Phone getPhoneNumber() {
        return getValue1();
    }

    public Address getAddress() {
        return getValue2();
    }
}

package ua.khpi.opp.labs.mykhailiuk12.models.containers;

import ua.khpi.opp.labs.mykhailiuk12.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk12.models.classes.User;

/**
 * Address book that represents container and extends from Storage room
 */
public class AddressBook extends StorageRoom<Note> {
    public AddressBook(User user) {
        super(user);
    }

    public AddressBook() {}
}

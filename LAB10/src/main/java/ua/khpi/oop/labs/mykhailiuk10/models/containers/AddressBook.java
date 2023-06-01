package ua.khpi.oop.labs.mykhailiuk10.models.containers;

import ua.khpi.oop.labs.mykhailiuk10.models.classes.Note;
import ua.khpi.oop.labs.mykhailiuk10.models.classes.User;

/**
 * Address book that represents container and extends from Storage room
 */
public class AddressBook extends StorageRoom<Note> {
    public AddressBook(User user) {
        super(user);
    }

    public AddressBook() {}
}

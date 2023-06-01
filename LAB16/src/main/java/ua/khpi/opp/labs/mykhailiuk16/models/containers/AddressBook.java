package ua.khpi.opp.labs.mykhailiuk16.models.containers;

import ua.khpi.opp.labs.mykhailiuk16.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.User;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Address book that represents container and extends from Storage room
 */
public class AddressBook extends StorageRoom<Note>
        implements Serializable {
    public AddressBook(User user) {
        super(user);
    }

    public AddressBook() {}

}

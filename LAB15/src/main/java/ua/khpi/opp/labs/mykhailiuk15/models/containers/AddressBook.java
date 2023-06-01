package ua.khpi.opp.labs.mykhailiuk15.models.containers;

import ua.khpi.opp.labs.mykhailiuk15.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk15.models.classes.User;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.LinkedList;

/**
 * Address book that represents container and extends from Storage room
 */
public class AddressBook extends LinkedList<Note>
        implements Serializable {
    @Override
    public String toString() {
        return super.toString();
    }
}

package ua.khpi.oop.labs.mykhailiuk10;


import ua.khpi.oop.labs.mykhailiuk10.models.classes.Address;
import ua.khpi.oop.labs.mykhailiuk10.models.classes.Note;
import ua.khpi.oop.labs.mykhailiuk10.models.classes.Phone;
import ua.khpi.oop.labs.mykhailiuk10.models.containers.AddressBook;
import ua.khpi.oop.labs.mykhailiuk10.models.containers.StorageRoom;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that represents program
 */
public class AddressBookApplication {
    private static AddressBookApplication instance = null;
    private boolean autoMode = false;

    private AddressBookApplication(boolean autoMode) {
        this.autoMode = autoMode;
    }

    public static AddressBookApplication getInstance(boolean autoMode) {
        if (instance == null) {
            instance = new AddressBookApplication(autoMode);
        }
        return instance;
    }

    /**
     * Starts the program
     */
    public void start() {
        AddressBook addressBook = new AddressBook();
        if (autoMode) {
            Note[] notes = new Note[2];
            for (int i = 0; i < notes.length; i++) {
                notes[i] = new Note(
                        new Phone("+3455555295557"),
                        new Address("Kharkiv, Kyivskaya")
                );
            }
            runAuto(addressBook, notes);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type here amount of notes");
            int times = getScannedInteger(scanner);
            Note[] seedingNotes = new Note[times];
            for (int i = 0; i < seedingNotes.length; i++) {
                System.out.println("Please, type here address number: " + (i + 1));
                String address = scanner.nextLine();
                System.out.println("Please, type here phone number: " + (i + 1));
                String phoneNumber = scanner.nextLine();
                seedingNotes[i] = new Note(
                        new Phone(phoneNumber),
                        new Address(address)
                );
            }
            run(scanner, addressBook, seedingNotes);
        }
    }

    private <T> void run(Scanner scanner, StorageRoom<T> storageRoom, T[] elements) {
        System.out.println("Seeding passed elements to your user storage...");
        storageRoom.addAll(Arrays.asList(elements));
        printGenericArray(storageRoom);
        System.out.println("Removing some elements...");
        storageRoom.remove(elements[new Random().nextInt(elements.length)]);
        System.out.println("After removing:");
        printGenericArray(storageRoom);
    }

    private <T> void runAuto(StorageRoom<T> storageRoom, T[] objectsToAdd) {
        printGenericArray(storageRoom);
        System.out.println("Adding new elements to it...");
        storageRoom.addAll(Arrays.asList(objectsToAdd));
        System.out.println("After adding elements: ");
        printGenericArray(storageRoom);
        System.out.println("Deleting elements...");
        storageRoom.remove(objectsToAdd[new Random().nextInt(objectsToAdd.length)]);
        printGenericArray(storageRoom);
    }

    private <T> void printGenericArray(StorageRoom<T> container) {
        System.out.println("Elements: " + container);
        System.out.println("Length: " + container.size());
    }

    private static int getScannedInteger(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("You typed wrong value, try again:");
            return getScannedInteger(scanner);
        }
    }
}

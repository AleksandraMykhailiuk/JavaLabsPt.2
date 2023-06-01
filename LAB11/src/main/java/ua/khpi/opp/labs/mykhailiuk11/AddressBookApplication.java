package ua.khpi.opp.labs.mykhailiuk11;

import ua.khpi.opp.labs.mykhailiuk11.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk11.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk11.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk11.models.containers.AddressBook;
import ua.khpi.opp.labs.mykhailiuk11.models.containers.StorageRoom;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Objects;
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
        Scanner scanner;
        if (autoMode) {
            scanner = new Scanner(
                    Objects.requireNonNull(AddressBookApplication.class.
                            getClassLoader().getResourceAsStream("Input.txt")
                    )
            );
        } else {
            scanner = new Scanner(System.in);
        }
        System.out.println("Type here amount of notes");
        int times = getScannedInteger(scanner);
        Note[] seedingNotes = new Note[times];
        for (int i = 0; i < seedingNotes.length; i++) {
            System.out.println("Please, type here address number: " + (i + 1));
            String address = scanner.nextLine();
            System.out.println("Please, type here phone number: " + (i + 1));
            String phoneNumber = scanner.nextLine();
            try {
                seedingNotes[i] = new Note(
                        Phone.createPhone(phoneNumber),
                        Address.createAddress(address)
                );
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        run(addressBook, seedingNotes);
    }

    private <T> void run(StorageRoom<T> storageRoom, T[] elements) {
        System.out.println("Seeding passed elements to your user storage...");
        storageRoom.addAll(Arrays.asList(elements));
        printGenericArray(storageRoom);
        System.out.println("Removing some elements...");
        storageRoom.remove(elements[new Random().nextInt(elements.length)]);
        System.out.println("After removing:");
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

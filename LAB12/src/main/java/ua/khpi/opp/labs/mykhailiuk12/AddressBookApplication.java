package ua.khpi.opp.labs.mykhailiuk12;

import ua.khpi.opp.labs.mykhailiuk12.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk12.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk12.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk12.models.containers.AddressBook;
import ua.khpi.opp.labs.mykhailiuk12.models.containers.StorageRoom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that represents program
 */
public class AddressBookApplication {
    private static AddressBookApplication instance = null;

    private AddressBookApplication() {

    }

    public static AddressBookApplication getInstance() {
        if (instance == null) {
            instance = new AddressBookApplication();
        }
        return instance;
    }

    /**
     * Starts the program
     */
    public void start() {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
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

        AddressBook addressBook = (AddressBook) storageRoom;
        System.out.println("Finding vodafone elements from Kharkiv:");
        System.out.println(new AddressBookRegexFinder(addressBook).findVodafoneInKharkiv());
        System.out.println("Finding kyivstar elements from Kharkiv:");
        System.out.println(new AddressBookRegexFinder(addressBook).findKyivstarInKharkiv());
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

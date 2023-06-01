package ua.khpi.opp.labs.mykhailiuk13;

import ua.khpi.opp.labs.mykhailiuk13.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk13.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk13.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk13.models.containers.AddressBook;
import ua.khpi.opp.labs.mykhailiuk13.models.containers.StorageRoom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        run(addressBook, seedingNotes, scanner);
    }

    private <T> void run(StorageRoom<T> storageRoom, T[] elements, Scanner scanner) {
        System.out.println("Seeding passed elements to your user storage...");
        storageRoom.addAll(Arrays.asList(elements));
        printGenericArray(storageRoom);

        AddressBook addressBook = (AddressBook) storageRoom;
        System.out.println("Start multhithreading part");
        System.out.println("Please, type here number of threads");
        Integer numberOfThreads = getScannedInteger(scanner);
        System.out.println("Please type here timeout for threads in seconds");
        Integer timeout = getScannedInteger(scanner);
        startMultiThreading(numberOfThreads, timeout, addressBook);
    }

    private static void startMultiThreading(Integer numberOfThreads, Integer timeOutValue, AddressBook book) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Runnable runnable = () -> {
            try {
                manipulate(book);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(runnable);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeOutValue, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static synchronized void manipulate(AddressBook addressBook) throws InterruptedException {
        Long start = System.currentTimeMillis();
        System.out.println(
                Thread.currentThread().getId() + " => " + Thread.currentThread().getName() + " started"
        );
        System.out.println("Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: " +
                Thread.currentThread().getId());
        System.out.println(new AddressBookRegexFinder(addressBook).findVodafoneInKharkiv() + " => "
                + Thread.currentThread().getId());
        System.out.println(new AddressBookRegexFinder(addressBook).findKyivstarInKharkiv() + " => "
                + Thread.currentThread().getId());
        Thread.sleep(10);

        Long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println(
                Thread.currentThread().getId() + " => " + Thread.currentThread().getName()
                        + " ended: "
                        + " time: "
                        + timeElapsed
        );

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

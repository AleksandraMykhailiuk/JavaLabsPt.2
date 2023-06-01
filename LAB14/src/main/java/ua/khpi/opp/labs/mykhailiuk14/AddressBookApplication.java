package ua.khpi.opp.labs.mykhailiuk14;

import tech.tablesaw.api.Table;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.FloatColumn;
import ua.khpi.opp.labs.mykhailiuk14.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk14.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk14.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk14.models.containers.AddressBook;
import ua.khpi.opp.labs.mykhailiuk14.models.containers.StorageRoom;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Float> timeResult = new AtomicReference<>(0.0F);
        AtomicReference<Float> timeResultSynchronized = new AtomicReference<>(0.0F);
        Table table = Table.create("Results",
                List.of(StringColumn.create("Type"), FloatColumn.create("Time"))
        );
        table.stringColumn("Type").append("Not synchronized");
        table.stringColumn("Type").append("Synchronized");
        table.stringColumn("Type").append("Result");
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Runnable runnable = () -> timeResult.updateAndGet(v -> {
            try {
                return v + manipulate(book);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Runnable runnableSynchronized = () -> timeResultSynchronized.updateAndGet(v -> {
            try {
                return v + manipulateSynchronized(book);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executeForAllThreads(executorService, runnable, numberOfThreads);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeOutValue, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        executorService = Executors.newFixedThreadPool(numberOfThreads);
        executeForAllThreads(executorService, runnableSynchronized, numberOfThreads);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeOutValue, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        table.floatColumn("Time").append(timeResult.get());
        table.floatColumn("Time").append(timeResultSynchronized.get());
        table.floatColumn("Time").append(timeResultSynchronized.get() - timeResult.get());

        System.out.println(table.print());
    }

    private static void executeForAllThreads(ExecutorService executorService, Runnable runnable, int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(runnable);
        }
    }

    private static long manipulate(AddressBook addressBook) throws InterruptedException {
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

        return timeElapsed;
    }

    private static synchronized long manipulateSynchronized(AddressBook addressBook) throws InterruptedException {
        return manipulate(addressBook);
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

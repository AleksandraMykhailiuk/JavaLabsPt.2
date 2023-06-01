package ua.khpi.opp.labs.mykhailiuk16;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import tech.tablesaw.api.Table;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.FloatColumn;
import ua.khpi.opp.labs.mykhailiuk16.exceptions.DataNotValidException;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Address;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Note;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Phone;
import ua.khpi.opp.labs.mykhailiuk16.models.containers.AddressBook;
import ua.khpi.opp.labs.mykhailiuk16.models.containers.StorageRoom;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
            Address address = createValidAddress(scanner, i);
            Phone phone = createValidPhone(scanner, i);
            seedingNotes[i] = new Note(
                    phone,
                    address
            );
        }
        run(addressBook, seedingNotes, scanner);
    }

    private Phone createValidPhone(Scanner scanner, int iteration) {
        Phone result = null;
        try {
            System.out.println("Please, type here phone number: " + (iteration + 1));
            String phoneNumber = scanner.nextLine();
            result = Phone.createPhone(phoneNumber);
        } catch (DataNotValidException e) {
            System.out.println(e.getMessage());
            createValidPhone(scanner, iteration);
        }
        return result;
    }

    private Address createValidAddress(Scanner scanner, int iteration) {
        Address address = null;
        try {
            System.out.println("Please, type here address number: " + (iteration + 1));
            String value = scanner.nextLine();
            address = Address.createAddress(value);
        } catch (DataNotValidException e) {
            System.out.println(e.getMessage());
            address = createValidAddress(scanner, iteration);
        }
        return address;
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
        try {
            startMultiThreading(numberOfThreads, timeout, addressBook);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Start serialization part part");
        serializeWithProtocolAndWithout(addressBook);
    }

    private static void serializeWithProtocolAndWithout(AddressBook userStorage) {
        serializeWithProtocol(userStorage);
        System.out.println(deserializeWithProtocol());
        serializeWithoutProtocol(userStorage);
        System.out.println(deserializeObjectWithoutProtocol());
    }

    private static void serializeWithoutProtocol(AddressBook addressBook) {
        System.out.println("Storing object without protocol started...");
        Gson gson = new Gson();
        String json = gson.toJson(addressBook);
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                "objectJSON.json"
        )) {
            fileOutputStream.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Storing object without protocol finished...");
    }

    private static Object deserializeObjectWithoutProtocol() {
        System.out.println("Getting object without protocol started...");
        String json;
        try (FileInputStream fileInputStream = new FileInputStream(
                "objectJSON.json"
        )) {
            json = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        System.out.println("Getting object without protocol finished...");
        return gson.fromJson(json, AddressBook.class);
    }

    private static void serializeWithProtocol(AddressBook addressBook) {
        System.out.println("Storing address book in file...");
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                "object.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(addressBook);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Storing completed successfully...");
    }

    private static Object deserializeWithProtocol() {
        System.out.println("Deserializing the object...");
        Object container;
        try (FileInputStream fileInputStream = new FileInputStream(
                "object.ser"
                );
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            container = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deserializing object completed...");
        return container;
    }


    private static void startMultiThreading(Integer numberOfThreads, Integer timeOutValue, AddressBook book) throws InterruptedException {
        Table table = Table.create("Results",
                List.of(StringColumn.create("Type"), FloatColumn.create("Time"))
        );
        table.stringColumn("Type").append("With multithreading");
        table.stringColumn("Type").append("Without multithreading");
        table.stringColumn("Type").append("Difference");
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Runnable runnable = () -> {
            try {
                manipulate(book ,timeOutValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("Executing with threads:");
        long timeWithThreads = System.currentTimeMillis();
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
        long finishWithThreads = System.currentTimeMillis();
        timeWithThreads = finishWithThreads - timeWithThreads;

        System.out.println("Executing without threads:");
        long timeWithoutThreads = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            manipulate(book, timeOutValue);
        }
        long finishWithoutThreads = System.currentTimeMillis();
        timeWithoutThreads = finishWithoutThreads - timeWithoutThreads;

        table.floatColumn("Time").append(timeWithThreads);
        table.floatColumn("Time").append(timeWithoutThreads);
        table.floatColumn("Time").append(timeWithoutThreads - timeWithThreads);

        System.out.println(table.print());
    }

    private static void executeForAllThreads(ExecutorService executorService, Runnable runnable, int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(runnable);
        }
    }

    private static void manipulate(AddressBook addressBook, int timeoutValue) throws InterruptedException {
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
        Thread.sleep(timeoutValue);

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

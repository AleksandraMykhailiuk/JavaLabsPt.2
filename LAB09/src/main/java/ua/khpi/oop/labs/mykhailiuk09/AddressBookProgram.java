package ua.khpi.oop.labs.mykhailiuk09;

import jakarta.xml.bind.*;
import ua.khpi.oop.labs.mykhailiuk09.models.containers.impl.AddressBook;
import ua.khpi.oop.labs.mykhailiuk09.models.classes.Address;
import ua.khpi.oop.labs.mykhailiuk09.models.classes.PhoneNumber;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class AddressBookProgram {
    private static final String PATH_TO_FILE = "./example,.ser";
    private static final String PATH_TO_XML_FILE = "./example.xml";
    private static AddressBookProgram instance = null;

    private AddressBookProgram() {}

    public static AddressBookProgram getInstance() {
        if (instance == null) {
            instance = new AddressBookProgram();
        }

        return instance;
    }

    public void start() {
        AddressBook addressBook = new AddressBook();
        addressBook.addAddress(new Address("Kharkiv"));
        addressBook.addPhoneNumber(new PhoneNumber("+380689619347"));
        addressBook.removeAddress(0);
        addressBook.clearPhoneNumbers();
        addressBook.addPhoneNumber(new PhoneNumber("+380991793046"));
        PhoneNumber phoneNumber = addressBook.getPhoneNumber(0);
        System.out.println(addressBook);
        System.out.println(phoneNumber);
        serializeAddressBook(addressBook);
        System.out.println(deserializeAddressBook());
        storeAddressBook(addressBook);
        System.out.println(getFromStorageAddressBook());
    }

    private static void serializeAddressBook(AddressBook addressBook) {
        System.out.println("Storing address book in file...");
        try (FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(addressBook);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Storing completed successfully...");
    }

    private static Object deserializeAddressBook() {
        System.out.println("Deserializing the object...");
        Object container;
        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            container = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deserializing object completed...");
        return container;
    }

    private static AddressBook getFromStorageAddressBook() {
        File xmlFile = new File(PATH_TO_XML_FILE);
        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(AddressBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<AddressBook> jaxbElement = jaxbUnmarshaller
                    .unmarshal(new StreamSource(xmlFile), AddressBook.class);
            return jaxbElement.getValue();
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void storeAddressBook(AddressBook addressBook) {
        try {
            JAXBElement<AddressBook> jaxbElement = new JAXBElement<>(new QName("", "AddressBook"), AddressBook.class, addressBook);
            JAXBContext jaxbContext = JAXBContext.newInstance(AddressBook.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File file = new File(PATH_TO_XML_FILE);

            //Writes XML file to file-system
            jaxbMarshaller.marshal(jaxbElement, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

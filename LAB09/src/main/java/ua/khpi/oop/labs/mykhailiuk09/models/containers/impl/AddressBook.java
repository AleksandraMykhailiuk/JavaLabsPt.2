package ua.khpi.oop.labs.mykhailiuk09.models.containers.impl;

import ua.khpi.oop.labs.mykhailiuk09.models.classes.Address;
import ua.khpi.oop.labs.mykhailiuk09.models.classes.PhoneNumber;
import ua.khpi.oop.labs.mykhailiuk09.models.containers.UserBook;

public class AddressBook extends UserBook<PhoneNumber, Address> {

    public PhoneNumber getPhoneNumber(int index) {
        return getElement1CustomLinkedList().get(index);
    }

    public boolean addPhoneNumber(PhoneNumber phoneNumber) {
        return getElement1CustomLinkedList().add(phoneNumber);
    }

    public Address getAddress(int index) {
        return getElement2CustomLinkedList().get(index);
    }

    public boolean addAddress(Address address) {
        return getElement2CustomLinkedList().add(address);
    }

    public void removePhoneNumber(int index) {
        getElement1CustomLinkedList().remove(index);
    }

    public void removeAddress(int index) {
        getElement2CustomLinkedList().remove(index);
    }

    public void clearPhoneNumbers() {
        getElement1CustomLinkedList().clear();
    }

    public void clearAddresses() {
        getElement2CustomLinkedList().clear();
    }

    @Override
    public String toString() {
        return "{"
                + getElement1CustomLinkedList().toString()
                + ", " + getElement2CustomLinkedList().toString()
                + "}";
    }
}

package ua.khpi.oop.labs.mykhailiuk09.models.containers;

import java.io.Serializable;

public abstract class UserBook<Element1, Element2> implements Serializable {
    private final CustomLinkedList<Element1> element1CustomLinkedList = new CustomLinkedList<>();
    private final CustomLinkedList<Element2> element2CustomLinkedList = new CustomLinkedList<>();

    protected CustomLinkedList<Element1> getElement1CustomLinkedList() {
        return element1CustomLinkedList;
    }

    protected CustomLinkedList<Element2> getElement2CustomLinkedList() {
        return element2CustomLinkedList;
    }
}

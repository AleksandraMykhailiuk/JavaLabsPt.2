package ua.khpi.opp.labs.mykhailiuk13.models.containers;

import ua.khpi.opp.labs.mykhailiuk13.models.classes.User;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * UserStorage class that stands for abstract container
 */
public abstract class StorageRoom<T> extends AbstractSequentialList<T>
        implements Serializable {

    /**
     * Node class for linked list elements
     */
    public static class Node<T> implements Serializable {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node<T> first;
    private Node<T> last;
    private int size = 0;
    private User user;

    public StorageRoom(User user) {
        this.user = user;
    }

    public StorageRoom() {}

    private void linkLast(T element) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    private void linkBefore(T e, Node<T> succ) {
        final Node<T> pred = succ.prev;
        final Node<T> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Method for returning node element from linked list by index
     */
    public Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method for getting the element from linked list
     */
    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * Method for returning list iterator by some index
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    /**
     * Class that represents LinkedList iterator for linked list
     */
    private class ListItr implements ListIterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        /**
         * Method for checking if there is next element in ListIterator
         */
        public boolean hasNext() {
            return nextIndex < size;
        }

        /**
         * Returning next element from the iterator
         */
        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        /**
         * Method that checks if there is previous element in the linked list
         */
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        /**
         * Method that returns previous element from linked list
         */
        public T previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        /**
         * Method that returns index from next element
         */
        public int nextIndex() {
            return nextIndex;
        }

        /**
         * Method that returns previous index from the element
         */
        public int previousIndex() {
            return nextIndex - 1;
        }

        /**
         * Method from removing element from the array
         */
        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<T> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(T e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        /**
         * Add element to the linked list
         */
        public void add(T e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        /**
         * For Each remaining method for linked list
         */
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.item);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * Method for getting the size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method for adding element to container by some id
     */
    public void add(int index, T element) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    /**
     * Method for unlinking element from the linked list
     */
    private T unlink(Node<T> x) {
        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Method for removing object from array
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Removing by index
     */
    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * Clearing the linked list
     */
    public void clear() {
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    /**
     * To array convertion
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    /**
     * Check if the element is contains
     */
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("{");
       for (int i = 0; i < size; i++) {
           stringBuilder.append(get(i).toString());
       }
       stringBuilder.append("}");
       return stringBuilder.toString();
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * For each method
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept(get(i));
        }
    }
}

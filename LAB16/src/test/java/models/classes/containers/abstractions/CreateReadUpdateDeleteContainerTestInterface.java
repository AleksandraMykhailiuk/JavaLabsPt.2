package models.classes.containers.abstractions;

/**
 * Interface for defining test methods for testing the container
 */
public interface CreateReadUpdateDeleteContainerTestInterface {
    /**
     * method for checking creation of the container
     * success expected
     */
    void createContainer_returnAddressBookContainer();

    /**
     * method for checking adding element to the container
     * success expected
     */
    void addElementToContainer_success();

    /**
     * method for checking removing element from the container
     * success expected
     */
    void removeElementFromContainer_success();

    /**
     * method for checking removing element from the container
     * fail expected
     */
    void removeElementFromContainer_fail();

    /**
     * method for checking getting element from the container
     * success expected
     */
    void getElementFromContainer_success();

    /**
     * method for checking getting element from the container
     * fail expected
     */
    void getElementFromContainer_fail();
}

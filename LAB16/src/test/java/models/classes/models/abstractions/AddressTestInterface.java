package models.classes.models.abstractions;

/**
 * Interface for defining methods for testing the address class
 */
public interface AddressTestInterface extends ModelTestingInterface {
    /**
     * Method for testing one value creation of address object
     * success expected
     */
    void createAddressWithOneValue_successExpected();

    /**
     * Method for testing one value creation of address object
     * fail expected
     */
    void createAddressWithOneValue_failExpected();
}

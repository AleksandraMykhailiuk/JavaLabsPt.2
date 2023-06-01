package models.classes.models.abstractions;

/**
 * Interface for defining methods
 * for testing cases for models classes
 */
public interface ModelTestingInterface {
    /**
     * Method for testing creation object
     * success result expected here
     */
    void successCreation_returnObject();

    /**
     * Method for testing creation object
     * exception expected
     */
    void failCreation_exceptionThrowing();
}


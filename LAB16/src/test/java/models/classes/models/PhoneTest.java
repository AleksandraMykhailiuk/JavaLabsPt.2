package models.classes.models;

import models.classes.models.abstractions.ModelTestingInterface;
import org.junit.jupiter.api.Test;
import ua.khpi.opp.labs.mykhailiuk16.exceptions.DataNotValidException;
import ua.khpi.opp.labs.mykhailiuk16.models.classes.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class for testing Phone Number
 */
public class PhoneTest implements ModelTestingInterface {
    @Override
    @Test
    public void successCreation_returnObject() {
        String successPhone = "+380999425174";
        Phone phone = Phone.createPhone(successPhone);

        assertEquals(successPhone, phone.getValue());

    }

    @Override
    @Test
    public void failCreation_exceptionThrowing() {
        String incorrectPhone = "379573hgdkfgd35";

        assertThrows(DataNotValidException.class,
                () -> Phone.createPhone(incorrectPhone));

    }
}

package ua.khpi.opp.labs.mykhailiuk16.models.classes;

import java.io.Serializable;
import java.util.Date;

/**
 * User representation as DTO object
 */
public class User implements Serializable {
    private String name;
    private String secondName;
    private String fatherName;
    private Date birthDay;

    public User(String name, String secondName, String fatherName, Date birthDay) {
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDay = birthDay;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "[" + name + " " + secondName + " " + fatherName + ", " + birthDay + "]";
    }
}

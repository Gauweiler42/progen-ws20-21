package edu.kit.informatik;

/**
 * Eine Klasse, von der alle Nutzer erben.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class User {
    private final String firstName;
    private final String lastName;
    private String password;


    public User(String firstName, String lastName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

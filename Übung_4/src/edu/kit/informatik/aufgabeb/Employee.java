package edu.kit.informatik;
/**
 * Eine Klasse um einen Postmitarbeiter zu simulieren.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class Employee extends User {
    private final int id;

    public Employee(String firstName, String lastName, String password, int id) {
        super(firstName, lastName, password);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

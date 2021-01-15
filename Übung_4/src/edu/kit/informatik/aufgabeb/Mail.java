package edu.kit.informatik;

/**
 * Eine Klasse, um Sendungen zu simulieren.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class Mail {
    /**
     * Fetched zeigt an, ob die Post bereits abgeholt wurde
     */
    public Boolean fetched = false;
    private final Customer addressee;

    public Mail(Customer addressee) {
        this.addressee = addressee;
    }
}

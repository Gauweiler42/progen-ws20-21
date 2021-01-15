package aufgabeb;

/**
 * Eine Klasse zur Arbeit mit einem Konto
 *
 * @version 1
 * @author Tobias Mintel
 * @author Leon Gauweiler
 * @author Frederik Pytlik
 */
public class Account {
    private final int accountNumber;
    private final int bankCode;
    private int balance;

    /**
     * Konstruktor für ein Bankkonto
     * @param bankCode Eine Bankleitzahl als Integer
     * @param accountNumber Eine Kontonummer als Integer
     */
    public Account(int bankCode, int accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = 0;

    }

    /**
     * Eine Getter Methode für das Attribut accountNumber
     * @return Gibt die Kontonummer als Integer zurück
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Eine Getter Methode für das Attribut bankCode
     * @return Gibt die Bankleitzahl als Integer zurück
     */
    private int getBankCode() {
        return bankCode;
    }

    /**
     * Eine Getter Methode für das Attribut balance
     * @return Gibt den Kontostand als Integer zurück
     */
    private int getBalance() {
        return balance;
    }

    /**
     * Eine Setter Methode für das Attribut balance
     * @param balance Eine Menge Geld als Integer, die als Wert für das Attribut balance gesetzt wird
     */
    private void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Eine Methode um zu prüfen, ob ein Konto genug Guthaben hat
     * @param amount Ein Betrag an Geld zum Vergleich mit dem Kontostand als Integer
     * @return Gibt true zurück, wenn der Kontostand ausreicht und false, falls der Kontostand nicht ausreicht
     */
    private boolean withdraw(int amount) {
        return this.balance >= amount;

    }

    /**
     * Eine Methode, um eine Gutschrift auf das Konto vorzunehmen. Verändert den Wert des Attributes balance
     * @param amount Eine Menge Geld als Integer, die dem Konto hinzugefügt werden soll
     */
    private void deposit(int amount) {
        this.balance += amount;
    }

    /**
     * Eine Methode, um eine negative Gutschrift auf dem Konto vorzunehmen. Verändert den Wert des Attributes balance
     * Prüft nicht, ob das Konto ausreichend Guthaben hat.
     * @param amount Eine Menge Geld als Integer, die dem Konto abgezogen werden soll
     */
    private void substract(int amount) {
        int balance = getBalance();
        setBalance(balance - amount);
    }

    /**
     * Eine Methode, um eine Überweisung auf ein anderes Konto auszuführen
     * @param account Der Account als Objekt der Klasse Account, auf das Geld überwiesen werden soll
     * @param amount Die Menge Geld, die auf das Konto überwiesen werden soll
     * @return Gibt true zurück, falls das Konto genug Guthaben hatte und die Überweisung vorgenommen wurde.
     *         Gibt false zurück, falls das Konto nicht genug Guthaben für diese Überweisung hat.
     */
    public boolean transfer(Account account , int amount) {
        if (this.withdraw(amount)) {
            this.substract(amount);
            account.deposit(amount);
            return true;
        }
        return false;
    }
}

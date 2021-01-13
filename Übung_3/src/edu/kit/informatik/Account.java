package edu.kit.informatik;

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
     * Konstruktor f�r ein Bankkonto
     * @param bankCode Eine Bankleitzahl als Integer
     * @param accountNumber Eine Kontonummer als Integer
     */
    public Account(int bankCode, int accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = 0;

    }

    /**
     * Eine Getter Methode f�r das Attribut accountNumber
     * @return Gibt die Kontonummer als Integer zur�ck
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Eine Getter Methode f�r das Attribut bankCode
     * @return Gibt die Bankleitzahl als Integer zur�ck
     */
    private int getBankCode() {
        return bankCode;
    }

    /**
     * Eine Getter Methode f�r das Attribut balance
     * @return Gibt den Kontostand als Integer zur�ck
     */
    private int getBalance() {
        return balance;
    }

    /**
     * Eine Setter Methode f�r das Attribut balance
     * @param balance Eine Menge Geld als Integer, die als Wert f�r das Attribut balance gesetzt wird
     */
    private void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Eine Methode um zu pr�fen, ob ein Konto genug Guthaben hat
     * @param amount Ein Betrag an Geld zum Vergleich mit dem Kontostand als Integer
     * @return Gibt true zur�ck, wenn der Kontostand ausreicht und false, falls der Kontostand nicht ausreicht
     */
    public boolean withdraw(int amount) {
        return this.balance >= amount;

    }

    /**
     * Eine Methode, um eine Gutschrift auf das Konto vorzunehmen. Ver�ndert den Wert des Attributes balance
     * @param amount Eine Menge Geld als Integer, die dem Konto hinzugef�gt werden soll
     */
    public void deposit(int amount) {
        this.balance += amount;
    }

    /**
     * Eine Methode, um eine negative Gutschrift auf dem Konto vorzunehmen. Ver�ndert den Wert des Attributes balance
     * Pr�ft nicht, ob das Konto ausreichend Guthaben hat.
     * @param amount Eine Menge Geld als Integer, die dem Konto abgezogen werden soll
     */
    private void substract(int amount) {
        int balance = getBalance();
        setBalance(balance - amount);
    }

    /**
     * Eine Methode, um eine �berweisung auf ein anderes Konto auszuf�hren
     * @param account Der Account als Objekt der Klasse Account, auf das Geld �berwiesen werden soll
     * @param amount Die Menge Geld, die auf das Konto �berwiesen werden soll
     * @return Gibt true zur�ck, falls das Konto genug Guthaben hatte und die �berweisung vorgenommen wurde.
     *         Gibt false zur�ck, falls das Konto nicht genug Guthaben f�r diese �berweisung hat.
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

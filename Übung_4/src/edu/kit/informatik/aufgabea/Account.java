package edu.kit.informatik.aufgabea;

/**
 * Eine Klasse zur Arbeit mit einem Konto
 * Große Teile habe ich aus meiner Abgabe für die Aufgabe 3B.1 entnommen
 *
 * @version 2
 * @author Tobias Mintel
 * @author Leon Gauweiler
 * @author Frederik Pytlik
 */
public class Account {
    /**
     * Der nächste Account in der Schlange als Objekt der Klasse Account
     */
    public Account nextAccount = null;

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

    public int getBankCode() {
        return bankCode;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public int compareTo(Account account) {
        if (account.getAccountNumber() == this.accountNumber) {
            return 0;
        } else if (account.getAccountNumber() > this.accountNumber) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Eine Methode um zu prüfen, ob ein Konto genug Guthaben hat
     * @param amount Ein Betrag an Geld Der vom Konto abgebucht werden muss
     * @return Gibt true zurück, wenn der das Geld vom Konto abgezogen wurde
     *         Gibt false zurück, wenn das Geld nicht abgezogen werden konnte
     */
    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Eine Methode, um eine Gutschrift auf das Konto vorzunehmen. Verändert den Wert des Attributes balance
     * @param amount Eine Menge Geld als Integer, die dem Konto hinzugefügt werden soll
     */
    public void deposit(int amount) {
        this.balance += amount;
    }

    /**
     * Eine Methode, um eine Überweisung auf ein anderes Konto auszuführen
     *
     * @param account Der Account als Objekt der Klasse Account, auf das Geld überwiesen werden soll
     * @param amount Die Menge Geld, die auf das Konto überwiesen werden soll
     * @return Gibt true zurück, falls das Konto genug Guthaben hatte und die Überweisung vorgenommen wurde.
     *         Gibt false zurück, falls das Konto nicht genug Guthaben für diese Überweisung hat.
     */
    public boolean transfer(Account account , int amount) {
        if (this.withdraw(amount)) {
            account.deposit(amount);
            return true;
        }
        return false;
    }
}

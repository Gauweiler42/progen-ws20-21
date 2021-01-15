package edu.kit.informatik.aufgabea;

/**
 * Eine Klasse, um eine Bank zu simulieren.
 * Teile habe ich aus meiner Abgabe für die Aufgabe 3B.1 entnommen
 * @version 2
 * @author Leon Gauweiler
 */
public class Bank {
    /**
     * Die Bankleitzahl als Attribut
     */
    public final int bankCode;
    private AccountList accounts;

    /**
     * Konstruktor für eine Bank
     * @param bankCode Die Bankleitzahl der erstellten Bank
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
        this.accounts = new AccountList();
    }

    public int getBankCode() {
        return bankCode;
    }

    /**
     * Eine Methode, um ein neues Konto zu erstellen
     *
     * @return Gibt die Kontonummer zurück
     */
    public int createAccount() {
        int accountNumber;
        if (accounts.getFirst() == null) {
            accountNumber = 0;
        } else {
            accountNumber = accounts.getLast().getAccountNumber() + 1;
        }
        accounts.add(new Account(bankCode, accountNumber));
        return accountNumber;
    }

    /**
     * Entfernt das Konto mit der gegebenen Kontonummer
     *
     * @param accountNumber Eine Kontonummer als Integer
     * @return Gibt true zurück, wenn das Konto erfolreich entfernt wurde
     *         Gibt false zurück, wenn das Konto nicht entfernt werden konnte
     */
    public boolean removeAccount(int accountNumber) {
        int index = accounts.getIndex(accountNumber);
        return accounts.remove(index);
    }

    /**
     * Überprüft, ob ein Konto in der accountliste existiert
     *
     * @param accountNumber Die Kontonummer, nach der gesucht werden soll
     * @return Gibt true zurück, wenn das Konto existiert
     *         Gibt false zurück, wenn das Konto nicht existiert
     */
    public boolean containsAccount(int accountNumber) {
        return accounts.contains(new Account(bankCode, accountNumber));
    }

    /**
     * Führt eine Überweisung von einem Konto der Bank auf ein anderes Konto der Bank aus
     *
     * @param fromAccountNumber Die Kontonummer, von der überwiesen werden soll
     * @param toAccountNumber Die Kontonummer, auf die überwiesen werden soll
     * @param amount Der Betrag, der überwiesen werden soll
     * @return Gibt zurück, ob die Überweisung ausgeführt werden konnte
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        int accountIndexSender = accounts.getIndex(fromAccountNumber);
        int accountIndexReceiver = accounts.getIndex(toAccountNumber);

        if (accountIndexSender >= 0 && accountIndexReceiver >= 0) {
            Account senderAccount = getAccount(accountIndexSender);
            Account receiverAccount = getAccount(accountIndexReceiver);

            if (senderAccount != null && receiverAccount != null) {
                return senderAccount.transfer(receiverAccount, amount);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Gibt die Anzahl der der Konten in der Liste zurück
     *
     * @return Anzahl der Konten in der Liste als Integer
     */
    public int size() {
        return accounts.size();
    }

    /**
     * Gibt die Anzahl der der Konten in der Liste zurück
     *
     * @return Anzahl der Konten in der Liste als Integer
     */
    public int length() {
        return this.size();
    }

    /**
     * Gibt das Konto zu einem Index zurück
     *
     * @param index Der Index des Kontos
     * @return Gibt einen Account zurück, oder null, wenn kein Account gefunden wurde
     */
    public Account getAccount(int index) {
        return accounts.get(index);
    }
}

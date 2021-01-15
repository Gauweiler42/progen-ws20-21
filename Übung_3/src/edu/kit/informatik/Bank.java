package edu.kit.informatik;

/**
 * Eine Klasse, um eine Bank zu simulieren.
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Bank {
    /**
     * Eine Kostante, um den F�llstand von accounts zu pr�fen
     */
    static final int MAX_LEVEL_DIVIDENT = 4;

    /**
     * Eine Konstante um zu pr�fen, wie gro� accounts sein muss, dass es halbiert werden kann
     */
    static final int MIN_LEVEL_ARRAY = 16;

    /**
     * Die Bankleitzahl als Attribut
     */
    public final int bankCode;

    /**
     * Die Accounts der Nutzer in einem Array
     */
    private Account[] accounts = new Account[8];

    /**
     * Konstruktor f�r eine Bank
     * @param bankCode Die Bankleitzahl der erstellten Bank
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Eine Getter Methode f�r das Attribut accounts
     * @return Gibt ein array mit Objekten der Klasse Account zur�ck
     */
    public Account[] getAccounts() {
        return accounts;
    }
    
    public int getBankCode() {
        return bankCode;
    }

    /**
     * Eine Setter Methode f�r das Attribut accounts
     * @param accounts Ein Array mit Objekten der Klasse Account
     */
    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    /**
     * Erstellt einen neuen Account und f�gt ihn dem Array accounts hinzu
     * @return Gibt die neue Kontonummer zur�ck
     */
    public int createAccount() {
        Account[] currentAccounts = getAccounts();

        // 1. Die n�chste Account Nummer finden
        for (int nextAccountNumber = 0; nextAccountNumber < length(); nextAccountNumber++) {
            if (currentAccounts[nextAccountNumber] == null) {
                // 2. Neuen Account erstellen
                currentAccounts[nextAccountNumber] = new Account(bankCode, nextAccountNumber);

                // Neue Accountliste setzen
                setAccounts(currentAccounts);

                return nextAccountNumber;
            }
        }
        // 3. Wenn die Liste vollst�ndig besetzt ist: Accountliste verdoppeln und nochmal checken
        Account newAccount = new Account(bankCode, length());
        doubleAccountLength(newAccount);
        return length();
    }

    /**
     * Gibt die L�nge des Account Arrays zur�ck
     * @return Die L�nge des Account Arrays als Integer
     */
    public int length() {
        return getAccounts().length;
    }

    /**
     * Eine Methode, um zu bestimmen, wie viele Accounts derzeit im array sind
     * @return Die Anzahl der accounts als Integer
     */
    public int size() {
        int numberOfAccounts = 0;
        for (int i = 0; i < length(); i++) {
            if (getAccounts()[i] != null) {
                numberOfAccounts++;
            } else {
                break;
            }
        }

        return numberOfAccounts;
    }

    /**
     * Falls alle Pl�tze in der Accountliste belegt sind wird hier die L�nge des Arrays verdoppelt und der neue Account
     * in die accounts hinzugef�gt
     * @param newAccount Ein Objekt der Klasse Account, der in der Methode createAccount erstellt wurde
     */
    private void doubleAccountLength(Account newAccount) {
        int numberAccounts = length();
        Account[] newSpace = new Account[numberAccounts];
        newSpace[0] = newAccount;

        Account[] currentAccounts = getAccounts();

        System.arraycopy(newSpace, 0, currentAccounts, numberAccounts, numberAccounts);
        setAccounts(currentAccounts);
    }

    /**
     * Eine Methode, um ein Konto anhand der Kontonummer l�schen zu k�nnen
     *
     * @param accountNumber Die Kontonummer als Integer des Kontos, dass gel�scht werden muss
     * @return Gibt true zur�ck, falls die Accountnummer gefunden wurde, die gel�scht werden soll
     *         Gibt false zur�ck, falls die Accountnummer nicht gefunden wurde
     */
    public boolean removeAccount(int accountNumber) {
        // 1. Schritt: Accountnummer finden
        int indexAccount = findAccountIndex(accountNumber);

        if (indexAccount >= 0) {
            // 2. Schritt: Account entfernen
            removeAccountFromArray(indexAccount);
            checkAccountsArrayLevel();
            return true;
        } else {
            return false;
        }
    }

    private void checkAccountsArrayLevel() {
        if (length() >= MIN_LEVEL_ARRAY && (size() > (length() / MAX_LEVEL_DIVIDENT))) {
            Account[] newAccountArray = new Account[length() / 2];
            for (int i = 0; i < size(); i++) {
                newAccountArray[i] = getAccounts()[i];
            }

            // Das neue kleinere Array wird als accounts gesetzt
            setAccounts(newAccountArray);
        }
    }

    /**
     * Eine Hilfsmethode, die den Account am entsprechenden Array aus der Liste l�scht
     * @param index Ein Index f�r das array accounts als Integer. Dieses Element wird aus der Liste entfernt
     */
    private void removeAccountFromArray(int index) {
        Account[] newAccountArray = new Account[index];
        Account[] currentAccounts = getAccounts();

        Account[] additionalAccounts = new Account[currentAccounts.length - (index + 1)];

        // 1. Schritt alle Elemente, bis zum index in ein array
        for (int i = 0; i < index; i++) {
            newAccountArray[i] = currentAccounts[i];
        }

        // 2. Schritt: Alle Elemente ab dem index in ein array
        for (int i = index + 1; i < currentAccounts.length; i++) {
            additionalAccounts[i] = currentAccounts[i];
        }

        // 3. Schritt: Die beiden Listen zusammenf�gen und als neue Accountliste setzen
        System.arraycopy(newAccountArray, 0, additionalAccounts, index, additionalAccounts.length);

        // 4. Schritt: Das neue Array setzen
        setAccounts(newAccountArray);
    }

    /**
     * Eine Hilfsmethode, um die Stelle des Kontos zu finden
     *
     * @param accountNumber Die Kontonummer eines Accounts als Integer
     * @return Gibt die Stelle, an der der Account steht als Integer zur�ck
     *         Falls die Accountnummer nicht in der accountsliste ist, wird -1 zur�ckgegeben
     */
    private int findAccountIndex(int accountNumber) {
        if (containsAccount(accountNumber)) {
            Account[] currentAccounts = getAccounts();

            for (int i = 0; i < length(); i++) {
                if (currentAccounts[i].getAccountNumber() == accountNumber) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Pr�ft, ob eine Kontonummer in der Kontoliste vorhanden ist
     *
     * @param accountNumber Eine Kontonummer als Integer
     * @return Gibt true zur�ck, falls die Accountnummer gefunden wurde
     *         Gibt false zur�ck, falls die Accountnummer nicht gefunden wurde
     */
    public boolean containsAccount(int accountNumber) {
        Account[] currentAccounts = getAccounts();
        for (int i = 0; i < length(); i++) {
            if (currentAccounts[i] == null) {
                break;
            } else if (currentAccounts[i].getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Eine Hilfsmethode, um einen Account zu erhalten
     *
     * @param index Ein Indexwert f�r accounts als Integer
     * @return Gibt ein Objekt der Klasse Account zur�ck, dass an der Stelle index gespeichert ist
     */
    public Account getAccount(int index) {
        if (index < length()) {
            return getAccounts()[index];
        } else {
            return null;
        }
    }

    /**
     * Eine Methode, um eine Interne Bank�berweisung vorzunehmen
     *
     * @param fromAccountNumber Die Kontonummer des Senderkontos als Integer
     * @param toAccountNumber Die Kontonummer des Empf�ngerkontos als Integer
     * @param amount Die Betrag des Geldes als Integer, das �berwiesen werden muss
     * @return Gibt true zur�ck, wenn die �berweisung erfolgen konnte
     *         Gibt false zur�ck, wenn die �berweisung nicht erfolgen konnte
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        int indexSender = findAccountIndex(fromAccountNumber);
        int indexReceiver = findAccountIndex(toAccountNumber);

        if (indexReceiver != -1 && indexSender != -1) {
            Account sender = getAccount(indexSender);
            Account receiver = getAccount(indexReceiver);

            return sender.transfer(receiver, amount);
        }
        return false;
    }
}
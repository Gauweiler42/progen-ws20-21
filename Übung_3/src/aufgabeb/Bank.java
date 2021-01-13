package aufgabeb;

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
     * Eine Kostante, um den Füllstand von accounts zu prüfen
     */
    static final int MAX_LEVEL_DIVIDENT = 4;

    /**
     * Eine Konstante um zu prüfen, wie groß accounts sein muss, dass es halbiert werden kann
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
     * Konstruktor für eine Bank
     * @param bankCode Die Bankleitzahl der erstellten Bank
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Eine Getter Methode für das Attribut accounts
     * @return Gibt ein array mit Objekten der Klasse Account zurück
     */
    public Account[] getAccounts() {
        return accounts;
    }

    /**
     * Eine Setter Methode für das Attribut accounts
     * @param accounts Ein Array mit Objekten der Klasse Account
     */
    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    /**
     * Erstellt einen neuen Account und fügt ihn dem Array accounts hinzu
     * @return Gibt die neue Kontonummer zurück
     */
    public int createAccount() {
        Account[] currentAccounts = getAccounts();

        // 1. Die nächste Account Nummer finden
        for (int nextAccountNumber = 0; nextAccountNumber < length(); nextAccountNumber++) {
            if (currentAccounts[nextAccountNumber] == null) {
                // 2. Neuen Account erstellen
                currentAccounts[nextAccountNumber] = new Account(bankCode, nextAccountNumber);

                // Neue Accountliste setzen
                setAccounts(currentAccounts);

                return nextAccountNumber;
            }
        }
        // 3. Wenn die Liste vollständig besetzt ist: Accountliste verdoppeln und nochmal checken
        Account newAccount = new Account(bankCode, length());
        doubleAccountLength(newAccount);
        return length();
    }

    /**
     * Gibt die Länge des Account Arrays zurück
     * @return Die Länge des Account Arrays als Integer
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
     * Falls alle Plätze in der Accountliste belegt sind wird hier die Länge des Arrays verdoppelt und der neue Account
     * in die accounts hinzugefügt
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
     * Eine Methode, um ein Konto anhand der Kontonummer löschen zu können
     *
     * @param accountNumber Die Kontonummer als Integer des Kontos, dass gelöscht werden muss
     * @return Gibt true zurück, falls die Accountnummer gefunden wurde, die gelöscht werden soll
     *         Gibt false zurück, falls die Accountnummer nicht gefunden wurde
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
     * Eine Hilfsmethode, die den Account am entsprechenden Array aus der Liste löscht
     * @param index Ein Index für das array accounts als Integer. Dieses Element wird aus der Liste entfernt
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

        // 3. Schritt: Die beiden Listen zusammenfügen und als neue Accountliste setzen
        System.arraycopy(newAccountArray, 0, additionalAccounts, index, additionalAccounts.length);

        // 4. Schritt: Das neue Array setzen
        setAccounts(newAccountArray);
    }

    /**
     * Eine Hilfsmethode, um die Stelle des Kontos zu finden
     *
     * @param accountNumber Die Kontonummer eines Accounts als Integer
     * @return Gibt die Stelle, an der der Account steht als Integer zurück
     *         Falls die Accountnummer nicht in der accountsliste ist, wird -1 zurückgegeben
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
     * Prüft, ob eine Kontonummer in der Kontoliste vorhanden ist
     *
     * @param accountNumber Eine Kontonummer als Integer
     * @return Gibt true zurück, falls die Accountnummer gefunden wurde
     *         Gibt false zurück, falls die Accountnummer nicht gefunden wurde
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
     * @param index Ein Indexwert für accounts als Integer
     * @return Gibt ein Objekt der Klasse Account zurück, dass an der Stelle index gespeichert ist
     */
    public Account getAccount(int index) {
        if (index < length()) {
            return getAccounts()[index];
        } else {
            return null;
        }
    }

    /**
     * Eine Methode, um eine Interne Banküberweisung vorzunehmen
     *
     * @param fromAccountNumber Die Kontonummer des Senderkontos als Integer
     * @param toAccountNumber Die Kontonummer des Empfängerkontos als Integer
     * @param amount Die Betrag des Geldes als Integer, das überwiesen werden muss
     * @return Gibt true zurück, wenn die Überweisung erfolgen konnte
     *         Gibt false zurück, wenn die Überweisung nicht erfolgen konnte
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

package edu.kit.informatik.aufgabea;

/**
 * Eine Klasse, um mit mehreren Accounts zu arbeiten
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class AccountList {
    private Account firstAccount;

    public AccountList() {

    }

    /**
     * Diese Methode fügt der Liste ein neues List am Ende hinzu. Wenn die Liste leer ist, ist das letzte Element
     * automatisch das erste.
     *
     * @param account Ein Account Objekt der Klasse Account, der der Liste hinzugefügt werden soll
     */
    public void add(Account account) {
        // Die Liste ist leer
        if (this.firstAccount == null) {
            this.firstAccount = account;
            return;
        }

        // Liste ist nicht leer
        Account currentAccount = this.firstAccount;
        while (currentAccount.nextAccount != null) {
            currentAccount = currentAccount.nextAccount;
        }

        currentAccount.nextAccount = account;
    }

    /**
     * Eine Methode, um einen Account an einer beliebigen Stelle der Liste einzufügen. Ist der Index zu hoch, wird das
     * Element am Ende angefügt
     *
     * @param account Der Account, der hinzugefügt werden soll
     * @param index Der Index an der der neue Account eingefügt werden soll. Der Index wird behandelt, wie bei einem
     *              Array (Start: 0)
     */
    public void add(Account account, int index) {
        // Die Liste ist leer
        if (this.firstAccount == null) {
            this.firstAccount = account;
            return;
        }

        if (index == 0) {
            account.nextAccount = firstAccount;
            firstAccount = account;
            return;
        }

        int indexCounter = 0;
        Account currentAccount = this.firstAccount;
        while (indexCounter < index - 1 && currentAccount.nextAccount != null) {
            // Element 1 vor dem Index erhalten
            currentAccount = currentAccount.nextAccount;
            indexCounter++;
        }

        if (currentAccount.nextAccount != null) {
            // Liste nach rechts verschieben
            account.nextAccount = currentAccount.nextAccount;
            currentAccount.nextAccount = account;
        } else {
            // Der Index ist außerhalb der Reichweite. Neuer Account wird ans ende angefügt
            this.add(account);
        }
    }

    /**
     *
     * @param index Der Index an der der Account gelöscht werden soll. Der Index wird behandelt, wie bei einem
     *              Array (Start: 0)
     * @return Gibt true zurück, wenn das Löschen erfolreich war
     *         Gibt false zurück, wenn das Löschen nicht erfolgreich war
     */
    public boolean remove(int index) {
        if (this.firstAccount == null) {
            return false;
        }

        if (index == 0) {
            firstAccount = firstAccount.nextAccount;
            return true;
        }
        
        Account currentAccount = this.firstAccount;
        int counterIndex = 0;
        while (currentAccount.nextAccount != null) {
            if (counterIndex + 1 == index) {
                currentAccount.nextAccount = currentAccount.nextAccount.nextAccount;
                return true;
            }
            currentAccount = currentAccount.nextAccount;
            counterIndex++;
        }
        return false;
    }

    /**
     * Gibt das erste Element der Liste zurück, wenn die Liste leer ist gibt es null zurück
     *
     * @return Gibt das Attribut firstAccount zurück.
     */
    public Account getFirst() {
        return this.firstAccount;
    }

    /**
     * Gibt das letzte Element der Liste zurück. Gibt null zurück, wenn die Liste leer ist
     * @return Gibt das letzte Element der Liste zurück und null wenn die Liste leer ist
     */
    public Account getLast() {
        if (firstAccount == null) {
            return null;
        }

        Account currentAccount = firstAccount;
        while (currentAccount.nextAccount != null) {
            currentAccount = currentAccount.nextAccount;
        }
        return currentAccount;
    }

    /**
     * Gibt den Index des gesuchten Kontos aus. Sucht mit einer Kontonummer
     * @param accountNumber Die Kontonummer des gesuchten Kontos
     * @return Gibt den index der gesuchten Kontonummer aus oder -1, wenn das Konto nicht gefunden werden konnte
     */
    public int getIndex(int accountNumber) {
        if (firstAccount == null) {
            return -1;
        }

        Account currentAccount = firstAccount;
        int indexCounter = 0;
        while (currentAccount != null) {
            if (currentAccount.getAccountNumber() == accountNumber) {
                return indexCounter;
            }
            currentAccount = currentAccount.nextAccount;
            indexCounter++;
        }
        return -1;
    }

    /**
     *
     * @param index Der Index an der der Account steht, der ausgegeben werden soll.
     *              Der Index wird behandelt, wie bei einem Array (Start: 0)
     *
     * @return gibt den Account an der Stelle Index zurück. Gibt null zurück, wenn index ungültig ist
     */
    public Account get(int index) {
        if (firstAccount == null) {
            return null;
        }

        if (index == 0) {
            return getFirst();
        }

        int indexCounter = 0;
        Account currentAccount = this.firstAccount;

        while (currentAccount.nextAccount != null) {
            if (indexCounter + 1 == index) {
                return currentAccount.nextAccount;
            }
            currentAccount = currentAccount.nextAccount;
            indexCounter++;
        }
        return null;
    }

    /**
     * Sucht nach dem gegebenen Account in der Accountliste
     *
     * @param account Ein Objekt der Klasse Account, das als Vergleich dient
     * @return Gibt true zurück, wenn das Objekt in der Liste existiert.
     *         Gibt false zurück, wenn das Objekt nicht in der Liste ist.
     */
    public boolean contains(Account account) {
        if (firstAccount == null) {
            return false;
        }

        Account currentAccount = firstAccount;
        while (currentAccount.nextAccount != null) {
            if (currentAccount.compareTo(account) == 0) {
                return true;
            }
            currentAccount = currentAccount.nextAccount;
        }
        return currentAccount.compareTo(account) == 0;
    }

    /**
     * Gibt die Anzahl der der Konten in der Liste zurück
     *
     * @return Anzahl der Konten in der Liste als Integer
     */
    public int size() {
        if (firstAccount == null) {
            return 0;
        }

        int size = 1;
        Account currentAccount = this.firstAccount;
        while (currentAccount.nextAccount != null) {
            size++;
            currentAccount = currentAccount.nextAccount;
        }

        return size;
    }
}

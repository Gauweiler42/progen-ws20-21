package edu.kit.informatik;

/**
 * Eine Klasse, um das Postgeschehen kontrollieren zu können.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class MailCenter {
    private Customer[] customers = {};
    private Mailman[] mailmen = {};
    private Agent[] agents = {};

    public MailCenter() {

    }

    // ARBEITEN AN KUNDEN AB HIER
    /**
     *
     * @param firstName Der Vorname als String
     * @param lastName Der Nachname als String
     * @param username Der Nutzername als String (muss einmalig sein; min: 4, max: 9)
     * @param password Das Passwort als String (min: 4, max: 9)
     * @param id Die Ausweisnummer als String
     * @return Gibt ein Integer zurück, um den Erfolg zu definieren:
     *         0: Der Kunde wurde erfolgreich erstellt
     *         1: Nutzername enspricht nicht den Parametern
     *         2: Passwort entspricht nicht den Parametern
     *         3: Der Nutzername existiert bereits als Kunde
     *         4: Die Ausweisnummer existiert bereits als Kunde
     */
    public int addCustomer(String firstName, String lastName, String username, String password, String id) {
        if (username.length() < 4 || username.length() > 9) {
            return 1;
        }
        if (password.length() < 4 | password.length() > 9) {
            return 2;
        }
        if (password.equals(id)) {
            return 2;
        }
        if (usernameExistsInCustomers(username)) {
            return 3;
        }
        if (idExistsInCustomers(id)) {
            return 4;
        }

        // customers wird hier um ein Element erweitert
        Customer[] newCustomerArray = new Customer[customers.length + 1];
        for (int index = 0; index < customers.length; index++) {
            newCustomerArray[index] = customers[index];
        }
        newCustomerArray[customers.length] = new Customer(firstName, lastName, password, username, id);

        customers = newCustomerArray;
        return 0;
    }

    /**
     * Überprüft, ob der Nutzername bereits existiert.
     *
     * @param username Ein Nutzername als String
     * @return Gibt true zurück, falls ein Nutzer existiert
     *         Gibt false zurück, falls kein Nutzer existiert
     */
    private boolean usernameExistsInCustomers(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Überprüft, ob die Ausweisnummer bereits existiert.
     *
     * @param id Eine Ausweisnummer als String
     * @return Gibt true zurück, falls ein Nutzer existiert
     *         Gibt false zurück, falls kein Nutzer existiert
     */
    private boolean idExistsInCustomers(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    // ARBEITEN AM POSTBOTEN AB HIER

    /**
     *
     * @param firstName Der Vorname als String
     * @param lastName Der Nachname als String
     * @param id Die Personalnummer als Int (> 0)
     * @param password Das Passwort als String (min: 4, max: 9)
     * @return Gibt ein Integer zurück, um den Erfolg zu definieren:
     *         0: Der Postbote wurde erfolgreich erstellt
     *         1: Personalnummer enspricht nicht den Parametern
     *         2: Passwort entspricht nicht den Parametern
     *         3: Die Ausweisnummer existiert bereits als Postbote
     */
    public int addMailman(String firstName, String lastName, int id, String password) {
        if (id <= 0) {
            return 1;
        }
        if (password.length() < 4 | password.length() > 9) {
            return 2;
        }
        if (idExistsInEmployees(id)) {
            return 3;
        }

        // mailmen wird hier um ein Element erweitert
        Mailman[] newMailmenArray = new Mailman[mailmen.length + 1];
        for (int index = 0; index < mailmen.length; index++) {
            newMailmenArray[index] = mailmen[index];
        }
        newMailmenArray[mailmen.length] = new Mailman(firstName, lastName, password, id);

        mailmen = newMailmenArray;
        return 0;
    }

    /**
     * Überprüft, ob die Personalnummer bereits existiert.
     *
     * @param id Eine Personalnummer als String
     * @return Gibt true zurück, falls ein Nutzer existiert
     *         Gibt false zurück, falls kein Nutzer existiert
     */
    private boolean idExistsInEmployees(int id) {
        for (Mailman mailman : mailmen) {
            if (mailman.getId() == id) {
                return true;
            }
        }

        for (Agent agent : agents) {
            if (agent.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // ARBEITEN AM CALLCENTER AB HIER
    /**
     *
     * @param firstName Der Vorname als String
     * @param lastName Der Nachname als String
     * @param id Die Personalnummer als Int (> 0)
     * @param password Das Passwort als String (min: 4, max: 9)
     * @return Gibt ein Integer zurück, um den Erfolg zu definieren:
     *         0: Der Callcenter Mitarbeiter wurde erfolgreich erstellt
     *         1: Personalnummer enspricht nicht den Parametern
     *         2: Passwort entspricht nicht den Parametern
     *         3: Die Ausweisnummer existiert bereits als Postbote
     */
    public int addAgent(String firstName, String lastName, int id, String password) {
        if (id <= 0) {
            return 1;
        }
        if (password.length() < 4 | password.length() > 9) {
            return 2;
        }
        if (idExistsInEmployees(id)) {
            return 3;
        }

        // mailmen wird hier um ein Element erweitert
        Agent[] newAgentArray = new Agent[agents.length + 1];
        for (int index = 0; index < agents.length; index++) {
            newAgentArray[index] = agents[index];
        }
        newAgentArray[agents.length] = new Agent(firstName, lastName, password, id);

        agents = newAgentArray;
        return 0;
    }

    // Login

    /**
     * Sucht einen Kunden aus der Liste und gibt ihn zurück
     *
     * @param username Der Nutzername des Kunden als String
     * @return Gibt einen Kunden zurück. Existiert der Nutzer nicht gibt es null zurück
     */
    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Sucht einen Postboten aus der Liste und gibt ihn zurück
     *
     * @param id Die Personalnummer des Mitarbeiters als int
     * @return Gibt einen Postboten zurück. Existiert der Nutzer nicht gibt es null zurück
     */
    public Mailman getMailman(int id) {
        for (Mailman mailman : mailmen) {
            if (mailman.getId() == id) {
                return mailman;
            }
        }
        return null;
    }

    /**
     * Sucht einen Callcentermitarbeiter aus der Liste und gibt ihn zurück
     *
     * @param id Die Personalnummer des Mitarbeiters als int
     * @return Gibt einen Callcentermitarbeiter zurück. Existiert der Nutzer nicht gibt es null zurück
     */
    public Agent getAgent(int id) {
        for (Agent agent : agents) {
            if (agent.getId() == id) {
                return agent;
            }
        }
        return null;
    }

    /**
     * Prüft, ob ein Nutzer berechtigt ist das System zu nutzen
     *
     * @param username Der Nutzername oder die Personalnummer eines Nutzers
     * @param password Das angegebene Passwort
     * @return Gibt ein Integer zurück, um den Erfolg zu definieren:
     *         0: Es gibt diesen Nutzer und das Passwort ist korrekt und es ist ein Kunde.
     *         1: Es wurde kein Nutzer gefunden
     *         2: Passwort stimmt nicht überein
     *         3: Es gibt diesen Nutzer und das Passwort ist korrekt und es ist ein Mailman.
     *         4: Es gibt diesen Nutzer und das Passwort ist korrekt und es ist ein Agent.
     */
    public int authentificate(String username, String password) {
        Customer customer = getCustomer(username);
        if (customer != null) {
            // Es wurde ein Nutzer gefunden
            if (customer.getPassword().equals(password)) {
                return 0;
            } else {
                // Das Passwort stimmt nicht
                return 2;
            }
        }

        int id = Integer.parseInt(username);
        Mailman mailman = getMailman(id);
        if (mailman != null) {
            if (mailman.getPassword().equals(password)) {
                return 3;
            } else {
                return 2;
            }
        }

        Agent agent = getAgent(id);
        if (agent != null) {
            if (agent.getPassword().equals(password)) {
                return 4;
            } else {
                return 2;
            }
        }

        return 1;
    }

    // ARBEITEN AM GESENDETEN AB HIER

    /**
     * Sendet Post an einen Nutzer
     *
     * @param type Der Typ des Pakets als String
     * @param receiver Der Nutzername des Empfängers als String
     * @param sender Der Nutzername des Senders als String
     * @return Gibt ein Integer zurück, um den Erfolg zu definieren:
     *         0: Die Post wurde geschickt.
     *         1: Der Sender Existiert nicht
     *         2: Der Empfänger existiert nicht
     *         3: Der Pakettyp existiert nicht
     */
    public int sendMail(String type, String receiver, String sender) {
        Customer senderCustomer = getCustomer(sender);
        Customer receiverCustomer = getCustomer(receiver);

        if (senderCustomer == null) {
            return 1;
        }
        if (receiverCustomer == null) {
            return 2;
        }

        PackageType finalType = null;
        switch (type) {
            case "Brief":
                finalType = PackageType.LETTER;
                break;
            case "Einwurf-Einschreiben":
                finalType = PackageType.THROW_IN_MAIL;
                break;
            case "Einschreiben":
                finalType = PackageType.REGISTERED_MAIL;
                break;
            case "PaketS":
                finalType = PackageType.S;
                break;
            case "PaketM":
                finalType = PackageType.M;
                break;
            case "PaketL":
                finalType = PackageType.L;
                break;
            default:
                return 3;
        }

        Package content = new Package(receiverCustomer, finalType);
        senderCustomer.sendPackage(content);
        receiverCustomer.receivePackage(content);
        return 0;
    }
}

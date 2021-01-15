package edu.kit.informatik;

/**
 * Eine Klasse um einen Kunden zu simulieren.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class Customer extends User {

    private final String username;
    private final String id;
    private Package[] sendPackages = {};
    private Package[] receivedPackages = {};

    public Customer(String firstName, String lastName, String password, String username, String id) {
        super(firstName, lastName, password);
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    /**
     * Eine Methode, um versendete Pakete hinzuzufügen
     * @param content Ein Paket, dass eingetragen werden soll
     */
    public void sendPackage(Package content) {
        Package[] newPackageArray = new Package[sendPackages.length + 1];
        for (int index = 0; index < sendPackages.length; index++) {
            newPackageArray[index] = sendPackages[index];
        }
        newPackageArray[sendPackages.length] = content;

        sendPackages = newPackageArray;
    }

    /**
     * Eine Methode, um empfangenen Pakete hinzuzufügen
     * @param content Ein Paket, dass eingetragen werden soll
     */
    public void receivePackage(Package content) {
        Package[] newPackageArray = new Package[sendPackages.length + 1];
        for (int index = 0; index < receivedPackages.length; index++) {
            newPackageArray[index] = receivedPackages[index];
        }
        newPackageArray[receivedPackages.length] = content;

        receivedPackages = newPackageArray;
    }

    /**
     * Empfängt alle erhaltene Pakete für den Kunden
     */
    public void fetchPackages() {
        for (Package receivedPackage : receivedPackages) {
            receivedPackage.fetched = true;
        }
    }

    /**
     * Zählt alle Pakete des Typs
     * 
     * @param compareType Der Typ der gezählt werden soll
     * @return Gibt die Anzahl als String zurück
     */
    private String getSumPackagesInboxType(PackageType compareType) {
        int counter = 0;
        for (Package receivedPackage : receivedPackages) {
            if (receivedPackage.packageType == compareType) {
                counter++;
            }
        }
        
        return String.valueOf(counter);
    }

    /**
     * Erstellt einen String, der den Anforderungen der Aufgabe entspricht
     * @return Gibt den String für die Ausgabe zurück
     */
    public String listMail() {
        String output = "";
        if (!getSumPackagesInboxType(PackageType.LETTER).equals("0")) {
            output = output + "Brief;" + getSumPackagesInboxType(PackageType.LETTER);
        }
        if (!getSumPackagesInboxType(PackageType.THROW_IN_MAIL).equals("0")) {
            output = output + "Einwurf-Einschreiben;" + getSumPackagesInboxType(PackageType.THROW_IN_MAIL);
        }
        if (!getSumPackagesInboxType(PackageType.REGISTERED_MAIL).equals("0")) {
            output = output + "Einschreiben;" + getSumPackagesInboxType(PackageType.REGISTERED_MAIL);
        }
        if (!getSumPackagesInboxType(PackageType.L).equals("0")) {
            output = output + "Einschreiben;" + getSumPackagesInboxType(PackageType.L);
        }
        if (!getSumPackagesInboxType(PackageType.M).equals("0")) {
            output = output + "Einschreiben;" + getSumPackagesInboxType(PackageType.M);
        }
        if (!getSumPackagesInboxType(PackageType.S).equals("0")) {
            output = output + "Einschreiben;" + getSumPackagesInboxType(PackageType.S);
        }
        
        return output;
    }

    /**
     * Zählt Pakete im Postausgang
     *
     * @param compareType Der Typ der gezählt werden soll
     * @return Gibt die Anzahl der Pakete im Postausgang zurück
     */
    private int getSumPackagesOutboxType(PackageType compareType) {
        int counter = 0;
        for (Package sendPackage : sendPackages) {
            if (sendPackage.packageType == compareType) {
                counter++;
            }
        }

        return counter;
    }

    /**
     *
     * @return Gibt den passenden String für list-price zurück
     */
    public String listPrice() {
        // Ja ganz viel Magic Numbers aber mir geht leider die Zeit aus!
        String output = "";
        if (getSumPackagesOutboxType(PackageType.LETTER) != 0) {
            double price = getSumPackagesOutboxType(PackageType.LETTER) * 0.70d;
            output = output
                    + "Brief;"
                    + getSumPackagesOutboxType(PackageType.LETTER)
                    + ";"
                    + String.valueOf(price)
                    + "0";
        }
        if (getSumPackagesOutboxType(PackageType.THROW_IN_MAIL) != 0) {
            double price = getSumPackagesOutboxType(PackageType.THROW_IN_MAIL) * 1.20d;
            output = output
                    + "Einwurf-Einschreiben;"
                    + getSumPackagesOutboxType(PackageType.THROW_IN_MAIL)
                    + price
                    + "0";
        }
        if (getSumPackagesOutboxType(PackageType.REGISTERED_MAIL) != 0) {
            double price = getSumPackagesOutboxType(PackageType.REGISTERED_MAIL) * 2.00d;
            output = output
                    + "Einschreiben;"
                    + getSumPackagesOutboxType(PackageType.REGISTERED_MAIL)
                    + price
                    + "0";
        }
        if (getSumPackagesOutboxType(PackageType.L) != 0) {
            double price = getSumPackagesOutboxType(PackageType.L) * 7.00d;
            output = output
                    + "Einschreiben;"
                    + getSumPackagesOutboxType(PackageType.L)
                    + price
                    + "0";
        }
        if (getSumPackagesOutboxType(PackageType.M) != 0) {
            double price = getSumPackagesOutboxType(PackageType.M) * 6.00d;
            output = output
                    + "Einschreiben;"
                    + getSumPackagesOutboxType(PackageType.M)
                    + price
                    + "0";
        }
        if (getSumPackagesOutboxType(PackageType.S) != 0) {
            double price = getSumPackagesOutboxType(PackageType.S) * 5.00d;
            output = output
                    + "Einschreiben;"
                    + getSumPackagesOutboxType(PackageType.S)
                    + price
                    + "0";
        }

        return output;
    }

    /**
     *
     * @param password Ein Passwort, dass den Parametern entspricht
     * @return Gibt ein int zurück, um den Erfolg zu definieren:
     *         0: Erfolreich geändert
     *         1: Passwort entspricht nicht den Parametern
     */
    public int resetPassword(String password) {
        if (password.length() < 4 | password.length() > 9) {
            return 1;
        }
        setPassword(password);
        return 0;
    }
}

package edu.kit.informatik;

public class Package extends Mail {
    /**
     * Speichert einen Typen, um den Preis berechnen zu können.
     */
    public final PackageType packageType;

    public Package(Customer addressee, PackageType packageType) {
        super(addressee);
        this.packageType = packageType;
    }
}

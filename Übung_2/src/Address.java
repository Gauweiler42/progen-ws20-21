/**
 * Eine Klasse, um eine Adresse speichern zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Address {
    private final String streetName;
    private final String city;
    private final int zipCode;

    /**
     * @param streetName Name der Straße
     * @param city Name der Stadt
     * @param zipCode Die PLZ der Stadt
     */
    public Address(String streetName, String city, int zipCode) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }
}

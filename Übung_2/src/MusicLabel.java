/**
 * Eine Klasse, um Daten über ein Musiklabel speichern zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class MusicLabel {
    private final String name;
    private final Address address;
    private final Date dateOfFounding;

    /**
     * Ein neues Label wird in der MusicCollection.main Methode erzeugt, oder das bereits vorhandene Label wird
     * verwendet.
     *
     * @param name String mit Namen des Labels
     * @param city Die Stadt der Adresse des Labels als String
     * @param streetName Der Straßenname der Adresse des Labels
     * @param zipCode Die PLZ der Stadt des Labels
     * @param dateOfFounding Ein Date Objekt, das in MusicCollection.main erzeugt wurde und übergeben wird
     */
    public MusicLabel(String name, String streetName, String city, int zipCode, Date dateOfFounding) {
        this.name = name;
        this.address = new Address(streetName, city, zipCode);
        this.dateOfFounding = dateOfFounding;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Date getDateOfFounding() {
        return dateOfFounding;
    }
}

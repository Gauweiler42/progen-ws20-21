/**
 * Eine Klasse, um einen Künstler beliebiger Art modellieren zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Artist {
    private final String lastName;
    private final String firstName;
    private final Date firstSongRelease;

    /**
     * Ein neuer Künstler wird in der MusicCollection.main Methode erzeugt, oder der bereits vorhandene Künstler wird
     * verwendet.
     *
     * @param lastName Der Nachname des Künstlers als String
     * @param firstName Der Vorname des Künstlers als String
     * @param firstSongRelease Das Datum an dem der Künstler den ersten Song veröffentlicht hat, als Date Obejekt
     */
    public Artist(String lastName, String firstName, Date firstSongRelease) {
        this.lastName = lastName;
        this.firstName = firstName;

        this.firstSongRelease = firstSongRelease;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Date getFirstSongRelease() {
        return this.firstSongRelease;
    }
}

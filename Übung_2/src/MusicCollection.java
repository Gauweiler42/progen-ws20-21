/**
 * Eine Klasse, um eine Musiksammlung zu modellieren
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class MusicCollection {
    private Album[] albums;
    private Artist[] artists;
    private MusicLabel[] labels;
    public enum Genre {
        Rock, Pop, Punk, HipHop, Schlager, Jazz, Blues, Klassik, Reggae
    }

    public Album[] getAlbums() {
        return this.albums;
    }

    public Artist[] getArtists() {
        return this.artists;
    }

    public MusicLabel[] getLabels() {
        return this.labels;
    }

    /**
     *
     * @param albums Eine Liste mit Objekten der Klasse Album.
     */
    private void setAlbums(Album[] albums) {
        this.albums = albums;
    }

    /**
     *
     * @param artists Eine Liste mit Objekten der Klasse Artist
     */
    private void setArtists(Artist[] artists) {
        this.artists = artists;
    }

    /**
     *
     * @param labels Eine Liste mit Objekten der Klasse MusicLabel
     */
    private void setLabels(MusicLabel[] labels) {
        this.labels = labels;
    }
}

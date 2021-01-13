/**
 * Eine Klasse, um einen Song in einer Musiksammlung zu modellieren
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Song {
    private final String title;
    private final Artist interpreter;
    private final Artist composer;
    private final Artist lyricist;
    private final SongDuration songDuration;
    private final Album album;

    /**
     * Eine Klasse zur Modellierung eines Songs in einer Musik Kollektion
     *
     * @param title Der Titel des Songs als String
     * @param interpreter Der Interpret des Songs als Objekt der Klasse Artist. In MusicCollection.main neu erzeugt
     *                    oder übergeben
     * @param composer Der Komponist des Songs als Objekt der Klasse Artist. In MusicCollection.main neu erzeugt
     *                 oder übergeben
     * @param lyricist Der Texter des Songs als Objekt der Klasse Artist. In MusicCollection.main neu erzeugt
     *                 oder übergeben
     * @param songDuration Die Song Laufzeit als Objekt der Klasse SongDuration. Übergeben aus MusicCollection.main
     * @param album Das Album auf dem der Song ist, als Objekt der Klasse Album. Neu erzeugt oder übergeben aus
     *              MusicCollection.main
     */
    public Song(String title, Artist interpreter, Artist composer, Artist lyricist, SongDuration songDuration, Album album) {
        this.title = title;
        this.interpreter = interpreter;
        this.composer = composer;
        this.lyricist = lyricist;
        this.songDuration = songDuration;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public Artist getInterpreter() {
        return interpreter;
    }

    public Artist getComposer() {
        return composer;
    }

    public Artist getLyricist() {
        return lyricist;
    }

    public SongDuration getSongDuration() {
        return songDuration;
    }

    public Album getAlbum() {
        return album;
    }
}

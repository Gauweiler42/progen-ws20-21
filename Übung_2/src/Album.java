/**
 * Eine Klasse, um ein Album in einer Musiksammlung simulieren zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Album {
    private final String title;
    private final Artist interpreter;
    private final Date releaseDate;
    private final MusicLabel label;
    private final int songCount;
    private final MusicCollection.Genre genre;

    /**
     * @param title Der Titel des Albums als String
     * @param artist Der Künstler als Künstlerobjekt, übergeben aus MusicCollection.main
     * @param date Das Veröffentlichungsdatum des Albums als Datumsobjekt, übergeben aus MusicCollection.main
     * @param label Das label das das Album veröffentlicht hat als MusicLabel Objekt, übergeben aus MusicCollection.main
     * @param genre Das Genre als Enum, übergeben aus MusicCollection.main
     * @param songs Die Songs auf dem Album in einer Liste als Song Objekte, übergeben aus MusicCollection.main
     */
    public Album(String title, Artist artist, Date date, MusicLabel label, MusicCollection.Genre genre, Song[] songs) {
        this.title = title;
        this.interpreter = artist;
        this.releaseDate = date;
        this.label = label;
        this.songCount = songs.length;
        this.genre = genre;
    }

    public String getTitle() {
        return this.title;
    }

    public Artist getInterpreter() {
        return this.interpreter;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public MusicLabel getLabel() {
        return this.label;
    }

    public int getSongCount() {
        return this.songCount;
    }

    public MusicCollection.Genre getGenre() {
        return this.genre;
    }

}

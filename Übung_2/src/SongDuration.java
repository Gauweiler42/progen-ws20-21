/**
 * Eine Klasse, um die Songdauer speichern zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class SongDuration {
    private final int minutes;
    private final int seconds;

    /**
     * Eine Klasse zur speicherung der Songdauer in Minuten und Sekunden
     *
     * @param minutes Die Minuten als String
     * @param seconds Die Sekunden als String
     */
    public SongDuration(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }
}

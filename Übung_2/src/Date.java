/**
 * Eine Klasse, um ein Datum speichern zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class Date {
    private final String year;
    private final String month;
    private final String day;

    /**
     *
     * @param year Das Jahr im Datum als String
     * @param month Der Monat im Datum als String
     * @param day Der Tag im Datum als String
     */
    public Date(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getYear() {
        return this.year;
    }

    public String getMonth() {
        return this.month;
    }

    public String getDay() {
        return this.day;
    }

    public String formateDate() {
        return this.getDay() + "." + this.getMonth() + "." + this.getYear();
    }
}

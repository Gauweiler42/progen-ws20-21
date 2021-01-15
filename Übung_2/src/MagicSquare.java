/**
 * Eine Klasse zur Arbeit mit magischen Quadraten.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class MagicSquare {
    private int[][] square;

    /**
     *
     * @param square Eine Matrix in 2 Dimensionen. Schema: {{1,2},{3,4}}. Symbolisiert das Magic Square
     */
    public MagicSquare(int[][] square) {
        this.square = square;
    }

    /**
     *
     * @param k ist eine integer in diesem Fall die Kantenlänge des Magic Squares
     * @return String mit allen Magischen Zahlen von 1 bis k formatiert
     */
    public static String showMagicNumbers(int k) {
        if (k <= 0) {
            return "";
        }
        // Kein else benötigt, da return die Methode abschließen würde
        String outputString = "1";
        for (int i = 2; i <= k; i++) {
            // Berechnung der Formel für entsprechendes i
            outputString = outputString + "," + String.valueOf((i * i * i + i) / 2);
        }
        return outputString;
    }

    private int[][] getSquare() {
        return this.square;
    }

    private void setSquare(int[][] square) {
        this.square = square;
    }

    /**
     *
     * @param column Ein Array mit int, Eine Horizontale Reihe aus dem magischen Quadrat
     * @return Gibt die Summe der Elemente dieser Reihe als int zurück
     */
    private static int getSumHorizontal(int[] column) {
        int sum = 0;
        for (int element : column) {
            // Jedes Element aus der Reihe werden der Summe hinzugefügt
            sum = sum + element;
        }
        return sum;
    }

    /**
     *
     * @param index Ein Index für die Spalte, die betrachtet werden soll
     * @return Gibt die Summe der Elemente dieser Spalte als int zurück
     */
    private int getSumVertical(int index) {
        int sum = 0;
        for (int[] row : this.getSquare()) {
            // Jedes Element am Index wird zusammengezählt
            sum = sum + row[index];
        }
        return sum;
    }

    /**
     *
     * @return Gibt einen Boolean zurück, je nachdem, ob die Ränder die Vorraussetzungen für ein magisches Quadrat
     * erfüllen
     */
    private boolean checkMargins() {
        int comparison = this.getSumHorizontal(this.square[0]);

        if (comparison == this.getSumHorizontal(this.getSquare()[this.getSquare().length - 1])
            && comparison == this.getSumVertical(0)
            && comparison == this.getSumVertical(this.getSquare()[0].length - 1)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param startPoint Gibt den Startpunkt der Betrachtung als String an. up entspricht oben links nach rechts unten
     *                   down entspricht von links unten nach rechts oben
     * @return Gibt die Summe der Elemente dieser Diagonalen als int zurück
     */
    private int getSumDiagonal(String startPoint) {
        int sum = 0;

        switch (startPoint) {
            case "up":
                // Startpunkt links oben
                for (int i = 0; i < this.getSquare()[0].length; i++) {
                    sum = sum + this.getSquare()[i][i];
                }
                return sum;
            case "down":
                // Startpunkt links unten
                int indexy = this.getSquare()[0].length - 1;
                int indexx = 0;
                while (indexx < this.getSquare()[0].length && indexy >= 0) {
                    sum = sum + this.getSquare()[indexx][indexy];
                    indexx++;
                    indexy--;
                }
                return sum;
            default:
                // Einen klar falschen Wert zurückgeben, da IntelliJ sonst keine Ausführung erlaubt
                return 0;
        }
    }

    /**
     *
     * @param comparison Gibt den Referenzwert der Ränder als int an
     * @return Gibt einen Boolean zurück, ob die beiden Diagonalen die gleiche Summe, wie die Ränder haben
     */
    private boolean checkCrossing(int comparison) {
        return this.getSumDiagonal("up") == comparison && this.getSumDiagonal("down") == comparison;
    }

    /**
     *
     * @return Gibt einen boolean zurück, ob das magische Dreieck wirklich magisch ist
     */
    public boolean isMagicSquare() {
        // Vergleichswert holen. Spart abfragen
        int comparison = this.getSumHorizontal(this.getSquare()[0]);

        return this.checkMargins() && this.checkCrossing(comparison);
    }

    /**
     *
     * @return Gibt einen boolean zurück, ob das magische Dreieck semi magisch ist
     */
    public boolean isSemimagicSquare() {
        if (this.checkMargins()) {
            // Die Ränder sind gleich in der Summe
            int comparison = this.getSumVertical(0);
            if (this.getSumDiagonal("up") != this.getSumDiagonal("down")) {
                // Die Summe der Diagonalen sind nicht gleich && Eine der Summen der Diagonalen ist ungelich der Summe
                // eines Rands
                return comparison != this.getSumDiagonal("up")
                    || comparison != this.getSumDiagonal("down");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Ändert das Attribut square zum entsprechenden komplementäre magischen Quadrat
     */
    public void complement() {
        int[][] complementSq = new int[this.getSquare().length][this.getSquare()[0].length];
        for (int indexRow = 0; indexRow < this.getSquare().length; indexRow++) {
            // Reihen Durchgehen
            for (int indexColumn = 0; indexColumn < this.getSquare()[indexRow].length; indexColumn++) {
                // Spalten in der Reihe durchgehen
                complementSq[indexRow][indexColumn] = this.getSquare()[indexRow][indexColumn] * (-1)
                    + (this.getSquare().length * this.getSquare().length + 1);
            }
        }
        this.setSquare(complementSq);
    }

    /**
     *
     * @return Gibt einen formatierten String des aktuellen Quadrates zurück
     */
    public String toString() {
        String formatedStr = "";
        for (int indexx = 0; indexx < this.getSquare().length; indexx++) {
            // Alle Spalten durchgehen
            for (int indexy = 0; indexy < this.getSquare()[0].length; indexy++) {
                if (indexy != this.getSquare()[0].length - 1) {
                    formatedStr = formatedStr + this.getSquare()[indexx][indexy] + " ";
                } else {
                    formatedStr = formatedStr + this.getSquare()[indexx][indexy];
                }
            }
            if (indexx != this.getSquare().length - 1) {
                formatedStr = formatedStr + "\n";
            }
        }
        return formatedStr;
    }
}

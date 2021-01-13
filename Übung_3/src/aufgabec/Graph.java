package aufgabec;

import edu.kit.informatik.Terminal;

/**
 * Eine Klasse zur Arbeit mit Adjanzenzmatrizen
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class Graph {
    /**
     * Die Adjanzenzmatrix mit der gearbeitet wird. Eine Quadratische Matrix mit Integerwerten
     */
    private int[][] adjanzenzmatrix;

    /**
     *
     * @param adjanzenzmatrix Erwartet eine Ajanzenzmatrix
     */
    public Graph(int[][] adjanzenzmatrix) {
        this.adjanzenzmatrix = adjanzenzmatrix;
    }

    /**
     * Mit dieser Methode werden die Anzahl der Wege ausgegeben.
     *
     * @param numberOfStartCity Erwartet ein Integer für den Start Knoten
     * @param numberOfTargetCity Erwartet ein Integer für den End Knoten
     * @param lengthOfPath Erwartet ein Integer für die Länge des Pfades
     * @return Gibt die Anzahl der Pfade mit einer bestimmten Länge als Integer zurück.
     */
    public int getConnectionNumber(int numberOfStartCity, int numberOfTargetCity, int lengthOfPath) {
        int sideLength = getAdjanzenzmatrix().length;
        int[][] newMatrix = new int[sideLength][sideLength];

        newMatrix = matrixPotency(lengthOfPath);
        return newMatrix[numberOfStartCity][numberOfTargetCity];
    }

    /**
     * Eine Methode, um eine Quadratische Matrix der Kantenlänge der Adjanzenzmatrix mit der Adjanzenzmatrix zu
     * multiplizieren
     *
     * @param matrix eine Quadratische Matrix der Kantenlänge der Adjanzenzmatrix
     * @return Gibt eine Quadratische Matrix der Kantenlänge der Adjanzenzmatrix zurück
     */
    private int[][] multiplyWithMatrix(int[][] matrix) {
        int sideLength = getAdjanzenzmatrix().length;
        int[][] newMatrix = new int[sideLength][sideLength];
        int sum = 0;

        // Anwendung der Formel aus der Aufgabe C.2.3
        for (int i = 0; i < getAdjanzenzmatrix().length; i++) {
            for (int j = 0; j < getAdjanzenzmatrix().length; j++) {
                for (int k = 0; k < sideLength; k++) {
                    sum += getAdjanzenzmatrix()[i][k] * matrix[k][j];
                }
                newMatrix[i][j] = sum;
                sum = 0;
            }
        }

        return newMatrix;
    }

    /**
     * Eine Methode, um die Adjanzenzmatrix zu petenzieren
     *
     * @param exponent Der Exponent der Matrix Potenz
     * @return Gibt eine Quadratische Matrix der Kantenlänge der Adjanzenzmatrix zurück
     */
    private int[][] matrixPotency(int exponent) {
        int sideLength = getAdjanzenzmatrix().length;
        int[][] newMatrix = multiplyWithMatrix(getAdjanzenzmatrix());

        for (int i = 1; i < exponent; i++) {
            newMatrix = multiplyWithMatrix(newMatrix);
        }
        return newMatrix;
    }

    /**
     * Eine Getter-Methode für das Attribut adjanzenzmatrix
     *
     * @return Gibt die Adjanzenzmatrix zurück
     */
    public int[][] getAdjanzenzmatrix() {
        return adjanzenzmatrix;
    }

    /**
     * Hier werden die Kommandozeilenargumente auf die Klasse angewendet.
     *
     * @param args Erwartet 4 Zeilenargumente in Reihenfolge: Pfad zu einer Textdatei, Nummer der Stadt von der ein Pfad
     *             gesucht wird, Nummer der Stadt zu der navigiert werden soll, Die gesuchte länge des Pfads
     */
    public static void main(String[] args) {
        /*
         * Die main() sollte immer in einer Utility Class sein,
         * nicht in einer Klasse, von der Objekte existieren.
         * main() in Modellierungsklasse (-0.5 Punkte)
         *
         * Aus der Korrektur des letzten Blattes, aber leider weiß ich es nicht anzuwenden
         */
        String[] fileContent = Terminal.readFile(args[0]);
        int[][] adjanzenzmatrix = new int[fileContent.length][fileContent.length];
        int numberOfStartCity = Integer.parseInt(args[1]);
        int numberOfTargetCity = Integer.parseInt(args[2]);
        int lengthOfPath = Integer.parseInt(args[3]);


        // Jede Stelle der Matrix 0 setzen
        for (int indexRow = 0; indexRow < fileContent.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < fileContent.length; indexColumn++) {
                adjanzenzmatrix[indexRow][indexColumn] = 0;
            }
        }

        // Die Pfade aus der Datei eintragen
        String[] line;
        int startCity;
        int destinationCity;

        for (String s : fileContent) {
            line = s.split(" ");
            startCity = Integer.parseInt(line[0]);
            destinationCity = Integer.parseInt(line[1]);

            adjanzenzmatrix[startCity][destinationCity] = 1;
        }

        Graph g = new Graph(adjanzenzmatrix);
        int numberOfConnections = g.getConnectionNumber(numberOfStartCity, numberOfTargetCity, lengthOfPath);
        Terminal.printLine(String.valueOf(numberOfConnections));
    }
}

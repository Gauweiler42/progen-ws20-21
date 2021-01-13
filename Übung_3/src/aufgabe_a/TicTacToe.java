package aufgabe_a;

import edu.kit.informatik.Terminal;

/**
 * Ein Programm zur Arbeit mit einem TicTacToe Feld
 *
 * @version 1
 * @author Leon Gauweiler
 */

public class TicTacToe {
    /**
     * Eine Klasse, um mit einem TicTacToe Feld arbeiten zu können
     */
    static final int[][] DIRECTIONS_TO_CHECK = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    private int[] ticTacToeField = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int winner = 0;

    /**
     *
     * @param fields Erwartet die Felder als Eingaben
     */
    public TicTacToe(String[] fields) {
        boolean winPrint = false;

        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
            // Alle Zahlen werden aus der Kommandozeile eingelesen
            if (!winPrint) {
                // Nächsten Zug prüfen
                int[] currentTicTacToeField = this.getTicTacToeField();
                if (fieldIndex % 2 == 0) {
                    currentTicTacToeField[Integer.parseInt(fields[fieldIndex])] = 1;
                } else {
                    currentTicTacToeField[Integer.parseInt(fields[fieldIndex])] = 2;
                }

                this.setTicTacToeField(currentTicTacToeField);
                this.checkWin();

                if (this.getWinner() != 0 || fieldIndex == 8) {
                    winPrint = true;
                }
            } else {
                // Den Sieger ausgeben
                String winnerNotification;
                if (this.getWinner() != 0) {
                    winnerNotification = "P";
                    winnerNotification = winnerNotification + this.getWinner();
                    winnerNotification = winnerNotification + " wins ";
                    winnerNotification = winnerNotification + (fieldIndex);
                } else {
                    winnerNotification = "draw";
                }

                // In diesem Fall ist der String auf jeden Fall gesetzt
                Terminal.printLine(winnerNotification);

                // Beende die Schleife, um keine doppelte Ausgabe zu erhalten
                break;
            }
        }
    }

    /**
     *
     * @param fields Erwartet die Felder des TicTacToe Feldes als Eingaben
     */
    public static void main(String[] fields) {
        String[] fields2 = {"0", "4", "2", "3", "1", "7", "5", "8", "6"};
        new TicTacToe(fields2);
    }

    /**
     * Diese Methode führt den nächsten Zug aus und ruft die Prüfungsmethode auf
     */
    private void checkWin() {
        for (int[] fieldIndex : DIRECTIONS_TO_CHECK) {
            if (this.getTicTacToeField()[fieldIndex[0]] != 0
                    && this.getTicTacToeField()[fieldIndex[1]] != 0
                    && this.getTicTacToeField()[fieldIndex[2]] != 0
            )
            {
                if (this.getTicTacToeField()[fieldIndex[0]] == this.getTicTacToeField()[fieldIndex[1]]) {
                    if (this.getTicTacToeField()[fieldIndex[1]] == this.getTicTacToeField()[fieldIndex[2]]) {
                        this.setWinner(this.getTicTacToeField()[fieldIndex[0]]);
                    }
                }
            }
        }
    }

    /**
     *
     * @param winner Ein Integer mit der Gewinnernummer: 0 = DRAW; 1 = P1; 2 = P2
     */
    public void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     *
     * @return Gibt den Gewinner int Wert zurück
     */
    public int getWinner() {
        return this.winner;
    }

    /**
     *
     * @param ticTacToeField Ein Array der Länge 9, welches ein TicTacToe Feld simuliert
     */
    public void setTicTacToeField(int[] ticTacToeField) {
        this.ticTacToeField = ticTacToeField;
    }

    /**
     *
     * @return Gibt ein Array mit 9 Elementen zurück. Diese simulieren das TicTacToe Feld
     */
    private int[] getTicTacToeField() {
        return this.ticTacToeField;
    }
}

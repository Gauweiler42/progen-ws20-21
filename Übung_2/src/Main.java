/**
 * Ein Programm zur Arbeit mit magischen Quadraten.
 *
 * @version 1
 * @author Leon Gauweiler
 */
public class Main {
    /**
     *
     * @param args Erwartet 2 Argumente: Mögliche Operation und Parameter für die Operation
     *             Bsp.: isMagicSquare? 4,9,2;3,5,7;8,1,6
     */
    public static void main(String[] args) {
        if (args[0].equals("showMagicNumbers")) {
            // Der Aufbau des magischen ist nicht genau bekannt und relevant, daher Sonderfall
            int[][] table = {{}, {}};
            MagicSquare magicSquare = new MagicSquare(table);

            System.out.println(magicSquare.showMagicNumbers(Integer.parseInt(args[1])));
        } else {
            // Erstelle Matrix aus String | Noch nicht funktionsfähig
            String[] rows = args[1].split(";", -1);

            // Matrix erstellen
            int[][] table = new int[rows.length][rows.length];

            for (int indexRows = 0; indexRows < rows.length; indexRows++) {
                String[] row = rows[indexRows].split(",", -1);
                for (int indexRow = 0; indexRow < row.length; indexRow++) {
                    table[indexRows][indexRow] = Integer.parseInt(row[indexRow]);
                }
            }



            MagicSquare magicSquare = new MagicSquare(table);

            /*
            int[][] table = {{14,7,6},{1,9,17},{12,11,14}};
            MagicSquare magicSquare = new MagicSquare(table);
             */

            if (args[0].equals("isMagicSquare?")) {
                if (magicSquare.isMagicSquare()) {
                    System.out.println("magic square");
                } else if (!magicSquare.isMagicSquare() && magicSquare.isSemimagicSquare()) {
                    System.out.println("semimagic square");
                } else {
                    System.out.println("not magical");
                }
            } else if (args[0].equals("complement")) {
                magicSquare.complement();
                System.out.println(magicSquare.toString());
            }
        }
    }
}

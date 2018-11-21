/******************************************************************************
   Compilation:  javac MatrixMult.java
   Execution:    java MatrixMult az as a_11 .. a_1as a_21 .. a2as .. a_az1 .. a_azas
                                 bz bs b_11 .. b_1bs b_21 .. b2bs .. b_bz1 .. b_bzbs

   Eingabe: Zwei (potentiell nicht quadratische) Matrizen aus Integer Werten.
            Die Matrizen werden hintereinander uebergeben und sind jeweils
            folgendermassen codiert:
              1. Wert: Anzahl der Zeilen der Matrix (z)
              2. Wert: Anzahl der Spalten der Matrix (s)
              Es folgen dann insgesamt (z*z) Werte der Matrix. Die Werte sind
              zeilenweise einzugeben, d.h. die ersten s Werte gehoeren zur ersten
              Zeile der Matrix, die naechsten s Werte zur zweiten Zeile, usw.

            Die Anzahl der Spalten der ersten und der Zeilen der zweiten Matrix
            muessen identisch sein. Wenn das nicht der Fall ist, soll das Programm
            ERROR ausgeben.

   Ausgabe: Das Produkt der beiden Matrizen. Die Produktmatrix soll zeilenweise
            erfolgen.


   Hinweise: Der Wert der Produktmatrix in der i-ten Zeile und j-ten Spalte ist
             die Summe des Produktes der Werte der i-ten Zeile der ersten Matrix
             und der j-ten Spalte der zweiten Matrix.

             Angenommen die erste Matrix ist
             1 2 3
             4 5 6

             und die zweite Matrix ist
             7 10
             8 11
             9 12

             dann ist der Wert an der Stelle 1,1 des Produktes
             1*7 + 2*9 + 3*9

             der Wert an der Stelle 2,1 ist entsprechend
             4*7 + 5*8 + 6*10

  Referenzen: https://de.wikipedia.org/wiki/Matrizenmultiplikation#Beispiel

 * 
 ******************************************************************************/

public class MatrixMult {

    /*
       Liest aus einer linearen Sequenz von Werten beginnend an der Stelle offset
       eine Matrix ein.
     */
    public static int[][] readMatrix(int[] input, int offset, int zeilen, int spalten) {
        int[][] matrix = new int[zeilen][spalten];
        for(int z=0;z<zeilen;z++) {
            for(int s=0;s<spalten;s++) {
                int index = offset + (z * spalten + s);
                matrix[z][s] = input[index];
            }
        }
        return matrix;
    }

    /*
       Funktion, die den Wert der Produktmatrix an der Stelle zeile, spalte ausrechnet
     */
    public static int computeEntry(int[][] m1, int[][] m2, int zeile, int spalte) {
        int wert = 0;
        int anzahl = m1[zeile].length; // Anzahl der Elemente einer Zeile in m1
        for (int i=0;i<anzahl;i++) {
            wert += m1[zeile][i] * m2[i][spalte];
        }
        return wert;
    }

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("ERROR");
            return;
        }

        int[] input = new int[args.length];
        for (int i=0; i<args.length; i++) {
            input[i] = Integer.parseInt(args[i]);
        }

        int z1 = input[0];
        int s1 = input[1];

        // Pruefe, ob Zeilen/Spalten von ersten Matrix gueltig
        if (z1 < 0 || s1 < 0) {
            System.out.println("ERROR");
            return;
        }

        int i2 = 2 + s1*z1; // Position der Zeileninfo der zweiten Matrix

        // Pruefe, ob Anzahl Zeilen/Spalten von zweiter Matrix vorhanden
        if (input.length <= i2 || input.length <= i2+1) {
            System.out.println("ERROR");
            return;
        }

        int z2 = input[i2];
        int s2 = input[i2+1];
        int offset1 = 2; // Position der Werte der ersten Matrix
        int offset2 = 2+i2; // Position der Werte der zweiten Matrix

        // Pruefe, ob Zeilen/Spalten von zweiter Matrix gueltig
        if (z2 < 0 || s2 < 0) {
            System.out.println("ERROR");
            return;
        }

        int[][] p = new int[z1][s2];

        // Pruefe, ob zweite Matrix vollstaendig
        if (i2 + 1 + z2 * s2 >= input.length) {
            System.out.println("ERROR");
            return;
        }

        if (s1 != z2) {
            System.out.println("ERROR");
        }
        else {
            // Einlesen der Matrizen
            int[][] m1 = readMatrix(input, offset1, z1, s1);
            int[][] m2 = readMatrix(input, offset2, z2, s2);

            // Berechnen des Produktes
            for (int z=0; z<z1; z++) {
                for (int s=0; s<s2; s++) {
                    p[z][s] = computeEntry(m1,m2,z,s);
                }
            }

            // Ausgeben des Produktes (koennte man mit der Berechnung zusammenfassen)
            for (int z=0; z<z1; z++) {
                for (int s=0; s<s2-1; s++) {
                    System.out.print(p[z][s]+" ");
                }
                System.out.println(p[z][s2-1]);
            }

        }
    }
}


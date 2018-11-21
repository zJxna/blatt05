/******************************************************************************
    Compilation:  javac Histogramm.java
    Execution:    java Histogramm min max n

    Eingabe: min, max und n sollen per Kommandozeilenargumente uebergeben werden

             Es sollen von der Standardeingabe bis zur EOF-Sequenz ganzzahlige,
             positive Werte zwischen min und max gelesen werden. Das Programm
             soll den Bereich zwischen min und max in n gleich grosse Bereiche
             aufteilen und zaehlen, wie viele gelesen Werte in jeden Bereich
             fallen. Die obere Grenze zaehlt nicht mehr zum Bereich. Eine
             Ausnahme ist der letzte Bereich, dort zaehlt die Obergrenze mit zum
             Bereich. (max - min) soll immer ganzzahlig durch n teilbar sein.
             Ansonsten soll "ERROR" ausgegeben und abgebrochen werden. 

    Ausgabe: Pro Bereich eine Zeile, in der die Bereichsgrenzen und die Anzahl
             der Werte, die in den Bereich fallen stehen.

    Hinweise: Fuer die Anzahl der eingegebenen Werte gibt es keine Obergrenze!

    Referenzen: https://de.wikipedia.org/wiki/Histogramm

    Beispiel (input.txt im Wurzelverzeichnis der Aufgabe):

 $ java Histogramm 0 10 5 < input.txt
 0 2 4
 2 4 5
 4 6 1
 6 8 2
 8 10 2


 ******************************************************************************/

public class Histogramm {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("ERROR");
            return;
        }

        int min = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);

        if (min > max) {
            System.out.println("ERROR");
            return;
        }

        if (min < 0 || max < 0 || n < 0) {
            System.out.println("ERROR");
            return;
        }

        if ((max - min) % n != 0) {
            System.out.println("ERROR");
            return;
        }

        int step = (max-min)/n;

        int[] histogramm = new int[n];

        while (!StdIn.isEmpty()){
            int x = StdIn.readInt();
            int bin = 0;
            int thres = min + step;

            while( x >= thres && bin < n){
                thres += step;
                bin++;
            }
            bin = Math.min(bin, n-1);
            histogramm[bin]++;
        }

        for(int i = 0; i < n; i++){
            StdOut.printf("%d %d %d\n", min+ i*step, min+(i+1)*step, histogramm[i]);
        }
    }
}


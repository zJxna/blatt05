/******************************************************************************
 *  Compilation:  javac Minesweeper2.java
 *  Execution:    java Minesweeper2 f00 f01 ... f0n f10 f11 ... f1n ... fn0 ... fnn
 *
 *  Eingabe: Wie Aufgabe 5 auf Blatt 4
 *  Ausgabe: Graphische Ausgabe des Boards
 *
 * Verwenden Sie die Musterloesung von Minesweeper1 oder Ihre eigene 
 * Implementierung und schreiben Sie eine graphische Ausgabe etwa wie 
 * in der Datei Minesweeper.png dargestellt. 
 *  
 * Tipp: Unter http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 * finden Sie eine Beschreibung aller Funktionen von StdDraw. Insbesondere die
 * Funktion scale koennte hilfreich sein.
 ******************************************************************************/

public class Minesweeper2 {
	public static void main(String[] args) {
		// Implementieren Sie Ihre Loesung hier 
		// Groesse der Matrix bestimmen
		int n = (int) Math.sqrt(args.length);
		int[][] input = new int[n][n];
		int[][] output = new int[n][n];
		// Matrix einlesen
		for (int z = 0; z < n; z++) {
			for (int s = 0; s < n; s++) {
				input[z][s] = Integer.parseInt(args[z * n + s]);
			}
		}
		// Nachbarn ausrechnen
		for (int z = 0; z < n; z++) {
			for (int s = 0; s < n; s++) {
				if (input[z][s] == 1) {
					output[z][s] = -1;
				} else {
					int count = 0;

					for (int i = z - 1; i <= z + 1; i++) {
						for (int j = s - 1; j <= s + 1; j++) {
							if (i < n && i >= 0 && // zeile ist gueltig
							j < n && j >= 0 && // spalte ist gueltig
							input[i][j] == 1) {
								count++;
							}
						}
					}
					output[z][s] = count;
				}
			}
		}

		// Matrix graphisch ausgeben
		// Gitter
		StdDraw.setScale(0, n);
		for (int i = 0; i < n; i++) {
			StdDraw.line(i, 0, i, n);
			StdDraw.line(0, i, n, i);
		}

		// Zahlen
		for (int z = 0; z < n; z++) {
			for (int s = 0; s < n; s++) {
				String text = Integer.toString(output[z][s]);
				StdDraw.text(s + 0.5, n - z - 0.5, text);
			}
		}
	}
}

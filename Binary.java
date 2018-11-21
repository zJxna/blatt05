/******************************************************************************
 *  Compilation:  javac Binary.java
 *  Execution:    ---
 *
 *  Die Klasse soll eine per Konstruktor uebergebene Dezimalzahl in Binaerform
 *  darstellen. Der Konstruktor soll bei Werten < 0 die Zahl auf 0 setzen.
 *  Wie die Speicherung dieser Zahl auszusehen hat, ist Ihnen ueberlassen. 
 *  Hierbei gibt es verschiedene Ansaetze mit entsprechenden Vor- und
 *  Nachteilen.
 *  Implementieren Sie die Methoden, die durch die Tests spezifiziert sind.
 *
 *  add: Addiere eine uebergebene Binaerzahl auf die aktuelle.
 *  toDec: Gibt die Binaerzahl als Dezimalwert (int) zurueck.
 *  toString: Gibt die Binaerzahl als String formatiert zurueck.
 *            Beachten Sie hierbei das @Override voranzustellen:
 *            @Override
 *            public String toString() {
 *                ....
 *            }
 *
 ******************************************************************************/

public class Binary{
    private String number;
    private int numberDec;

    public Binary(int n) {
        if (n < 0) {
            n = 0;
        }

        this.numberDec = n;
        this.number = convert(n);
    }
    
    public void add(Binary x) {
        this.numberDec += x.toDec();
        this.number = convert(this.numberDec);
    }

    public int toDec() {
        return numberDec;
    }

    @Override
    public String toString() {
        return number;
    }

    private static String convert(int n) {
        String str = "";
        
        do {
            if ((n & 1) == 1) {
                str = "1" + str;
            } else {
                str = "0" + str;
            }
    
            n = n >> 1;
        } while (n > 0);

        return str;
    }
}

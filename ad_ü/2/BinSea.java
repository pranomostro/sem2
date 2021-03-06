/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär
 * nach Wertebereichen zu suchen.
 */
class BinSea {
  /**
   * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
   * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
   * einem Intervall zu finden. Wird kein passender Index gefunden, wird
   * -1 zurückgegeben.
   * 
   * Beispiel:
   * 
   *             0 <-----------------------> 5
   * sortedData: [-10, 33, 50, 99, 123, 4242 ]
   * value: 80             ^   ^
   *                       |   |
   *                       |   `- Ergebnis (3) für ersten Index im Intervall (lower == true),
   *                       |      da 99 als erster Wert im Feld größer oder gleich 80 ist
   *                       |
   *                       `- Ergebnis (2) für letzten Index im Intervall (lower == false),
   *                          da 50 als letzter Wert kleiner oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param value der Wert der Intervallbegrenzung, nach dem gesucht wird
   * @param lower true für untere Intervallbegrenzung, false für obere Intervallbegrenzung
   * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
   */
  private static int search (int[] sortedData, int value, boolean lower) {
	if(!lower&&sortedData[0]>value||lower&&sortedData[sortedData.length-1]<value||sortedData.length==0)
		return -1;

	int hi, lo, mid;
	lo=mid=0;
	hi=sortedData.length-1;

	while(lo<=hi) {
		mid=(hi+lo)/2;
		if(sortedData[mid]>value)
			hi=mid-1;
		else if(sortedData[mid]<value)
			lo=mid+1;
		else
			break;
	}
	if(lower&&sortedData[mid]<value) {
		return mid+1;
	} else if(!lower&&sortedData[mid]>value) {
		return mid-1;
	}
	return mid;
  }

  /**
   * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes, deren Werte
   * in einem Wertebereich liegen. Es soll dabei binäre Suche verwendet werden.
   * 
   * Beispiel:
   *                     0 <-----------------------> 5
   * sortedData:         [-10, 33, 50, 99, 123, 4242 ]
   * valueRange: [80;700]              ^   ^
   *                                   |   |
   *                                   |   `- Ergebnis (4) für obere Intervallbegrenzung,
   *                                   |      da 123 als letzter Wert kleiner oder gleich 700 ist
   *                                   |
   *                                   `- Ergebnis (3) für untere Intervallbegrenzung,
   *                                      da 99 als erster Wert größer oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param valueRange der Wertebereich, zu dem Indices gesucht werden
   */
  public static Interval search (int[] sortedData, Interval valueRange) {
    /**
     * Todo: Aufgabe b)
     */
	int lb=search(sortedData, valueRange.getFrom(), true);
	int ub=search(sortedData, valueRange.getTo(), false);
	return new NonEmptyInterval(lb, ub);
  }

  public static void main(String[] args) {
	int[] testarray={-10, 33, 50, 99, 123, 4242};
	System.out.println("" + search(testarray, new NonEmptyInterval(-20, 124)));
  }
}

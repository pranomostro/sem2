import java.util.ArrayList;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		//Scanner scanner = new Scanner(System.in);

		//while (scanner.hasNextInt()) {
			// TODO: Eingabe in einer Liste speichern.
		//}

		// TODO: eingegebene Liste per RadixSort sortieren und zeilenweise ausgeben.
		Integer[] testarr={41, 73, 98, 36, 93, 48, 39, 226, 10054, 19, 5, 22, 85, 37, 33, 34, 52, 82, 74, 44, 70, 55};
		RadixSort.sort(testarr);

		for(int i=0; i<testarr.length; i++)
			System.out.println(testarr[i]);
	}
}

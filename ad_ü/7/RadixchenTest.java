import java.util.ArrayList;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		Integer n;
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> nums=new ArrayList<Integer>();

		while (scanner.hasNextInt()) {
			n=scanner.nextInt();
			if(n<0)
				System.exit(0);
			nums.add(n);
		}

		Integer[] testarr=nums.toArray(new Integer[nums.size()]);

		RadixSort.sort(testarr);

		for(int i=0; i<testarr.length; i++)
			System.out.println(testarr[i]);
	}
}

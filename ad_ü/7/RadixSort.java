import java.lang.reflect.Array;
import java.util.ArrayList;

public class RadixSort {
	private static int key(Integer element, int digit) throws IllegalArgumentException {
		if(Integer.toString(element).length()-1<digit)
			return 0;
		String nr=Integer.toString(element);
		return nr.charAt(nr.length()-digit-1)-'0';
	}

	private static void kSort(Integer[] elements, int digit) {
		ArrayList<ArrayList<Integer>> buckets=new ArrayList<ArrayList<Integer>>(10);

		for(int i=0; i<10; i++)
			buckets.add(new ArrayList<Integer>());

		for(int i=0; i<elements.length; i++) {
			buckets.get(key(elements[i], digit)).add(elements[i]);
		}

		int idx=0;
		for(int i=0; i<buckets.size(); i++)
			for(int j=0; j<buckets.get(i).size(); j++)
				elements[idx++]=buckets.get(i).get(j);
	}

	public static void sort(Integer[] elements) {
		int d=0;

		for(int i=0; i<elements.length; i++)
			if(Math.floor(Math.log10(elements[i]))>d)
				d=(int)Math.floor(Math.log10(elements[i]));

		for(int i=0; i<=d; i++)
			kSort(elements, i);
	}
}

public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		qsort(numbers, 0, numbers.length-1);
	}

	private void qsort(int[] n, int l, int u) {
		if(l>=u) // one element or less
			return;

		int i, j, tmp;

		tmp=n[(l+u)/2];
		n[(l+u)/2]=n[u];
		n[u]=tmp;

		i=l-1;

		for(j=l; j<u; j++)
			if(n[j]<n[u]) {
				i++;
				tmp=n[i];
				n[i]=n[j];
				n[j]=tmp;
			}

		i++;
		tmp=n[u];
		n[u]=n[i];
		n[i]=tmp;

		qsort(n, l, i-1);
		qsort(n, i+1, u);
	}

	public String getName() {
		return "Quicksort";
	}
}

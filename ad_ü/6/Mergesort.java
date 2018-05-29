public class Mergesort implements SortingBase {
	private int[] res;

	public void sort(int[] numbers) {
		res=new int[numbers.length];
		msort(numbers, 0, numbers.length-1);
	}

	private void msort(int[] n, int l, int u) {
		if(l>=u) //one element or less
			return;

		int m=(l+u)/2;
		msort(n, l, m);
		msort(n, m+1, u);
		merge(n, l, m, m+1, u);
	}

	private void merge(int[] n, int l1, int u1, int l2, int u2) {
		int i, j, k, lim;

		lim=u2-l1+1;
		i=l1;
		j=l2;
		k=0;

		while(k<lim) {
			if(i<=u1&&j<=u2) {
				if(n[i]<n[j])
					res[k++]=n[i++];
				else
					res[k++]=n[j++];
			}
			else if(i<=u1)
				res[k++]=n[i++];
			else
				res[k++]=n[j++];
		}

		for(k=0; k<lim; k++)
			n[l1+k]=res[k];
	}

	public String getName() {
		return "Mergesort";
	}
}

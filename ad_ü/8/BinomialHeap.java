import java.util.ArrayList;
import java.util.ArrayDeque;

public class BinomialHeap {
	private ArrayList<BinomialTreeNode> list;
	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	public BinomialHeap() {
		list=new ArrayList<BinomialTreeNode>();
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen.
	 * Wenn es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		if(list.size()==0)
			throw new RuntimeException("Empty heap");
		int val=list.get(0).min();
		for(int i=1; i<list.size(); i++)
			if(list.get(i).min()<val)
				val=list.get(i).min();
		return val;
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		BinomialTreeNode tn, nn=new BinomialTreeNode(key);
		list.add(nn);
		System.out.println("" + list.size());
		for(int i=list.size()-1; i>=1&&list.get(i).rank()==list.get(i-1).rank(); i--) {
			tn=list.get(i).merge(list.get(i), list.get(i-1));
			list.remove(i);
			list.remove(i-1);
			list.add(tn);
		}
		for(int i=0; i<list.size(); i++)
			;
	}

	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen
	 * Haufen und gibt es zurück.
	 *
	 * @return das minimale Element
	 */
	public int deleteMin() {
		int i, j, k, min, minindex;

		/* find the minimum tree in the current heap */

		minindex=list.get(0).min();
		for(i=1; i<list.size(); i++)
			if(list.get(i).min()<minindex)
				minindex=list.get(i).min();

		min=list.get(i).min();
		BinomialTreeNode[] new_nodes=list.get(i).deleteMin();
		list.remove(i);

		/* Convert the BinomialTreeNode array into an ArrayList, */
		/* then produce a new ArrayList that is "the sum" of */
		/* new_nodes and list, and then assign this new ArrayList */
		/* to list */

		BinomialTreeNode cy;
		ArrayDeque<BinomialTreeNode> nl=new ArrayQeue<BinomialTreeNode>();
		for(i=children.size()-1, j=new_nodes.size()-1; k=0; i>0||j>0;) {
			nl.add();
			if(children.get(i).rank()==new_nodes.get(i).rank()) {
			} else if(children.get(i).rank()<new_nodes.get(i).rank()) {
			} else {
			}
		}

		return min;
	}
}

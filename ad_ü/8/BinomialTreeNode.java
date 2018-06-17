import java.util.ArrayList;

public class BinomialTreeNode {
	private int val;
	private ArrayList<BinomialTreeNode> children;

	public BinomialTreeNode(int key) {
		System.out.println("Creating new BinomialTreeNode with value " + key);
		children=new ArrayList<BinomialTreeNode>();
		val=key;
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */

	public int min() {
		return val;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */

	public int rank() {
		System.out.println("----------------------Begin of rank()");
		System.out.println("Determining the rank of the tree with root " + val);
		int i;
		if(children.size()==0)
			return 0;
		BinomialTreeNode n=this;
		for(i=0; n.children.size()!=0; n=n.children.get(n.children.size()-1), i++)
			System.out.println(":: " + n.children.get(0).min());
		System.out.println("----------------------End of rank()");
		return i;
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man
	 * den Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */

	public BinomialTreeNode[] deleteMin() {
		BinomialTreeNode[] nodes=children.toArray(new BinomialTreeNode[children.size()]);;

		return nodes;
	}

	public void append(BinomialTreeNode b) {
		System.out.println("--------------------Begin of append()");
		System.out.println("Appending tree with root " + b.min() + " and rank " + b.rank() + " into tree with minimum " + min() + " and rank " + rank());
		children.add(b);
		System.out.println("Last Element: " + children.get(children.size()-1).min() + " at length " + (children.size()-1) + " with rank " + children.get(children.size()-1).rank());
		System.out.println("Rank: " + rank());
		System.out.println("--------------------End of append()");
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */

	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		System.out.println("---------------Begin of merge()");
		System.out.println("Merging Tree with root " + a.min() + " and rank " + a.rank() + " with tree with root " + b.min() + " and rank " + b.rank());
		int mina, minb;
		mina=a.min();
		minb=b.min();

		if(mina<minb) {
			a.append(b);
			System.out.println("-----------------End of merge()");
			return a;
		} else {
			b.append(a);
			System.out.println("-----------------End of merge()");
			return b;
		}
	}
}

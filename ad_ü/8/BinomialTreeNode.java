import java.util.ArrayList;

public class BinomialTreeNode {
	private int val;
	private ArrayList<BinomialTreeNode> children;

	public BinomialTreeNode(int key) {
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
		int i;
		if(children.size()==0)
			return 0;
		BinomialTreeNode n=this;
		for(i=0; n.children.size()!=0; n=n.children.get(n.children.size()-1), i++)
			;
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
		children.add(b);
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */

	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		int mina, minb;
		mina=a.min();
		minb=b.min();

		if(mina<minb) {
			a.append(b);
			return a;
		} else {
			b.append(a);
			return b;
		}
	}
}

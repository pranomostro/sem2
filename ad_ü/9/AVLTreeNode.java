/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
	/**
	 * Diese Variable enthält den Schlüssel, den der Knoten speichert.
	 */
	private int key;

	/**
	 * Diese Variable speichert die Balancierung des Knotens - wie in der
	 * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
	 * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
	 * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
	 * rechte Teilbaum tiefer ist.
	 */
	private int balance = 0;

	private AVLTreeNode left = null;
	private AVLTreeNode right = null;

	public AVLTreeNode(int key) {
		this.key=key;
	}

	public void updateBalance() {
		int hl, hr;
		hl=hr=0;
		if(left!=null)
			hl=1+left.height();
		if(right!=null)
			hr=1+right.height();
		balance=hr-hl;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {
		int hl, hr;
		hl=hr=0;
		if(left!=null)
			hl=1+left.height();
		if(right!=null)
			hr=1+right.height();
		return hr>hl?hr:hl;
	}

	public boolean validAVL() {
		int hl, hr;

		hl=hr=0;
		if(left!=null)
			hl=1+left.height();
		if(right!=null)
			hr=1+right.height();

		if((hl-hr)>1||(hr-hl)>1)
			return false;

		if(left!=null)
			if(left.key>key)
				return false;
		if(right!=null)
			if(right.key<=key)
				return false;

		boolean rt, lt;
		rt=lt=true;

		if(left!=null)
			lt=left.validAVL();
		if(right!=null)
			rt=right.validAVL();

		return rt&&lt;
	}

	/**
	 * Diese Methode sucht einen Schlüssel im Baum.
	 *
	 * @param key der zu suchende Schlüssel
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		if(this.key==key)
			return true;
		if(key>this.key) {
			if(right!=null)
				return right.find(key);
			else
				return false;
		}
		if(key<=this.key) {
			if(left!=null)
				return left.find(key);
			else
				return false;
		}
		return false;
	}

	public void insert(int key) {
		if(key>this.key) {
			if(right!=null)
				right.insert(key);
			else
				right=new AVLTreeNode(key);
		} else if(key<this.key) {
			if(left!=null)
				left.insert(key);
			else
				left=new AVLTreeNode(key);
		}
		/* now go forth and rotate! */
		updateBalance();
		System.err.println("Now rotating the tree after inserting " + key + " with the balance " + balance);
		if(balance==2) {
			if(right.balance<0) {
				System.err.println("Double-rotating left");
				right.rotateRight();
			} else
				System.err.println("Single-rotating left");
			rotateLeft();
		} else if(balance==-2) {
			if(left.balance>0) {
				System.err.println("Double-rotating right");
				left.rotateLeft();
			} else
				System.err.println("Single-rotating right");
			rotateRight();
		}
	}

	public void rotateLeft() {
		System.err.println("Rotating left");
		AVLTreeNode a, b, c, al, bl;
		a=this; b=a.right; c=b.right; bl=b.left; al=a.left;
		this.left=new AVLTreeNode(this.key);
		this.left.left=al;
		this.left.right=bl;
		this.right=c;
		this.key=b.key;
	}

	public void rotateRight() {
		System.err.println("Rotating right");
		AVLTreeNode a, b, c, ar, br;
		a=this; b=a.left; c=b.left; br=b.right; ar=a.right;
		this.right=new AVLTreeNode(this.key);
		this.right.right=ar;
		this.right.left=br;
		this.left=c;
		this.key=b.key;
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @param sb
	 *          der StringBuilder für die Ausgabe
	 */
	public void dot(StringBuilder sb) {
		dotNode(sb, 0);
	}

	private int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
		int next = idx + 1;
		if (left != null)
			next = left.dotLink(sb, idx, next, "l");
		if (right != null)
			next = right.dotLink(sb, idx, next, "r");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};
}

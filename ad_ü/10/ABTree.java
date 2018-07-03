import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen (a,b)-Baum.
 */
public class ABTree {
	/**
	 * Diese Variable speichert die untere/obere Schranke des Knotengrades.
	 */
	private int a, b;

	/**
	 * Diese Klasse repräsentiert einen Knoten des Baumes. Es kann sich
	 * dabei um einen inneren Knoten oder ein Blatt handeln.
	 */
	public abstract class ABTreeNode {
		/**
		 * Diese Methode fügt einen Schlüssel in den Baum ein.
		 *
		 * @param key der Schlüssel, der eingefügt wird
		 */
		public abstract ABTreeNode insert(int key, boolean above);

		/**
		 * Diese Methode ermittelt, ob aus dem entsprechenden Knoten gestohlen
		 * werden kann oder nicht.
		 *
		 * @return 'true' falls gestohlen werden kann, 'false' sonst
		 */
		public abstract boolean canSteal();

		/**
		 * Diese Methode sucht den Schlüssel 'key' im Teilbaum.
		 *
		 * @param key der Schlüssel, der gesucht wird
		 * @return 'true' falls der Schlüssel vorhanden ist, 'false' sonst
		 */
		public abstract boolean find(int key);

		/**
		 * Diese Methode entfernt den Schlüssel 'key' im Teilbaum, falls es ihn gibt.
		 *
		 * @param key der Schlüssel, der entfernt werden soll
		 * @return 'true' falls der Schlüssel gefunden und entfernt wurde, 'false' sonst
		 */
		public abstract boolean remove(int key);

		/**
		 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
		 *
		 * @return die ermittelte Höhe
		 */
		public abstract int height();

		/**
		 * Diese Methode ermittelt das Minimum im jeweiligen Teilbaum.
		 * @return das Minimum
		 */
		public abstract Integer min();

		/**
		 * Diese Methode ermittelt das Maximum im jeweiligen Teilbaum.
		 * @return das Maximum
		 */
		public abstract Integer max();

		/**
		 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich
		 * um einen validen (a,b)-Baum handelt.
		 *
		 * @return 'true' falls der Baum ein valider (a,b)-Baum ist, 'false' sonst
		*/
		public abstract boolean validAB(boolean root, Integer lb, Integer ub);

		/**
		 * Diese Methode wandelt den Baum in das Graphviz-Format um.
		 *
		 * @return der nächste freie Index für das Graphviz-Format
		 */
		public abstract int dot(StringBuilder sb, int from);
	}

	/**
	 * Diese Klasse repräsentiert einen inneren Knoten des Baumes.
	 */
	private class ABTreeInnerNode extends ABTreeNode {
		private ArrayList<Integer> keys;
		private ArrayList<ABTreeNode> children;

		public ABTreeInnerNode(ArrayList<Integer> keys, ArrayList<ABTreeNode> children) {
			this.keys = keys;
			this.children = children;
		}

		public ABTreeInnerNode(int key, ABTreeNode left, ABTreeNode right) {
			keys = new ArrayList<Integer>();
			children = new ArrayList<ABTree.ABTreeNode>();
			keys.add(key);
			children.add(left);
			children.add(right);
		}

		public ABTreeInnerNode(int key) {
			this(key, new ABTreeLeaf(), new ABTreeLeaf());
		}

		@Override
		public ABTreeNode insert(int key, boolean above) {
			if(above==true) {
				ABTreeInnerNode abtn=(ABTreeInnerNode)this.insert(key, false);
				return mergeUp(abtn, true);
			}

			if(children.size()==0) {
				keys.add(key);
				children.add(new ABTreeLeaf());
				children.add(new ABTreeLeaf());
				return null;
			}
			if(children.get(0) instanceof ABTreeLeaf) {
				sortedKeyInsert(key);
				children.add(new ABTreeLeaf());
				if(children.size()>b)
					return this;
				return null;
			} else {
				ABTreeInnerNode abtn=null;
				if(key>keys.get(keys.size()-1))
					abtn=(ABTreeInnerNode)children.get(children.size()-1).insert(key, false);
				else
					for(int i=0; i<keys.size(); i++)
						if(key<keys.get(i)) {
							abtn=(ABTreeInnerNode)children.get(i).insert(key, false);
							break;
						}
				mergeUp(abtn, false);
				if(children.size()>b)
					return this;
			}
			return null;
		}

		/* inserts the key into the lower level, if the level is too big, split it up and merge it */

		public ABTreeNode mergeUp(ABTreeInnerNode abtn, boolean top) {
			if(abtn==null)
				return null;
			/* if abtn is too big, split it and give back the middle key */
			int mid=abtn.getMiddle();
			if(top==true) {
				ArrayList<ABTreeNode> sl=abtn.deleteMiddle();
				ABTreeInnerNode newroot=new ABTreeInnerNode(mid, sl.get(0), sl.get(1));
				return newroot;
			}
			int mp=sortedKeyInsert(abtn.getMiddle());
			ArrayList<ABTreeNode> sl=abtn.deleteMiddle();
			children.set(mp, sl.get(0));
			children.add(mp+1, sl.get(1));
			return null;
		}

		/* returns: the position at which key was inserted in keys */

		private int sortedKeyInsert(int key) {
			int i;
			for(i=0; i<keys.size(); i++)
				if(keys.get(i)>key) {
					keys.add(i, key);
					return i;
				}
			if(key>keys.get(keys.size()-1))
				keys.add(key);
			return keys.size()-1;
		}

		/* delete the middle, return the resulting two lists as two separate ABTreeInnerNodes */

		public ArrayList<ABTreeNode> deleteMiddle() {
			ArrayList<ABTreeNode> res=new ArrayList<ABTreeNode>();
			int mid=(keys.size()/2);
			res.add(new ABTreeInnerNode(new ArrayList<Integer>(keys.subList(0, mid)),
						    new ArrayList<ABTreeNode>(children.subList(0, mid+1))));
			res.add(new ABTreeInnerNode(new ArrayList<Integer>(keys.subList(mid+1, keys.size())),
						    new ArrayList<ABTreeNode>(children.subList(mid+1, children.size()))));
			return res;
		}

		public int getMiddle() {
			return keys.get((keys.size())/2);
		}

		@Override
		public boolean canSteal() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public boolean find(int key) {
			if(children.size()==0)
				return false;
			for(int i=0; i<keys.size(); i++)
				if(keys.get(i)==key)
					return true;
				else if(keys.get(i)>key)
					return children.get(i).find(key);
			return children.get(children.size()-1).find(key);
		}

		public boolean remove(int key) {
			int i;
			for(i=0; i<keys.size(); i++)
				if(keys.get(i)>=key)
					break;
			if(keys.get(i)!=key)
				children.get(i).remove(key);

			System.err.println("found the key at " + keys);

			return true;
		}

		@Override
		public int height() {
			if(children.size()==0)
				return 0;
			return 1+children.get(0).height();
		}

		@Override
		public Integer min() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public Integer max() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		/* if lb/ub is null, that means that the upper bound is (minus) infinity */

		@Override
		public boolean validAB(boolean root, Integer lb, Integer ub) {
			/* the non-roots have a<=children.size()<=b */
			if(root==false)
				if(children.size()<a)
					return false;

			if(children.size()>b)
				return false;

			/* children have the same height */

			int hgt=children.get(0).height();
			for(int i=0; i<children.size(); i++) {
				int h=children.get(i).height();
				if(h!=hgt)
					return false;
			}

			/* keys are sorted */

			for(int i=0; i<keys.size()-1; i++)
				if(keys.get(i)>=keys.get(i+1))
					return false;

			/* check whether the children fit in */

			if(lb!=null)
				if(keys.get(0)<=lb)
					return false;
			if(ub!=null)
				if(keys.get(keys.size()-1)>=ub)
					return false;

			/* check the validity for every lower tree */

			for(int i=0; i<keys.size(); i++) {
				if(i==0) {
					if(children.get(i).validAB(false, null, keys.get(0))==false)
						return false;
				} else {
					if(children.get(i).validAB(false, keys.get(i-1), keys.get(i))==false)
						return false;
				}
			}

			if(children.get(keys.size()).validAB(false, keys.get(keys.size()-1), null)==false)
				return false;

			return true;
		}

		@Override
		public int dot(StringBuilder sb, int from) {
			int mine = from++;
			sb.append(String.format("\tstruct%s [label=\"", mine));
			for (int i = 0; i < 2 * keys.size() + 1; i++) {
				if (i > 0)
					sb.append("|");
				sb.append(String.format("<f%d> ", i));
				if (i % 2 == 1)
					sb.append(keys.get(i / 2));
			}
			sb.append("\"];\n");
			for (int i = 0; i < children.size(); i++) {
				int field = 2 * i;
				sb.append(String.format("\tstruct%d:<f%d> -> struct%d;\n", mine, field, from));
				from = children.get(i).dot(sb, from);
			}
			return from;
		}
	}

	/**
	 * Diese Klasse repräsentiert ein Blatt des Baumes.
	 */
	private class ABTreeLeaf extends ABTreeNode {
		@Override
		public ABTreeNode insert(int key, boolean above) {
			throw new RuntimeException("insert() called on ABTreeLeaf, not good");
		}

		@Override
		public boolean canSteal() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public boolean find(int key) {
			return false;
		}

		@Override
		public boolean remove(int key) {
			throw new RuntimeException("remove() called on ABTreeLeaf, not good");
		}

		@Override
		public int height() {
			return 0;
		}

		@Override
		public Integer min() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public Integer max() {
			// TODO
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public boolean validAB(boolean root, Integer lb, Integer ub) {
			return true;
		}

		@Override
		public int dot(StringBuilder sb, int from) {
			sb.append(String.format("\tstruct%d [label=leaf, shape=ellipse];\n", from));
			return from + 1;
		}
	}

	public ABTree(int a, int b) {
		this.a=a;
		this.b=b;
	}

	private ABTreeInnerNode root = null;

	public boolean validAB() {
		if(root!=null)
			return root.validAB(true, null, null);
		return true;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Baumes.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {
		return root.height();
	}

	/**
	 * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
	 *
	 * @param key der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		return root.find(key);
	}

	/**
	 * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		ABTreeInnerNode nn=null;
		if(root==null)
			root=new ABTreeInnerNode(key);
		if(root.find(key)==false)
			nn=(ABTreeInnerNode)root.insert(key, true);
		if(nn!=null)
			root=nn;
	}

	/**
	 * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
	 *
	 * @param key der zu löschende Schlüssel
	 * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
	 */
	public boolean remove(int key) {
		if(!root.find(key))
			return false;
		return root.remove(key);
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		sb.append("\tnode [shape=record];\n");
		if (root != null)
			root.dot(sb, 0);
		sb.append("}");
		return sb.toString();
	}
}

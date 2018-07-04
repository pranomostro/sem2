import java.util.ArrayList;

public class Graph {
	public class Node {
		private Integer id;
		private ArrayList<Integer> adj;

		public Node(Integer i) {
			id=i;
			adj=new ArrayList<Integer>();
		}

		public Integer getId() {
			return id;
		}

		public void addEdge(Integer id) {
			if(adj.contains(id))
				return;
			adj.add(id);
		}

		public ArrayList<Integer> getEdges() {
			return adj;
		}
	}

	private ArrayList<Node> nds;

	public Graph() {
		nds=new ArrayList<Node>();
	}

	/**
	 * Erstellt einen neuen Knoten, und gibt den Index dieses Knotens zurück.
	 * Der erste erstellte Knoten sollte Index 0 haben.
	 * Knoten, die direkt nacheinander erstellt werden, sollten aufsteigende Indices haben.
	 */
	public Integer addNode() {
		nds.add(new Node(nds.size()));
		return nds.size()-1;
	}

	public Integer nodesSize() {
		return nds.size();
	}

	/**
	 * Liefert den Knoten zum angegebenen Index zurück
	 */
	public Graph.Node getNode(Integer id) {
		return nds.get(id);
	}

	/**
	 * Fügt zwischen den beiden angegebenen Knoten eine (ungerichtete) Kante hinzu.
	 */
	public void addEdge(Graph.Node a, Graph.Node b) {
		a.addEdge(b.getId());
		b.addEdge(a.getId());
	}
}

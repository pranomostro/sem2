public class ConnectedComponents {
	public ConnectedComponents() {
	}

	/**
	 * Zählt alle Zusammenhangskomponenten im gegebenen Graphen g
	 */
	public int countConnectedComponents(Graph g) {
		int np, c;
		BFS search=new BFS(g);

		c=np=0;

		while(search.nullAt()>=0) {
			search.sssp(g.getNode(np));
			np=search.nullAt();
			c++;
		}

		return c;
	}
}

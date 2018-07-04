import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;

public class BFS {
	private Integer[] p, d;
	private Graph g;

	public BFS(Graph graph) {
		g=graph;
		p=new Integer[g.nodesSize()];
		d=new Integer[g.nodesSize()];
	}

	/**
	 * Führt eine Breitensuche vom Startknoten "start" aus, um das SSSP-Problem zu lösen.
	 */
	public void sssp(Graph.Node start) {
		Graph.Node c;
		ArrayList<Integer> n;

		d[start.getId()]=0;
		p[start.getId()]=start.getId();

		/* dfs: further queue/deque f for the finish number */
		ArrayDeque<Graph.Node> q=new ArrayDeque<Graph.Node>();

		q.add(start);

		/* dfs: while q is non-empty, push all neighbours of  the
		first element of q that are neither in q nor in f to
		the front of q
		if this pushes no elements, pop the first element of
		q and push it to f
		if the first element in q is not yet visited, set
		dfsnum[q.id] to dfscount and increase dfs_count*/

		while(q.isEmpty()==false) {
			c=q.pollFirst();
			n=c.getEdges();
			for(Integer i: n) {
				if(p[i]==null) {
					q.addLast(g.getNode(i));
					d[i]=d[c.getId()]+1;
					p[i]=c.getId();
				}
			}
		}
	}

	public int nullAt() {
		for(int i=0; i<p.length; i++)
			if(p[i]==null)
				return i;
		return -1;
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion die Tiefe des Knotens n
	 * vom Startknoten überprüft werden.
	 */
	public Integer getDepth(Graph.Node n) {
		return d[n.getId()];
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion der Vaterknoten
	 * des Knotens n in Richtung Startknoten abgefragt werden.
	 */
	public Graph.Node getParent(Graph.Node n) {
		return g.getNode(p[n.getId()]);
	}
}

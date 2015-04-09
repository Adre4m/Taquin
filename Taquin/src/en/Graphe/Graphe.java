package en.Graphe;

import java.util.ArrayList;
import java.util.Iterator;

public class Graphe {
	private ArrayList<Node> nodes;

	public Graphe(Node initial) {
		nodes = new ArrayList<Node>();
		nodes.add(initial);
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public Iterator<Node> vertices() {
		return nodes.iterator();
	}

}

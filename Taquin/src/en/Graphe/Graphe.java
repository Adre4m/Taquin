package en.Graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Graphe {

	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.compareTo(o2);
		}
	}

	private ArrayList<Node> nodes;

	int nbMove = -1;

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

	public boolean contains(Object obj) {
		if (!(obj instanceof Node))
			return false;
		else {
			Node n = (Node) obj;
			for (int index = 0; index < nodes.size(); ++index) {
				if (nodes.get(index).equals(n)) {
					if (nodes.get(index).compareTo(n) > 0) {
						nodes.remove(index);
						return false;
					} else
						return true;
				}
			}
			return false;
		}
	}

	public boolean add(Node father, Node son) {
		if (!contains(son)) {
			son.setFather(father);
			/*
			 * if (father != null) father.add(son);
			 */
			nodes.add(son);
			return true;
		}
		return false;
	}

	public Node deepSearch() {
		if (!nodes.isEmpty())
			return deepSearch(nodes.get(0));
		else
			return null;
	}

	private Node deepSearch(Node start) {
		return visit(start);
	}

	public Node searchBF() {
		return searchBF(nodes);
	}

	private Node searchBF(ArrayList<Node> next) {
		nbMove++;
		Collections.sort(next, new NodeComparator());
		if (next == null)
			next = new ArrayList<Node>();
		Iterator<Node> it = next.iterator();
		Node n = it.next();
		while (!n.win() && it.hasNext())
			n = it.next();
		if (n != null && n.win())
			return n;
		return searchBF(growBF(next));
	}

	public Node searchAStar() {
		return searchAStar(nodes);
	}

	private Node searchAStar(ArrayList<Node> o) {
		if (o.isEmpty())
			return null;
		else {
			Node n = selectMin(o);
			if (n.win())
				return n;
			return searchAStar(grow(n));
		}
	}

	private ArrayList<Node> grow(Node toGrow) {
		ArrayList<Node> toCheck = new ArrayList<Node>();
		ArrayList<String> moves = toGrow.possibleMoves();
		Iterator<String> it = moves.iterator();
		while (it.hasNext()) {
			Node son = toGrow.makeMove(it.next());
			if (add(toGrow, son))
				toCheck.add(son);
		}
		return toCheck;
	}

	private ArrayList<Node> growBF(ArrayList<Node> toGrow) {
		ArrayList<Node> toCheck = new ArrayList<Node>();
		for (int i = 0; i < toGrow.size(); ++i)
			toCheck.addAll(grow(toGrow.get(i)));
		return toCheck;
	}

	private Node visit(Node u) {
		nbMove++;
		if (nbMove % 10 == 0) {
			ArrayList<Node> newNodes = new ArrayList<Node>();
			newNodes.add(u);
			this.setNodes(newNodes);
		}
		if (u.win())
			return u;
		u.setState("reach");
		ArrayList<Node> next = grow(u);
		Collections.sort(next, new NodeComparator());
		Iterator<Node> it = next.iterator();
		while (it.hasNext()) {
			Node v = it.next();
			if (v.win())
				return v;
			else if (v.getState().equals("unreach"))
				return visit(v);
		}
		u.setState("ok");
		return u;
	}

	private Node selectMin(ArrayList<Node> toCheck) {
		Collections.sort(toCheck, new NodeComparator());
		return toCheck.get(0);
	}

	@Override
	public String toString() {
		return nodes.toString();
	}
}

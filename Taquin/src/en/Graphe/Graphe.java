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
			if (father != null)
				father.add(son);
			nodes.add(son);
			son.setFather(father);
			return true;
		}
		return false;
	}

	public Node deepSearch() {
		if (!nodes.isEmpty()) {
			Node res = deepSearch(nodes.get(0));
			if (res != null && res.win())
				return res;
		}
		return null;
	}

	private Node deepSearch(Node start) {
		return visit(start);
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

	private void growBF(ArrayList<Node> toGrow) {
		Iterator<Node> it = toGrow.iterator();
		ArrayList<Node> toAdd = new ArrayList<Node>();
		ArrayList<Node> toDel = new ArrayList<Node>();
		while (it.hasNext()) {
			Node node = it.next();
			ArrayList<String> possibleMoves = node.possibleMoves();
			Iterator<String> moves = possibleMoves.iterator();
			while (moves.hasNext()) {
				String dir = moves.next();
				Node other = node.makeMove(dir);
				add(node, other);
				toDel.add(node);
				toAdd.add(other);
			}
		}
		toAdd.removeAll(toDel);
		toGrow.removeAll(toDel);
		toGrow.addAll(toAdd);
	}

	private Node visit(Node u) {
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

	public Node selectMin(ArrayList<Node> toCheck) {
		Collections.sort(toCheck, new NodeComparator());
		return toCheck.get(0);
	}

	@Override
	public String toString() {
		return nodes.toString();
	}
}

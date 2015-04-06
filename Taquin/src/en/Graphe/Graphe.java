package en.Graphe;

import java.util.ArrayList;
import java.util.Iterator;

import en.Game.Taquin;

public class Graphe {

	private ArrayList<Node> graphe;
	int nbMove = -1;

	public Graphe() {
		graphe = new ArrayList<Node>();
	}

	public Graphe(ArrayList<Node> graphe) {
		this.graphe = graphe;
	}

	public Graphe(Node n) {
		graphe = new ArrayList<Node>();
		addNode(null, n);
	}

	public Graphe(Taquin t) {
		Node n = new Node(t.getGame());
		graphe = new ArrayList<Node>();
		addNode(null, n);
	}

	public boolean contains(Object o) {
		if (!(o instanceof Node))
			return false;
		else {
			Node node = (Node) o;
			Iterator<Node> it = graphe.iterator();
			while (it.hasNext()) {
				if (node.equals(it.next()))
					return true;
			}
			return false;
		}
	}

	public boolean isEmpty() {
		return graphe.isEmpty();
	}

	public ArrayList<Node> grow(Node toGrow) {
		ArrayList<Node> next = new ArrayList<Node>();
		ArrayList<String> moves = toGrow.possibleMoves();
		if (!moves.isEmpty()) {
			Iterator<String> it = moves.iterator();
			int cpt = 4;
			while (it.hasNext() && cpt > 1) {
				Node toAdd = toGrow.makeMove(it.next());
				addNode(toGrow, toAdd);
				next.add(toAdd);
				cpt--;
			}
		}
		return next;
	}
	
	public void growBF(ArrayList<Node> toGrow) {
		Iterator<Node> it = toGrow.iterator();
		ArrayList<Node> toAdd = new ArrayList<Node>();
		ArrayList<Node> toDel = new ArrayList<Node>();
		while (it.hasNext()) {
			Node node = it.next();
			ArrayList<String> possibleMoves = node.possibleMoves();
			Iterator<String> moves = possibleMoves.iterator();
			while (moves.hasNext()) {
				String dir = moves.next();
				Node other = new Node(node.makeMove(dir));
				addNode(node, other);
				toDel.add(node);
				toAdd.add(other);
			}
		}
		toAdd.removeAll(toDel);
		toGrow.removeAll(toDel);
		toGrow.addAll(toAdd);
	}

	public ArrayList<Node> getGraphe() {
		return graphe;
	}

	public void setGraphe(ArrayList<Node> graphe) {
		this.graphe = graphe;
	}

	public boolean addNode(Node father, Node son) {
		if (!contains(son)) {
			son.setFather(father);
			return graphe.add(son);
		} else
			return false;

	}

	public String toString() {
		if (graphe.isEmpty())
			return "Empty graphe";
		else {
			String s = "";
			Iterator<Node> it = graphe.iterator();
			while (it.hasNext())
				s += it.next().toString();
			return s;
		}
	}

	public int length() {
		return graphe.size();
	}

	public Node searchAStar(ArrayList<Node> next) {
		nbMove++;
		if (next.isEmpty())
			return null;
		else {
			Iterator<Node> it = next.iterator();
			Node n = it.next();
			n.setProcess("reach");
			while (it.hasNext() && !n.win()) {
				n = it.next();
				n.setProcess("reach");
			}
			if (n.win())
				return n;
			else {
				next = grow(selectMin());
				return searchAStar(next);
			}
		}
	}

	public Node searchBF(ArrayList<Node> next) {
		nbMove++;
		if (next == null)
			next = new ArrayList<Node>();
		Iterator<Node> it = next.iterator();
		Node n = it.next();
		while (!n.win() && it.hasNext())
			n = it.next();
		if (n != null && n.win())
			return n;
		//growBF(next);
		return searchBF(next);
	}

	public Node selectMin() {
		if (graphe.isEmpty())
			return null;
		else {
			Iterator<Node> it = graphe.iterator();
			Node node = it.next();
			node.setProcess("processed");
			while (it.hasNext()) {
				Node tmp = it.next();
				if (!tmp.getProcess().equals("processed")) {
					if (node.f() > tmp.f()) {
						node = tmp;
					}
				}
			}
			node.setProcess("processed");
			return node;
		}
	}
}

package en.Graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import en.Game.Taquin;

public class Graphe {

	private Set<Node> graphe;

	public Graphe() {
		graphe = new HashSet<Node>();
	}

	public Graphe(Node origin) {
		graphe = new HashSet<Node>();
		addNode(null, origin);
	}

	public Graphe(Set<Node> graphe) {
		this.graphe = graphe;
	}

	public Graphe(Taquin t) {
		Node n = new Node(t.getGame());
		graphe = new HashSet<Node>();
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

	public ArrayList<Node> grow(Node toGrow) {
		ArrayList<Node> next = new ArrayList<Node>();
		ArrayList<String> possibleMoves = toGrow.possibleMoves();
		Iterator<String> moves = possibleMoves.iterator();
		while (moves.hasNext()) {
			String dir = moves.next();
			Node n = new Node(toGrow.makeMove(dir));
			addNode(toGrow, n);
			next.add(n);
		}
		return next;
	}

	public Set<Node> getGraphe() {
		return graphe;
	}

	public void setGraphe(Set<Node> graphe) {
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
		Iterator<Node> it = graphe.iterator();
		String s = "";
		while (it.hasNext())
			s += it.next().toString() + "\n";
		return s;
	}

	public int length() {
		return graphe.size();
	}

	public Node search(ArrayList<Node> next) {
		if (next == null)
			next = new ArrayList<Node>();
		Iterator<Node> it = next.iterator();
		Node n = null;
		boolean win = false;
		while (!win && it.hasNext()) {
			n = it.next();
			win = n.win();
		}
		if (n != null && win)
			return n;
		return search(grow(weight(next)));
	}

	public Node weight(ArrayList<Node> toCheck) {
		Iterator<Node> it = toCheck.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			int[][] victory = n.getVictory();
			for (int i = 0; i < victory.length; ++i)
				for (int j = 0; j < victory[0].length; ++j)
					if (n.getState()[i][j] != victory[i][j])
						n.setWeight(n.getWeight() + 1);
		}
		return min(toCheck);
	}

	public Node min(ArrayList<Node> toCheck) {
		Iterator<Node> it = toCheck.iterator();
		Node n = it.next();
		while (it.hasNext()) {
			Node comp = it.next();
			if (n.getWeight() > comp.getWeight())
				n = comp;
		}
		return n;
	}

	public boolean isEmpty() {
		return graphe.isEmpty();
	}
}

package en.Graphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graphe {

	private Set<Node> graphe;

	public Graphe() {
		graphe = new HashSet<Node>();
	}

	public Graphe(Set<Node> graphe) {
		this.graphe = graphe;
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

	public void grow(ArrayList<Node> toGrow) {
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
		//System.out.println(toGrow);
		toAdd.removeAll(toDel);
		toGrow.removeAll(toDel);
		//System.out.println(toGrow);
		toGrow.addAll(toAdd);
		//System.out.println(toGrow);
		/*
		 * Iterator<Node> it = graphe.iterator(); ArrayList<Node> toGrow = new
		 * ArrayList<Node>(); while (it.hasNext()) { Node node = it.next(); if
		 * (!node.isHasSons()) { toGrow.add(node); node.setHasSons(true); } } it
		 * = toGrow.iterator(); while (it.hasNext()) { Node node = it.next();
		 * ArrayList<String> possibleMoves = node.possibleMoves();
		 * Iterator<String> moves = possibleMoves.iterator(); while
		 * (moves.hasNext()) { String dir = moves.next(); Node other = new
		 * Node(node.makeMove(dir)); addNode(node, other); f.add(other); } }
		 */
	}

	public Set<Node> getGraphe() {
		return graphe;
	}

	public void setGraphe(Set<Node> graphe) {
		this.graphe = graphe;
	}

	public boolean addNode(Node father, Node son) {
		/*if (!contains(son)) {*/
			son.setFather(father);
			return graphe.add(son);
		/*} else
			return false;*/
	}

	public String toString() {
		return graphe.toString();
	}

	public int length() {
		return graphe.size();
	}

	public Node search(ArrayList<Node> next) {
		if (next == null)
			next = new ArrayList<Node>();
		Iterator<Node> it = next.iterator();
		Node n = it.next();
		while (!n.win() && it.hasNext())
			n = it.next();
		if (n != null && n.win())
			return n;
		//System.out.println(next);
		grow(next);
		//System.out.println(this.graphe);
		return search(next);
	}
}

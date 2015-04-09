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
			for (int i = 0; i < graphe.size(); ++i) {
				Node other = graphe.get(i);
				if (node.equals(other)) {
					if (node.f() > other.f()) {
						graphe.remove(i);
						return false;
					}
					return true;
				}
			}
			return false;
		}
	}

	public boolean isEmpty() {
		return graphe.isEmpty();
	}

	public ArrayList<Node> growAStar(Node toGrow) {
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
		System.out.println(this);
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
			if (father != null)
				father.getSons().add(son);
			son.setFather(father);
			return graphe.add(son);
		} else
			return false;

	}

	public String toString() {
		if (graphe.isEmpty())
			return "Empty graphe";
		else {
			/*String s = "";
			Iterator<Node> it = graphe.iterator();
			while (it.hasNext())
				s += it.next().toString();
			return s;*/
			return "----DEBUT GRAPHE----\n\n" + graphe.toString() + "\n\n----FIN GRAPHE----";
		}
	}

	public int length() {
		return graphe.size();
	}

	public Node searchAStar(ArrayList<Node> o) {
		nbMove++;
		if (o.isEmpty())
			return null;
		else {
			Iterator<Node> it = o.iterator();
			Node n = it.next();
			n.setProcess("reach");
			while (it.hasNext() && !n.win()) {
				n = it.next();
				n.setProcess("reach");
			}
			if (n.win())
				return n;
			else {
				o = growAStar(selectMin(o));
				return searchAStar(o);
			}
		}

	}

	public Node searchBF(ArrayList<Node> next) {
		nbMove++;
		if (next == null)
			next = new ArrayList<Node>();
		Iterator<Node> it = next.iterator();
		Node n = it.next();
		while (it.hasNext() && !n.win())
			n = it.next();
		if (n != null && n.win())
			return n;
		growBF(next);
		return searchBF(next);
	}

	public Node searchDeep(ArrayList<Node> next) {
		for (Node u : graphe) {
			if (u.getProcess().equals("unreach")) {
				Node res = visit(3, u);
				// if (res.win())
				return res;
			}
		}
		return null;
	}

	public Node visit(int i, Node u) {
		if (i > 0) {
			// System.out.println(u);
			u.setProcess("reach");
			if (u.win())
				return u;
			ArrayList<Node> next = growAStar(u);
			for (Node v : next) {
				if (v.getProcess().equals("unreach"))
					visit(--i, v);
			}
			u.setProcess("processed");
			return u;
		} else
			return u;
	}

	public Node selectMin(ArrayList<Node> o) {
		if (o.isEmpty())
			return null;
		else {
			Iterator<Node> it = o.iterator();
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

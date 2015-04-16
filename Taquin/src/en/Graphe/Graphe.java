package en.Graphe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import en.Game.Taquin;

public class Graphe {

	Taquin game;
	Collection<Node> toDo;
	Map<String, Node> signed;

	public Graphe(Taquin game, Collection<Node> toDo, Map<String, Node> signed) {
		this.game = game;
		this.toDo = toDo;
		this.signed = signed;
	}

	public Node search() {
		if (toDo.isEmpty())
			toDo.add(new Node(game));
		do {
			// TODO -> Coder nos propres List. Remplacer iterator().next()
			// par juste next(), et retirer toDo.remove(n);
			Node n = toDo.iterator().next();
			toDo.remove(n);
			// En théorie il n'y a pas besoin de cette condition
			// puisque vérifié dans grow.
			/*
			 * if (!signed.containsKey(n.getGame())) signed.put(n.getGame(), n);
			 */
			if (n.win())
				return n;
			else
				toDo.addAll(grow(n));
		} while (!toDo.isEmpty());
		return null;
	}

	public Collection<Node> grow(Node n) {
		ArrayList<String> dir = n.possibleMoves();
		Iterator<String> it = dir.iterator();
		ArrayList<Node> res = new ArrayList<Node>();
		while (it.hasNext()) {
			Node toAdd = n.makeMove(it.next());
			if (!signed.containsKey(toAdd.getGame()))
				res.add(toAdd);
			else if (toAdd.f() > signed.get(toAdd.getGame()).f()) {
				signed.remove(toAdd.getGame());
				res.add(toAdd);
			}
		}
		return res;
	}
}
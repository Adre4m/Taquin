package en.Graphe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import en.Game.Taquin;
import en.List.List;
import en.List.Tas;

public class Graphe {

	Taquin game;
	List toDo;
	Map<String, Node> signed;

	public Graphe(Taquin game, List toDo, Map<String, Node> signed) {
		this.game = game;
		this.toDo = toDo;
		this.signed = signed;
	}

	public Node search() {
		if (toDo.isEmpty())
			toDo.add(new Node(game));
		do {
			Node n = toDo.next();
			if (n.win())
				return n;
			else
				toDo.addAll(grow(n));
		} while (!toDo.isEmpty());
		return null;
	}

	public List grow(Node n) {
		ArrayList<String> dir = n.possibleMoves();
		Iterator<String> it = dir.iterator();
		List res = new Tas();
		while (it.hasNext()) {
			Node toAdd = n.makeMove(it.next());
			toAdd.setFather(n);
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
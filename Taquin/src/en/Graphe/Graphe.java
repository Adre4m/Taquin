package en.Graphe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import en.Game.Taquin;
import en.List.Fifo;
import en.List.List;

/**
 * <h2></h2>
 * 
 * @author
 *
 */
public class Graphe {

	Taquin game;
	List toDo;
	Map<String, Node> signed;

	/**
	 * <h2>Initalisation du graphe.</h2>
	 * 
	 * @author
	 * @param game
	 *            la position initiale du jeu.
	 * @param toDo
	 *            Une liste des elements a traiter.
	 * @param signed
	 *            Une mpa contenant les elements deja traites.
	 * 
	 *            {@code}
	 *            <p>
	 *            Le type de toDo determinera le type de parcours. En effet une
	 *            pile induira un parcours profondeur, une file un largeur, un
	 *            tas un parcours utilisant l'heuristique.
	 *            </p>
	 */
	public Graphe(Taquin game, List toDo, Map<String, Node> signed) {
		this.game = game;
		this.toDo = toDo;
		this.signed = signed;
	}

	/**
	 * <h2>Parcours</h2>
	 * 
	 * @author
	 * @param delai
	 *            le temps maximal d'execution defini par l'utilisateur
	 * @return le noeud gagnant s'il existe, et si le temps maximum defini par
	 *         delai n'est pas depasser. Sinon la valeur renvoyee sera null.
	 * 
	 *         {@code}
	 *         <p>
	 *         Ce parcours va fonctionner de la facon suivante. on va toujours
	 *         recupperer le premier element (Attention : cela depends du type
	 *         de liste utilisee). Apres analyse du noeud, la methode va
	 *         agrandir le graphe.
	 *         </p>
	 */
	public Node search(long delai) {
		long tempsD = System.currentTimeMillis();
		double tempsExec = 0;
		if (delai >= 0) {
			if (toDo.isEmpty())
				toDo.add(new Node(game));
			do {
				tempsExec = (System.currentTimeMillis() - tempsD) / 1000;
				if (delai > 0 && tempsExec > delai)
					return null;
				else {
					Node n = toDo.next();
					if (n.win())
						return n;
					else
						toDo.addAll(grow(n));
				}
			} while (!toDo.isEmpty());
			return null;
		} else
			return null;
	}

	/**
	 * <h2></h2>
	 * 
	 * @author
	 * @param n
	 *            le noeud a partir duquel faire l'agrandissement.
	 * @return une liste avec les noeuds crees.
	 * 
	 *         {@code}
	 *         <p>
	 *         Cette methode va creer des noeuds a partir des mouvements
	 *         disponibles depuis n. Elle va egalement servir d'elagage.
	 *         </p>
	 */
	public List grow(Node n) {
		ArrayList<String> dir = n.possibleMoves();
		Iterator<String> it = dir.iterator();
		List res = new Fifo();
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
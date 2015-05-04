package en.List;

import en.Graphe.Node;

/**
 * <h2>Interface des Listes</h2>
 * 
 * @author
 *
 */
public interface List {

	/**
	 * <h2></h2>
	 * 
	 * @author
	 * @return
	 * 
	 *         {@code}
	 */
	public Node next();

	/**
	 * <h2>Ajout d'une liste</h2>
	 * 
	 * @author
	 * @param l
	 *            liste a ajouter
	 * 
	 */
	public boolean addAll(List l);

	/**
	 * <h2>Ajout d'un element</h2>
	 * 
	 * @param n
	 *            noeud a ajouter
	 * 
	 */
	public boolean add(Node n);

	/**
	 * <h2>Est vide</h2>
	 * 
	 * @author
	 * @return true si vide, false sinon.
	 * 
	 */
	public boolean isEmpty();

	public String toString();
}

package en.List;

import en.Graphe.Node;

/**
 * <h2>Maillon</h2>
 * 
 * @author
 *
 */
public class Maillon {

	private Node info;
	private Maillon next;

	/**
	 * <h2>Constructeur vide</h2>
	 * 
	 * @author
	 */
	public Maillon() {
		info = null;
		next = null;
	}

	/**
	 * <h2>Constructeur avec position.</h2>
	 * 
	 * @author
	 * @param info
	 *            position.
	 */
	public Maillon(Node info) {
		this.info = info;
		next = null;
	}

	/**
	 * <h2>Constructeur avec position et suivant.</h2>
	 * 
	 * @author
	 * @param info
	 *            position.
	 * @param next
	 *            suivant.
	 */
	public Maillon(Node info, Maillon next) {
		this.info = info;
		this.next = next;
	}

	public Node getInfo() {
		return info;
	}

	public void setInfo(Node info) {
		this.info = info;
	}

	public Maillon getNext() {
		return next;
	}

	public void setNext(Maillon next) {
		this.next = next;
	}

	public String toString() {
		return toString(this);
	}

	/**
	 * <h2>Methode toString</h2>
	 * 
	 * @author
	 * @param m
	 *            le maillon encours
	 * @return va afficher recursivement les positions des noeuds.
	 */
	private String toString(Maillon m) {
		if (m == null)
			return "";
		else {
			return m.info + "\n" + toString(m.getNext());
		}
	}

}

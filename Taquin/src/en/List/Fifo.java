package en.List;

import en.Graphe.Node;

/**
 * <h2>File</h2>
 * 
 * @author Antoine LeHenaff
 *
 */
public class Fifo implements List {

	private Maillon first;
	private Maillon last;

	/**
	 * <h2>Ajout d'une liste complette a this</h2>
	 * 
	 * @author Antoine LeHenaff
	 * @param l
	 *            la liste a ajouter
	 * 
	 *            {@code}
	 *            <p>
	 *            Va ajouter successivement les elements de la liste l a this.
	 *            </p>
	 */
	@Override
	public boolean addAll(List l) {
		while (!l.isEmpty())
			add(l.next());
		return true;
	}

	/**
	 * <h2>isEmpty</h2>
	 * 
	 * @author Antoine LeHenaff
	 * @return true si la liste est vide, false sinon
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * <h2>Prochain element de la file</h2>
	 * 
	 * @author Antoine LeHenaff
	 * @return le noeud en tete {@code}
	 *         <p>
	 *         Cette methode va non seulement renvoyer le noeud en tete, mais
	 *         egalement supprmier celle ci de la liste.
	 *         </p>
	 */
	@Override
	public Node next() {
		if (isEmpty()) {
			return null;
		} else {
			Node n = first.getInfo();
			first = first.getNext();
			return n;
			// }
		}
	}

	/**
	 * <h2>Methode d'ajout d'un noeud</h2>
	 * 
	 * @author Antoine LeHenaff
	 * 
	 *         {@code}
	 *         <p>
	 *         Va ajouter un element en fin de liste.
	 *         </p>
	 */
	@Override
	public boolean add(Node n) {
		Maillon m = new Maillon(n);
		if (isEmpty()) {
			first = m;
			last = new Maillon();
			first.setNext(last);
			return true;
		} else if (last.getInfo() == null) {
			last.setInfo(n);
			return true;
		} else {
			last.setNext(m);
			last = m;
			return true;
		}
	}

	public Maillon getFirst() {
		return first;
	}

	public void setFirst(Maillon first) {
		this.first = first;
	}

	/**
	 * <h2>Methode toString</h2>
	 * 
	 * @author Antoine LeHenaff
	 */
	public String toString() {
		return first.toString();
	}
}

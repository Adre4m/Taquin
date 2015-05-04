package en.List;

import en.Graphe.Node;

/**
 * <h2></h2>
 * 
 * @author
 *
 */

public class Tas implements List {

	private Maillon first;

	/**
	 * <h2>Ajout d'une liste complette a this</h2>
	 * 
	 * @author
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
		return false;
	}

	/**
	 * <h2>isEmpty</h2>
	 * 
	 * @author
	 * @return true si la liste est vide, false sinon
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * <h2>Prochain element du tas</h2>
	 * 
	 * @author
	 * @return le noeud en tete {@code}
	 *         <p>
	 *         Cette methode va non seulement renvoyer le noeud en tete, mais
	 *         egalement supprmier celle ci de la liste.
	 *         </p>
	 */
	@Override
	public Node next() {
		if (isEmpty())
			return null;
		else {
			Node res = first.getInfo();
			first = first.getNext();
			return res;
		}
	}

	/**
	 * <h2>Methode d'ajout d'un noeud</h2>
	 * 
	 * @author
	 * 
	 *         {@code}
	 *         <p>
	 *         Va ajouter un element a sa position, gardant la liste this
	 *         toujours triee.
	 *         </p>
	 */
	@Override
	public boolean add(Node n) {
		if (isEmpty()) {
			first = new Maillon(n, null);
			return true;
		} else {
			if (first.getInfo().compareTo(n) >= 0) {
				Maillon other = new Maillon(n, first);
				first = other;
				return true;
			}
			for (Maillon m = first; m != null; m = m.getNext()) {
				if (m.getInfo().compareTo(n) >= 0) {
					Maillon other = new Maillon(n, m.getNext());
					m.setNext(other);
					;
					return true;
				} else if (m.getNext() == null) {
					m.setNext(new Maillon(n));
					return true;
				}
			}
			return true;
		}
	}

	/**
	 * <h2>Methode toString</h2>
	 * 
	 * @author
	 */
	public String toString() {
		return first.toString();
	}
}

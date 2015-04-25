package en.List;

import en.Graphe.Node;

public class Tas implements List {

	private Maillon first;

	@Override
	public boolean addAll(List l) {
		while (!l.isEmpty())
			add(l.next());
		return false;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

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

	public String toString() {
		return first.toString();
	}
}

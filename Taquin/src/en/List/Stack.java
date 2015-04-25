package en.List;

import en.Graphe.Node;

public class Stack implements List {

	private Maillon first;

	@Override
	public boolean addAll(List l) {
		while (!l.isEmpty())
			add(l.next());
		return true;
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
		Maillon other = new Maillon(n, first);
		first = other;
		return true;
	}

	public Maillon getFirst() {
		return first;
	}

	public void setFirst(Maillon first) {
		this.first = first;
	}

	public String toString() {
		return first.toString();
	}
}

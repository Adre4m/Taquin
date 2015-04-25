package en.List;

import en.Graphe.Node;

public class Fifo implements List {

	private Maillon first;
	private Maillon last;

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
		if (isEmpty()) {
			return null;
		} else {
			Node n = first.getInfo();
			first = first.getNext();
			return n;
			// }
		}
	}

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

	public String toString() {
		return first.toString();
	}
}

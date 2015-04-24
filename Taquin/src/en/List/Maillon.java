package en.List;

import en.Graphe.Node;

public class Maillon {

	private Node info;
	private Maillon next;

	public Maillon(Node info) {
		this.info = info;
		next = null;
	}

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

}

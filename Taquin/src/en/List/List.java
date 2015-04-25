package en.List;

import en.Graphe.Node;

public interface List {

	public Node next();

	public boolean addAll(List l);

	public boolean add(Node n);

	public boolean isEmpty();

	public String toString();
}

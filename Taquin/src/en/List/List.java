package en.List;

import java.util.Collection;

import en.Graphe.Node;

public interface List extends Collection<Node> {

	public Node next();

	public boolean add(Node n);
}

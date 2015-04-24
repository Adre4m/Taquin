package en.List;

import java.util.Collection;
import java.util.Iterator;

import en.Graphe.Node;

public class Stack implements List {

	private Maillon first;

	@Override
	public boolean addAll(Collection<? extends Node> arg0) {
		Iterator<? extends Node> it = arg0.iterator();
		while (it.hasNext()) {
			Maillon m = new Maillon((Node) it.next(), first);
			setFirst(m);
		}
		return true;
	}

	@Override
	public void clear() {
		first = null;
	}

	@Override
	public boolean contains(Object arg0) {

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {

		return false;
	}

	@Override
	public boolean isEmpty() {

		return false;
	}

	@Override
	public Iterator<Node> iterator() {

		return null;
	}

	@Override
	public boolean remove(Object arg0) {

		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {

		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {

		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Node n) {
		// TODO Auto-generated method stub
		return false;
	}

	public Maillon getFirst() {
		return first;
	}

	public void setFirst(Maillon first) {
		this.first = first;
	}

}

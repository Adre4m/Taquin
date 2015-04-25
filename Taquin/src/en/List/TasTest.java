package en.List;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Graphe.Node;

public class TasTest {

	List l = new Tas();

	@Before
	public void setUp() throws Exception {
		int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } };
		l.add(new Node(test));
	}

	@After
	public void tearDown() throws Exception {
	}

	public void testAdd() {
		int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		int[][] test2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 8 } };
		int[][] test3 = { { 1, 2, 3 }, { 0, 5, 6 }, { 4, 7, 8 } };
		l.add(new Node(test));
		l.add(new Node(test3));
		l.add(new Node(test2));
		assertEquals(true, true);
	}
	
	@Test
	public void testNext() {
		int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		int[][] test2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 8 } };
		int[][] test3 = { { 1, 2, 3 }, { 0, 5, 6 }, { 4, 7, 8 } };
		l.add(new Node(test));
		l.add(new Node(test2));
		l.add(new Node(test3));
		Node cmp = new Node(test);
		Node n = l.next();
		System.out.println(l);
		assertEquals(cmp, n);
	}
}

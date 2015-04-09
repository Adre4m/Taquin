package en.Graphe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {
	Node n;
	Node n2;
	Node n3;

	@Before
	public void setUp() throws Exception {
		int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 8 } };
		int[][] test2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } };
		int[][] test3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		n = new Node(test);
		n2 = new Node(n, test2);
		n3 = new Node(n2, test3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHeuristique() {
		assertEquals(2, n3.f());
	}
	
	@Test
	public void testWin() {
		assertEquals(true, n3.win());
	}

}

package en.Graphe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrapheTest {

	Node test = new Node();
	Node test2 = new Node();
	Graphe g = new Graphe();

	@Before
	public void setUp() throws Exception {
		int[][] state = { { 7, 3, 1 }, { 6, 2, 4 }, { 8, 5, 0 } };
		test.setState(state);
		g.addNode(null, test);
		System.out.println(g);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrow() {
		ArrayList<Node> next = new ArrayList<Node>();
		next.add(g.getGraphe().iterator().next());
		Node n = g.search(next);
		System.out.println(n);
		assertEquals(true, n.win());
	}
}

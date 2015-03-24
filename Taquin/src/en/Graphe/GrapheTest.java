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
		int[][] state = { { 4, 1, 2 }, { 5, 3, 6 }, { 0, 7, 8 } };
		test.setState(state);
		g.addNode(null, test);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrow() {
		ArrayList<Node> next = new ArrayList<Node>();
		next.add(g.getGraphe().iterator().next());
		g.search(next);
		System.out.println(g);
		assertEquals(true, false);
	}
}

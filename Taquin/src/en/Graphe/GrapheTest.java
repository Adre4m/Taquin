package en.Graphe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrapheTest {

	Node test = new Node();
	// Node test2 = new Node();
	Graphe g;

	@Before
	public void setUp() throws Exception {
		int[][] state = { { 5, 1, 2 }, { 4, 6, 3 }, { 0, 7, 8 } };
		test.setState(state);
		g = new Graphe(test);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrow() {
		long debut = System.nanoTime();
		ArrayList<Node> next = new ArrayList<Node>();
		next.add(g.getGraphe().iterator().next());
		Node n = g.search(next);
		System.out.println("Temps d'exectution : "
				+ (System.nanoTime() - debut));
		System.out.println("Nombre de mouvement : " + g.nbMove);
		System.out.println("Solution :\n" + n);
		assertEquals(true, n.win());
	}
}

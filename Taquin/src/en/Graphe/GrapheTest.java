package en.Graphe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Game.Taquin;
import en.Parser.Parser;

public class GrapheTest {

	Node test = new Node();
	Node test2 = new Node();
	Graphe g = new Graphe();

	@Before
	public void setUp() throws Exception {
		/*
		 * int[][] state = { { 7, 3, 1 }, { 6, 2, 4 }, { 8, 5, 0 } };
		 * test.setState(state); g.addNode(null, test);
		 */
		Taquin t = new Taquin(Parser.read("test1.taq"));
		if (t.solvable())
			g = new Graphe(t);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrow() {
		if (!g.isEmpty()) {
			System.out.println("Solvable");
			ArrayList<Node> next = new ArrayList<Node>();
			next.add(g.getGraphe().iterator().next());
			Node n = g.search(next);
			System.out.println(n);
			assertEquals(true, n.win());
		} else {
			System.out.println("Not solvable");
			assertEquals(true, true);
		}
	}
}

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
	Node initial;

	@Before
	public void setUp() throws Exception {
		Taquin t = new Taquin(Parser.read("test1.taq"));
		initial = new Node(t.getGame());
		// if (t.solvable())
		g = new Graphe(initial);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrow() {
		if (!g.isEmpty()) {
			ArrayList<Node> next = new ArrayList<Node>();
			next.add(initial);
			//Node n = g.searchBF(next);
			//Node n = g.searchDeep(next);
			Node n = g.searchAStar(next);
			// System.out.println("Grahpe début :\n" + g + "Graphe fin.\n");
			System.out.println("Chemin solution :\n" + n);
			assertEquals(true, n.win());
		} else
			assertEquals(true, true);
	}
}

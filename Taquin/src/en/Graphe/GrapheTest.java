package en.Graphe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Game.Taquin;
import en.Parser.Parser;

public class GrapheTest {

	Graphe g;
	Node initial;

	@Before
	public void setUp() throws Exception {
		Taquin t = new Taquin(Parser.read("test.taq"));
		initial = new Node(t);
		g = new Graphe(initial);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Node res = g.deepSearch();
		if (res != null) {
			System.out.println(res);
			assertEquals(true, res.win());
		}
		else
			assertEquals(false, true);

	}

}

package en.Graphe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Game.Taquin;
import en.Parser.Parser;

public class GrapheTest {

	Graphe gBF;
	Graphe gD;
	Node initial;

	@Before
	public void setUp() throws Exception {
		Taquin t = new Taquin(Parser.read("test.taq"));
		initial = new Node(t);
		gBF = new Graphe(initial);
		gD = new Graphe(initial);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeep() {
		Node res = gD.deepSearch();
		if (res != null) {
			System.out.println(res);
			assertEquals(true, res.win());
		} else
			assertEquals(false, true);

	}

	@Test
	public void testBF() {
		Node res = gBF.searchBF();
		if (res != null) {
			System.out.println(res);
			assertEquals(true, res.win());
		} else
			assertEquals(false, true);
	}

}

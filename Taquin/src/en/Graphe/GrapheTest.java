package en.Graphe;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Game.Taquin;
import en.List.List;
import en.List.Stack;
import en.Parser.Parser;

public class GrapheTest {

	Graphe g;

	@Before
	public void setUp() throws Exception {
		Taquin t = new Taquin(Parser.read("test.taq"));
		List l = new Stack();
		Map<String, Node> signed = new HashMap<String, Node>();
		g = new Graphe(t, l, signed);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Node n = g.search(1);
		System.out.println(n);
		assertEquals(true, n.win());
	}

}

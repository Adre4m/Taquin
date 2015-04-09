package en.Graphe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import en.Game.Taquin;
import en.Parser.Parser;

public class GrapheTest {

	Graphe g = new Graphe();
	Node initial;
	Node vict;
	ArrayList<Node> next = new ArrayList<Node>();

	@Before
	public void setUp() throws Exception {
		vict = new Node(new int[3][3]);
		vict.setState(vict.getVictory());
		Taquin t = new Taquin(Parser.read("test.taq"));
		initial = new Node(t.getGame());
		if (t.solvable())
			g = new Graphe(initial);
		next.add(initial);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*@Test
	public void testElagage() {
		
	}*/

	/*
	 * @Test public void testGrow() { int[][] res = { { 2, 1, 0 }, { 7, 3, 4 },
	 * { 8, 6, 5 } }; Node test1 = new Node(res); int[][] res2 = { { 2, 1, 4 },
	 * { 7, 0, 3 }, { 8, 6, 5 } }; Node test2 = new Node(res2); int[][] res3 = {
	 * { 2, 1, 4 }, { 7, 0, 5 }, { 8, 3, 6 } }; Node test3 = new Node(res3);
	 * int[][] res4 = { { 2, 1, 4 }, { 7, 3, 5 }, { 0, 8, 6 } }; Node test4 =
	 * new Node(res4); ArrayList<Node> toGrow = new ArrayList<Node>();
	 * toGrow.add(initial); g.growBF(toGrow); g.growBF(toGrow); boolean equal =
	 * g.contains(test1) && g.contains(test2) && g.contains(test3) &&
	 * g.contains(test4); assertEquals(true, equal); }
	 */

	/*
	 * @Test public void testSearchBF() { Node test = g.searchBF(next);
	 * assertEquals(true, test.equals(vict)); }
	 */

	/*
	 * @Test public void testSearchAstar() { g = new Graphe(initial); Node res =
	 * g.searchAStar(next); assertEquals(true, vict.equals(res)); }
	 */

	@Test
	public void testSearchDeep() {
		g = new Graphe(initial);
		next.addAll(g.growAStar(initial));
		Node res = g.searchDeep(next);
		//System.out.println(g);
		for(int i = 0; i < g.getGraphe().size(); ++i)
			if(g.getGraphe().get(i).win())
				res = g.getGraphe().get(i);
		System.out.println(res);
		assertEquals(true, res.win());
	}
}

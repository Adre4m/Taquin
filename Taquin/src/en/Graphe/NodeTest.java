package en.Graphe;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	Node test = new Node();
	Node test2 = new Node();

	@Before
	public void setUp() throws Exception {
		int[][] state = { { 1, 5, 6 }, { 2, 7, 3 }, { 4, 8, 0 } };
		/*
		 * test.setState(state); test2.setFather(test); test2.setState(state);
		 */
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		moves.add("South");
		moves.add("East");
		assertEquals(moves, test.possibleMoves());
	}

	@Test
	public void testEquals() {
		assertEquals(true, test.equals(test2));
	}

	@Test
	public void testMove() {
		boolean equal = true;
		int[][] state = { { 1, 5, 6 }, { 2, 7, 0 }, { 4, 8, 3 } };
		Node res = test.makeMove("South");
		/*
		 * for (int i = 0; i < 3 && equal; ++i) for (int j = 0; j < 3 && equal;
		 * ++j) if (state[i][j] != res.getState()[i][j]) equal = false;
		 */
		assertEquals(true, equal);
	}

}

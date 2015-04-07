package en.Graphe;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	Node test;
	Node test2;

	@Before
	public void setUp() throws Exception {
		int[][] state = { { 1, 5, 6 }, { 2, 7, 3 }, { 4, 8, 0 } };
		test = new Node(state);
		test2 = new Node(state);
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
		int[][] state = { { 1, 5, 6 }, { 2, 7, 0 }, { 4, 8, 3 } };
		Node test3 = new Node(state);
		Node res = test.makeMove("South");
		boolean equal = test3.getState().equals(res.getState());
		assertEquals(true, equal);
	}

}

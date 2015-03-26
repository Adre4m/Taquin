package en.Game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaquinTest {

	protected Taquin taquin;

	@Before
	public void setUp() throws Exception {
		int[][] game = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 8, 7 } };
		taquin = new Taquin(game);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMov0() {
		assertEquals(2, taquin.mov0());
	}

	@Test
	public void testSoluble() {
		assertEquals(true, taquin.solvable());
	}

}

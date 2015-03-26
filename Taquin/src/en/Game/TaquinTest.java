package en.Game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaquinTest {

	protected Taquin taquin;

	@Before
	public void setUp() throws Exception {
		int[][] game = { { 1, 5, 6 }, { 2, 7, 3 }, { 4, 8, 0 } };
		taquin = new Taquin(game);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMov0() {
		assertEquals(0, taquin.mov0());
	}

	@Test
	public void testSoluble() {
		assertEquals(false, taquin.solvable());
	}

}

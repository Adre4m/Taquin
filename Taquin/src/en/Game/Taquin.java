package en.Game;

public class Taquin {

	private int[][] game = {};
	private int posX;
	private int posY;

	public Taquin(int[][] game) {
		this.game = game;
		position();
	}

	public int[][] getGame() {
		return game;
	}

	public void setGame(int[][] game) {
		this.game = game;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String disp() {
		String s = "";
		for (int i = 0; i < game.length; ++i) {
			s += game[i][0];
			for (int j = 1; j < game[i].length; ++j) {
				s += " " + game[i][j];
			}
			s += "\n";
		}
		return s;
	}

	public boolean win() {
		if (posX != game.length - 1 && posY != game[0].length - 1)
			return false;
		else {
			int cpt = 1;
			for (int i = 0; i < game.length; ++i) {
				int max;
				if (i != game.length - 1)
					max = game[i].length;
				else
					max = game[i].length - 1;
				for (int j = 0; j < max; ++j) {
					if (game[i][j] != cpt)
						return false;
					cpt++;
				}
			}
			return true;
		}
	}

	public void position() {
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[i].length; ++j)
				if (game[i][j] == 0) {
					posX = i;
					posY = j;
					return;
				}
	}

	public void play(String dir) {
		// position();
		switch (dir) {
		case "2":
			if (posX != 0) {
				exchange(dir);
				return;
			}
			break;
		case "4":
			if (posY != game[0].length - 1) {
				exchange(dir);
				return;
			}
			break;
		case "6":
			if (posY != 0) {
				exchange(dir);
				return;
			}
			break;
		case "8":
			if (posX != game.length - 1) {
				exchange(dir);
				return;
			}
			break;
		}
		System.out.println("TaquinPasDirectionException");
	}

	public void exchange(String dir) {
		switch (dir) {
		case "2":
			game[posX][posY] = game[posX - 1][posY];
			game[posX - 1][posY] = 0;
			posX--;
			break;
		case "4":
			game[posX][posY] = game[posX][posY + 1];
			game[posX][posY + 1] = 0;
			posY++;
			break;
		case "8":
			game[posX][posY] = game[posX + 1][posY];
			game[posX + 1][posY] = 0;
			posX++;
			break;
		case "6":
			game[posX][posY] = game[posX][posY - 1];
			game[posX][posY - 1] = 0;
			posY--;
			break;
		}
	}

	public boolean soluble() {
		int[] t = new int[game.length * game[0].length];
		int cpt = 0;
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[i].length; ++j) {
				t[cpt] = game[i][j];
				cpt++;
			}
		return (mov0() % 2) == (triBulle(t) % 2);
	}

	public int triBulle(int[] tab) {
		// tri
		int nbEchange = 0;
		for (int i = 0; i < tab.length; ++i) {
			if (tab[i] == 0) {
				tab[i] = tab[tab.length - 1];
				tab[tab.length - 1] = 0;
				nbEchange++;
				break;
			}
		}
		for (int i = tab.length - 1; i > 0; --i) {
			for (int j = 0; j < i - 1; ++j)
				if (tab[j] > tab[j + 1]) {
					int tmp = tab[j];
					tab[j] = tab[j + 1];
					tab[j + 1] = tmp;
					nbEchange++;
				}
		}
		return nbEchange;
	}

	public int mov0() {
		/*
		 * int posX0 = posX; int posY0 = posY; int nbMov0 = 0; while (posX0 <
		 * game.length - 1 || posY0 < game[0].length - 1) { if (posX0 <
		 * game.length - 1) { posX0++; nbMov0++; } if (posY0 < game[0].length -
		 * 1) { posY0++; nbMov0++; } } return nbMov0;
		 */
		return (game.length - 1 - posX) + (game[0].length - 1 - posY);
	}
}

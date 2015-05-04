package en.Game;

/**
 * <h2></h2>
 * 
 * @author Adrien Bourgeois, Lindsay Grignon, Antoine LeHenaff, Alexandre Samoes
 *
 */

public class Taquin {

	private int[][] game = {};
	private int posX;
	private int posY;

	/**
	 * <h2>Initialisation du jeu.</h2>
	 * 
	 * @author
	 * @param game
	 *            La position initiale du jeu.
	 * 
	 */
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

	/**
	 * <h2>Affichage du jeu</h2>
	 * 
	 * @author
	 * @return Un affichage propre du jeu a la position actuelle.
	 * 
	 */
	public String toString() {
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

	/**
	 * <h2>Verification de la victoire</h2>
	 * 
	 * @author
	 * @return Un boolean, true si la position actuelle est gagnante, false
	 *         sinon.
	 * 
	 *         {@code}
	 *         <p>
	 *         Cette fonction va parcourir le jeu, et verifier si la position
	 *         est gagante.
	 *         </p>
	 * 
	 */
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

	/**
	 * <h2>Position du zero dans le jeu</h2>
	 * 
	 * @author
	 * 
	 *         {@code}
	 *         <p>
	 *         Cette methode va parcourir le jeu, jusqu'a trouver le zéro.
	 *         </p>
	 * 
	 */
	public void position() {
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[i].length; ++j)
				if (game[i][j] == 0) {
					posX = i;
					posY = j;
					return;
				}
	}

	/**
	 * <h2>Action de jeu</h2>
	 * 
	 * @author
	 * @param dir
	 *            La direction choisie par le joueur.
	 * 
	 *            {@code}
	 *            <p>
	 *            Cette methode va modifier le jeu si c'est possible.
	 *            </p>
	 * 
	 */
	public void play(String dir) {
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
	}

	/**
	 * <h2>Echange deux valeurs</h2>
	 * 
	 * @author
	 * @param dir
	 *            La direction desiree par le joueur.
	 * 
	 *            {@code}
	 *            <p>
	 *            Si possible, la methode va echanger la valeur designee par la
	 *            direction avec zero.
	 *            </p>
	 * 
	 */
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

	/**
	 * <h2>Verification de la solvabilite</h2>
	 * 
	 * @author
	 * @return true si le jeu est estime solvable, false sinon.
	 * 
	 *         {@code}
	 *         <p>
	 *         Attention, la verification de la solvabilite n'est aps absolue.
	 *         En effet la verification n'est pas exacte dans tout les cas.
	 *         Cependant cela reste utile de l'utiliser.
	 *         </p>
	 * 
	 */
	public boolean solvable() {
		int[] t = new int[game.length * game[0].length];
		int cpt = 0;
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[0].length; ++j) {
				t[cpt] = game[i][j];
				cpt++;
			}
		return (mov0() % 2) == (triInsert(t) % 2);
	}

	/**
	 * <h2>Tri</h2>
	 * 
	 * @author
	 * @param tab
	 *            la valeur du jeu mis en tableau simple dimension.
	 * @return le tableau trier.
	 * 
	 *         {@code}
	 *         <p>
	 *         Tri par insertion.
	 *         </p>
	 */
	public int triInsert(int[] tab) {
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
		for (int i = tab.length - 1; i > 0; --i)
			for (int j = 0; j < i - 1; ++j)
				if (tab[j] > tab[j + 1]) {
					int tmp = tab[j];
					tab[j] = tab[j + 1];
					tab[j + 1] = tmp;
					nbEchange++;
				}
		return nbEchange;
	}

	/**
	 * <h2>Nombre de mouvement de zero</h2>
	 * 
	 * @author
	 * @return le nombre de mouvement necessaire pour amener le zero a sa bonne
	 *         place.
	 */
	public int mov0() {
		return (game.length - 1 - posX) + (game[0].length - 1 - posY);
	}
}

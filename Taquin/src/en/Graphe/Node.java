package en.Graphe;

import java.util.ArrayList;
import java.util.Scanner;

import en.Game.Taquin;

/**
 * <h2>Noeud</h2>
 * 
 * @author
 *
 */
public class Node {
	private String game;
	private String state;
	private Node father;
	private int high;
	private int length;
	int x;
	int y;

	/**
	 * <h2>Initalisation d'un noeud</h2>
	 * 
	 * @author
	 * @param game
	 *            l'etat du jeu
	 * 
	 */
	public Node(int[][] game) {
		this.game = "";
		high = game.length;
		length = game[0].length;
		father = null;
		state = "unreach";
		for (int i = 0; i < high; ++i) {
			int max = length;
			if (i == high - 1)
				max--;
			for (int j = 0; j < max; ++j)
				this.game += game[i][j] + " ";
		}
		this.game += game[game.length - 1][game[0].length - 1];
		pos();
	}

	/**
	 * <h2>Initialisation d'un noeud avec un pere defini</h2>
	 * 
	 * @author
	 * @param father
	 *            le pere
	 * @param game
	 *            l'etat du jeu
	 * 
	 */
	public Node(Node father, int[][] game) {
		this.game = "";
		high = game.length;
		length = game[0].length;
		this.father = father;
		state = "unreach";
		for (int i = 0; i < high; ++i) {
			int max = length;
			if (i == high - 1)
				max--;
			for (int j = 0; j < max; ++j)
				this.game += game[i][j] + " ";
		}
		this.game += game[game.length - 1][game[0].length - 1];
		pos();
	}

	/**
	 * <h2>Initialisation d'un noeud a partir d'un Taquin</h2>
	 * 
	 * @author
	 * @param t
	 *            jeu de taquin
	 * 
	 */
	public Node(Taquin t) {
		this.game = "";
		int[][] game = t.getGame();
		high = game.length;
		length = game[0].length;
		this.father = null;
		state = "unreach";
		for (int i = 0; i < high; ++i) {
			int max = length;
			if (i == high - 1)
				max--;
			for (int j = 0; j < max; ++j)
				this.game += game[i][j] + " ";
		}
		this.game += game[game.length - 1][game[0].length - 1];
		pos();
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * <h2>Premiere heuristique</h2>
	 * 
	 * @author
	 * @return la valeur de h1
	 * 
	 *         {@code}
	 *         <p>
	 *         h1 est determinee par difference entre la position actuelle et la
	 *         position de victoir.
	 *         </p>
	 */
	@SuppressWarnings("resource")
	public int h1() {
		int h1 = 0;
		String victory = getVictory();
		Scanner scState = new Scanner(game).useDelimiter(" ");
		Scanner scVictory = new Scanner(victory).useDelimiter(" ");
		while (scState.hasNext()) {
			if (scState.nextInt() != scVictory.nextInt())
				h1++;
		}
		return h1;
	}

	/**
	 * <h2>Deuxieme heuristique</h2>
	 * 
	 * @author
	 * @return la valeur de h2
	 * 
	 *         {@code}
	 *         <p>
	 *         h2 est la distance de Manhattan de chaque valeur sur la position
	 *         actuelle par rapport a celle final.
	 *         </p>
	 */
	public int h2() {
		int h2 = 0;
		for (int i = 0; i < (length * high - 1); ++i) {
			h2 += dm(i);
		}
		return h2;
	}

	/**
	 * <h2>Distance de Manhattan</h2>
	 * 
	 * @author
	 * @param pion
	 *            le pion a analyser.
	 * @return la distance de Manhattan de ce pion.
	 * 
	 *         {@code}
	 *         <p>
	 *         Va parcourir le jeu jusqu'a trouver la valeur dans les deux
	 *         tableaux. Puis renvoyer la difference entre les deux positions.
	 *         </p>
	 */
	public int dm(int pion) {
		int[][] victory = toArray(getVictory());
		int[][] s = toArray(game);
		int posFX = 0, posFY = 0, posSX = 0, posSY = 0;
		for (int i = 0; i < s.length; ++i)
			for (int j = 0; j < s[0].length; ++j) {
				if (s[i][j] == pion) {
					posSX = i;
					posSY = j;
				}
				if (victory[i][j] == pion) {
					posFX = i;
					posFY = j;
				}
			}
		return Math.abs(posFX - posSX) + Math.abs(posFY - posSY);
	}

	/**
	 * <h2>Nombre de coup.</h2>
	 * 
	 * @author
	 * @return le nombre de coup qu'il a fallu faire pour arriver a cette
	 *         position.
	 * 
	 */
	public int g() {
		int g = 0;
		if (father != null)
			g += father.g() + 1;
		return g;
	}

	/**
	 * <h2>L'heuristique</h2>
	 * 
	 * @author
	 * @return la valeur de g, h1 et h2, et renvoi ainsi l'heuristique du noeud.
	 * 
	 */
	public int f() {
		return g() + h1() + h2();
	}

	/**
	 * <h2>Transformation du jeu en tableau</h2>
	 * 
	 * @author
	 * @param s
	 *            l'etat du jeu.
	 * @return le jeu transformer en tableau
	 * 
	 *         {@code}
	 *         <p>
	 *         Comme un noued defini l'etat du jeu par un string, pour une
	 *         verification accleree, il est necessaire pour certaine fonction
	 *         de retransformer ce string en tableau.
	 *         </p>
	 */
	@SuppressWarnings("resource")
	public int[][] toArray(String s) {
		int[][] res = new int[length][high];
		Scanner sc = new Scanner(s).useDelimiter(" ");
		for (int i = 0; i < length; ++i)
			for (int j = 0; j < high; ++j)
				res[i][j] = sc.nextInt();
		return res;
	}

	/**
	 * <h2>Victoire</h2>
	 * 
	 * @author
	 * @return true si la possition est victorieuse, false sinon.
	 * 
	 */
	public boolean win() {
		return game.equals(getVictory());
	}

	/**
	 * <h2>Posistion de victoire</h2>
	 * 
	 * @author
	 * @return la position de victoire
	 * 
	 */
	public String getVictory() {
		String victory = "";
		int cpt = 1;
		for (int i = 0; i < length; ++i) {
			int max = high;
			if (i == length - 1)
				max--;
			for (int j = 0; j < max; ++j) {
				victory += cpt + " ";
				cpt++;
			}
		}
		victory += 0;
		return victory;
	}

	/**
	 * <h2>Position du zero</h2>
	 * 
	 * @author
	 * 
	 *         {@code}
	 *         <p>
	 *         Recupere la position du zero dans le jeu actuel.
	 *         </p>
	 */
	public void pos() {
		int[][] res = toArray(game);
		for (int i = 0; i < res.length; ++i)
			for (int j = 0; j < res[0].length; ++j)
				if (res[i][j] == 0) {
					x = i;
					y = j;
					return;
				}
	}

	/**
	 * <h2>Mouvements possibles</h2>
	 * 
	 * @author
	 * @return une liste contenant tout les mouvements possibles
	 * 
	 *         {@code}
	 *         <p>
	 *         Cette methodes regarde si un mouvement est possible auquel cas
	 *         elle l'ajoute dans la liste
	 *         </p>
	 */
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		if (x != 0)
			moves.add("South");
		if (x != (length - 1))
			moves.add("North");
		if (y != 0)
			moves.add("East");
		if (y != (high - 1))
			moves.add("West");
		return moves;
	}

	/**
	 * <h2>Executer un mouvement</h2>
	 * 
	 * @author
	 * @param dir
	 *            la direction souhaitee.
	 * @return un noeud nouvellement creer avec un jeu apres mouvement.
	 * 
	 */
	public Node makeMove(String dir) {
		int[][] res = toArray(game);
		switch (dir) {
		case "West":
			res[x][y] = res[x][y + 1];
			res[x][y + 1] = 0;
			break;
		case "East":
			res[x][y] = res[x][y - 1];
			res[x][y - 1] = 0;
			break;
		case "North":
			res[x][y] = res[x + 1][y];
			res[x + 1][y] = 0;
			break;
		case "South":
			res[x][y] = res[x - 1][y];
			res[x - 1][y] = 0;
		}
		return new Node(res);
	}

	/**
	 * <h2>Methode HashCode</h2>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + high;
		result = prime * result + length;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * <h2>Methode Equals</h2>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		return true;
	}

	/**
	 * <h2>Compare deux noeuds</h2>
	 * 
	 * @author
	 * @param obj
	 *            l'objet a comparer a this.
	 * @return la valeur de la difference entre leurs heuristiques. cette valeur
	 *         est positive si this a une heuristique superieur, 0 si les
	 *         heuristique sont egales, et une valeur negative si this a une
	 *         heuristique plus faible.
	 * 
	 */
	public int compareTo(Object obj) {
		if (obj instanceof Node) {
			Node other = (Node) obj;
			return this.f() - other.f();
		}
		return -1;
	}

	/**
	 * <h2>Methode toString</h2>
	 */
	@Override
	public String toString() {
		if (father == null) {
			return dispArray();
		} else
			return /* father.toString() + "\n" + */dispArray();
	}

	/**
	 * <h2>Transformation du jeu en String</h2>
	 * 
	 * @author
	 * @return un String avec le jeu proprement afficher.
	 * 
	 */
	@SuppressWarnings("resource")
	public String dispArray() {
		String s = "";
		Scanner sc = new Scanner(game).useDelimiter(" ");
		for (int i = 0; i < length; ++i) {
			s += sc.nextInt();
			for (int j = 1; j < high; ++j)
				s += " " + sc.nextInt();
			s += "\n";
		}
		return s;
	}
}

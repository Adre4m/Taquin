package en.Graphe;

import java.util.ArrayList;
import java.util.Scanner;

import en.Game.Taquin;

public class Node {
	private String game;
	private String state;
	private Node father;
	private int high;
	private int length;
	int x;
	int y;

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

	public int h2() {
		int h2 = 0;
		for (int i = 0; i < (length * high - 1); ++i) {
			h2 += dm(i);
		}
		return h2;
	}

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

	public int g() {
		int g = 0;
		if (father != null)
			g += father.g() + 1;
		return g;
	}

	public int f() {
		return g() + h1() + h2();
	}

	@SuppressWarnings("resource")
	public int[][] toArray(String s) {
		int[][] res = new int[length][high];
		Scanner sc = new Scanner(s).useDelimiter(" ");
		for (int i = 0; i < length; ++i)
			for (int j = 0; j < high; ++j)
				res[i][j] = sc.nextInt();
		return res;
	}

	public boolean win() {
		return game.equals(getVictory());
	}

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

	public int compareTo(Object obj) {
		if (obj instanceof Node) {
			Node other = (Node) obj;
			return this.f() - other.f();
		}
		return -1;
	}

	@Override
	public String toString() {
		if (father == null) {
			return dispArray();
		} else
			return father.toString() + "\n" + dispArray();
	}

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

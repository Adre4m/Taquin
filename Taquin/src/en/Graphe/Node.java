package en.Graphe;

import java.util.ArrayList;
import java.util.Scanner;

public class Node {
	private String game;
	private String state;
	private Node father;
	private ArrayList<Node> sons;
	private int high;
	private int length;
	int x;
	int y;

	public Node(int[][] game) {
		this.game = "";
		high = game.length;
		length = game[0].length;
		father = null;
		sons = new ArrayList<Node>();
		state = "unreach";
		for (int i = 0; i < high; ++i)
			for (int j = 0; j < length; ++j)
				this.game += game[i][j] + " ";
	}

	public Node(Node father, int[][] game) {
		this.game = "";
		high = game.length;
		length = game[0].length;
		this.father = father;
		sons = new ArrayList<Node>();
		state = "unreach";
		for (int i = 0; i < high; ++i)
			for (int j = 0; j < length; ++j)
				this.game += game[i][j] + " ";
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

	public ArrayList<Node> getSons() {
		return sons;
	}

	public void setSons(ArrayList<Node> sons) {
		this.sons = sons;
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
		Scanner scState = new Scanner(state).useDelimiter(" ");
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
		int[][] s = toArray(state);
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
		if (father != null) {
			g += father.g();
		}
		return g;
	}

	public int f() {
		g();
		h1();
		h2();
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
		if (x != length - 1 || y != high - 1)
			return false;
		return state.equals(getVictory());
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

}

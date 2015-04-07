package en.Graphe;

import java.util.ArrayList;
import java.util.Scanner;

public class Node {
	private Node father = null;
	private String state = "";
	int length;
	int high;
	private boolean hasSons = false;
	private String process;
	private int weight = 0;
	int h1 = 0;
	int h2 = 0;
	int g = 0;
	int x = 0;
	int y = 0;

	public Node() {
		state = null;
		process = "unreach";
	}

	public Node(int[][] game) {
		length = game.length;
		high = game[0].length;
		for (int i = 0; i < game.length; ++i) {
			int max = game[0].length;
			if (i == game.length - 1)
				max--;
			for (int j = 0; j < max; ++j)
				state += game[i][j] + " ";
		}
		state += game[game.length - 1][game[0].length - 1];
		pos();
	}

	public Node(String state) {
		this.state = state;
		pos();
		process = "unreach";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + (hasSons ? 1231 : 1237);
		result = prime * result + state.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return state.equals(other.state);
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isHasSons() {
		return hasSons;
	}

	public void setHasSons(boolean hasSons) {
		this.hasSons = hasSons;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getH1() {
		h1();
		return h1;
	}

	public void setH1(int h1) {
		this.h1 = h1;
	}

	public int getH2() {
		h2();
		return h2;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getG() {
		g();
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<String> possibleMoves() {
		// pos();
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

	public void pos() {
		int[][] res = toArray(state);
		for (int i = 0; i < res.length; ++i)
			for (int j = 0; j < res[0].length; ++j)
				if (res[i][j] == 0) {
					x = i;
					y = j;
					return;
				}
	}

	public String toString() {
		if (father == null)
			return dispArray();
		else
			return /* father.toString() + */ "\n" + dispArray();
	}

	public String dispArray() {
		String s = "";
		int[][] res = toArray(state);
		for (int i = 0; i < res.length; ++i) {
			int max = res[0].length;
			s += res[i][0];
			if(i == res.length - 1)
				max--;
			for(int j = 1; j < max; ++j)
				s += " " + res[i][j];
			s += "\n";
		}
		return s;
	}

	public Node makeMove(String dir) {
		int[][] res = toArray(state);
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

	public boolean win() {
		if (x != state.length() - 1)
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
				victory += cpt;
				cpt++;
			}
		}
		victory += 0;
		return victory;
	}

	@SuppressWarnings("resource")
	public void h1() {
		String victory = getVictory();
		Scanner scState = new Scanner(state).useDelimiter(" ");
		Scanner scVictory = new Scanner(victory).useDelimiter(" ");
		while (scState.hasNext()) {
			if (scState.nextInt() != scVictory.nextInt())
				h1++;
		}
	}

	public void h2() {
		for (int i = 0; i < (length * high - 1); ++i) {
			h2 += dm(i);
		}
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

	public void g() {
		if (father != null) {
			g += father.g;
		}
	}

	public int f() {
		g();
		h1();
		h2();
		return g + h1 + h2;
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
}

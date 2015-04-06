package en.Graphe;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	private Node father = null;
	private int[][] state;
	private boolean hasSons = false;
	private String process = "unreach";
	private int weight = 0;
	int h1 = 0;
	int h2 = 0;
	int g = 0;
	int x = 0;
	int y = 0;

	public Node() {
		state = null;
	}

	public Node(int[][] state) {
		this.state = state;
		pos();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + (hasSons ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(state);
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
		if (state.length != other.state.length
				|| state[0].length != other.state[0].length)
			return false;
		else
			for (int i = 0; i < state.length; ++i)
				for (int j = 0; j < state[0].length; ++j)
					if (state[i][j] != other.state[i][j])
						return false;
		return true;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
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
		ArrayList<String> moves = new ArrayList<String>();
		if (x != 0)
			moves.add("South");
		if (x != (state.length - 1))
			moves.add("North");
		if (y != 0)
			moves.add("East");
		if (y != (state[0].length - 1))
			moves.add("West");
		return moves;
	}

	public void pos() {
		for (int i = 0; i < state.length; ++i)
			for (int j = 0; j < state[0].length; ++j)
				if (state[i][j] == 0) {
					x = i;
					y = j;
					return;
				}
	}

	public String toString() {
		if (father == null)
			return dispArray();
		else
			return father.toString() + "\n" + dispArray();
	}

	public String dispArray() {
		String s = "";
		for (int i = 0; i < state.length; ++i) {
			s += state[i][0];
			for (int j = 1; j < state[i].length; ++j) {
				s += " " + state[i][j];
			}
			s += "\n";
		}
		return s;
	}

	public Node makeMove(String dir) {
		int[][] res = new int[state.length][state[0].length];
		for (int i = 0; i < res.length; ++i)
			for (int j = 0; j < res[0].length; ++j)
				res[i][j] = state[i][j];
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
		if (x != state.length - 1 && y != state[0].length - 1)
			return false;
		else {
			int cpt = 1;
			for (int i = 0; i < state.length; ++i) {
				int max;
				if (i != state.length - 1)
					max = state[i].length;
				else
					max = state[i].length - 1;
				for (int j = 0; j < max; ++j) {
					if (state[i][j] != cpt)
						return false;
					cpt++;
				}
			}
			return true;
		}
	}

	public int[][] getVictory() {
		int[][] victory = new int[state.length][state[0].length];
		int cpt = 1;
		for (int i = 0; i < victory.length; ++i) {
			int max = victory.length;
			if (i == victory.length - 1)
				max--;
			for (int j = 0; j < max; ++j) {
				victory[i][j] = cpt;
				cpt++;
			}
		}
		victory[state.length - 1][state[0].length - 1] = 0;
		return victory;
	}

	public void h1() {
		int[][] victory = getVictory();
		for (int i = 0; i < victory.length; ++i)
			for (int j = 0; j < victory[0].length; ++j)
				if (state[i][j] != victory[i][j])
					h1++;
	}

	public void h2() {
		for (int i = 0; i < (state.length * state[0].length - 1); ++i) {
			h2 += dm(i);
		}
	}

	public int dm(int pion) {
		int[][] victory = getVictory();
		int posFX = 0, posFY = 0, posSX = 0, posSY = 0;
		for (int i = 0; i < state.length; ++i)
			for (int j = 0; j < state[0].length; ++j) {
				if (state[i][j] == pion) {
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

	public void place(int i, int j, int num) {
		if (state[i][j] == 0) {
			state[i][j] = num;
			if (i > 0)
				if (state[i - 1][j] == num)
					state[i - 1][j] = 0;
		}
		if (j > 0)
			if (state[i][j - 1] == num)
				state[i][j - 1] = 0;
		if (i < state.length)
			if (state[i + 1][j] == num)
				state[i + 1][j] = 0;
		if (j < state[0].length)
			if (state[i][j + 1] == num)
				state[i][j + 1] = 0;
	}

	public boolean isPlaced() {
		boolean result = true;
		for (int i = 0; i < state.length; ++i)
			for (int j = 0; j < state[0].length; ++j)
				if (state[i][j] != i + j * 4)
					result = false;
		return result;
	}
}

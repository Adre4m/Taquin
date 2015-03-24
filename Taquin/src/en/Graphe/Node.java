package en.Graphe;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	private Node father = null;
	private int[][] state;
	private boolean hasSons = false;
	private String process = "unreach";

	public Node() {
		state = null;
	}

	public Node(int[][] state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((father == null) ? 0 : father.hashCode());
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

	public ArrayList<String> possibleMoves() {
		int[] xy = new int[2];
		pos(xy);
		int x = xy[0];
		int y = xy[1];
		ArrayList<String> moves = new ArrayList<String>();
		if (x != 0)
			moves.add("South");
		if (x != state.length - 1)
			moves.add("North");
		if (y != 0)
			moves.add("East");
		if (y != state[0].length - 1)
			moves.add("West");
		return moves;
	}

	public void pos(int[] xy) {
		for (int i = 0; i < state.length; ++i)
			for (int j = 0; j < state.length; ++j)
				if (state[i][j] == 0) {
					xy[0] = i;
					xy[1] = j;
					return;
				}
	}

	public String toString() {
		if (father == null)
			return dispArray();
		else
			return /* father.dispArray() + "\n" + */dispArray();
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

	public int[][] makeMove(String dir) {
		int[] xy = new int[2];
		pos(xy);
		int x = xy[0];
		int y = xy[1];
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
		return res;
	}

	public boolean win() {
		int[] xy = new int[2];
		pos(xy);
		int x = xy[0];
		int y = xy[1];
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
}

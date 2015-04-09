package en.Graphe;

import java.util.ArrayList;

public class Node {
	private String game;
	private String state;
	private Node father;
	private ArrayList<Node> sons;
	private int high;
	private int length;

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

}

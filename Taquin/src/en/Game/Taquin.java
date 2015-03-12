package en.Game;

import java.io.FileNotFoundException;
import java.util.Scanner;

import en.Parser.Parser;

public class Taquin {

	private static int[][] game = {};
	private static int posX = 0;
	private static int posY = 0;
	

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			game = Parser.read("test.taq");
			position();
			while (!win()) {
				System.out.println(disp());
				boolean ok = false;
				do {
					System.out
							.println("\n8 - North, 6 - East, 2 - South, 4 - West");
					String dir = in.nextLine();
					if (dir.equals("8") || dir.equals("6") || dir.equals("2")
							|| dir.equals("4")) {
						play(dir);
						ok = true;
					} else {
						System.out.println("Wrong input");
						ok = false;
					}
				} while (!ok);
			}
			disp();
			if (win())
				System.out.println("Gagné");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	public static String disp() {
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

	public static boolean win() {
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

	public static void position() {
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[i].length; ++j)
				if (game[i][j] == 0) {
					posX = i;
					posY = j;
					return;
				}
	}

	public static void play(String dir) {
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

	public static void exchange(String dir) {
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

	public static boolean soluble() {
		int[] t = new int[game.length * game[0].length];
		int cpt = 0;
		for (int i = 0; i < game.length; ++i)
			for (int j = 0; j < game[i].length; ++j) {
				t[cpt] = game[i][j];
				cpt++;
			}
		return (mov0() % 2) == (triBulle(t) %2);
	}

	public static int triBulle(int[] tab) {
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

	public static int mov0() {
		int posX0 = posX;
		int posY0 = posY;
		int nbMov0 = 0;
		while (posX0 < game.length - 1 || posY0 < game[0].length - 1) {
			if (posX0 < game.length - 1) {
				posX0++;
				nbMov0++;
			}
			if (posY0 < game[0].length - 1) {
				posY0++;
				nbMov0++;
			}
		}
		return nbMov0;
	}
}

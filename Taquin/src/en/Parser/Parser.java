package en.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	@SuppressWarnings("resource")
	public static int[][] read(String fichier) throws FileNotFoundException {
		File file = new File(fichier);
		Scanner scanner = new Scanner(file).useDelimiter(",");
		int largeur = scanner.nextInt();
		int hauteur = scanner.nextInt();
		int[][] taquin = new int[largeur][hauteur];
		for (int colonne = 0; colonne < hauteur; colonne++)
			for (int ligne = 0; ligne < largeur; ligne++) {
				taquin[colonne][ligne] = scanner.nextInt();
			}
		return taquin;
	}

	public static void main(String[] args) {
		try {
			int[][] taquin = read("jeu.taq");
			for (int i = 0; i < taquin.length; i++)
				for (int j = 0; j < taquin[0].length; j++)
					System.out.print(taquin[i][j] + ", ");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("le fichier n'existe pas");
		}

	}

}

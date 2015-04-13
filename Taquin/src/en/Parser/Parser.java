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
		int[][] taquin = new int[hauteur][largeur];
		for (int colonne = 0; colonne < hauteur; colonne++)
			for (int ligne = 0; ligne < largeur; ligne++) {
				taquin[colonne][ligne] = scanner.nextInt();
			}
		return taquin;
	}
}

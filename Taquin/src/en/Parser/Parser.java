package en.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <h2>Parser qui va lire le .taq</h2>
 * 
 * @author Grignon Lindsay
 *
 */
public class Parser {

	/**
	 * <h2>Methode de lecture</h2>
	 * 
	 * @author Grignon Lindsay
	 * @param fichier
	 *            le nom du fichier .taq
	 * @return Retourne un tableau a 2 dimentions correspondant au jeu taquin
	 *         associe au fichier.taq
	 * @throws FileNotFoundException
	 *             Cette erreur sera lance si il n'existe pas de fichier portant
	 *             le nom donne en parametres
	 * 
	 *             {@code}
	 *             <p>
	 *             Les 2 premiers entier du .taq correspondent a la taille du
	 *             Taquin
	 *             </p>
	 *             <p>
	 *             </p>
	 *             <p>
	 *             On les stock dans des variables puis on cree le tableau avec
	 *             les dimentions correspondantes et on le remplis puis on le
	 *             renvois
	 *             </p>
	 * 
	 */
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

package en.Display;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Timer;

import en.Game.Taquin;
import en.Graphe.Graphe;
import en.Graphe.Node;
import en.List.Fifo;
import en.List.Stack;
import en.List.Tas;
import en.Parser.Parser;

/**
 * <h2>Animation de la solution</h2>
 *
 * @author Adrien Bourgeois
 * 
 *         {@code}
 *         <p>
 *         Sur les bases du code de M. Couvreur, modifié par Adrien Bourgeois.<br>
 *         Selon les paramettres l'affichage sera différent.<br>
 *         Executer avec -h pour plus d'information
 *         </p>
 */

public class Terminal {

	static String message;
	static ArrayList<Node> l = new ArrayList<Node>();

	static public void main(String[] args) {
		if (args[0].equals("-name"))
			System.out
					.println("Lindsay Grignon, Adrien Bourgeois, Antoine LeHenaff, Alexandre Samoes");
		else if (args[0].equals("-h"))
			System.out
					.println("-name : Affiche les noms.\n"
							+ "-h : Affiche les options.\n"
							+ "-sol [nom fichier].taq -j : Test s'il y a une solution.\n"
							+ "-joue [nom fichier].taq : Permet de jouet sur terminal.\n"
							+ "-cal delai algo [nom ficher].taq : Affiche la solution si elle existe, delai est optionnel. -cal affichera la solution sous forme de liste.\n"
							+ "-anime delai algo [nom.fichier].taq : Comme -cal, excepté que la solution sera animée.");
		else if (args[0].equals("-sol"))
			try {
				Taquin t = new Taquin(Parser.read(args[1]));
				System.out.println("Solvable = " + t.solvable());
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		else if (args[0].equals("-joue"))
			try {
				Taquin t = new Taquin(Parser.read(args[1]));
				do {
					@SuppressWarnings("resource")
					Scanner in = new Scanner(System.in);
					message = t.toString()
							+ "8 - Nord, 6 - Est, 2 - Sud, 4 - Ouest";
					String dir = in.nextLine();
					t.play(dir);
				} while (t.win());
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		else if (args[0].equals("-cal") || args[0].equals("-anime")) {
			Graphe g = null;
			int list = (args.length == 4) ? 2 : 1;
			int fic = (args.length == 4) ? 3 : 2;
			long delai = (args.length == 4) ? Integer.parseInt(args[1]) : 0;
			try {
				Taquin t = new Taquin(Parser.read(args[fic]));
				if (args[list].equals("Pile"))
					g = new Graphe(t, new Stack(), new HashMap<String, Node>());
				else if (args[list].equals("File"))
					g = new Graphe(t, new Fifo(), new HashMap<String, Node>());
				else if (args[list].equals("Tas"))
					g = new Graphe(t, new Tas(), new HashMap<String, Node>());
				Node res = g.search(delai);
				if (res != null) {
					l.add(res);
					Node father = res.getFather();
					while (father != null) {
						l.add(father);
						father = father.getFather();
					}
					if (args[0].equals("-cal"))
						System.out.println(l.toString());
					else {
						// System.out.print((char) Event.ESCAPE);
						Timer time = new Timer(200, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int index = l.size() - 1;
								while (index >= 0) {
									message = l.get(index).toString();
									System.out.print((char) Event.ESCAPE);
									System.out.println(message);
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									index--;
								}
								// System.out.println("t = " + i);
							}
						});
						time.start();
						try {
							System.in.read();
						} catch (IOException e) {

						}
						time.stop();
					}
				}
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
	}
}
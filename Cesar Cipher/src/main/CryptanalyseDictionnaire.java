package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fr.esiag.security.cesar.Cesar;
import fr.esiag.util.Alphabet;


public class CryptanalyseDictionnaire {
	
	private static List<String> dictionnaire;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// initialisation du dictionnaire
		initializeDictionnaire();
		
		Random r = new Random();
		int decalage = r.nextInt(4000);

		File f = new File("Basic.txt");
		
		File fOutput;
		
		// chiffrement du fichier
		fOutput = Cesar.encryptFile(f.getAbsolutePath(), decalage);
		
		FileInputStream in = null;
		ArrayList<String> text = new ArrayList<String>();
		
		Integer tabExist[] = new Integer[2]; // 0 = true ; 1 = false
		tabExist[0] = tabExist[1] = 0;
		
		boolean trouve = false;
		int compteur = 1;
		String line;
		
		System.out.println("Il existe " + Alphabet.getAlphabet().length() + " possibles...");
		System.out.print("Essai numéro : ");
		// on test chaque combinaison possible
		while(compteur<Alphabet.getAlphabet().length() && trouve == false) {
			System.out.print(compteur + " - ");
			/**
			 * on charge le contenu du fichier crypté
			 * et on applique le décryptage avec la clé à tester
			 */
			try {
				in = new FileInputStream(fOutput.getAbsoluteFile());

				Scanner scanner = new Scanner(in);
				try {
					while (scanner.hasNextLine()){
						line = Cesar.decrypt(scanner.nextLine(),compteur);
						for(String s : line.split(" ")) {
							text.add(s);
						}
					}
				}
				finally{
					scanner.close();
					in.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			

			/**
			 * On parcours le texte qui est censé être décrypté
			 * pour vérifier la proportion de mots
			 * qui existent dans le dico
			 */
			for(String s : text) {
				if (dictionnaire.contains(s)) {
					tabExist[0]++;
				} else {
					tabExist[1]++;
				}
			}
			
			/**
			 * si on a plus de 20% de mots qui existent dans le dico il est probable
			 * que l'on ait réussi à décoder
			 */
			if(tabExist[0]>text.size()*20/100) { 
				trouve = true;
				System.out.println("done");
			} else {
				text.clear();
				tabExist[0] = tabExist[1] = 0;
				compteur++;
			}
			
		}
		
		if (trouve) {
			System.out.println("Il semble que le texte ait été décrypté.");
			Cesar.decryptFile(fOutput.getAbsolutePath(), compteur);
		} else {
			System.out.println("Clé non trouvée");
		}


	}
	
	private static void initializeDictionnaire() {
		// initialisation de la taille de la liste a la taille du dictionnaire
		// pour éviter les changements de taille de celle-ci
		// au chargement du dictionnaire
		dictionnaire = new ArrayList<String>(94883);
		
		FileInputStream in = null;
		try {
			in = new FileInputStream("dico.txt");

			Scanner scanner = new Scanner(in);
			try {
				while (scanner.hasNextLine()){
					dictionnaire.add(scanner.nextLine());
				}
			}
			finally{
				scanner.close();
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

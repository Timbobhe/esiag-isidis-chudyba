package fr.esiag.security.cesar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fr.esiag.util.Alphabet;
import fr.esiag.util.Frequence;

public class cryptanalyseCesar {
	private static boolean initialise = false;
	private static int keyDictionnaire=0;
	private static List<String> dictionnaire;
	private static int keyFrequence=0;



	public static void initialiseDictionnaire() {
		dictionnaire = new ArrayList<String>(94883);

		FileInputStream in = null;
		try {
			in = new FileInputStream("dico.txt");

			Scanner scanner = new Scanner(in);
			try {
				while (scanner.hasNextLine()) {
					dictionnaire.add(scanner.nextLine());
				}
				initialise= true;
			} finally {
				scanner.close();
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static int getKeyDictionnaire(){
		return keyDictionnaire;
	}
	public static int getKeyFrequence(){
		return keyFrequence;
	}
	public static String cryptanalyseFrequence(String input){
		Frequence freq = new Frequence(input);
		Character cMax = freq.getMax();
		keyFrequence = Alphabet.getPosition(cMax) - 4 % 36;
		return Cesar.decrypt(input, keyFrequence);
		
	}
	public static String cryptanalyseDictionnaire(String input){
		while(!initialise)
			initialiseDictionnaire();
		String output=null;
		
		
		ArrayList<String> text = new ArrayList<String>();
		
		Integer tabExist[] = new Integer[2]; // 0 = true ; 1 = false
		tabExist[0] = tabExist[1] = 0;
		
		boolean trouve = false;
		int compteur = 1;
		String line;
		
		System.out.println("Il existe " + Alphabet.getAlphabet().length() + " possibles...");
		System.out.print("Essai numero : ");
		// on test chaque combinaison possible
		while(compteur<Alphabet.getAlphabet().length() && trouve == false) {
			System.out.print(compteur + " - ");
			/**
			 * on charge le contenu du fichier crypt�
			 * et on applique le d�cryptage avec la cl� � tester
			 */
			

				Scanner scanner = new Scanner(input);
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
					
				}
			
			

			/**
			 * On parcours le texte qui est cens� �tre d�crypt�
			 * pour v�rifier la proportion de mots
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
			 * que l'on ait r�ussi � d�coder
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
			System.out.println("Il semble que le texte ait ete decrypte.");
			keyDictionnaire = compteur;
			return Cesar.decrypt(input, compteur);
		} else {
			keyDictionnaire=0;
			System.out.println("Cle non trouve");
		}

		
		
		
		
		
		
		
		return output;
	}
}

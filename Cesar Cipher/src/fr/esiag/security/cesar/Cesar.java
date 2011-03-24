package fr.esiag.security.cesar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import fr.esiag.util.Alphabet;

public class Cesar {
	

	private Cesar() { super(); }

	/**
	 * Methode de chiffrement d une chaine de caracteres
	 * @param message Message a chiffrer
	 * @param decalage Decalage
	 * @return Message chiffre
	 */
	public static String encrypt(String message, int decalage) {
		StringBuilder sb = new StringBuilder();
		int position;
		char lettre;
		for(int i=0; i<message.length(); i++){
			if (message.charAt(i) == ' ') { // si il y a un espace on le garde
				lettre = ' ';
			} else {
				// on recupere la nouvelle lettre apres decalage
				position = (Alphabet.getPosition(message.charAt(i))+decalage) %	Alphabet.getAlphabet().length();
				lettre = Alphabet.getLettre(position);	
			}
			sb.append(lettre);
		}
		return sb.toString();
	}

	/**
	 * Methode de dechiffrement d une chaine de caracteres
	 * @param message Message a dechiffrer
	 * @param decalage Decalage
	 * @return Message dechiffre
	 */
	public static String decrypt(String message, int decalage) {
		StringBuilder sb = new StringBuilder();
		int position;
		char lettre;
		for(int i=0; i<message.length(); i++){
			if (message.charAt(i) == ' ') {
				lettre = ' ';
			} else {
				position = (Alphabet.getPosition(message.charAt(i))+Alphabet.getAlphabet().length()-decalage) % Alphabet.getAlphabet().length();
				if (position < 0) position += Alphabet.getAlphabet().length();
				lettre = Alphabet.getLettre(position);
			}
			sb.append(lettre);
		}
		return sb.toString();
	}
	
	public static File encryptFile(String path, int decalage) {
		StringBuilder text = new StringBuilder();
		FileInputStream in = null;
		File fileOut = new File(path + ".encrypt");
		try {
			in = new FileInputStream(path);

			Scanner scanner = new Scanner(in);
			
			Writer out = new OutputStreamWriter(new FileOutputStream(fileOut));
			try {
				while (scanner.hasNextLine()){
					text.append(encrypt(scanner.nextLine(),decalage));
					text.append(System.getProperty("line.separator"));
				}
			}
			finally{
				scanner.close();
				in.close();
			}
			out.write(text.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return fileOut;
	}
	
	public static File decryptFile(String path, int decalage) {
		StringBuilder text = new StringBuilder();
		FileInputStream in = null;
		File fileOut = new File(path.replace(".encrypt", ".decrypt"));
		try {
			in = new FileInputStream(path);

			Scanner scanner = new Scanner(in);
			
			Writer out = new OutputStreamWriter(new FileOutputStream(fileOut));
			try {
				while (scanner.hasNextLine()){
					text.append(decrypt(scanner.nextLine(),decalage));
					text.append(System.getProperty("line.separator"));
				}
			}
			finally{
				scanner.close();
				in.close();
			}
			out.write(text.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return fileOut;
	}

	
}

package main;

import java.io.File;
import java.util.Random;

import fr.esiag.security.cesar.Cesar;

public class BasicFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Random r = new Random();
		int decalage = r.nextInt(4000);

		File f = new File("GeneratedText.txt");
		
		File fOutput;
		
		// chiffrement du fichier
		fOutput = Cesar.encryptFile(f.getAbsolutePath(), decalage);
		
		// dechiffrement du fichier
		Cesar.decryptFile(fOutput.getAbsolutePath(), decalage);

	}

}

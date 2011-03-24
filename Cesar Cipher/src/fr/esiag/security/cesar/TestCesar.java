package fr.esiag.security.cesar;

import java.io.File;
import java.util.Random;


/**
 * manuel@miage.u-pec.fr
 * -> envoyer les binomes
 * -> uppercase : 26 lettres + chiffres + garder espaces dans la structure : S_OK
 * 
 * -> chiffrement en prŽcisant la clŽ : S_OK
 * -> dŽchiffrement en prŽcisant la clŽ : S_OK
 * -> appliquer le chiffrement/dŽchiffrement ˆ un fichier et rendre un fichier
 * -> cryptanalyse : dico txt du langage courant (8000 mots)  
 * 
 * @author eric
 *
 */

public class TestCesar {
	
	public static void main(String[] args) {

		
		// on genere un decalage aleatoire (enfin pseudo aleatoire ;-))
		Random r = new Random();
		int decalage = r.nextInt(4000);
		//int decalage = 200;
		File f = new File("GeneratedText.txt");
		
		testCesarFile(f.getAbsolutePath(), decalage);
		

		
	}
	
	public static void testCesarFile(String path, int decalage) {
		File fOutput;
		
		fOutput = Cesar.encryptFile(path, decalage);
		
		Cesar.decryptFile(fOutput.getAbsolutePath(), decalage);
		
	}

	
}

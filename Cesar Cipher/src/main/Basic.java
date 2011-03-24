package main;

import java.util.Random;

import fr.esiag.security.cesar.Cesar;
import fr.esiag.util.Alphabet;
import fr.esiag.util.Frequence;

public class Basic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "et si je prenais une feuille";
		
		// on genere un decalage aleatoire (enfin pseudo aleatoire ;-))
		Random r = new Random();
		int decalage = r.nextInt(4000);
		
		// chiffrement
		String strEncrypt = Cesar.encrypt(str, decalage);
		
		// calcule le nombre d'occurrence de chaque lettre dans la chaine chiffree
		Frequence freq = new Frequence(strEncrypt);
		
		// on recupere la lettre qui revient le plus souvent
		Character cMax = freq.getMax();
		
		// on defini la cle a utiliser pour le decryptage
		// le texte est long donc on peut se permettre ce calcul direct
		// pour recoller avec la lette 'e'
		Integer cle = Alphabet.getPosition(cMax) - 4 % 36;
		
		// affichage des resultats
		System.out.println("****************************************************************");
		System.out.println("*\t Algorithme de chiffrement/dechiffrement de Cesar\n*");
		System.out.println("****************************************************************\n");
		System.out.println(" -- Chiffrement --");
		System.out.println("* Decalage a utiliser : " + decalage);
		System.out.println("* Chaine a chiffrer : " + str + "\n");
		System.out.println("* Chaine chiffree : " + strEncrypt + "\n");
		
		System.out.println("****************************************************************\n");
		System.out.println(" -- Dechiffrement--");
		
		System.out.println("* Chaine dechiffree : " + Cesar.decrypt(strEncrypt, decalage) + "\n");

		System.out.println("****************************************************************\n");
		System.out.println(" -- Decryptage --");
		
		System.out.println("* Lettre max : " + cMax + " position : " + Alphabet.getPosition(cMax)  + "\n");
		System.out.println("* Cle probable : " + cle);
		
		System.out.println("* Chaine decryptee : " + Cesar.decrypt(strEncrypt, cle));

	}

}

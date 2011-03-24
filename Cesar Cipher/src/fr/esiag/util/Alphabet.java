package fr.esiag.util;

public class Alphabet {
	/**
	 * Reprsentation de l'alphabet contenant
	 * l'ensemble des caractres pouvant tre utiliss
	 * dans le chiffrement et dchiffrement
	 * du texte
	 */
	private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890/*-+&ˆ‰Š‘Ÿ•”™š$£()%ÔÕ'\".?!;:,ÇÈ";
	private Alphabet() { super(); }

	
	/**
	 * Mthode retournant l'indice d'une lettre dans l'alphabet
	 * @param character
	 * @return Position de la lettre dans l'alphabet
	 */
	public static int getPosition(char character){
		return alphabet.indexOf(character);
	}
	
	/**
	 * Mthode retournant la lettre qui correspond ˆ la position donne
	 * @param position
	 * @return La lettre de l'alphabet 
	 */
	public static char getLettre(int position){
		return alphabet.charAt(position);
	}
	
	public static String getAlphabet() {
		return alphabet;
	}

}

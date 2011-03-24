package fr.esiag.util;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class Frequence {
	
	private Hashtable<Character,Integer> occurrence;
	private Hashtable<Character,Float> freqOccurrence;
	
	public Frequence() { 
		super();
		occurrence = new Hashtable<Character, Integer>();
		freqOccurrence = new Hashtable<Character, Float>();
	}
	public Frequence(String msg) {
		this();
		calcOccurrences(msg);
	}

	public Hashtable<Character, Integer> getOccurrence() {
		return occurrence;
	}

	public void putOccurrence(Character car, Integer occurrence) {
		this.occurrence.put(car, occurrence);
	}
	
	public void calcOccurrences(String msg) {
		int nbOcc;
		for(int i = 0;i<msg.length();i++) {
			nbOcc = (occurrence.containsKey(msg.charAt(i))) ? occurrence.get(msg.charAt(i))+1 : 1;
			occurrence.put(msg.charAt(i),nbOcc);
		}
		occurrence.remove(' ');
		calcFreq(msg.length());
	}
	
	private void calcFreq(int taille) {
		for(Entry<Character,Integer> e : occurrence.entrySet()) {
			freqOccurrence.put(e.getKey(),(e.getValue() * 100f / taille));
		}
	}
	
	public Character getMax() {
		Character c=null;
		Set<Entry<Character,Integer>> s = occurrence.entrySet();
        for(Entry<Character,Integer> e : s) {
        	if (null == c || c.equals(' ')) c = e.getKey();
        	else {
        		if(occurrence.get(c) < e.getValue()) {
        			c = e.getKey();
        		}	
        	}
              	
        }
        return c;
	}

	
	
	

}

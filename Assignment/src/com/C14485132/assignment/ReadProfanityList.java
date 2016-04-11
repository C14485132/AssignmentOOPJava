package com.C14485132.assignment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadProfanityList {
	
	static ArrayList<String> profanity = new ArrayList<String>();
	
	//opening badwords.txt, adding all words in the file to an ArrayList, then returning the arraylist
	public ArrayList<String> getProfList() {
		try {
			InputStream badwordsLoc = this.getClass().getResourceAsStream("badwords.txt");
			Scanner fp = new Scanner(badwordsLoc);
			
			while (fp.hasNext()) {
				profanity.add(fp.next());
			}
			
			fp.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return profanity;
	}
	
}

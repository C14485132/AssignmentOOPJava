package com.C14485132.assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

public class OpenFileToFilter {
	
	String filterFileLoc;
	static ArrayList<String> posts = new ArrayList<String>();
	
	public ArrayList<String> getPostsToFilter (String fileLocation) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
			Scanner fp = new Scanner(reader);
			
			while (fp.hasNext()) {
				posts.add(fp.nextLine());
			}
			
			for (String s : posts) {
				System.out.println(s + "BREAK");
			}
			
			fp.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		return posts;
		
	}
	
	/*Reference stuff
	
	static ArrayList<String> profanity = new ArrayList<String>();
	
	//opening badwords.txt, adding all words in the file to an ArrayList, then returning the arraylist
	public ArrayList<String> () {
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
	
	*/
	
}
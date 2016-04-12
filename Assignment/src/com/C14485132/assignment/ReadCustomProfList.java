package com.C14485132.assignment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCustomProfList {
	//Some code acquired from:
	//http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
	
	static ArrayList<String> profanityUser = new ArrayList<String>();
	
	//opening badwords.txt, adding all words in the file to an ArrayList, then returning the arraylist
	public ArrayList<String> getCustProfList() {
		String fileLoc = new String(MainScreen.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1));
		fileLoc = fileLoc.replaceAll("/AssignmentOOPJava/bin", "/AssignmentOOPJava/Assignment/src");
		fileLoc = fileLoc +  "/com/C14485132/assignment/CustomFilter.txt";
		
		try {
			InputStream badwordsLoc = this.getClass().getResourceAsStream("CustomFilter.txt");
			Scanner fp = new Scanner(badwordsLoc);
			
			while (fp.hasNext()) {
				profanityUser.add(fp.next());
			}
			
			fp.close();
		}
		catch (Exception e) {
			//If no file exists, create a new file
			File text = new File(fileLoc);
			try {
				text.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return profanityUser;
	}
	
	public void setCustProfList(ArrayList<String> newPU){
		profanityUser = newPU;
	}
	
}
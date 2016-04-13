package com.C14485132.assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class OpenFileToFilter {
	
	String filterFileLoc;
	static ArrayList<String> posts = new ArrayList<String>();
	
	public ArrayList<String> getPostsToFilter (String fileLocation) {
		
		//Tries to open the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
			Scanner fp = new Scanner(reader);
			
			//Reading all of the lines within the file, and adding them to an array list
			while (fp.hasNext()) {
				posts.add(fp.nextLine());
			}
			
			fp.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return posts;
	}
	
}
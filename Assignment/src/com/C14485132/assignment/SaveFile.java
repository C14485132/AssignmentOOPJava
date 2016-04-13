package com.C14485132.assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveFile {
	public void saveFileInLoc (String fileLocation, ArrayList<String> saving) {
		
		try {
			//Wipe the current file
			PrintWriter writer = new PrintWriter(fileLocation);
			writer.print("");
			writer.close();
			
			//Now reopenit and add stuff to it
			FileWriter fw = new FileWriter(fileLocation, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    
		    for (String s : saving) {
		    	out.println(s);
		    }
		    
		    out.close();

		} catch (IOException e) {
			e.printStackTrace();
			//If file doesn't open, do nothing because if the file doesn't exist, problem solved, file is technically blanked.

		}
	}
}

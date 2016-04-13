package com.C14485132.assignment;

import java.util.ArrayList;

public class MainFiltering {
	
	private static ArrayList<String> postsFiltered = new ArrayList<String>();
	boolean shouldBeAdded;
	int capsCount;
	
	public ArrayList<String> filtering (ArrayList<String> posts, ArrayList<String> defaultFilter, boolean isDefaultUsed, ArrayList<String> user, boolean isUserUsed, boolean isCapsUsed) {
		
		//Checking each submission against the default filter
		for (String submission : posts) {
			shouldBeAdded = true;
			capsCount = 0;
			
			//Checking for default filter issues
			for (String badWordDefault : defaultFilter) {
				if (submission.contains(badWordDefault)) {
					if(isDefaultUsed) {
						shouldBeAdded = false;
					}
				}
			}
			
			//Checking for user filter issues
			for (String badWordUser : user) {
				if (submission.contains(badWordUser)) {
					if(isUserUsed){
						shouldBeAdded = false;
					}
				}
			}
			
			//Checking for caps, but don't bother checking for caps if the submission is under 5 letters
			if (isCapsUsed && submission.length() > 5 ) {
				for (int i=0;i<submission.length();i++) {
					if (Character.isUpperCase(submission.charAt(i))) {
						capsCount++;
					}
				}
				
				//50% caps threshold
				if (submission.length()*0.5 <= capsCount) {
					shouldBeAdded = false;
				}
			}
			
			//Adding to arraylist so it can be returned
			if (shouldBeAdded) {
				postsFiltered.add(submission);
			}
		}
		
		return postsFiltered;
	}
	
}

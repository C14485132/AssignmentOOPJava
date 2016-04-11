package com.C14485132.assignment;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OptionsScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtWordtoadd;
	private JButton btnAddword;
	static ArrayList<String> profanityUser = new ArrayList<String>();
	private JButton btnClearCustomFilter;
	private JButton btnOpenCustomFilter;
	

	public OptionsScreen(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAddProfanityRule = new JLabel("Add profanity rule: ");
		contentPane.add(lblAddProfanityRule);
		
		
		txtWordtoadd = new JTextField();
		txtWordtoadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProfanity(txtWordtoadd.getText());
				
			}
		});
		contentPane.add(txtWordtoadd);
		txtWordtoadd.setColumns(10);
		
		btnAddword = new JButton("Add Word");
		btnAddword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProfanity(txtWordtoadd.getText());
				
			}
		});
		contentPane.add(btnAddword);
		
		btnClearCustomFilter = new JButton("Clear Custom Filter");
		btnClearCustomFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "<html>Continuing will mean that your custom filter will be wiped!<br>Do you wish to continue?</html>", "Warning", JOptionPane.YES_NO_OPTION);
				if (dialogResult == 0) {
					//Wipe the text file
					wipeFile();
				}
			}
		});
		contentPane.add(btnClearCustomFilter);

		
	}
	
	public void addProfanity(String wordToAdd) {
		//Code acquired from:
		//http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
		//http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		
		//Tries to open a file named CustomFilter.txt if it can't do that, create it and open
		String fileLoc = new String(MainScreen.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1));
		fileLoc = fileLoc.replaceAll("/AssignmentOOPJava/bin", "/AssignmentOOPJava/Assignment/src");
		fileLoc = fileLoc +  "/com/C14485132/assignment/CustomFilter.txt";
		File customFilter = new File(fileLoc);
		boolean isWordAlreadyThere = false;
		
		PrintWriter out = null;
		//If the file exists
		if ( customFilter.exists() && !customFilter.isDirectory() ) {
			try {
				out = new PrintWriter(new FileOutputStream(new File(fileLoc), true));
				
				InputStream badwordsLoc = this.getClass().getResourceAsStream("CustomFilter.txt");
				Scanner fp = new Scanner(badwordsLoc);
				
				while (fp.hasNext()) {
					profanityUser.add(fp.next());
				}
				
				fp.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		    //Only add words that are not in the file to the array list and file
		    wordToAdd = wordToAdd.toLowerCase();
		    
		    //Checking to see if the word is already in the user filter list
		    for(String s : profanityUser) {
		    	if(wordToAdd.equalsIgnoreCase(s)) {
		    		isWordAlreadyThere = true;
		    	}
		    }
		    
		    if (!isWordAlreadyThere) {
			    out.println(wordToAdd);
			    profanityUser.add(wordToAdd);
			    JOptionPane.showMessageDialog(new JFrame(), "<html><center>Word added!</center></html>", "Success!", JOptionPane.PLAIN_MESSAGE);
		    } else {
		    	JOptionPane.showMessageDialog(new JFrame(), "<html><center>That word is already in the filter.</center></html>", "Just a heads up", JOptionPane.PLAIN_MESSAGE);
		    }
		    
		    out.close();
		}
		else {
		    try {
				out = new PrintWriter(fileLoc);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		    wordToAdd = wordToAdd.toLowerCase();
		    
		    //Checking to see if the word is already in the user filter list
		    for(String s : profanityUser) {
		    	if(wordToAdd.equalsIgnoreCase(s)) {
		    		isWordAlreadyThere = true;
		    	}
		    }
		    
		    if (!isWordAlreadyThere) {
			    out.println(wordToAdd);
			    profanityUser.add(wordToAdd);
			    JOptionPane.showMessageDialog(new JFrame(), "<html><center>Word added!</center></html>", "Success!", JOptionPane.PLAIN_MESSAGE);
		    } else {
		    	JOptionPane.showMessageDialog(new JFrame(), "<html><center>That word is already in the filter.</center></html>", "Just a heads up", JOptionPane.PLAIN_MESSAGE);
		    }
		    out.close();
		}
		
	}
	
	public void wipeFile() {
		String fileLoc = new String(MainScreen.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1));
		fileLoc = fileLoc.replaceAll("/AssignmentOOPJava/bin", "/AssignmentOOPJava/Assignment/src");
		fileLoc = fileLoc +  "com/C14485132/assignment/CustomFilter.txt";
		
		//http://stackoverflow.com/questions/29878237/java-how-to-clear-a-text-file-without-deleting-it
		//Clears the file without deleting it
		try {
			PrintWriter writer = new PrintWriter(fileLoc);
			writer.print("");
			writer.close();
			profanityUser.clear();
			
			//now open the file and check if it's blank
			InputStream file = this.getClass().getResourceAsStream("CustomFilter.txt");
			Scanner fp = new Scanner(file);
			
			
			if (!fp.hasNext()) {
				JOptionPane.showMessageDialog(new JFrame(), "<html><center>File cleared!</center></html>", "Success!", JOptionPane.PLAIN_MESSAGE);
			} else {
				//Just tell the user it wasn't wiped
				JOptionPane.showMessageDialog(new JFrame(), "<html><center>File was not properly wiped!</center></html>", "Error!", JOptionPane.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			e.printStackTrace();
			//If file doesn't open, do nothing because if the file doesn't exist, problem solveed, file is technically blanked.
		}
	}

}

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
import java.io.BufferedWriter;
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
	String fileLoc;
	ReadCustomProfList list = new ReadCustomProfList();

	public OptionsScreen(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		fileLoc = new String(MainScreen.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1));
		fileLoc = fileLoc.replaceAll("/AssignmentOOPJava/bin", "/AssignmentOOPJava/Assignment/src");
		fileLoc = fileLoc +  "/com/C14485132/assignment/CustomFilter.txt";
		
		JLabel lblAddProfanityRule = new JLabel("Add profanity rule: ");
		contentPane.add(lblAddProfanityRule);
		
		
		txtWordtoadd = new JTextField();
		txtWordtoadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profanityUser = list.getCustProfList();
				addProfanity(txtWordtoadd.getText());
				
			}
		});
		contentPane.add(txtWordtoadd);
		txtWordtoadd.setColumns(10);
		
		btnAddword = new JButton("Add Word");
		btnAddword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profanityUser = list.getCustProfList();
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
		//http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		boolean isWordAlreadyThere = false;
		//Tries to open a file named CustomFilter.txt if it can't do that, create it and open
		try(FileWriter fw = new FileWriter(fileLoc, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
		{
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
			    list.setCustProfList(profanityUser);
			    JOptionPane.showMessageDialog(new JFrame(), "Word added!", "Success!", JOptionPane.PLAIN_MESSAGE);
		    } else {
		    	JOptionPane.showMessageDialog(new JFrame(), "That word is already in the filter.", "Just a heads up", JOptionPane.PLAIN_MESSAGE);
		    }
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Wipes the CustomFilter text file
	public void wipeFile() {
		
		//http://stackoverflow.com/questions/29878237/java-how-to-clear-a-text-file-without-deleting-it
		//Clears the file without deleting it
		try {
			PrintWriter writer = new PrintWriter(fileLoc);
			writer.print("");
			writer.close();
			profanityUser.clear();
		    list.setCustProfList(profanityUser);

			JOptionPane.showMessageDialog(new JFrame(), "File cleared!", "Success!", JOptionPane.PLAIN_MESSAGE);

		} catch (IOException e) {
			e.printStackTrace();
			//If file doesn't open, do nothing because if the file doesn't exist, problem solved, file is technically blanked.
		}
	}
}

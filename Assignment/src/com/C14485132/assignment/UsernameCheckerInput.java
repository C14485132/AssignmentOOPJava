package com.C14485132.assignment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class UsernameCheckerInput extends JFrame {

	private JPanel contentPane;
	private double thresholdPercent;
	private JCheckBox chckbxCapscheck;
	private JCheckBox chckbxProfanitycheck;

	public UsernameCheckerInput(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 250, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUnamereqs = new JLabel("<html><center>Please enter the username to check<br>(max 15 characters, alphanumeric/underscore only)</center></html>");
		contentPane.add(lblUnamereqs);
		
		JTextField txtUserInput = new JTextField(15);
		//Press enter to run
		txtUserInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameFilters(txtUserInput.getText());
			}
		});
		contentPane.add(txtUserInput);
		
		JButton btnSubmit = new JButton("Check");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameFilters(txtUserInput.getText());
			}
		});
		contentPane.add(btnSubmit);
		
		setChckbxCapscheck(new JCheckBox("Check for excessive caps"));
		chckbxCapscheck.setSelected(true);
		contentPane.add(chckbxCapscheck);
		
		setChckbxProfanitycheck(new JCheckBox("Check for profanity"));
		chckbxProfanitycheck.setSelected(true);
		contentPane.add(chckbxProfanitycheck);
		
		JLabel lblThresholdtext = new JLabel("Caps warning threshold:");
		contentPane.add(lblThresholdtext);
		
		Choice choiceThreshold = new Choice();
		setThresholdPercent(0.25);
		choiceThreshold.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Swapping % string to double
				switch (choiceThreshold.getSelectedItem()) {
					case "25%": {
						setThresholdPercent(0.25);
						break;
					} case "50%": {
						setThresholdPercent(0.5);
						break;
					} case "75%": {
						setThresholdPercent(0.75);
						break;
					} case "90%": {
						setThresholdPercent(0.90);
						break;
					} case "100%": {
						setThresholdPercent(1);
						break;
					}
				}//End switch
			}
		});
		choiceThreshold.addItem("25%");
		choiceThreshold.addItem("50%");
		choiceThreshold.addItem("75%");
		choiceThreshold.addItem("90%");
		choiceThreshold.addItem("100%");
		contentPane.add(choiceThreshold);
	}
	
	//Checks all of the filters
	public void usernameFilters(String s) {
		boolean badUsername = false;
		boolean badLength = false;
		boolean badChars = false;
		boolean badLang = false;
		boolean badCaps = false;
		int capsCount = 0;
		
		//Checking the length of the string
		if (s.length() > 15) {
			badUsername = true;
			badLength = true;
		}
		
		//Checking if all the letter are valid (alphanumeric underscore only)
		for (int i=0;i<s.length();i++) {
			if (!(Character.isDigit(s.charAt(i))) && !(Character.isLetter(s.charAt(i))) && !(s.charAt(i) == '_')) {
				badUsername = true;
				badChars = true;
			}
		}
		
		//Checking the username against the profanity filters
		if (chckbxProfanitycheck.isSelected()) {
			//TODO: profanity stuff
		}
		
		//Checking to see if <50% of characters are caps.
		if (chckbxCapscheck.isSelected()) {
			for (int i=0;i<s.length();i++) {
				if (Character.isUpperCase(s.charAt(i))) {
					capsCount++;
				}
			}
			
			if (s.length()*getThresholdPercent() <= capsCount) {
				badUsername = true;
				badCaps = true;
			}
		}
		
		//Opening the dialogue option
		//If any of the elements are true, open dialogue
		if (badUsername) {
			String errorString;
			errorString = "The username has the following errors:\n";
			
			if (badLength) {
				errorString = errorString + "\nUsername too long";
			}
			
			if (badChars) {
				errorString = errorString + "\nIllegal characters";
			}
			
			if (badLang) {
				errorString = errorString + "\nProfanity in username";
			}
			
			if (badCaps) {
				errorString = errorString + "\nToo many caps";
			}
			
			JOptionPane.showMessageDialog(new JFrame(), errorString, "Error!", JOptionPane.WARNING_MESSAGE);
			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "<html><center>Username does not break any rules!</center></html>", "Success!", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	double getThresholdPercent() {
		return thresholdPercent;
	}

	void setThresholdPercent(double thresholdPercent) {
		this.thresholdPercent = thresholdPercent;
	}

	JCheckBox getChckbxCapscheck() {
		return chckbxCapscheck;
	}

	void setChckbxCapscheck(JCheckBox chckbxCapscheck) {
		this.chckbxCapscheck = chckbxCapscheck;
	}

	JCheckBox getChckbxProfanitycheck() {
		return chckbxProfanitycheck;
	}

	void setChckbxProfanitycheck(JCheckBox chckbxProfanitycheck) {
		this.chckbxProfanitycheck = chckbxProfanitycheck;
	}

}

package com.C14485132.assignment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
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

				System.out.println(getThresholdPercent());
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
		//0=badLength
		//1=containsBadChars
		//2=profanityFound
		//3=capsWarn
		Boolean[] bArr = new Boolean[4];
		int capsCount = 0;
		
		//Checking the length of the string
		if (s.length() > 15) {
			bArr[0] = true;
		}
		
		//Checking if all the letter are valid (alphanumeric underscore only)
		for (int i=0;i<s.length();i++) {
			if (!(Character.isDigit(s.charAt(i))) && !(Character.isLetter(s.charAt(i))) && !(s.charAt(i) == '_')) {
				bArr[1] = true;
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
				bArr[3] = true;
			}
		}
		
		//Opening the dialogue option
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

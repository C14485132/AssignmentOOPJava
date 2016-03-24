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

public class UsernameCheckerInput extends JFrame {

	private JPanel contentPane;

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
		
		JCheckBox chckbxCapscheck = new JCheckBox("Check for excessive caps");
		contentPane.add(chckbxCapscheck);
		
		JCheckBox chckbxProfanitycheck = new JCheckBox("Check for Profanity");
		contentPane.add(chckbxProfanitycheck);
	}
	
	//Checks all of the 
	public Boolean[] usernameFilters(String s) {
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
		
		//TODO: profanity stuff
		
		//Checking to see if <50% of characters are caps.
		for (int i=0;i<s.length();i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				capsCount++;
			}
		}
		
		if (s.length()/2 < capsCount) {
			bArr[3] = true;
		}
		
		return bArr;
	}

}

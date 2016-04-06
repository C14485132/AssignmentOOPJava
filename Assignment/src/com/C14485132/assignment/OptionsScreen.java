package com.C14485132.assignment;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OptionsScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtWordtoadd;
	private JButton btnAddword;

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
				
			}
		});
		txtWordtoadd.setText("wordToAdd");
		contentPane.add(txtWordtoadd);
		txtWordtoadd.setColumns(10);
		
		btnAddword = new JButton("Add Word");
		btnAddword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.add(btnAddword);
		
		
	}

}

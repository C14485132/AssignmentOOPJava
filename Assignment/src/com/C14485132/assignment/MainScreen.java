package com.C14485132.assignment;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//File menu bar
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNewMenu = new JMenu("Open...");
		mnFile.add(mnNewMenu);
		
		JMenuItem mntmCheckTextFile = new JMenuItem("Check text file");
		mntmCheckTextFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Choose a text file", FileDialog.LOAD);
				fd.setFile("*.txt");
				fd.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmCheckTextFile);
		
		JMenuItem mntmCheckUsername = new JMenuItem("Check username");
		mnNewMenu.add(mntmCheckUsername);

		JMenuItem mntmCheck = new JMenuItem("Check pasted text");
		mnNewMenu.add(mntmCheck);
		
		JMenu mnSave = new JMenu("Save");
		mnFile.add(mnSave);
		
		//Filter Menu bar
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JCheckBoxMenuItem chckbxmntmFilter = new JCheckBoxMenuItem("Filter 1");
		mnFilters.add(chckbxmntmFilter);
		
		//Options Menu bar
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
	}

}

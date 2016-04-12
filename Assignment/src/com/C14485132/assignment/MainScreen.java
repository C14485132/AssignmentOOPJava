package com.C14485132.assignment;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;


public class MainScreen {

	private JFrame frame;
	private static ArrayList<String> profanityUser = new ArrayList<String>();
	private static ArrayList<String> profanity= new ArrayList<String>();

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
		frame = new JFrame("Post Filter");
		frame.setBounds(100, 100, 750, 500);
		frame.setMinimumSize(new Dimension(750,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		OptionsScreen options = new OptionsScreen("Filter Options");
		ReadProfanityList profList = new ReadProfanityList();
		ReadCustomProfList custProfList = new ReadCustomProfList();
		profanity = profList.getProfList();
		profanityUser = custProfList.getCustProfList();
		
		JTextArea textAreaIn = new JTextArea(5, 23);
		JScrollPane scrollPaneIn = new JScrollPane(textAreaIn);
		scrollPaneIn.setVisible(true);
		frame.getContentPane().add(textAreaIn, BorderLayout.WEST);
		
		JTextArea textAreaOut = new JTextArea(5, 23);
		JScrollPane scrollPaneOut = new JScrollPane(textAreaOut);
		textAreaOut.setEditable(false);
		scrollPaneOut.setVisible(true);
		frame.getContentPane().add(textAreaOut, BorderLayout.EAST);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		/**
		 * File menu bar
		 * Contains check username, open file and pasted text
		 */
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
				
				//Once a file is selected, open it up, read the contents, and put it in the textAreaIn
				OpenFileToFilter referenceFile = new OpenFileToFilter();
				referenceFile.getPostsToFilter(fd.getDirectory() + fd.getFile());
				
			}
		});
		mnNewMenu.add(mntmCheckTextFile);
		
		JMenuItem mntmCheckUsername = new JMenuItem("Check username");
		mntmCheckUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsernameCheckerInput userInput = new UsernameCheckerInput("Username Checker");
				userInput.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCheckUsername);

		JMenuItem mntmCheck = new JMenuItem("Check pasted text");
		mnNewMenu.add(mntmCheck);
		
		JMenu mnSave = new JMenu("Save");
		mnFile.add(mnSave);
		
		/**
		 * Filter Menu bar
		 * The user selects what filters should be active for the file checking
		 */
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JCheckBoxMenuItem chckbxmntmFilter1 = new JCheckBoxMenuItem("Default Filter");
		mnFilters.add(chckbxmntmFilter1);
		
		JCheckBoxMenuItem chckbxmntmFilter2 = new JCheckBoxMenuItem("Custom Filter");
		mnFilters.add(chckbxmntmFilter2);
		
		JCheckBoxMenuItem chckbxmntmFilterCaps = new JCheckBoxMenuItem("Caps Filter");
		mnFilters.add(chckbxmntmFilterCaps);
		
		/**
		 * Options menu
		 * 
		 */
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmFilterOptions = new JMenuItem("Filter Options");
		mntmFilterOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				options.setVisible(true);
			}
		});
		mnOptions.add(mntmFilterOptions);
	}

}

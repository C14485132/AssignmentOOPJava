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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class MainScreen {

	private JFrame frame;
	private static ArrayList<String> profanityUser = new ArrayList<String>();
	private static ArrayList<String> profanity = new ArrayList<String>();
	private static ArrayList<String> posts = new ArrayList<String>();
	private static ArrayList<String> postsFiltered = new ArrayList<String>();
	String openFileLoc;

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
		//Creating the Frame
		frame = new JFrame("Post Filter");
		frame.setBounds(100, 100, 750, 500);
		frame.setMinimumSize(new Dimension(750,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		//Creating some things to be used later
		OptionsScreen options = new OptionsScreen("Filter Options");
		ReadProfanityList profList = new ReadProfanityList();
		ReadCustomProfList custProfList = new ReadCustomProfList();
		profanity = profList.getProfList();
		profanityUser = custProfList.getCustProfList();
		JCheckBoxMenuItem chckbxmntmFilter1= new JCheckBoxMenuItem("Default Filter");
		JCheckBoxMenuItem chckbxmntmFilter2 = new JCheckBoxMenuItem("Custom Filter");
		JCheckBoxMenuItem chckbxmntmFilterCaps = new JCheckBoxMenuItem("Caps Filter");
		
		//text area for displaying contents of file
		JTextArea textAreaIn = new JTextArea(5, 23);
		JScrollPane scrollPaneIn = new JScrollPane(textAreaIn);
		frame.getContentPane().add(scrollPaneIn, BorderLayout.WEST);
		
		//Text area for displaying filter output
		JTextArea textAreaOut = new JTextArea(5, 23);
		JScrollPane scrollPaneOut = new JScrollPane(textAreaOut);
		textAreaOut.setEditable(false);
		frame.getContentPane().add(scrollPaneOut, BorderLayout.EAST);
		
		//http://stackoverflow.com/questions/13011705/how-to-add-an-imageicon-to-a-jframe
		//Convert button with custom image because the button with text on a button that size looks bad
		java.net.URL imgUrl = getClass().getResource("convertImage.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		JButton btnFilterText = new JButton(icon);
		btnFilterText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Clear the current JTextArea
				textAreaOut.setText("");
				postsFiltered.clear();
				
				//Calling the filtering once clicked
				MainFiltering filter = new MainFiltering();
				postsFiltered = filter.filtering(posts, profanity, chckbxmntmFilter1.isSelected(), profanityUser, chckbxmntmFilter2.isSelected(), chckbxmntmFilterCaps.isSelected());
				
				//Adding posts to right box
				for (String addingPostsFiltered : postsFiltered) {
					textAreaOut.append(addingPostsFiltered);
					textAreaOut.append("\n");
				}
			}
		});
		frame.getContentPane().add(btnFilterText, BorderLayout.CENTER);
		btnFilterText.setSize(new Dimension(40, 40));
		
		
		//Menu bars for the stuff up at the top
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
		
		//Selecting the text file to filter
		JMenuItem mntmCheckTextFile = new JMenuItem("Check text file");
		mntmCheckTextFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//FileDialog to select the file
				FileDialog fd = new FileDialog(frame, "Choose a text file", FileDialog.LOAD);
				fd.setFile("*.txt");
				fd.setVisible(true);
				openFileLoc = fd.getDirectory() + fd.getFile();
				
				//Clear the current JTextArea
				posts.clear();
				textAreaIn.setText("");
				
				//Once a file is selected, open it up, read the contents, and put it in the textAreaIn
				OpenFileToFilter referenceFile = new OpenFileToFilter();
				posts = referenceFile.getPostsToFilter(fd.getDirectory() + fd.getFile());
				
				
				//Adding posts to left box
				for (String addingPosts : posts) {
					textAreaIn.append(addingPosts);
					textAreaIn.append("\n");
				}
				
			}
		});
		mnNewMenu.add(mntmCheckTextFile);
		
		//Username checker, opens in its own window
		JMenuItem mntmCheckUsername = new JMenuItem("Check username");
		mntmCheckUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsernameCheckerInput userInput = new UsernameCheckerInput("Username Checker");
				userInput.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCheckUsername);
		
		//Saving the text file to overwrite the current filter input file
		JMenuItem mnSave = new JMenuItem("Save");
		mnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveFile newSave = new SaveFile();
				if (openFileLoc == null) {
					JOptionPane.showMessageDialog(new JFrame(), "No file opened!", "Error!", JOptionPane.WARNING_MESSAGE);
				} else {
					newSave.saveFileInLoc(openFileLoc, postsFiltered);
				}
			}
		});
		mnFile.add(mnSave);
		
		/**
		 * Filter Menu bar
		 * The user selects what filters should be active for the file checking
		 */
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		mnFilters.add(chckbxmntmFilter1);
		chckbxmntmFilter1.setSelected(true);
		mnFilters.add(chckbxmntmFilter2);
		chckbxmntmFilter2.setSelected(true);
		mnFilters.add(chckbxmntmFilterCaps);
		chckbxmntmFilterCaps.setSelected(true);
		
		/**
		 * Options menu
		 * Pops out in a new window, for adding stuff to the user filter
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

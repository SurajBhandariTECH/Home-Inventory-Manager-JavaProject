//home Inventory Manager Code
package com.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;


public class Homeinventory {

	private JFrame frmHomeInventoryManager;
	private JTextField itemTextField;
	private JTextField serialTextField;
	private JTextField priceTextField;
	private  JTextField storeTextField;
	private JTextField noteTextField;
	@SuppressWarnings("rawtypes")
	private JComboBox locationComboBox;
	private JDateChooser dateDateChooser;
	private JCheckBox markedCheckBox;
	private JButton photoButton;
	
	//toolbar buttons
	private JToolBar InventoryToolBar;
	private JButton newButton;
	private JButton deleteButton;
	private JButton saveButton;
	private JButton nextButton;
	private JButton previousButton;
	private JButton printButton;
	private JButton exitButton;
	
	private JLabel itemLabel;
	private JLabel locationLabel;
	private JLabel serialLabel;
	private JLabel priceLabel;
	private JLabel dateLabel;
	private JLabel storeLabel;
	private JLabel noteLabel;
	private JLabel photoLabel;
	
	private JPanel searchPanel;
	private JButton[] searchButton;
	private PhotoPanel photoPanel;
	
	
	static int numberOfEntries;
	static InventoryItem[] myInventory = new InventoryItem[InventoryConstant.MAX_ENTRIES];
	int currentEntry;
	static JTextArea photoTextArea;
	
	static final int entriesPerPage = 2;
	static int lastPage;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homeinventory window = new Homeinventory();
					window.frmHomeInventoryManager.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Homeinventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {

		
		frmHomeInventoryManager = new JFrame();
		frmHomeInventoryManager.setResizable(false);
		frmHomeInventoryManager.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		frmHomeInventoryManager.setTitle("Home Inventory Manager");
		frmHomeInventoryManager.setBounds(100, 50, 900, 600);
		frmHomeInventoryManager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 400, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 267, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmHomeInventoryManager.getContentPane().setLayout(gridBagLayout);
		
		InventoryToolBar = new JToolBar();
		InventoryToolBar.setBackground(new Color(0, 102, 51));
		InventoryToolBar.setOrientation(SwingConstants.VERTICAL);
		InventoryToolBar.setFloatable(false);
		GridBagConstraints gbc_InventoryToolbar = new GridBagConstraints();
		gbc_InventoryToolbar.gridheight = 9;
		gbc_InventoryToolbar.fill = GridBagConstraints.VERTICAL;
		gbc_InventoryToolbar.insets = new Insets(0, 0, 0, 5);
		gbc_InventoryToolbar.gridx = 0;
		gbc_InventoryToolbar.gridy = 0;
		frmHomeInventoryManager.getContentPane().add(InventoryToolBar, gbc_InventoryToolbar);
	
		
		Dimension bSize = new Dimension(70,50);
		newButton = new JButton("New");
		Image newB = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\new-by-copy.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		newButton.setIcon(new ImageIcon(newB));
		sizeButton(newButton,bSize);
		newButton.setToolTipText("Add New Item");
		newButton.setHorizontalTextPosition(SwingConstants.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		newButton.setFocusable(false);
		InventoryToolBar.add(newButton);
		
		newButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						newButtonActionPerformed(e);
					}
				});
		
		deleteButton = new JButton("Delete");
		sizeButton(deleteButton, bSize);
		Image delete = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\delete-forever.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		deleteButton.setIcon(new ImageIcon(delete));
		deleteButton.setToolTipText("Delete Current Item");
		deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteButton.setFocusable(false);
		InventoryToolBar.add(deleteButton);
		deleteButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) {
							deleteButtonActionPerformed(e);
					}
				}
				);
		
		saveButton = new JButton("Save");
		saveButton.setToolTipText("Save Current Item");
		sizeButton(saveButton,bSize);
		Image save = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\save.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		saveButton.setIcon(new ImageIcon(save));
		saveButton.setFocusable(false);
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		InventoryToolBar.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inside actionPerformed: " );
				saveButtonActionPerformed(e);
			}
		});
		
		

		previousButton = new JButton("Previous");
		sizeButton(previousButton,bSize);
		Image previous = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\back.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		previousButton.setIcon(new ImageIcon(previous));
		previousButton.setToolTipText("Display Previous Item");
		previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
		previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		previousButton.setFocusable(false);
		InventoryToolBar.add(previousButton);
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousButtonActionPerformed(e);
			}
		});
		
		
		nextButton = new JButton("Next");
		sizeButton(nextButton, bSize);
		Image next = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\forward.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		nextButton.setIcon(new ImageIcon(next));
		nextButton.setToolTipText("Display Next Item");
		nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
		nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		nextButton.setFocusable(false);
		InventoryToolBar.add(nextButton);
		nextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
					nextButtonActionPerformed(e);
				}
			});
		
		printButton = new JButton("Print");
		Image printer = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\print.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		printButton.setIcon(new ImageIcon(printer));
		sizeButton(printButton, bSize);
		printButton.setToolTipText("Print Inventory List");
		printButton.setHorizontalTextPosition(SwingConstants.CENTER);
		printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		printButton.setFocusable(false);
		InventoryToolBar.add(printButton);
		printButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						printButtonActionPerformed(e);
				}
			});
		
		exitButton = new JButton("Exit");
		sizeButton(exitButton, bSize);
		Image exit = new ImageIcon("F:\\LIBRARY\\JMS Workspace\\Workspace\\HomeJava Project\\exit.png").getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
		exitButton.setIcon(new ImageIcon(exit));
		exitButton.setToolTipText("Exit Program");
		exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
		exitButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		exitButton.setFocusable(false);
		InventoryToolBar.add(exitButton);
		exitButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						exitButtonActionPerformed(e);
				}
			});
		
		itemLabel = new JLabel("Inventory Item");
		itemLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_itemLabel = new GridBagConstraints();
		gbc_itemLabel.anchor = GridBagConstraints.EAST;
		gbc_itemLabel.insets = new Insets(10, 10, 5, 10);
		gbc_itemLabel.gridx = 1;
		gbc_itemLabel.gridy = 1;
		frmHomeInventoryManager.getContentPane().add(itemLabel, gbc_itemLabel);
		
		itemTextField = new JTextField();
		GridBagConstraints gbc_itemTextField = new GridBagConstraints();
		gbc_itemTextField.gridwidth = 3;
		gbc_itemTextField.insets = new Insets(10, 0, 5, 10);
		gbc_itemTextField.anchor = GridBagConstraints.WEST;
		gbc_itemTextField.fill = GridBagConstraints.BOTH;
		gbc_itemTextField.gridx = 2;
		gbc_itemTextField.gridy = 1;
		frmHomeInventoryManager.getContentPane().add(itemTextField, gbc_itemTextField);
		itemTextField.setColumns(10);
		itemTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTextFieldActionPerformed(e);
			}
		});
		
		locationLabel = new JLabel("Location");
		locationLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.anchor = GridBagConstraints.EAST;
		gbc_locationLabel.insets = new Insets(10, 10, 5, 10);
		gbc_locationLabel.gridx = 1;
		gbc_locationLabel.gridy = 2;
		frmHomeInventoryManager.getContentPane().add(locationLabel, gbc_locationLabel);
		
		locationComboBox = new JComboBox();
		GridBagConstraints gbc_locationComboBox = new GridBagConstraints();
		gbc_locationComboBox.gridwidth = 2;
		gbc_locationComboBox.insets = new Insets(10, 0, 5, 10);
		gbc_locationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationComboBox.gridx = 2;
		gbc_locationComboBox.gridy = 2;
		locationComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		locationComboBox.setEditable(true);
		locationComboBox.setBackground(Color.WHITE);
		frmHomeInventoryManager.getContentPane().add(locationComboBox, gbc_locationComboBox);
		locationComboBox.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						locationComboBoxActionPerformed(e);
					}
				});
		
		markedCheckBox = new JCheckBox("Marked?");
		markedCheckBox.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_markedCheckBox = new GridBagConstraints();
		gbc_markedCheckBox.anchor = GridBagConstraints.WEST;
		gbc_markedCheckBox.insets = new Insets(10, 10, 5, 0);
		gbc_markedCheckBox.gridx = 4;
		gbc_markedCheckBox.gridy = 2;
		frmHomeInventoryManager.getContentPane().add(markedCheckBox, gbc_markedCheckBox);
		
		serialLabel = new JLabel("Serial Number");
		serialLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_serialLabel = new GridBagConstraints();
		gbc_serialLabel.anchor = GridBagConstraints.EAST;
		gbc_serialLabel.insets = new Insets(10, 10, 5, 10);
		gbc_serialLabel.gridx = 1;
		gbc_serialLabel.gridy = 3;
		frmHomeInventoryManager.getContentPane().add(serialLabel, gbc_serialLabel);
		
		serialTextField = new JTextField();
		GridBagConstraints gbc_serialTextField = new GridBagConstraints();
		gbc_serialTextField.gridwidth = 2;
		gbc_serialTextField.insets = new Insets(10, 0, 5, 10);
		gbc_serialTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_serialTextField.gridx = 2;
		gbc_serialTextField.gridy = 3;
		frmHomeInventoryManager.getContentPane().add(serialTextField, gbc_serialTextField);
		serialTextField.setColumns(10);
		serialTextField.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent e)
				{
					serialTextFieldActionPerformed(e);
				}
		});

		
		priceLabel = new JLabel("Purchase Price");
		priceLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.anchor = GridBagConstraints.EAST;
		gbc_priceLabel.insets = new Insets(10, 10, 5, 10);
		gbc_priceLabel.gridx = 1;
		gbc_priceLabel.gridy = 4;
		frmHomeInventoryManager.getContentPane().add(priceLabel, gbc_priceLabel);
		
		priceTextField = new JTextField();
		GridBagConstraints gbc_priceTextField = new GridBagConstraints();
		gbc_priceTextField.insets = new Insets(10, 0, 5, 10);
		gbc_priceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTextField.gridx = 2;
		gbc_priceTextField.gridy = 4;
		gbc_priceTextField.anchor = GridBagConstraints.WEST;
		frmHomeInventoryManager.getContentPane().add(priceTextField, gbc_priceTextField);
		priceTextField.setColumns(10);
		priceTextField.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent e)
				{
					priceTextFieldActionPerformed(e);
				}
			});

		
		dateLabel = new JLabel("Purchase Date");
		dateLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(10, 10, 5, 5);
		gbc_dateLabel.gridx = 3;
		gbc_dateLabel.gridy = 4;
		frmHomeInventoryManager.getContentPane().add(dateLabel, gbc_dateLabel);
		
		dateDateChooser = new JDateChooser();
		GridBagConstraints gbc_dateDateChooser = new GridBagConstraints();
		gbc_dateDateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateDateChooser.gridx=4;
		gbc_dateDateChooser.gridy=4;
		gbc_dateDateChooser.insets = new Insets(10, 0, 5, 10);
		frmHomeInventoryManager.getContentPane().add(dateDateChooser,gbc_dateDateChooser);
		dateDateChooser.addPropertyChangeListener(new PropertyChangeListener()
				{
					public void propertyChange(PropertyChangeEvent e) {
						dateDateChooserPropertyChange(e);
					}
				});
		
		storeLabel = new JLabel("Store/Website");
		storeLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_storeLabel = new GridBagConstraints();
		gbc_storeLabel.anchor = GridBagConstraints.EAST;
		gbc_storeLabel.insets = new Insets(10, 10, 5, 10);
		gbc_storeLabel.gridx = 1;
		gbc_storeLabel.gridy = 5;
		frmHomeInventoryManager.getContentPane().add(storeLabel, gbc_storeLabel);
		
		storeTextField = new JTextField();
		GridBagConstraints gbc_storeTextField = new GridBagConstraints();
		gbc_storeTextField.gridwidth = 3;
		gbc_storeTextField.insets = new Insets(10, 0, 5, 10);
		gbc_storeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_storeTextField.gridx = 2;
		gbc_storeTextField.gridy = 5;
		gbc_storeTextField.anchor = GridBagConstraints.WEST;
		frmHomeInventoryManager.getContentPane().add(storeTextField, gbc_storeTextField);
		storeTextField.setColumns(10);
		storeTextField.addActionListener(new ActionListener ()
		{
		public void actionPerformed(ActionEvent e)
			{  
				storeTextFieldActionPerformed(e);
			}
		});
		
		noteLabel = new JLabel("Note");
		noteLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_noteLabel = new GridBagConstraints();
		gbc_noteLabel.anchor = GridBagConstraints.EAST;
		gbc_noteLabel.insets = new Insets(10, 10, 5, 10);
		gbc_noteLabel.gridx = 1;
		gbc_noteLabel.gridy = 6;
		frmHomeInventoryManager.getContentPane().add(noteLabel, gbc_noteLabel);
		
		noteTextField = new JTextField();
		GridBagConstraints gbc_noteTextField = new GridBagConstraints();
		gbc_noteTextField.gridwidth = 3;
		gbc_noteTextField.insets = new Insets(10, 0, 5, 10);
		gbc_noteTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_noteTextField.gridx = 2;
		gbc_noteTextField.gridy = 6;
		gbc_noteTextField.anchor = GridBagConstraints.WEST;
		frmHomeInventoryManager.getContentPane().add(noteTextField, gbc_noteTextField);
		noteTextField.setColumns(10);
		noteTextField.addActionListener(new ActionListener ()
			{
			public void actionPerformed(ActionEvent e)
			{
				noteTextFieldActionPerformed(e);
			}
		});
		
		photoLabel = new JLabel("Photo");
		photoLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		GridBagConstraints gbc_photoLabel = new GridBagConstraints();
		gbc_photoLabel.anchor = GridBagConstraints.EAST;
		gbc_photoLabel.insets = new Insets(10, 10, 5, 10);
		gbc_photoLabel.gridx = 1;
		gbc_photoLabel.gridy = 7;
		frmHomeInventoryManager.getContentPane().add(photoLabel, gbc_photoLabel);
		
		photoTextArea = new JTextArea();
		GridBagConstraints gbc_photoTextArea = new GridBagConstraints();
		gbc_photoTextArea.gridwidth = 2;
		gbc_photoTextArea.insets = new Insets(10, 10, 5, 10);
		gbc_photoTextArea.fill = GridBagConstraints.BOTH;
		gbc_photoTextArea.gridx = 2;
		gbc_photoTextArea.gridy = 7;
		photoTextArea.setFont(new Font("Arial",Font.PLAIN,12));
		photoTextArea.setEditable(false);
		photoTextArea.setLineWrap(true);
		photoTextArea.setWrapStyleWord(true);
		photoTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		photoTextArea.setBackground(new Color(255, 255, 224));
		photoTextArea.setFocusable(false);
		gbc_photoTextArea.anchor = GridBagConstraints.WEST;
		photoTextArea.setPreferredSize(new Dimension(350, 35));
		frmHomeInventoryManager.getContentPane().add(photoTextArea, gbc_photoTextArea);
		
		
		
		photoButton = new JButton("...");
		GridBagConstraints gbc_photoButton = new GridBagConstraints();
		gbc_photoButton.anchor = GridBagConstraints.WEST;
		gbc_photoButton.insets = new Insets(10, 0, 5, 10);
		gbc_photoButton.gridx = 4;
		gbc_photoButton.gridy = 7;
		frmHomeInventoryManager.getContentPane().add(photoButton, gbc_photoButton);
		photoButton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e) {
									photoButtonActionPerformed(e);
						}
					
					}
				);
		
		searchPanel = new JPanel();
		searchPanel.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
		searchPanel.setPreferredSize(new Dimension(240,160));
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.gridwidth = 2;
		gbc_searchPanel.anchor = GridBagConstraints.NORTHWEST; 
		gbc_searchPanel.insets = new Insets(10,80,0,0);
		gbc_searchPanel.gridx = 1;
		gbc_searchPanel.gridy = 8;
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbl_searchPanel = new GridBagConstraints();
		gbl_searchPanel.anchor = GridBagConstraints.CENTER;
		frmHomeInventoryManager.getContentPane().add(searchPanel, gbc_searchPanel);
		
		
		
		
		searchButton = new JButton[26];
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		int x=0 ,y=0;
		//create and position 26 buttons
		for(int i=0;i<26;i++) {
			//create new button
			searchButton[i] = new JButton();
			//set text property
			searchButton[i].setText(String.valueOf((char)(65+i)));
			searchButton[i].setFont(new Font("arial",Font.BOLD,12));
			searchButton[i].setMargin(new Insets(-10,-10,-10,-10));
			sizeButton(searchButton[i],new Dimension(37,27));
			searchButton[i].setBackground(new Color(179, 255, 230));
			searchButton[i].setFocusable(false);
			gbc_searchButton.gridx=x;
			gbc_searchButton.gridy=y;
			searchPanel.add(searchButton[i],gbc_searchButton);
			//add method
			searchButton[i].addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) {
							searchButtonActionPerformed(e);
						}
					});
			x++;
			//six button per row
			if(x%6==0) {
				x=0;
				y++;
			}
			
			
		}
		

		
		photoPanel = new PhotoPanel();
		photoPanel.setPreferredSize(new Dimension(240, 160));
		GridBagConstraints gbc_photoPanel = new GridBagConstraints();
		gbc_photoPanel.gridwidth = 2;
		gbc_photoPanel.gridx = 3;
		gbc_photoPanel.gridy = 8;
		gbc_photoPanel.insets = new Insets(10,0,-10,0);
		gbc_photoPanel.anchor = GridBagConstraints.NORTHWEST;
		frmHomeInventoryManager.getContentPane().add(photoPanel, gbc_photoPanel);
		
		frmHomeInventoryManager.pack();
		int n;
		//open file for entries
		try {
				BufferedReader inputFile = new BufferedReader(new FileReader("inventory.txt"));
				numberOfEntries = Integer.valueOf(inputFile.readLine()).intValue();
				if(numberOfEntries!=0) {
					for(int i= 0;i<numberOfEntries;i++) {
						myInventory[i]=new InventoryItem();
						myInventory[i].description = inputFile.readLine();
						myInventory[i].location = inputFile.readLine();
						myInventory[i].serialNumber = inputFile.readLine();
						myInventory[i].marked = Boolean.valueOf(inputFile.readLine()).booleanValue();
						myInventory[i].purchasePrice = inputFile.readLine();
						myInventory[i].purchaseDate = inputFile.readLine();
						myInventory[i].purchaseLocation = inputFile.readLine();
						myInventory[i].note = inputFile.readLine();
						myInventory[i].photoFile = inputFile.readLine();
						}
				}
				//read in combo box elements
				n = Integer.valueOf(inputFile.readLine()).intValue();
				if(n!=0) {
					for(int i=0;i<n;i++) {
						locationComboBox.addItem(inputFile.readLine());
					}
				}
				inputFile.close();
				currentEntry = 1;
				showEntry(currentEntry);
			}
			
			catch (Exception ex) {
				numberOfEntries = 0;
				currentEntry = 0;
				ex.printStackTrace();
				
			}
			   if(numberOfEntries == 0) {
				newButton.setEnabled(false);
				deleteButton.setEnabled(false);
				previousButton.setEnabled(false);;
				nextButton.setEnabled(false);
				printButton.setEnabled(false);
				
			}
			   
		
		
	}
	
	private void exitForm(WindowEvent evt)
		{	
			if(JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost,\nAre you sure you want to exit?", "exit program" ,
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.NO_OPTION)
			{
				return;
			}
			//	write entries back to file
			saveItem();
	
			
				System.exit(0);
		}

	private void saveItem() {
		System.out.println("Performing save item operation");
		try {
			PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("inventory.txt")));
			outputFile.println(numberOfEntries);
			if(numberOfEntries!=0) {
				for(int i=0;i<numberOfEntries;i++) {
					outputFile.println(myInventory[i].description);
					outputFile.println(myInventory[i].location);
					outputFile.println(myInventory[i].serialNumber);
					outputFile.println(myInventory[i].marked);
					outputFile.println(myInventory[i].purchasePrice);
					outputFile.println(myInventory[i].purchaseDate);
					outputFile.println(myInventory[i].purchaseLocation);
					outputFile.println(myInventory[i].note);
					outputFile.println(myInventory[i].photoFile);					
				}
			}
			//write combo box entries
			outputFile.println(locationComboBox.getItemCount());
				if (locationComboBox.getItemCount() != 0)
				{
					for (int i = 0; i < locationComboBox.getItemCount(); i++)
							outputFile.println(locationComboBox.getItemAt(i));
				}
				outputFile.close();
			}
			catch (Exception ex)
				{	ex.printStackTrace();
					}
	}
	
	private void newButtonActionPerformed(ActionEvent e)
	{	
		checkSave();
		blankValues();
	}
	
	private void deleteButtonActionPerformed(ActionEvent e)
	{
		if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the item?","Delete inventory Item", JOptionPane.YES_NO_OPTION
				, JOptionPane.QUESTION_MESSAGE)==JOptionPane.NO_OPTION)
				return;
		
		deleteEntry(currentEntry);
		if(numberOfEntries ==0) {
			currentEntry = 0;
			blankValues();
			}
		else {
			currentEntry--;
				
				if(currentEntry==0)
					currentEntry = 1;
			
			showEntry(currentEntry);
		}
			
		
	}
	
	private void saveButtonActionPerformed(ActionEvent e)
	{
		System.out.println("Inside saveButtonActionPerformed");
		//check for description
		itemTextField.setText(itemTextField.getText().trim());
		if(itemTextField.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Must Have Item Description","Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				itemTextField.requestFocusInWindow();
				return;
			}
		if (newButton.isEnabled())
		{
			// delete edit entry then resave
			deleteEntry(currentEntry);
		}
		//capitalize first letter
		String s = itemTextField.getText();
		itemTextField.setText(s.substring(0,1).toUpperCase()+s.substring(1));
		numberOfEntries++;
		
		//determine new entry location based of description
		currentEntry = 1;
		if(numberOfEntries != 1)
		{
			do {
				if(itemTextField.getText().compareTo(myInventory[currentEntry-1].description)<0) {
					break;
				}
			currentEntry++;
			}while(currentEntry<numberOfEntries);
		}
		// move all entries below new value down one position unless at end
		if (currentEntry != numberOfEntries) {
			for(int i=numberOfEntries; i>=currentEntry+1 ; i--) {
				myInventory[i-1] = myInventory[i-2];
				myInventory[i-2] = new InventoryItem();
				}
			}
		
		myInventory[currentEntry-1] = new InventoryItem();
		myInventory[currentEntry-1].description = itemTextField.getText();
		myInventory[currentEntry-1].location = locationComboBox.getSelectedItem().toString();
		myInventory[currentEntry-1].marked = markedCheckBox.isSelected();
		myInventory[currentEntry-1].serialNumber = serialTextField.getText();
		myInventory[currentEntry-1].purchasePrice = priceTextField.getText();
		myInventory[currentEntry-1].purchaseDate = dateToString(dateDateChooser.getDate());
		myInventory[currentEntry-1].purchaseLocation = storeTextField.getText();
		myInventory[currentEntry-1].photoFile = photoTextArea.getText();
		myInventory[currentEntry-1].note = noteTextField.getText();
		
		System.out.println("myInventody : "+ myInventory[currentEntry-1]);
		saveItem();
		showEntry(currentEntry); 
		if (numberOfEntries < InventoryConstant.MAX_ENTRIES)
		{	newButton.setEnabled(true);
		}
		else
		{	newButton.setEnabled(false);
		}
		deleteButton.setEnabled(true);
		printButton.setEnabled(true);
		
	}
			
	private void previousButtonActionPerformed(ActionEvent e)
	{ 
		checkSave();
		currentEntry--;
		showEntry(currentEntry);
		
		
	}
	
	private void nextButtonActionPerformed(ActionEvent e)
	{	
		checkSave();
		currentEntry++;
		showEntry(currentEntry);
		
	}
	
	private void printButtonActionPerformed(ActionEvent e)
	{
		lastPage = (int) (1 + (numberOfEntries - 1) / entriesPerPage);
		PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
		inventoryPrinterJob.setPrintable(new InventoryDocument());
		if (inventoryPrinterJob.printDialog())
		{
			try
				{
					inventoryPrinterJob.print();
				}
			catch (PrinterException ex)
			{
				JOptionPane.showConfirmDialog(null, ex.getMessage(), "Print Error",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	private void exitButtonActionPerformed(ActionEvent e)
	{
		exitForm(null);
	}

	private void photoButtonActionPerformed(ActionEvent e) 
	{
		JFileChooser openChooser = new JFileChooser();
		openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		openChooser.setDialogTitle("Open Photo File");
		openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg","png","jpeg"));
		if(openChooser.showOpenDialog(openChooser)==JFileChooser.APPROVE_OPTION) {
			showPhoto(openChooser.getSelectedFile().toString());
		}
	}
	
	private void searchButtonActionPerformed(ActionEvent e)
	{
		int i;
		if (numberOfEntries == 0)
			return;
		// search for item letter
		String letterClicked = e.getActionCommand();
		i = 0;
		do
		{
			if (myInventory[i].description.substring(0, 1).equals(letterClicked))
				{
					currentEntry = i + 1;
					showEntry(currentEntry);
					return;
				}
			i++;
		}
		while (i < numberOfEntries);
		JOptionPane.showConfirmDialog(null, "No " + letterClicked + " inventory items.",
		"None Found", JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE);

	}
	
	private void itemTextFieldActionPerformed(ActionEvent e) 
	{
		locationComboBox.requestFocusInWindow();
	}
	
	@SuppressWarnings("unchecked")
	private void locationComboBoxActionPerformed(ActionEvent e) {
		//if in list - exit method
		if(locationComboBox.getItemCount()!=0) {
			for(int i=0;i<locationComboBox.getItemCount();i++) {
				if(locationComboBox.getSelectedItem().toString().equals(locationComboBox.getItemAt(i).toString())) {
					serialTextField.requestFocusInWindow();
					return;
				}
			}
		}
		//if not found, add to list box
		locationComboBox.addItem(locationComboBox.getSelectedItem());
		serialTextField.requestFocusInWindow();
		
	}
	
	private void serialTextFieldActionPerformed(ActionEvent e)
	{
		priceTextField.requestFocusInWindow();
	}
	private void priceTextFieldActionPerformed(ActionEvent e)
	{
		dateDateChooser.requestFocusInWindow();
	}
	private void dateDateChooserPropertyChange(PropertyChangeEvent e) {
		storeTextField.requestFocusInWindow();
	}
	private void storeTextFieldActionPerformed(ActionEvent e)
	{
		noteTextField.requestFocusInWindow();
	}
	private void noteTextFieldActionPerformed(ActionEvent e)
	{
		
		photoButton.requestFocusInWindow();
	}
	
	private void sizeButton(JButton b, Dimension d)
	{
			b.setPreferredSize(d);
			b.setMinimumSize(d);
			b.setMaximumSize(d);
		}

	private void showEntry(int j) {
		//display entry j (from 1 to numberOFEntries)
		
		
		
		itemTextField.setText(myInventory[j-1].description);
		locationComboBox.setSelectedItem(myInventory[j-1].location);
		markedCheckBox.setSelected(myInventory[j-1].marked);
		serialTextField.setText(myInventory[j-1].serialNumber);
		priceTextField.setText(myInventory[j-1].purchasePrice);
		dateDateChooser.setDate(stringToDate(myInventory[j-1].purchaseDate));
		storeTextField.setText(myInventory[j-1].purchaseLocation);
		noteTextField.setText(myInventory[j-1].note);
		showPhoto(myInventory[j-1].photoFile);
		nextButton.setEnabled(true);
		if(j==1) {
		 previousButton.setEnabled(false);
		}
		if (j == numberOfEntries) {
			previousButton.setEnabled(true);
			nextButton.setEnabled(false);
		}
		
		itemTextField.requestFocusInWindow();
	}
	
	@SuppressWarnings("deprecation")
	private Date stringToDate(String s)
		{
		int m = Integer.valueOf(s.substring(0, 2)).intValue() - 1;
		int d = Integer.valueOf(s.substring(3, 5)).intValue();
		int y = Integer.valueOf(s.substring(6)).intValue() - 1900;
		return(new Date(y, m, d));
		}
	@SuppressWarnings("deprecation")
	private String dateToString(Date dd)
		{
		String yString = String.valueOf(dd.getYear() + 1900);
		int m = dd.getMonth() + 1;
		String mString = new DecimalFormat("00").format(m);
		int d = dd.getDate();
		String dString = new DecimalFormat("00").format(d);
		return(mString + "/" + dString + "/" + yString);
		}
	

	
	private void showPhoto(String photoFile) {
		if(!photoFile.equals("")) {
			try {
				photoTextArea.setText(photoFile);
			}
		
			catch(Exception e){
				photoTextArea.setText("");
				e.printStackTrace();
			}
		}
		else {
			photoTextArea.setText("");
			
			}
		photoPanel = new PhotoPanel();
		photoPanel.repaint();
	}

	private void deleteEntry(int j) {
		//delete entry j
		if(j!=numberOfEntries){
			//move all entry under j up one level
			for(int i=j;i<numberOfEntries;i++) {
				myInventory[i-1] = new InventoryItem();
				myInventory[i-1] = myInventory[i];
			}
		}
		numberOfEntries--;
	}
	private void checkSave() {
		boolean edited = false;
		if(!myInventory[currentEntry-1].description.equals(itemTextField.getText()))
			edited = true;
		else if (!myInventory[currentEntry-1].location.equals(locationComboBox.getSelectedItem().toString()))
			edited = true;
		else if(myInventory[currentEntry-1].marked != markedCheckBox.isSelected())
			edited = true;
		else if (!myInventory[currentEntry-1].serialNumber.equals(serialTextField.getText()))
			edited = true;
		else if (!myInventory[currentEntry-1].purchasePrice.equals(priceTextField.getText()))
			edited = true;
		else if (!myInventory[currentEntry-1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
			edited = true;
		else if (!myInventory[currentEntry-1].purchaseLocation.equals(storeTextField.getText()))
			edited = true;
		else if (!myInventory[currentEntry-1].note.equals(noteTextField.getText()))
			edited = true;
		else if (!myInventory[currentEntry-1].photoFile.equals(photoTextArea.getText()))
			edited = true;
		if(edited) {
			if(JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the Change?","Save Item",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			{	saveButton.doClick();
			}
		}
	}	
	
	private void blankValues() {
		//blank input screen
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
		saveButton.setEnabled(true);
		previousButton.setEnabled(false);
		nextButton.setEnabled(false);
		printButton.setEnabled(false);
		itemTextField.setText("");
		locationComboBox.setSelectedItem("");
		markedCheckBox.setSelected(false);
		serialTextField.setText("");
		priceTextField.setText("");
		dateDateChooser.setDate(new Date());
		storeTextField.setText("");
		noteTextField.setText("");
		photoTextArea.setText("");
		photoPanel.repaint();
		itemTextField.requestFocus();

		
	}
	
}



class PhotoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g2D);
		
		//draw border
		g2D.setPaint(Color.BLACK);
		g2D.draw(new Rectangle2D.Double(0,0,getWidth()-1,getHeight()-1));
		
		//show photo
		Image photoImage = new ImageIcon(Homeinventory.photoTextArea.getText()).getImage();
		int w = getWidth();
		int h = getHeight();
		double rWidth = (double) getWidth() / (double) photoImage.getWidth(null);
		double rHeight = (double) getHeight()/ (double) photoImage.getHeight(null);
		if(rWidth>rHeight) {
			
			//leave height at display height, change width by amount height is changed
			w = (int) (photoImage.getWidth(null)*rHeight);	
		}
		else {
			
			//leave width at display width , change height by amount width is changed
			h = (int)(photoImage.getHeight(null)*rWidth);
		}
		
		//center in panel
		g2D.drawImage(photoImage, (int)(0.5*(getWidth()-w)), (int)(0.5*(getHeight()-h)), w,h,null);
		g2D.dispose();
		}
	
	}

class InventoryDocument implements Printable
{
	public int print(Graphics g, PageFormat pf, int pageIndex)
	{
		Graphics2D g2D = (Graphics2D) g;
		if ((pageIndex + 1) > Homeinventory.lastPage)
		{
			return NO_SUCH_PAGE;
			}
		int i, iEnd;
		// here you decide what goes on each page and draw it
		// header
		g2D.setFont(new Font("Arial", Font.BOLD, 14));
		g2D.drawString("Home Inventory Items - Page " + String.valueOf(pageIndex + 1),
				(int) pf.getImageableX(), (int) (pf.getImageableY() + 25));
		// get starting y
			int dy = (int) g2D.getFont().getStringBounds("S",
					g2D.getFontRenderContext()).getHeight();
			int y = (int) (pf.getImageableY() + 4 * dy);
			iEnd = Homeinventory.entriesPerPage * (pageIndex + 1);
			if (iEnd > Homeinventory.numberOfEntries)
				iEnd = Homeinventory.numberOfEntries;
			for (i = 0 + Homeinventory.entriesPerPage * pageIndex; i < iEnd; i++)
			{
			// dividing line
			Line2D.Double dividingLine = new Line2D.Double(pf.getImageableX(), y,
			pf.getImageableX() + pf.getImageableWidth(), y);
			g2D.draw(dividingLine);
			y += dy;
			g2D.setFont(new Font("Arial", Font.BOLD, 12));
			g2D.drawString(Homeinventory.myInventory[i].description, (int) pf.getImageableX(), y);
			y += dy;
			g2D.setFont(new Font("Arial", Font.PLAIN, 12));
			g2D.drawString("Location: " + Homeinventory.myInventory[i].location, (int)
			(pf.getImageableX() + 25), y);
			y += dy;
			if (Homeinventory.myInventory[i].marked)
			g2D.drawString("Item is marked with identifying information.", (int)
			(pf.getImageableX() + 25), y);
			else
			g2D.drawString("Item is NOT marked with identifying information.", (int)
			(pf.getImageableX() + 25), y);
			y += dy;
			g2D.drawString("Serial Number: " +
			Homeinventory.myInventory[i].serialNumber, (int) (pf.getImageableX() + 25), y);
			y += dy;
			g2D.drawString("Price: $" + Homeinventory.myInventory[i].purchasePrice + ",Purchased on: " + Homeinventory.myInventory[i].purchaseDate, (int) (pf.getImageableX() +
			25), y);
			y += dy;
			g2D.drawString("Purchased at: " +
			Homeinventory.myInventory[i].purchaseLocation, (int) (pf.getImageableX() + 25), y);
			y += dy;
			g2D.drawString("Note: " + Homeinventory.myInventory[i].note, (int)
			(pf.getImageableX() + 25), y);
			y += dy;
			try
			{
			// maintain original width/height ratio
			Image inventoryImage = new
			ImageIcon(Homeinventory.myInventory[i].photoFile).getImage ();
			double ratio = (double) (inventoryImage.getWidth(null)) / (double)
			inventoryImage.getHeight(null);
			g2D.drawImage(inventoryImage, (int) (pf.getImageableX() + 25), y, (int) (100 *
			ratio), 100, null);
			}
			catch (Exception ex)
					{
					// have place to go in case image file doesn't open
					}
					y += 2 * dy + 100;
					}
					return PAGE_EXISTS;
					}
			}


package com.home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DebugGraphics;
import java.awt.event.KeyEvent;

public class HomeInventory {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeInventory window = new HomeInventory();
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
	public HomeInventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 883, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JToolBar inventoryToolBar = new JToolBar();
		inventoryToolBar.setOrientation(SwingConstants.VERTICAL);
		inventoryToolBar.setBackground(new Color(0, 102, 102));
		inventoryToolBar.setFloatable(false);
		GridBagConstraints gbc_inventoryToolBar = new GridBagConstraints();
		gbc_inventoryToolBar.fill = GridBagConstraints.VERTICAL;
		gbc_inventoryToolBar.insets = new Insets(0, 0, 0, 5);
		gbc_inventoryToolBar.gridheight = 15;
		gbc_inventoryToolBar.gridx = 0;
		gbc_inventoryToolBar.gridy = 0;
		frame.getContentPane().add(inventoryToolBar, gbc_inventoryToolBar);
		inventoryToolBar.addSeparator();
		
		JButton newButton = new JButton("New");
		newButton.setMnemonic(KeyEvent.VK_ROMAN_CHARACTERS);
		newButton.setMargin(new Insets(9, 15, 2, 14));
		newButton.setIconTextGap(0);
		newButton.setFont(new Font("Algerian", Font.BOLD, 12));
		newButton.setAutoscrolls(true);
		Dimension bSize = new Dimension(70, 50);
		sizeButton(newButton, bSize);
		newButton.setHorizontalTextPosition(SwingConstants.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		newButton.setIcon(new ImageIcon("C:\\Users\\Suraj Singh Bhandari\\Pictures\\print.png"));
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newButton.setToolTipText("Add New Item");
		newButton.setSize(new Dimension(200, 100));
		inventoryToolBar.add(newButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon("C:\\Dev\\app\\eclipse-workspace\\homeJava Project\\delete-forever (2).png"));
		deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteButton.setToolTipText("delete current item");
		inventoryToolBar.add(deleteButton);
		
		JButton btnNewButton_5 = new JButton("New button");
		inventoryToolBar.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("New button");
		inventoryToolBar.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("New button");
		inventoryToolBar.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		inventoryToolBar.add(btnNewButton_7);
		
		JLabel inventoryItemLabel = new JLabel("Inventory Item");
		GridBagConstraints gbc_inventoryItemLabel = new GridBagConstraints();
		gbc_inventoryItemLabel.anchor = GridBagConstraints.EAST;
		gbc_inventoryItemLabel.insets = new Insets(0, 0, 5, 5);
		gbc_inventoryItemLabel.gridx = 1;
		gbc_inventoryItemLabel.gridy = 1;
		frame.getContentPane().add(inventoryItemLabel, gbc_inventoryItemLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 10;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel locationLabel = new JLabel("Location");
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.anchor = GridBagConstraints.EAST;
		gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_locationLabel.gridx = 1;
		gbc_locationLabel.gridy = 2;
		frame.getContentPane().add(locationLabel, gbc_locationLabel);
		 
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 11;
		gbc_chckbxNewCheckBox.gridy = 2;
		frame.getContentPane().add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JLabel serialNumberLabel = new JLabel("Serial Number");
		GridBagConstraints gbc_serialNumberLabel = new GridBagConstraints();
		gbc_serialNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_serialNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_serialNumberLabel.gridx = 1;
		gbc_serialNumberLabel.gridy = 3;
		frame.getContentPane().add(serialNumberLabel, gbc_serialNumberLabel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 3;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel purchaseLabel = new JLabel("Purchase Price");
		GridBagConstraints gbc_purchaseLabel = new GridBagConstraints();
		gbc_purchaseLabel.anchor = GridBagConstraints.EAST;
		gbc_purchaseLabel.insets = new Insets(0, 0, 5, 5);
		gbc_purchaseLabel.gridx = 1;
		gbc_purchaseLabel.gridy = 4;
		frame.getContentPane().add(purchaseLabel, gbc_purchaseLabel);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 4;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 4;
		frame.getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 4;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 4;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JLabel storeLabel = new JLabel("Store/Website");
		GridBagConstraints gbc_storeLabel = new GridBagConstraints();
		gbc_storeLabel.anchor = GridBagConstraints.EAST;
		gbc_storeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_storeLabel.gridx = 1;
		gbc_storeLabel.gridy = 5;
		frame.getContentPane().add(storeLabel, gbc_storeLabel);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 5;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel noteLabel = new JLabel("Note");
		GridBagConstraints gbc_noteLabel = new GridBagConstraints();
		gbc_noteLabel.anchor = GridBagConstraints.EAST;
		gbc_noteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_noteLabel.gridx = 1;
		gbc_noteLabel.gridy = 6;
		frame.getContentPane().add(noteLabel, gbc_noteLabel);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 6;
		frame.getContentPane().add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblPhoto = new JLabel("Photo");
		GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
		gbc_lblPhoto.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPhoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoto.gridx = 1;
		gbc_lblPhoto.gridy = 7;
		frame.getContentPane().add(lblPhoto, gbc_lblPhoto);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 9;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 7;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridheight = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 11;
		gbc_btnNewButton_1.gridy = 7;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		Panel panel = new Panel();
		panel.setFont(new Font("Agency FB", Font.PLAIN, 12));
		panel.setName("Item Search");
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 5;
		gbc_panel.gridheight = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 9;
		frame.getContentPane().add(panel, gbc_panel);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.gridheight = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 8;
		gbc_panel_1.gridy = 9;
		frame.getContentPane().add(panel_1, gbc_panel_1);
	}

	private void sizeButton(JButton newButton, Dimension bSize) {
		// TODO Auto-generated method stub
		
	}

}

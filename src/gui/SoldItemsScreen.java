package gui;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import core.GameEnvironment;
import core.Goods;
import core.Ship;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

/**
 * Class to perform sold items screen to show a list of items that
 * has been sold by the player in a screen using absolute layout
 *
 */
public class SoldItemsScreen implements Screen {
	// The selected ship
	private Ship ship;
	
	//The Game Environment that the screen interacts with
	private GameEnvironment game;
	
	//Frame for Sold Item Screen
	private JFrame frmSolsItemsScreen;
	
	// Table to show list of goods that has been sold
	private JTable soldItemsTable;
	
	// Table to show Items that has been sold by the player
	private JLabel soldItemListsLabel;
	
	// ScrollPane for sold Items table
	private JScrollPane soldItemTableScrollPane;
	
	// Button for going back to the previous screen
	private JButton goBackButton;
	
	/**
	 * Creating the Sold Item Screen and make it visible to user
	 * 
	 * @param game The GameEnvironment that interacts with this screen
	 */
	public SoldItemsScreen(GameEnvironment game) {
		this.game = game;
		this.ship = game.getSelectedShip();
		initialize();
		frmSolsItemsScreen.setVisible(true);
	}
	
	/**
	 *Close the Sold Item Screen window
	 */
	@Override
	public void closeWindow() {
		frmSolsItemsScreen.dispose();
	}

	/**
	 * Initialize the contents for the Sold Item Screen
	 */
	private void initialize() {
		frmSolsItemsScreen = new JFrame();
		frmSolsItemsScreen.getContentPane().setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		frmSolsItemsScreen.getContentPane().setBackground(new Color(204, 153, 0));
		frmSolsItemsScreen.setTitle("Sold Items Screen");
		frmSolsItemsScreen.setBounds(100, 100, 850, 560);
		frmSolsItemsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolsItemsScreen.getContentPane().setLayout(null);
		
		soldItemListsLabel = new JLabel("Sold Items List");
		soldItemListsLabel.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 17));
		soldItemListsLabel.setBounds(357, 59, 135, 30);
		frmSolsItemsScreen.getContentPane().add(soldItemListsLabel);
		
		soldItemTableScrollPane = new JScrollPane();
		soldItemTableScrollPane.setBounds(10, 100, 814, 410);
		frmSolsItemsScreen.getContentPane().add(soldItemTableScrollPane);
		
		soldItemsTable = new JTable();
		soldItemsTable.setBackground(new Color(222, 184, 135));
		soldItemsTable.setForeground(new Color(51, 51, 51));
		soldItemsTable.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		soldItemsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Product' name", "Quantity", "Island (The place it was sold)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		soldItemsTable.getColumnModel().getColumn(0).setPreferredWidth(45);
		soldItemsTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		soldItemsTable.getColumnModel().getColumn(3).setPreferredWidth(160);
		soldItemTableScrollPane.setViewportView(soldItemsTable);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		goBackButton.setForeground(new Color(204, 153, 0));
		goBackButton.setBackground(new Color(51, 51, 51));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.seeShipSpec();
			}
		});
		goBackButton.setBounds(10, 11, 135, 36);
		frmSolsItemsScreen.getContentPane().add(goBackButton);
		
		fillSoldItemsTable();
	}
	
	/**
	 * Fill the table which shows list of goods that has been sold
	 */
	public void fillSoldItemsTable() {
		HashMap<Goods, ArrayList<String>> soldItems = ship.getSoldItems();
		DefaultTableModel model = (DefaultTableModel)this.soldItemsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, ArrayList<String>> set: soldItems.entrySet()) {
			String name = set.getKey().getName();
			int quantity = set.getKey().getQuantitySold();
			ArrayList<String> islands = set.getValue();
			model.addRow(new Object [] {number, name, quantity, islands});
			number++;
		}
	}

	/**
	 *Return the frame for sold item screen
	 *@return the frame for sold item screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmSolsItemsScreen;
	}
}

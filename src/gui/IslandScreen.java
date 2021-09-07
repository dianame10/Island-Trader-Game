package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Equipment;
import core.GameEnvironment;
import core.Goods;
import core.Island;
import core.Store;
import java.awt.Font;
import java.awt.Color;

/**
 * Class to perform Island screen to show the specification of the island in a screen using absolute layout
 *
 */
public class IslandScreen implements Screen {
	// The Game Environment that this screen will interacts with
	private GameEnvironment game;
	
	// the chosen target Island that the player wanted to see its specification
	private Island targetIsland;
	
	// The store that exist in the target island
	private Store targetStore;
	
	// frame that used for island screen
	private JFrame islandScreen;
	
	// Label to show the target island name
	private JLabel islandNameLabel;
	
	// Go back button to return to main screen
	private JButton goBackButton;
	
	// Title Label above sell Goods Table
	private JLabel sellGoodsLabel;
	
	// Title Label above interested Goods Table
	private JLabel interestedGoodsLabel;
	
	// Label for sold Equipment
	private JLabel soldEquipmentTitaleLabel;
	
	// Button to call Route screen
	private JButton showRouteButton;
	
	// Table to show what goods sold by target chosen island
	private JTable soldGoodsTable;
	
	// Tale to show what goods that the store in target island would like to buy
	private JTable interestedGoodsTable;
	
	// Label to show the price of equipment sold in target Island
	private JLabel priceEqupmentLabel;
	
	//Label to show the name of equipment that sold by target island
	private JLabel equipmentNameLabel;
	
	// ScrollPane for interestedGoods Table
	private JScrollPane interestedGoodsScrollPane;
	
	// ScrollPane for sellGoods Table
	private JScrollPane sellGoodsScrollPane;



	/**
	 * Create the Island Screen where player can see other island specification
	 * @param game the Game Environment that this screen will interacts with
	 * @param targetIsland the chosen target Island that the player want to see
	 */
	public IslandScreen(GameEnvironment game, Island targetIsland) {
		this.game = game;
		this.targetIsland = targetIsland;
		this.targetStore = targetIsland.getStore();
		game.setCurrentChosenIsland(targetIsland);
		initialize();
		islandScreen.setVisible(true);
	}
	
	/**
	 *close Island Screen window
	 */
	@Override
	public void closeWindow() {
		islandScreen.dispose();
	}
	
	/**
	 * Return the screen for island screen
	 *@return the Island Screen frame
	 */
	@Override
	public JFrame getFrame() {
		return this.islandScreen;
	}
	
	/**
	 * fill and/or update the table that showing Goods that stored in the ship
	 */
	public void fillSoldGoodTable() {
		HashMap<Goods, Integer> sellGoods = targetStore.getSellGoods();
		DefaultTableModel model = (DefaultTableModel)this.soldGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: sellGoods.entrySet()) {
			String name = set.getKey().getName();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, price});
			number++;
		}
	}
	
	/**
	 * fill and/or update the table that showing Goods that store in target Island want to buy
	 */
	public void fillInterestedGoodsTable() {
		HashMap<Goods, Integer> interestedGoods = targetStore.getInteresedGoods();
		DefaultTableModel model = (DefaultTableModel)this.interestedGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: interestedGoods.entrySet()) {
			String name = set.getKey().getName();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, price});
			number++;
		}
	}
	
	/**
	 * Update the labels that shows the name of Equipment that sold by the store in target island and its price
	 */
	public void setSellEquipmentLabel() {
		Equipment equipment = targetStore.getEquipment();
		int sellingPrice = equipment.getSellingPrice();
		this.equipmentNameLabel.setText("Name : " + equipment.getName());
		this.priceEqupmentLabel.setText("Price : "+sellingPrice);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		islandScreen = new JFrame();
		islandScreen.getContentPane().setBackground(new Color(218, 165, 32));
		islandScreen.setBounds(100, 100, 793, 435);
		islandScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		islandScreen.getContentPane().setLayout(null);
		
		islandNameLabel = new JLabel(this.targetIsland.getIslandName() + " Island");
		islandNameLabel.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 17));
		islandNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		islandNameLabel.setBounds(229, 6, 279, 44);
		islandScreen.getContentPane().add(islandNameLabel);
		
		sellGoodsLabel = new JLabel("Sold Goods");
		sellGoodsLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		sellGoodsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellGoodsLabel.setBounds(110, 72, 116, 14);
		islandScreen.getContentPane().add(sellGoodsLabel);
		
		interestedGoodsLabel = new JLabel("Interested Goods");
		interestedGoodsLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		interestedGoodsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		interestedGoodsLabel.setBounds(490, 72, 158, 14);
		islandScreen.getContentPane().add(interestedGoodsLabel);
		
		soldEquipmentTitaleLabel = new JLabel("Ship's Equipment Sold By The Store:");
		soldEquipmentTitaleLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		soldEquipmentTitaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		soldEquipmentTitaleLabel.setBounds(171, 284, 470, 14);
		islandScreen.getContentPane().add(soldEquipmentTitaleLabel);
		
		goBackButton = new JButton("Go back");
		goBackButton.setForeground(new Color(218, 165, 32));
		goBackButton.setBackground(new Color(0, 0, 0));
		goBackButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 13));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.backToMain();
			}
		});
		goBackButton.setBounds(10, 11, 124, 39);
		islandScreen.getContentPane().add(goBackButton);
		
		showRouteButton = new JButton("Show the routes!");
		showRouteButton.setBackground(new Color(0, 0, 0));
		showRouteButton.setForeground(new Color(218, 165, 32));
		showRouteButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		showRouteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.showTheRoute();
			}
		});
		showRouteButton.setBounds(321, 349, 187, 23);
		islandScreen.getContentPane().add(showRouteButton);
		
		sellGoodsScrollPane = new JScrollPane();
		sellGoodsScrollPane.setBounds(10, 89, 324, 159);
		islandScreen.getContentPane().add(sellGoodsScrollPane);
		
		soldGoodsTable = new JTable();
		soldGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		soldGoodsTable.getColumnModel().getColumn(0).setResizable(false);
		soldGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		soldGoodsTable.getColumnModel().getColumn(1).setResizable(false);
		soldGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		soldGoodsTable.getColumnModel().getColumn(2).setResizable(false);
		sellGoodsScrollPane.setViewportView(soldGoodsTable);
		
		interestedGoodsScrollPane = new JScrollPane();
		interestedGoodsScrollPane.setBounds(414, 89, 318, 159);
		islandScreen.getContentPane().add(interestedGoodsScrollPane);
		
		interestedGoodsTable = new JTable();
		interestedGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		interestedGoodsTable.getColumnModel().getColumn(0).setResizable(false);
		interestedGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		interestedGoodsTable.getColumnModel().getColumn(1).setResizable(false);
		interestedGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		interestedGoodsTable.getColumnModel().getColumn(2).setResizable(false);
		interestedGoodsScrollPane.setViewportView(interestedGoodsTable);
		
		equipmentNameLabel = new JLabel("Name : equipment Name");
		equipmentNameLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		equipmentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		equipmentNameLabel.setBounds(181, 300, 467, 23);
		islandScreen.getContentPane().add(equipmentNameLabel);
		
		priceEqupmentLabel = new JLabel("Price :");
		priceEqupmentLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		priceEqupmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceEqupmentLabel.setBounds(164, 319, 484, 23);
		islandScreen.getContentPane().add(priceEqupmentLabel);
		
		this.fillInterestedGoodsTable();
		this.fillSoldGoodTable();
		this.setSellEquipmentLabel();
	}
}

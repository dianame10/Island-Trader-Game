package gui;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import core.Equipment;
import core.GameEnvironment;
import core.Goods;
import core.Ship;
import core.Store;
import seng201_project.GameEnviromentUi;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.UIManager;

/**
 * Class to perform store screen to show list of items that store sell 
 * and list of items thats store wanting to buy in a screen using absolute layout
 *
 */
public class StoreScreen implements Screen {
	// Frame for Store Screen
	private JFrame frmStore;
	
	// The Game Environment that the screen interacts with
	private GameEnvironment game;
	
	// Button to go back to Main Screen
	private JButton goBackButton;
	
	// Ship that the player select
	private Ship ship;
	
	//The store at the current location
	private Store currentStore;
	
	// Hash map of items that sold by the store 
	private HashMap<Goods, Integer> sellItems;
	
	// Hash map of items that the player owned
	private HashMap<Goods, Integer> myGoods;
	
	// Button to repair ship
	private JButton repairShipButton;
	
	// TabbedPane for differentiate sell Items, interested Goods, and sell equipment by the Store
	private JTabbedPane tabbedPane;
	
	//Panel to put components of activity that player can do when he want to buy items
	private JPanel sellGoodsPanel;
	
	//Panel to put components of activity that player can do when he want to buy equipment
	private JPanel sellEquipmentPanel;
	
	// Panel to put components of activity that player can do when he want to sell items
	private JPanel interestedGoodsPanel;
	
	// Label title to show what below it is the table to show what the store wnat to buy
	private JLabel interestedGoodsByStoreLabel;
	
	//Text field so the player can input the quantity he want to buy
	private JTextField quantityToSellTextField;
	
	//Text field so the player can input the quantity he want to sell
	private JTextField quantityTextField;
	
	//Button to execute transaction
	private JButton buyButton;
	
	// Button to execute transaction
	private JButton sellButton;
	
	//Label to ask player how many does he want to sell
	private JLabel askToSellLabel;
	
	//Label to ask player how many does he want to buy
	private JLabel askToBuyLabel;
	
	//Label title
	private JLabel myGoodsListLabel;
	
	//Label title
	private JLabel saleGoodsListLabel;
	
	//Label title
	private JTable saleGoodsTable;
	
	//Table to show goods that owned by the player
	private JTable myGoodsTable;
	
	private JScrollPane interestedGoodsListTableScrollPane;
	
	private JScrollPane myGoodsTableScrollPane;
	
	//Table to show what goods that the store sells
	private JTable interestedGoodsTable;
	
	//Label title
	private JLabel equipmentTitleLabel;
	
	//Label to show error message if error happens
	private JLabel errorLabel;
	
	//Button to see if the player owned the goods that the store want to buy and he want to sell
	private JButton lookUpButton;
	
	// Label to show name of equipment sold by the store
	private JLabel equipmentNameLabel;
	
	// Label to show price of equipment sold by the store
	private JLabel equipmentPriceLabel;
	
	//Button to buy equipment
	private JButton buyEquipmentButton;
	
	private JScrollPane saleGoodsScrollPane;
	
	//Label to show current money that the player has
	private JLabel moneyLabel;
	
	// Label to show the cost of ship repairment
	private JLabel repairExplanationLabel;
	
	//Label for equipment
	private JLabel equipmentPictureLabel;
	
	/**
	 * Creating the Store screen
	 * @param game The Game Environment that the store screen interacts with
	 */
	public StoreScreen(GameEnvironment game) {
		this.game = game;
		ship = game.getSelectedShip();
		currentStore = game.getCurrentLocation().getStore();
		this.sellItems = currentStore.getSellGoods();
		this.myGoods = ship.getMyGoods();
		initialize();
		frmStore.setVisible(true);
		
	}
	

	/**
	 * Initialize the contents for Store Screen and add action hander for the buttons
	 */
	private void initialize() {
		frmStore = new JFrame();
		frmStore.getContentPane().setBackground(new Color(218, 165, 32));
		frmStore.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmStore.setTitle("Store Screen");
		frmStore.setBounds(100, 100, 950, 600);
		frmStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStore.getContentPane().setLayout(null);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(new Color(218, 165, 32));
		goBackButton.setBackground(new Color(0, 0, 0));
		goBackButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.backToMain();
			}
		});
		goBackButton.setBounds(12, 7, 124, 29);
		frmStore.getContentPane().add(goBackButton);
		
		repairShipButton = new JButton("Repair my ship!");
		repairShipButton.setForeground(new Color(218, 165, 32));
		repairShipButton.setBackground(new Color(0, 0, 0));
		repairShipButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		repairShipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.repairMyShip();
				updateRepairLabel();
				
			}
		});
		repairShipButton.setBounds(666, 525, 220, 29);
		frmStore.getContentPane().add(repairShipButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 71, 932, 443);
		frmStore.getContentPane().add(tabbedPane);
		
		sellGoodsPanel = new JPanel();
		sellGoodsPanel.setBackground(new Color(255, 228, 196));
		tabbedPane.addTab("Items that store sells\r\n", null, sellGoodsPanel, null);
		sellGoodsPanel.setLayout(null);
		
		saleGoodsListLabel = new JLabel("\"Goods that sell by the store\"");
		saleGoodsListLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 16));
		saleGoodsListLabel.setBounds(45, 39, 224, 14);
		sellGoodsPanel.add(saleGoodsListLabel);
		
		myGoodsListLabel = new JLabel("\"My Goods\"");
		myGoodsListLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 16));
		myGoodsListLabel.setBounds(716, 50, 103, 14);
		sellGoodsPanel.add(myGoodsListLabel);
		
		askToBuyLabel = new JLabel("How many items do\n you want to buy?");
		askToBuyLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		askToBuyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		askToBuyLabel.setBounds(286, 129, 308, 14);
		sellGoodsPanel.add(askToBuyLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setHorizontalAlignment(SwingConstants.CENTER);
		quantityTextField.setBounds(365, 155, 118, 25);
		sellGoodsPanel.add(quantityTextField);
		quantityTextField.setColumns(10);
		
		errorLabel = new JLabel();
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setText(GameEnviromentUi.INPUT_REQUIREMENTS);
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		errorLabel.setPreferredSize(errorLabel.getPreferredSize());
		errorLabel.setBounds(315, 192, 200, 14);
		sellGoodsPanel.add(errorLabel);
		
		saleGoodsScrollPane = new JScrollPane();
		saleGoodsScrollPane.setBounds(14, 64, 262, 305);
		sellGoodsPanel.add(saleGoodsScrollPane);
		
		saleGoodsTable = new JTable();
		saleGoodsTable.setFont(new Font("Century Schoolbook L", Font.PLAIN, 12));
		saleGoodsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		buyButton = new JButton("Buy now!");
		buyButton.setBackground(UIManager.getColor("OptionPane.warningDialog.titlePane.background"));
		buyButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 13));
		buyButton.setEnabled(false);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<Goods, Integer> sellItems = currentStore.getSellGoods();
				Set <Goods> keySet = sellItems.keySet();
				ArrayList<Goods> listOfSellGoods = new ArrayList<>(keySet);
				HashMap<Goods, Integer> myGoods = ship.getMyGoods();
				int selectedRowIndex = saleGoodsTable.getSelectedRow();
				Goods selectedGoods =  listOfSellGoods.get(selectedRowIndex);
				int goodsPrice = sellItems.get(selectedGoods);
				int quantity = Integer.parseInt(quantityTextField.getText());
				if (game.checkConditionsForBuying(1, selectedGoods, goodsPrice) == true) {
					if (game.checkConditionsForBuying(quantity, selectedGoods, goodsPrice) == true) {
						game.buyGoods(quantity, selectedGoods, goodsPrice);
						String message = "Purchase Successful!";
						game.tellPlayer(message);
						fillMyGoodsTable(myGoods);
						moneyLabel.setText("Money : "+game.getMoney()+" coin(s)");
					}
					else {
						String message = "You either don't have enough money or enough capacity!";
						game.tellPlayer(message);
					}
				}
				else {
					String message = "You either don't have enough money or enough capacity even to buy 1 item";
					game.tellPlayer(message);
				}
			}
		});
		buyButton.setBounds(335, 218, 161, 23);
		sellGoodsPanel.add(buyButton);
		
		
		quantityTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});

		
		saleGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Product's name", "Price (coin)"
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
		saleGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		saleGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		saleGoodsScrollPane.setViewportView(saleGoodsTable);
		
		myGoodsTableScrollPane = new JScrollPane();
		myGoodsTableScrollPane.setBounds(600, 74, 317, 296);
		sellGoodsPanel.add(myGoodsTableScrollPane);
		
		interestedGoodsPanel = new JPanel();
		interestedGoodsPanel.setBackground(new Color(255, 228, 196));
		tabbedPane.addTab("Items that store want to buy", null, interestedGoodsPanel, null);
		interestedGoodsPanel.setLayout(null);
		
		interestedGoodsByStoreLabel = new JLabel("Goods that store wanting to buy");
		interestedGoodsByStoreLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		interestedGoodsByStoreLabel.setBounds(97, 13, 341, 25);
		interestedGoodsPanel.add(interestedGoodsByStoreLabel);
		
		quantityToSellTextField = new JTextField();
		quantityToSellTextField.setHorizontalAlignment(SwingConstants.CENTER);
		quantityToSellTextField.setBounds(693, 225, 69, 27);
		interestedGoodsPanel.add(quantityToSellTextField);
		quantityToSellTextField.setColumns(10);
		
		askToSellLabel = new JLabel("How many do you want to sell?");
		askToSellLabel.setHorizontalAlignment(SwingConstants.CENTER);
		askToSellLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		askToSellLabel.setBounds(566, 199, 311, 14);
		interestedGoodsPanel.add(askToSellLabel);
		
		interestedGoodsListTableScrollPane = new JScrollPane();
		interestedGoodsListTableScrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		interestedGoodsListTableScrollPane.setBounds(12, 50, 506, 322);
		interestedGoodsPanel.add(interestedGoodsListTableScrollPane);
		
		interestedGoodsTable = new JTable();
		HashMap<Goods, Integer> interestedGoods = currentStore.getInteresedGoods();
		Set <Goods> goodsSet = interestedGoods.keySet();
		ArrayList<Goods> listOfInterestedGoods = new ArrayList<>(goodsSet);
		interestedGoodsTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		interestedGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Product's name", "Offered price (coin)"
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
		
		interestedGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		interestedGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		interestedGoodsTable.getColumnModel().getColumn(2).setPreferredWidth(113);
		interestedGoodsListTableScrollPane.setViewportView(interestedGoodsTable);
		
		lookUpButton = new JButton("Look up item");
		lookUpButton.setBackground(UIManager.getColor("OptionPane.warningDialog.titlePane.background"));
		lookUpButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 13));
		lookUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = interestedGoodsTable.getSelectedRow();
				Goods selectedGoods = listOfInterestedGoods.get(selectedRow);
				if (game.checkAvailability(selectedGoods)) {
					String message = "You have " + selectedGoods.getQuantityOwned() +" "+ selectedGoods.getName() + " stored in your ship!";
					game.tellPlayer(message);
					sellButton.setEnabled(true);
				}
				else {
					String message = "You don't have the item!";
					game.tellPlayer(message);
				}
				
			}
		});
		lookUpButton.setBounds(160, 381, 180, 23);
		interestedGoodsPanel.add(lookUpButton);
		
		sellButton = new JButton("Sell now!");
		sellButton.setBackground(UIManager.getColor("OptionPane.warningDialog.titlePane.background"));
		sellButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = interestedGoodsTable.getSelectedRow();
				Goods selectedGoods = listOfInterestedGoods.get(selectedRow);
				int quantity = Integer.parseInt(quantityToSellTextField.getText());
				int offeredPrice = interestedGoods.get(selectedGoods);
				if (game.checkQuantity(quantity, selectedGoods)) {
					game.sellGoods(selectedGoods, quantity, offeredPrice);
					String message = "Sales were successful";
					game.tellPlayer(message);
					moneyLabel.setText("Money : "+game.getMoney()+" coin(s)");
					fillMyGoodsTable(ship.getMyGoods());
				}
				else {
					String message = "You don't have that many quanity of " + selectedGoods.getName();
					game.tellPlayer(message);
	
				}
			}
		});
		
		sellButton.setBounds(668, 263, 139, 23);
		interestedGoodsPanel.add(sellButton);
		sellButton.setEnabled(false);
		
		sellEquipmentPanel = new JPanel();
		sellEquipmentPanel.setBackground(new Color(255, 228, 196));
		tabbedPane.addTab("Buy Ship Equipment", null, sellEquipmentPanel, null);
		sellEquipmentPanel.setLayout(null);
		
		equipmentTitleLabel = new JLabel("Ship's equipment sell by the store: ");
		equipmentTitleLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		equipmentTitleLabel.setBounds(12, 12, 521, 40);
		sellEquipmentPanel.add(equipmentTitleLabel);
		
		equipmentNameLabel = new JLabel("");
		equipmentNameLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		equipmentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		equipmentNameLabel.setBounds(364, 288, 222, 23);
		sellEquipmentPanel.add(equipmentNameLabel);
		
		String equipmentName = currentStore.getEquipment().getName();
		equipmentNameLabel.setText("Name: " + equipmentName);
		
		equipmentPriceLabel = new JLabel("");
		equipmentPriceLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		equipmentPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		equipmentPriceLabel.setBounds(364, 323, 204, 14);
		sellEquipmentPanel.add(equipmentPriceLabel);
		
		int equipmentPrice = currentStore.getEquipment().getSellingPrice();
		equipmentPriceLabel.setText("Price: " + equipmentPrice);
		
		buyEquipmentButton = new JButton("Buy Equipment");
		buyEquipmentButton.setBackground(new Color(255, 222, 173));
		buyEquipmentButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 13));
		buyEquipmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.checkEquipmentFull()) {
					String message = "You can't add more equipment to your ship";
					game.tellPlayer(message);
				}
				else {
					ArrayList<Equipment> equipments = game.getSelectedShip().getEquipment();
					Equipment equipmentForSale = currentStore.getEquipment();
					if(equipments.contains(equipmentForSale)) {
						String message = "You can't add this equipment since you already have it set in your ship!";
						game.tellPlayer(message);
					}
					else {
						if (game.checkEnoughMoney(equipmentPrice)) {
							String message = "The equipment has been added to your ship!";
							game.tellPlayer(message);
							game.buyEquipment(currentStore.getEquipment());
							moneyLabel.setText("Money : "+game.getMoney()+"coin(s)");
						}
						else {
							String message = "Sorry your money is not enought to buy this equipment";
							game.tellPlayer(message);
						}

					}
				}
			}
		});
		buyEquipmentButton.setBounds(328, 349, 276, 23);
		sellEquipmentPanel.add(buyEquipmentButton);
		

		
		moneyLabel = new JLabel("Money : " + game.getMoney() + " coin(s)");
		moneyLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		moneyLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		moneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		moneyLabel.setBounds(682, 13, 243, 16);
		frmStore.getContentPane().add(moneyLabel);
		
		repairExplanationLabel = new JLabel("");
		repairExplanationLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		repairExplanationLabel.setBounds(359, 525, 306, 29);
		frmStore.getContentPane().add(repairExplanationLabel);
		
		equipmentPictureLabel = new JLabel("");
		equipmentPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/cannons.png")));
		equipmentPictureLabel.setBounds(149, 63, 659, 223);
		sellEquipmentPanel.add(equipmentPictureLabel);
		
		this.fillMyGoodsTable(myGoods);
		this.fillSellGoodsTable(sellItems);
		this.fillInterestedGoodsTable(interestedGoods);
		this.updateRepairLabel();
		this.fillEquipmentPicture();
		
	}
	
	/**
	 * Function to fill the equipment that sell by the store
	 */
	public void fillEquipmentPicture() {
		String equipment = this.currentStore.getEquipment().getName();
		if (equipment == "Cannons") {
			equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/cannons.png")));
		}
		else if (equipment == "X-bow") {
			equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/x-bow.png")));
		}
		else if (equipment == "Shield") {
			equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/shield.png")));
		}
		else if (equipment == "Flamethrower") {
			equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/flamethrower.png")));
		}
		else {
			equipmentPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/catapulte.png")));
		}
	}
	
	/**
	 * Update the repair Label to show the cost of the repairment
	 */
	public void updateRepairLabel() {
		int repairCost = game.calculateRepairmentCost();
		if(repairCost == 0) {
			this.repairExplanationLabel.setText("Your ship is fully repaired");
		}
		else {
			this.repairExplanationLabel.setText("The cost to repair your ship is "+repairCost+"coin(s)");
		}
	}
	
	/**
	 * fill the table to shows the goods owned by the player
	 * @param myGoods a hash map of good that the player owned
	 */
	public void fillMyGoodsTable(HashMap<Goods, Integer> myGoods) {
		myGoodsTable = new JTable();
		myGoodsTable.setFont(new Font("Century Schoolbook L", Font.PLAIN, 12));
		myGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"No", "Product's name", "Quantity", "Purchase price (coin)"}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		myGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		myGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		myGoodsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		myGoodsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		myGoodsTableScrollPane.setViewportView(myGoodsTable);
		DefaultTableModel model = (DefaultTableModel)this.myGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: myGoods.entrySet()) 
		{
			String name = set.getKey().getName();
			int quantity = set.getKey().getQuantityOwned();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, quantity, price});
			number++;
		}
	}
	
	
	/**
	 * fill the goods that the store sell
	 * @param sellItems a hash map of the goods that the store sells
	 */
	public void fillSellGoodsTable(HashMap<Goods, Integer> sellItems) 
	{
		DefaultTableModel model = (DefaultTableModel)this.saleGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: sellItems.entrySet())
		{
			String name = set.getKey().getName();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, price});
			number ++;
		}
	}
	
	
	/**
	 * Fill the table which shows the goods that the store want to buy
	 * @param interestedGoods a hash map of goods that the store want to buy
	 */
	public void fillInterestedGoodsTable(HashMap<Goods, Integer> interestedGoods) 
	{
		DefaultTableModel model = (DefaultTableModel)this.interestedGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: interestedGoods.entrySet())
		{
			String name = set.getKey().getName();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, price});
			number ++;
		}
	}
	
	
	/**
	 * check whether the input is in correct format
	 */
	private void checkCanContinue() 
	{
		boolean validInput = quantityTextField.getText().matches(GameEnviromentUi.INPUT_REGEX);
		errorLabel.setText(validInput ? null : GameEnviromentUi.INPUT_REQUIREMENTS);
		buyButton.setEnabled(validInput);
	}

	
	/**
	 *Return the frame for Store screen
	 *@return the frame for store screen
	 */
	@Override
	public JFrame getFrame() 
	{
		return this.frmStore;
	}
	
	
	/**
	 *Close the store screen window
	 */
	@Override
	public void closeWindow() 
	{
		frmStore.dispose();
	}
}

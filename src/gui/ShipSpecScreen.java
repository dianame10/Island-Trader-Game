package gui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Equipment;
import core.GameEnvironment;
import core.Goods;
import core.Ship;
import java.awt.Color;
import java.awt.Font;

/**
 * Class to perform ship's specification screen in a screen using absolute layout
 *
 */
public class ShipSpecScreen implements Screen{
	// The Game Environment the screen interacts with
	private GameEnvironment game;
	
	//The selected Ship by the player
	private Ship ship;
	
	//Frame for Ship Specification Screen
	private JFrame frmShip;
	
	//Panel for the ship specification screen
	private JPanel shipSpecificationPanel;
	
	// Label to display ship's name
	private JLabel shipsNameLabel;
	
	// Label for status label
	private JLabel shipsStatusLabel;
	
	//Label to show ship's current remaining capacity
	private JLabel shipsCurrentCapacityLabel;
	
	// Label for ship's speed
	private JLabel shipsSpeedLabel;
	
	//Label to display equipment for ship
	private JLabel shipsEquipmentLabel;
	
	//Label to show number of crew
	private JLabel crewsInShipLabel;
	
	//Button to call Sold Item Screen
	private JButton soldItemsButton;
	
	//Title Label to display the List of Goods owned by the player
	private JLabel goodsListLabel;
	
	private JScrollPane scrollPane;
	
	//Table to shows Goods owned by the player
	private JTable myGoodsTable;
	
	//Label to show the cost of sailing per day
	private JLabel sailingCostLabel;
	
	//Button to go back to main screen
	private JButton goBackButtton;
	
	//The picture of selected ship
	private JLabel shipPictureLabel;
	
	
	/**
	 * Creating the sip specification screen
	 * @param game The Game Environment the screen will interacts with
	 */
	public ShipSpecScreen(GameEnvironment game) {
		this.game = game;
		this.ship = game.getSelectedShip();
		initialize();
		fillIdentity();
		frmShip.setVisible(true);
		
	}
	
	/**
	 * Fill up Every Label to show Ship Specification
	 */
	public void fillIdentity() {
		String shipName = ship.getShipName();
		String name = shipsNameLabel.getText();
		shipsNameLabel.setText( name + shipName);
		
		double shipStatus = ship.getShipsStatus();
		String status = shipsStatusLabel.getText();
		shipsStatusLabel.setText( status + shipStatus + "HP");
		
		double shipCap = ship.getCapacity();
		String capacity = shipsCurrentCapacityLabel.getText();
		shipsCurrentCapacityLabel.setText(capacity + String.format("%.2f", shipCap) + "m^3");
		
		int shipSpeed = ship.getShipsSpeed();
		String speed = shipsSpeedLabel.getText();
		shipsSpeedLabel.setText( speed + shipSpeed + " km/day");
		
		int shipCrew = ship.getCrew();
		String crew = crewsInShipLabel.getText();
		crewsInShipLabel.setText(crew + shipCrew + " people");
		
		ArrayList<Equipment> shipsEquipment = ship.getEquipment();
		ArrayList<String> shipsEquipmentName = new ArrayList<String>();
		for (Equipment equipment: shipsEquipment) {
			shipsEquipmentName.add(equipment.getName());
		}
		String equipments = shipsEquipmentLabel.getText();
		shipsEquipmentLabel.setText(equipments + shipsEquipmentName);
		
	}
	
	
	
	/**
	 * Initialize the component of the ship specification screen and add action handler to button
	 */
	private void initialize() {
		frmShip = new JFrame();
		frmShip.setTitle("Ship's Specification Screen");
		frmShip.setBounds(100, 100, 850, 560);
		frmShip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		shipSpecificationPanel = new JPanel();
		shipSpecificationPanel.setBackground(new Color(204, 153, 51));
		shipSpecificationPanel.setBounds(16, 47, 280, 148);
		frmShip.getContentPane().add(shipSpecificationPanel);
		shipSpecificationPanel.setLayout(null);
		
		shipsNameLabel = new JLabel("Name: ");
		shipsNameLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		shipsNameLabel.setBounds(16, 47, 354, 29);
		shipSpecificationPanel.add(shipsNameLabel);
		
		shipsStatusLabel = new JLabel("Ship's health: ");
		shipsStatusLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		shipsStatusLabel.setBounds(16, 64, 319, 30);
		shipSpecificationPanel.add(shipsStatusLabel);
		
		shipsCurrentCapacityLabel = new JLabel("Remaining capacity: ");
		shipsCurrentCapacityLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		shipsCurrentCapacityLabel.setBounds(16, 86, 390, 24);
		shipSpecificationPanel.add(shipsCurrentCapacityLabel);
		
		crewsInShipLabel = new JLabel("Crew: ");
		crewsInShipLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		crewsInShipLabel.setBounds(16, 105, 319, 25);
		shipSpecificationPanel.add(crewsInShipLabel);
		
		shipsSpeedLabel = new JLabel("Speed: ");
		shipsSpeedLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		shipsSpeedLabel.setBounds(16, 141, 319, 26);
		shipSpecificationPanel.add(shipsSpeedLabel);
		
		shipsEquipmentLabel = new JLabel("Ship's equipment: ");
		shipsEquipmentLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		shipsEquipmentLabel.setBounds(16, 167, 813, 24);
		shipSpecificationPanel.add(shipsEquipmentLabel);
		
		goBackButtton = new JButton("Go back");
		goBackButtton.setForeground(new Color(204, 153, 0));
		goBackButtton.setBackground(new Color(51, 51, 51));
		goBackButtton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		goBackButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.backToMain();
			}
		});
		shipSpecificationPanel.add(goBackButtton);
		goBackButtton.setBounds(6, 6, 110, 29);
		
		JButton button = new JButton("Button 1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		soldItemsButton = new JButton("See Sold Items");
		soldItemsButton.setForeground(new Color(204, 153, 0));
		soldItemsButton.setBackground(new Color(51, 51, 51));
		soldItemsButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		soldItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.seeSoldItems();
			}
		});
		soldItemsButton.setBounds(664, 219, 151, 29);
		shipSpecificationPanel.add(soldItemsButton);
		
		goodsListLabel = new JLabel("My Goods");
		goodsListLabel.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 17));
		goodsListLabel.setBounds(361, 203, 126, 45);
		shipSpecificationPanel.add(goodsListLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 259, 819, 293);
		shipSpecificationPanel.add(scrollPane);
		
		myGoodsTable = new JTable();
		myGoodsTable.setBackground(new Color(222, 184, 135));
		myGoodsTable.setFont(new Font("Century Schoolbook L", Font.PLAIN, 12));
		myGoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Poduct's name", "Quantity", "Purchase price (coin)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		myGoodsTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		myGoodsTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		myGoodsTable.getColumnModel().getColumn(3).setPreferredWidth(117);
		scrollPane.setViewportView(myGoodsTable);
		
		sailingCostLabel = new JLabel("Sailing cost per day : ");
		sailingCostLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		sailingCostLabel.setBackground(new Color(204, 153, 0));
		sailingCostLabel.setBounds(16, 121, 432, 28);
		shipSpecificationPanel.add(sailingCostLabel);
		sailingCostLabel.setText("Sailing cost per day : " + ship.getWagesPerDay() + "coin(s)");
		
		shipPictureLabel = new JLabel("");
		shipPictureLabel.setBounds(588, 13, 212, 190);
		shipSpecificationPanel.add(shipPictureLabel);
		frmShip.setSize(855, 602);
		
		this.fillMyGoodsTable();
		this.fillShipPictureLabel();
		
	}
	
	
	/**
	 * Function to fill the selected ship's picture
	 */
	public void fillShipPictureLabel() {
		String shipsName = this.ship.getShipName();
		if (shipsName == "Thousand Sunny") {
			shipPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/ThousandSunny.png")));
		}
		else if (shipsName == "Black Pearl") {
			shipPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/BlackPearl.png")));
		}
		else if (shipsName == "Victoria Hunter") {
			shipPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/VictoriaHunter.png")));
		}
		else {
			shipPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/StarOne.png")));
		}
	}
	
	/**
	 * Fill the table to display goods that owned by the player
	 */
	public void fillMyGoodsTable() {
		HashMap<Goods, Integer> myGoods = ship.getMyGoods();
		DefaultTableModel model = (DefaultTableModel)this.myGoodsTable.getModel();
		int number = 1;
		for (HashMap.Entry<Goods, Integer> set: myGoods.entrySet()) {
			String name = set.getKey().getName();
			int quantity = set.getKey().getQuantityOwned();
			int price = set.getValue();
			model.addRow(new Object [] {number, name, quantity, price});
			number++;
		}
	}
	
	
	/**
	 *Close the Ship Specification Screen window
	 */
	@Override
	public void closeWindow() {
		frmShip.dispose();
	}

	/**
	 * Return the frame used by Ship Specification Screen
	 *@return frame for the ship specification screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmShip;
	}
}

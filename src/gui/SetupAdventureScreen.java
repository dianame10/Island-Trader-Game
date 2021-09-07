package gui;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.GameEnvironment;
import core.Ship;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

/**
 * Class to perform setup adventure screen where the player can choose number
 * of days play and choose the ship in a screen using absolute layout
 *
 */
public class SetupAdventureScreen implements Screen {
	// Frame for Setup adventure screen
	private JFrame frmSetupadventure;
	
	//The Game environment that the screen interacts with
	private GameEnvironment game;
	
	// the available ships
	private ArrayList<Ship> availableShips;
	
	//the selected ship by the player
	private Ship selectedShip;
	
	// JSlider to select the maximum traveling days
	private JSlider chooseDaysSlider;
	
	//Label to ask the maximum traveling days
	private JLabel askForDaysLabel;
	
	//Label to give instruction to player
	private JLabel instructionLabel;
	
	// Label to show the chosen maximum traveling days
	private JLabel currentChoosenDaysLabel;
	
	// Label to show TITLE current selected Ship
	private JLabel currentSelectedShipLabel;
	
	// Label to current selected ships
	private JLabel selectedShipLabel;
	
	//Button to choose ship1
	private JButton ship1Button;
	
	//Button to choose ship2
	private JButton ship2Button;
	
	//Button to choose Ship3
	private JButton ship3Button;
	
	//Button to choose Ship4
	private JButton ship4Button;
	
	//Button to start adventure
	private JButton startAdventureButton;
	
	//
	private event days;
	private JLabel blackPearlPictureLabel;
	private JLabel victoriaHunterPictureLabel;
	private JLabel starOnePictureLabel;
	
	/**
	 * Creating the Setup adventure Screen
	 * @param incomingGame the Game environment that screen will interacts with
	 */
	public SetupAdventureScreen(GameEnvironment incomingGame) {
		this.game = incomingGame;
		this.availableShips = game.getAvailableShips();
		this.selectedShip = availableShips.get(0);
		initialize();
		frmSetupadventure.setVisible(true);
	}
	
	/**
	 * Update the selected ship that the layer want to use 
	 * @param index the Index of the ship in availableShip list
	 */
	public void choosenShip(int index) {
		this.selectedShip = availableShips.get(index);
	}
	
	/**
	 * get the selected ship
	 * @return the chosen ship by the player
	 */
	public Ship getSelectedShip() {
		return this.selectedShip;
	}
	
	/**
	 * Initialize the contents of the Setup adventure Screen
	 */
	private void initialize() {
		frmSetupadventure = new JFrame();
		frmSetupadventure.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmSetupadventure.getContentPane().setBackground(new Color(204, 153, 0));
		frmSetupadventure.setTitle("Setup Adventure Screen");
		frmSetupadventure.setBounds(100, 100, 850, 560);
		frmSetupadventure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetupadventure.getContentPane().setLayout(null);
		
		askForDaysLabel = new JLabel("How many days you want your adventure last?");
		askForDaysLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		askForDaysLabel.setBounds(23, 26, 458, 36);
		frmSetupadventure.getContentPane().add(askForDaysLabel);
		
		instructionLabel = new JLabel("Choose your ship!");
		instructionLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		instructionLabel.setBounds(23, 74, 232, 29);
		frmSetupadventure.getContentPane().add(instructionLabel);
		
		currentChoosenDaysLabel = new JLabel("You will trading for: 50 days");
		currentChoosenDaysLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		currentChoosenDaysLabel.setBounds(503, 58, 269, 29);
		frmSetupadventure.getContentPane().add(currentChoosenDaysLabel);
		
		ship1Button = new JButton("Thousand Sunny");
		ship1Button.setBackground(new Color(51, 51, 51));
		ship1Button.setForeground(new Color(204, 153, 0));
		ship1Button.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		ship1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosenShip(0);
				Ship ship = getSelectedShip();
				generatePopUp(ship);
				shipSelection();
			}

		});
		ship1Button.setBounds(23, 298, 165, 29);
		frmSetupadventure.getContentPane().add(ship1Button);
		
		ship2Button = new JButton("Black Pearl");
		ship2Button.setBackground(new Color(51, 51, 51));
		ship2Button.setForeground(new Color(204, 153, 0));
		ship2Button.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		ship2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosenShip(1);
				Ship ship = getSelectedShip();
				generatePopUp(ship);
				shipSelection();
			}
		});
		ship2Button.setBounds(234, 296, 148, 29);
		frmSetupadventure.getContentPane().add(ship2Button);
		
		ship3Button = new JButton("Victoria Hunter");
		ship3Button.setBackground(new Color(51, 51, 51));
		ship3Button.setForeground(new Color(204, 153, 0));
		ship3Button.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		ship3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosenShip(2);
				Ship ship = getSelectedShip();
				generatePopUp(ship);
				shipSelection();
			}
		});
		ship3Button.setBounds(437, 296, 157, 29);
		frmSetupadventure.getContentPane().add(ship3Button);
		
		ship4Button = new JButton("Star One");
		ship4Button.setBackground(new Color(51, 51, 51));
		ship4Button.setForeground(new Color(204, 153, 0));
		ship4Button.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		ship4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosenShip(3);
				Ship ship = getSelectedShip();
				generatePopUp(ship);
				shipSelection();
			}
		});
		ship4Button.setBounds(642, 296, 148, 29);
		frmSetupadventure.getContentPane().add(ship4Button);
		
		startAdventureButton = new JButton("Start your adventure!");
		startAdventureButton.setForeground(new Color(204, 153, 51));
		startAdventureButton.setBackground(new Color(0, 0, 51));
		startAdventureButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 15));
		startAdventureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ship selectedShip = getSelectedShip();
				game.startAdventure(chooseDaysSlider.getValue(), selectedShip);
			}
		});
		startAdventureButton.setBounds(592, 462, 214, 29);
		frmSetupadventure.getContentPane().add(startAdventureButton);
		
		chooseDaysSlider = new JSlider();
		chooseDaysSlider.setBackground(new Color(51, 51, 51));
		chooseDaysSlider.setBounds(503, 11, 259, 29);
		chooseDaysSlider.setMaximum(50);
		chooseDaysSlider.setMinimum(20);
		frmSetupadventure.getContentPane().add(chooseDaysSlider);
		chooseDaysSlider.setMajorTickSpacing(5);
		chooseDaysSlider.setPaintTicks(true);
		
		currentSelectedShipLabel = new JLabel("Your current ship selection:  ");
		currentSelectedShipLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 16));
		currentSelectedShipLabel.setBounds(23, 348, 304, 16);
		frmSetupadventure.getContentPane().add(currentSelectedShipLabel);
		
		selectedShipLabel = new JLabel("");
		selectedShipLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
		selectedShipLabel.setBounds(244, 338, 224, 36);
		frmSetupadventure.getContentPane().add(selectedShipLabel);
		
		JLabel thousandSunnyPictureLabel = new JLabel("");
		thousandSunnyPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/ThousandSunny.png")));
		thousandSunnyPictureLabel.setBounds(33, 114, 179, 182);
		frmSetupadventure.getContentPane().add(thousandSunnyPictureLabel);
		
		blackPearlPictureLabel = new JLabel("");
		blackPearlPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/BlackPearl.png")));
		blackPearlPictureLabel.setBounds(224, 114, 179, 182);
		frmSetupadventure.getContentPane().add(blackPearlPictureLabel);
		
		victoriaHunterPictureLabel = new JLabel("");
		victoriaHunterPictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/VictoriaHunter.png")));
		victoriaHunterPictureLabel.setBounds(426, 114, 179, 182);
		frmSetupadventure.getContentPane().add(victoriaHunterPictureLabel);
		
		starOnePictureLabel = new JLabel("");
		starOnePictureLabel.setIcon(new ImageIcon(SetupAdventureScreen.class.getResource("/images/StarOne.png")));
		starOnePictureLabel.setBounds(627, 114, 179, 182);
		frmSetupadventure.getContentPane().add(starOnePictureLabel);
		
		days = new event();
		chooseDaysSlider.addChangeListener(days);
		
		shipSelection();
	}

	
	/**
	 * Function to get selected days from the player and shows it to the screen
	 *
	 */
	public class event implements ChangeListener {
		public void stateChanged(ChangeEvent days) {
			
			int value = chooseDaysSlider.getValue();
			
			currentChoosenDaysLabel.setText("You will Trading for " + value + " days");
		}
	}
	
	/**
	 * Shows to the user the name of the selected ship by setting the selectedShipLabel to the name of the ship
	 */
	public void shipSelection() {
		String shipName = this.selectedShip.getShipName();
		selectedShipLabel.setText(shipName);
	}
	
	/**
	 * Shows pop up screen to tell the user the specification of the ship
	 * @param ship the selected ship
	 */
	public void generatePopUp(Ship ship) {
		String crew = "Number of crews: " + ship.getCrew() + " people";
		String capacity = "Max cargo capacity: " + ship.getCapacity() + " meter cubic";
		String healthRate = "Ship's health: 100%";
		String speed = "Ship's speed: " + ship.getShipsSpeed() + " Km/day";
		String specification = crew + "\n" + capacity + "\n" + healthRate + "\n" + speed;
		JOptionPane.showMessageDialog(frmSetupadventure, specification);
		
	}
	/**
	 *close the Setup adventure Screen window
	 */
	@Override
	public void closeWindow() {
		frmSetupadventure.dispose();
	}

	/**
	 * Return the frame used for setupAdventureScreen
	 *@return the frame for setupAdventureScreen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmSetupadventure;
	}
}

package gui;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import core.GameEnvironment;
import core.Island;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * Class to perform main screen to show all Islands, remaining days and current money in a screen using absolute layout
 *
 */
public class MainScreen implements Screen {
	// The Game Environment that this Main screen will interacts with
	private GameEnvironment game;
	
	// the frame for main screen
	private JFrame mainScreen;
	
	//Button to see ship specification
	private JButton seeShipButton;
	
	//Button to see Island Sumatra specification
	private JButton sumatraButton;
	
	//Button to see Island Kalimantan specification
	private JButton kalimantanButton;
	
	//Button to see Island Sulawesi specification
	private JButton sulawesiButton;
	
	//Button to see Island Papua specification
	private JButton papuaButton;
	
	//Button to see Island Jawa specification
	private JButton jawaButton;
	
	//Button to see the store at current Location
	private JButton goToStoreButton;
	
	//Label to show how much money that the player has
	private JLabel moneyLabel;
	
	// Label to show how many days remaining
	private JLabel remainingDaysLabel;
	
	// Label to show the current location of the player
	private JLabel currentLocationLabel;
	
	//Button to end the game
	private JButton endGameButton;
	
	// Label to put Sumatra picture
	private JLabel sumatraPictureLabel;
	
	// Label to put Kalimantan picture
	private JLabel kalimantanPictureLabel;
	
	// Label to put Jawa picture
	private JLabel jawaPictureLabel;
	
	// Label to put Sulawesi picture
	private JLabel sulawesiPictureLabel;
	
	// Label to put Papua picture
	private JLabel papuaPictureLabel;
	private JLabel coinPictureLabel;
	
	/**
	 * Create the application.
	 * @param game The GameEnvironment that interacts with this screen
	 */
	public MainScreen(GameEnvironment game) {
		this.game = game;
		initialize();
		mainScreen.setVisible(true);
	}

	
	/**
	 * Disable every island button if no island can be reached
	 */
	public void checkAccessibleIsland() {
		Island island = game.getCurrentLocation();
		if (game.checkAccessibleIsland(island)== false) {
			this.jawaButton.setEnabled(false);
			this.papuaButton.setEnabled(false);
			this.sulawesiButton.setEnabled(false);
			this.kalimantanButton.setEnabled(false);
			this.sumatraButton.setEnabled(false);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainScreen = new JFrame();
		mainScreen.getContentPane().setBackground(new Color(0, 0, 139));
		mainScreen.getContentPane().setForeground(Color.BLACK);
		mainScreen.setTitle("MainScreen");
		mainScreen.setBounds(100, 100, 1093, 642);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.getContentPane().setLayout(null);
		
		seeShipButton = new JButton("See your ship!");
		seeShipButton.setForeground(new Color(204, 153, 0));
		seeShipButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		seeShipButton.setBackground(new Color(51, 51, 51));
		seeShipButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				game.seeShipSpec();
			}
		});
		seeShipButton.setBounds(6, 11, 163, 29);
		mainScreen.getContentPane().add(seeShipButton);
		
		sumatraButton = new JButton("Sumatra");
		sumatraButton.setForeground(new Color(204, 153, 0));
		sumatraButton.setBackground(new Color(51, 51, 51));
		sumatraButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		sumatraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.chooseOtherIsland(1);
			}
		});
		sumatraButton.setBounds(71, 328, 117, 29);
		mainScreen.getContentPane().add(sumatraButton);
		
		kalimantanButton = new JButton("Kalimantan");
		kalimantanButton.setForeground(new Color(204, 153, 0));
		kalimantanButton.setBackground(new Color(51, 51, 51));
		kalimantanButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		kalimantanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.chooseOtherIsland(2);
			}
		});
		kalimantanButton.setBounds(301, 210, 117, 29);
		mainScreen.getContentPane().add(kalimantanButton);
		
		sulawesiButton = new JButton("Sulawesi");
		sulawesiButton.setForeground(new Color(204, 153, 0));
		sulawesiButton.setBackground(new Color(51, 51, 51));
		sulawesiButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		sulawesiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.chooseOtherIsland(3);
			}
		});
		sulawesiButton.setBounds(562, 176, 117, 29);
		mainScreen.getContentPane().add(sulawesiButton);
		
		jawaButton = new JButton("Jawa");
		jawaButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		jawaButton.setBackground(new Color(51, 51, 51));
		jawaButton.setForeground(new Color(204, 153, 0));
		jawaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.chooseOtherIsland(0);
			}
		});
		jawaButton.setBounds(402, 407, 117, 29);
		mainScreen.getContentPane().add(jawaButton);
		
		papuaButton = new JButton("Papua");
		papuaButton.setForeground(new Color(204, 153, 0));
		papuaButton.setBackground(new Color(51, 51, 51));
		papuaButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		papuaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.chooseOtherIsland(4);
			}
		});
		papuaButton.setBounds(823, 285, 117, 29);
		mainScreen.getContentPane().add(papuaButton);
		
		goToStoreButton = new JButton("Go to store at your current location");
		goToStoreButton.setForeground(new Color(204, 153, 0));
		goToStoreButton.setBackground(new Color(51, 51, 51));
		goToStoreButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 13));
		goToStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.goToStore();
			}
		});
		goToStoreButton.setBounds(6, 563, 324, 29);
		mainScreen.getContentPane().add(goToStoreButton);
		
		remainingDaysLabel = new JLabel();
		remainingDaysLabel.setForeground(new Color(218, 165, 32));
		remainingDaysLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		remainingDaysLabel.setText("You have : " + game.getRemainingDays() + " days left.");
		remainingDaysLabel.setBounds(531, 11, 282, 29);
		mainScreen.getContentPane().add(remainingDaysLabel);
		
		moneyLabel = new JLabel();
		moneyLabel.setForeground(new Color(218, 165, 32));
		moneyLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		moneyLabel.setText("Money: " + game.getMoney() + " coin");
		moneyLabel.setBounds(856, 11, 199, 29);
		mainScreen.getContentPane().add(moneyLabel);
		
		currentLocationLabel = new JLabel();
		currentLocationLabel.setForeground(new Color(218, 165, 32));
		currentLocationLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 19));
		Island island = game.getCurrentLocation();
		String locationName = island.getIslandName();
		currentLocationLabel.setText("Your current location: " + locationName);
		currentLocationLabel.setBounds(16, 52, 417, 40);
		mainScreen.getContentPane().add(currentLocationLabel);
		
		endGameButton = new JButton("End Game");
		endGameButton.setForeground(new Color(204, 153, 0));
		endGameButton.setBackground(new Color(51, 51, 51));
		endGameButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		endGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.gameOver("You ended the game!");
			}
		});
		endGameButton.setBounds(351, 563, 126, 29);
		mainScreen.getContentPane().add(endGameButton);
		
		sumatraPictureLabel = new JLabel("");
		sumatraPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/sumatraIsland.png")));
		sumatraPictureLabel.setBounds(16, 118, 185, 373);
		mainScreen.getContentPane().add(sumatraPictureLabel);
		
		kalimantanPictureLabel = new JLabel("");
		kalimantanPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/kalimantanIsland.png")));
		kalimantanPictureLabel.setBounds(230, 97, 289, 264);
		mainScreen.getContentPane().add(kalimantanPictureLabel);
		
		jawaPictureLabel = new JLabel("");
		jawaPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/jawaIsland.png")));
		jawaPictureLabel.setBounds(324, 434, 302, 96);
		mainScreen.getContentPane().add(jawaPictureLabel);
		
		sulawesiPictureLabel = new JLabel("");
		sulawesiPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/sulawesiIsland.png")));
		sulawesiPictureLabel.setBounds(551, 118, 199, 246);
		mainScreen.getContentPane().add(sulawesiPictureLabel);
		
		papuaPictureLabel = new JLabel("");
		papuaPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/papuaIsland.png")));
		papuaPictureLabel.setBounds(760, 191, 309, 272);
		mainScreen.getContentPane().add(papuaPictureLabel);
		
		coinPictureLabel = new JLabel("");
		coinPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/coin.png")));
		coinPictureLabel.setBounds(823, 11, 30, 29);
		mainScreen.getContentPane().add(coinPictureLabel);
		checkAccessibleIsland();
	}
	
	/**
	 *close this Main screen Window
	 */
	@Override
	public void closeWindow() {
		mainScreen.dispose();
	}
	
	/**
	 * Return the frame for main screen
	 *@return the frame used for main screen
	 */
	@Override
	public JFrame getFrame() {
		return this.mainScreen;
	}
}

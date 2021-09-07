package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;

import core.GameEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 * Class to perform random event screen that happened in the route in a screen using absolute layout
 *
 */
public class RandomEventScreen implements Screen {
	// Frame for Random Event Screen
	private JFrame randomEventFrame;
	
	//The Game Environment that this Random Event Screen interacts with
	private GameEnvironment game;
	
	// The Random event that the player will face
	private String event;
	
	// The label to show the event that the player will face
	private JLabel eventNameLabel;
	
	// the OK button
	private JButton confirmationButton;
	
	// Label to explain what the effect of facing a particular event
	private JLabel explainationLabel;
	
	//Label to put random event picture
	private JLabel randomEventPictureLabel;

	
	/**
	 * Create the random Event Screen
	 * @param game The Game Environment that RandomEventScreen interacts with
	 * @param event The event that the player will face
	 */
	public RandomEventScreen(GameEnvironment game, String event) {
		this.game = game;
		this.event = event;
		initialize();
		randomEventFrame.setVisible(true);
	}
	
	/**
	 * update the explanation Label to describe what will happen if player pirate a certain Random Event
	 */
	public void updateNameEvent(){

		if(this.event == "PIRATE") 
		{
			this.explainationLabel.setText("You meet a pirates, you should play number games to against pirates");

		}
		else if (this.event == "UNFORTUNATE WEATHER"){
			int damage = game.meetUnfortunateWeather();
			this.explainationLabel.setText("Your ship's health has been reduced by " + damage + " (point) because you just meet unfortunate weather");
		}
		else {
			int numberOfReward = game.meetSailorToRescue();
			this.explainationLabel.setText("Your have been rewarded by sailors you rescued, they gave you " + numberOfReward + " coin(s)");
		}
	}
	
	public void fillRandomEventPictureTable() {
		if (this.event == "PIRATE") {
			this.randomEventPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/pirate.png")));
		}
		else if(this.event == "UNFORTUNATE WEATHER") {
			this.randomEventPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/unfortunateweather.png")));
		}
		else {
			this.randomEventPictureLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/rescueSailors.png")));
		}
	}

	/**
	 * Initialize the contents of Random Event Screen
	 */
	private void initialize() {
		randomEventFrame = new JFrame();
		randomEventFrame.getContentPane().setBackground(new Color(218, 165, 32));
		randomEventFrame.setTitle("Random Event Screen");
		randomEventFrame.setBounds(100, 100, 735, 520);
		randomEventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		randomEventFrame.getContentPane().setLayout(null);
		
		eventNameLabel = new JLabel("You are facing: " + this.event);
		eventNameLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 17));
		eventNameLabel.setBounds(10, 11, 454, 16);
		randomEventFrame.getContentPane().add(eventNameLabel);
		
		confirmationButton = new JButton("OK");
		confirmationButton.setFont(new Font("Century Schoolbook L", Font.BOLD, 23));
		confirmationButton.setForeground(new Color(218, 165, 32));
		confirmationButton.setBackground(new Color(0, 0, 0));
		confirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.actionRandomEvent(event);
			}
		});
		confirmationButton.setBounds(268, 407, 153, 65);
		randomEventFrame.getContentPane().add(confirmationButton);
		
		explainationLabel = new JLabel("");
		explainationLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 12));
		explainationLabel.setBounds(10, 38, 699, 52);
		randomEventFrame.getContentPane().add(explainationLabel);
		
		randomEventPictureLabel = new JLabel("");
		randomEventPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		randomEventPictureLabel.setBounds(10, 91, 699, 309);
		randomEventFrame.getContentPane().add(randomEventPictureLabel);
		
		updateNameEvent();
		this.fillRandomEventPictureTable();
	}
	
	/**
	 * close Random Event Screen window
	 */
	@Override
	public void closeWindow() {
		randomEventFrame.dispose();
	}

	/**
	 * Return frame that used for Random Event screen
	 *@return the frame for Random Event Screen
	 */
	@Override
	public JFrame getFrame() {
		return this.randomEventFrame;
	}
}

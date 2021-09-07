package gui;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import core.GameEnvironment;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Class to perform game over screen and show the reason in a screen using absolute layout
 *
 */
public class GameOverScreen implements Screen{


	//	Screen to lay out components for screen that used when the game is over for some reason
	private JFrame gameOverScreen;
	
	// The Game Environment that this screen interact with
	private GameEnvironment game;
	
	//	A Label to show why the game is over
	private JLabel reasonLabel;
	
	//	A label to show how many the player plays
	private JLabel daysPlayedLabel;
	
	//	A Label to show Title final score
	private JLabel scoreTitleLabel;
	
	// a button to create new game if the player want to play again
	private JButton tryAgainButton;
	
	// A button to quit the game
	private JButton quitButton;
	
	// Label to show total profit the player achieved
	private JLabel profitLossLabel;
	
	// Label asking does the player want to play again or not
	private JLabel tryAgainLabel;
	
	// A label to show the player's name
	private JLabel userNameLabel;
	
	// A Label to show the final score achieved by the player
	private JLabel finalScoreLabel;
	
	// message to player
	private String message;
	
	// player's name
	private String userName;
	
	// duration the player plays
	private int durationGame;
	
	// calculated final score
	private String finalScore;
	private JLabel scoreFramePictureLabel;
	
	
	
	/**
	 * Create Game Over Screen
	 * 
	 * @param game The Game Environment this Game Over screen Interact with
	 * @param message The message why the Game ended
	 */
	public GameOverScreen(GameEnvironment game, String message) {
		this.game = game;
		this.message = message;
		this.userName = game.getName();
		this.durationGame = game.getTravellingDays();
		this.finalScore = Integer.toString(game.countFinalScore());
		initialize();
		gameOverScreen.setVisible(true);
	}
	
	/**
	 * Update profitLossTable to show whether the player make profit or not, if yes then it shows how much is his profit
	 */
	public void updateProfitLabel() {
		if(game.countProfit()<=0) {
			this.profitLossLabel.setText("You don't make any profit!");
		}
		else {
			this.profitLossLabel.setText("Your total profit is "+game.countProfit());
		}
	}

	/**
	 * Initialize the contents of the frmGameOver.
	 */
	private void initialize() {
		gameOverScreen = new JFrame();
		gameOverScreen.getContentPane().setBackground(new Color(218, 165, 32));
		gameOverScreen.setBounds(100, 100, 850, 560);
		gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverScreen.getContentPane().setLayout(null);
		
		reasonLabel = new JLabel();
		reasonLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		reasonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reasonLabel.setText(message);
		reasonLabel.setBounds(12, 162, 816, 16);
		gameOverScreen.getContentPane().add(reasonLabel);
		
		daysPlayedLabel = new JLabel("");
		daysPlayedLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		daysPlayedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		daysPlayedLabel.setText("You have play for: " + durationGame + " days");
		daysPlayedLabel.setBounds(319, 216, 214, 16);
		gameOverScreen.getContentPane().add(daysPlayedLabel);
		
		scoreTitleLabel = new JLabel("Your score is: ");
		scoreTitleLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		scoreTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTitleLabel.setBounds(365, 293, 117, 16);
		gameOverScreen.getContentPane().add(scoreTitleLabel);
		
		tryAgainButton = new JButton("Try again");
		tryAgainButton.setForeground(new Color(218, 165, 32));
		tryAgainButton.setBackground(new Color(0, 0, 0));
		tryAgainButton.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		tryAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				game.newGame();
			}
		});
		tryAgainButton.setBounds(192, 452, 187, 55);
		gameOverScreen.getContentPane().add(tryAgainButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBackground(new Color(0, 0, 0));
		quitButton.setForeground(new Color(218, 165, 32));
		quitButton.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		quitButton.setBounds(481, 452, 187, 55);
		gameOverScreen.getContentPane().add(quitButton);
		
		userNameLabel = new JLabel("");
		userNameLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setText("Name: " + this.userName);
		userNameLabel.setBounds(331, 190, 177, 14);
		gameOverScreen.getContentPane().add(userNameLabel);
		
		finalScoreLabel = new JLabel(finalScore);
		finalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		finalScoreLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 25));
		finalScoreLabel.setBounds(365, 321, 123, 82);
		gameOverScreen.getContentPane().add(finalScoreLabel);
		
		tryAgainLabel = new JLabel("Would You Like To Try Again?");
		tryAgainLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		tryAgainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tryAgainLabel.setBounds(297, 396, 269, 44);
		gameOverScreen.getContentPane().add(tryAgainLabel);
		
		profitLossLabel = new JLabel("Profit Label");
		profitLossLabel.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		profitLossLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profitLossLabel.setBounds(251, 244, 337, 37);
		gameOverScreen.getContentPane().add(profitLossLabel);
		
		JLabel pictureLabel = new JLabel("");
		pictureLabel.setIcon(new ImageIcon(GameOverScreen.class.getResource("/images/GameOver.png")));
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLabel.setBounds(264, 30, 324, 148);
		gameOverScreen.getContentPane().add(pictureLabel);
		
		scoreFramePictureLabel = new JLabel("");
		scoreFramePictureLabel.setIcon(new ImageIcon(GameOverScreen.class.getResource("/images/scoreFrame.png")));
		scoreFramePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreFramePictureLabel.setBounds(365, 321, 129, 82);
		gameOverScreen.getContentPane().add(scoreFramePictureLabel);
		this.updateProfitLabel();
	}
	
	/**
	 *Close Game Over screen
	 */
	@Override
	public void closeWindow() {
		gameOverScreen.dispose();
		
	}

	/**
	 *Return the frame for Game Over screen
	 */
	@Override
	public JFrame getFrame() {
		return this.gameOverScreen;
	}
}

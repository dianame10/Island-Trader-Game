package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import core.GameEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

/**
 * Class to perform rolling a dice games in a screen using absolute layout
 *
 */
public class RollingDiceScreen implements Screen {
	//The Game Environment that the RollingDiceScreen interacts with
	private GameEnvironment game;
	
	// Frame for the RollingDiceScreen
	private JFrame frmRollingADie;
	
	//Label that shows the instruction to play the game
	private JLabel instructionLabel;
	
	// Label to show the instruction to play the game
	private JLabel gameInstructionLabel;
	
	// Button for player to choose odd
	private JButton oddButton;
	
	// Button for player to choose even
	private JButton evenButton;
	
	// Roll the  Dice button
	private JButton takeActionButton;
	
	// Label to explain the explain the result of the game
	private JLabel explanationLabel;
	
	// Label to display the result of the game
	private JLabel resultLabel;
	
	// The OK Button
	private JButton okButton;
	
	// The selected outcome prediction by the player 
	private String selectedPrediction;
	
	// Label to show the selected prediction by the player
	private JLabel predictionByPlayerLabel;
	
	// state of the result of the game
	private String state;
	
	/**
	 * Creating Rolling Dice Screen
	 * @param game The Game Environment that the screen interacts with
	 */
	public RollingDiceScreen(GameEnvironment game) {
		this.game = game;
		initialize();
		frmRollingADie.setVisible(true);
	}
	
	/**
	 * update the selected outcome prediction and set the text of predictionBylabel to show the current prediction
	 * @param chosenPrediction the selected prediction by player
	 */
	public void updateSelectedPrediction(String chosenPrediction) {
		this.selectedPrediction = chosenPrediction;
		this.predictionByPlayerLabel.setText("You predict the outcome is "+ chosenPrediction);
	}
	
	/**
	 * Make the odd, even, and roll the dice button disabled if the player win or lose
	 * Displaying the game result in explanationLabel
	 */
	public void decideWinOrLose() {
		this.takeActionButton.setEnabled(false);
		this.evenButton.setEnabled(false);
		this.oddButton.setEnabled(false);
		this.okButton.setVisible(true);
		this.explanationLabel.setVisible(true);
		this.resultLabel.setVisible(true);
		int decision = game.determineWinnningRollingADie();
		int piratesThrows = game.getWhatPiratesThrowRD(decision, this.selectedPrediction);
		this.resultLabel.setText("The die is showing " + piratesThrows);
		if (decision == 0) {
			int piratesDemand = game.getPiratesDemand();
			this.explanationLabel.setText("You lose! Pirates demands goods in your ship worth at least: " + piratesDemand + " coins");
			if (game.checkPirateSatisfaction()) {
				game.removeAllGoods();
				this.state = "Lose but safe";
				
			}
			
			else 
			{
				game.removeAllGoods();
				int money = game.getMoney();
				game.updateMoney(money * -1);
				this.state="Game over";			
			}

		}
		else {
			this.state = "Win";
			this.explanationLabel.setText("You Win!! You can Continue your journey to your destination!");
		}
	}

	
	
	/**
	 * Initialize the contents of Rolling A Die game screen
	 */
	private void initialize() {
		frmRollingADie = new JFrame();
		frmRollingADie.getContentPane().setBackground(new Color(218, 165, 32));
		frmRollingADie.setTitle("Rolling A Die Game Screen");
		frmRollingADie.setBounds(100, 100, 679, 539);
		frmRollingADie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRollingADie.getContentPane().setLayout(null);
		
		instructionLabel = new JLabel("The pirate ask you to play guessing game!");
		instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLabel.setBounds(12, 29, 655, 29);
		frmRollingADie.getContentPane().add(instructionLabel);
		
		gameInstructionLabel = new JLabel("You just have to guess whether after the dices rolled, they will be odd or even");
		gameInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameInstructionLabel.setBounds(22, 62, 645, 29);
		frmRollingADie.getContentPane().add(gameInstructionLabel);
		
		oddButton = new JButton("Odd");
		oddButton.setForeground(new Color(218, 165, 32));
		oddButton.setBackground(new Color(0, 0, 0));
		oddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedPrediction("Odd");
			}
		});
		oddButton.setBounds(32, 122, 269, 60);
		frmRollingADie.getContentPane().add(oddButton);
		
		evenButton = new JButton("Even");
		evenButton.setForeground(new Color(218, 165, 32));
		evenButton.setBackground(new Color(0, 0, 0));
		evenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedPrediction("Even");
			}
		});
		evenButton.setBounds(345, 122, 256, 60);
		frmRollingADie.getContentPane().add(evenButton);
		
		takeActionButton = new JButton("Roll The Dice!");
		takeActionButton.setBackground(new Color(0, 0, 0));
		takeActionButton.setForeground(new Color(218, 165, 32));
		takeActionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decideWinOrLose();
			}
		});
		takeActionButton.setBounds(257, 233, 191, 38);
		frmRollingADie.getContentPane().add(takeActionButton);
		
		explanationLabel = new JLabel("explanation");
		explanationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		explanationLabel.setBounds(10, 308, 645, 112);
		frmRollingADie.getContentPane().add(explanationLabel);
		explanationLabel.setVisible(false);
		
		resultLabel = new JLabel("The die show number");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(10, 283, 645, 14);
		frmRollingADie.getContentPane().add(resultLabel);
		resultLabel.setVisible(false);
		
		okButton = new JButton("OK");
		okButton.setForeground(new Color(218, 165, 32));
		okButton.setFont(new Font("Dialog", Font.BOLD, 12));
		okButton.setBackground(new Color(0, 0, 0));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gameName = "rolling a die";
				game.executeOkButton(state, gameName);
			}
		});
		okButton.setBounds(248, 431, 151, 60);
		frmRollingADie.getContentPane().add(okButton);
		okButton.setVisible(false);
		
		predictionByPlayerLabel = new JLabel("prediction");
		predictionByPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		predictionByPlayerLabel.setBounds(168, 190, 368, 33);
		frmRollingADie.getContentPane().add(predictionByPlayerLabel);
		
	}

	/**
	 *Close the Rolling A Die Screen
	 */
	@Override
	public void closeWindow() {
		this.frmRollingADie.dispose();
	}

	/**
	 * Return frame for Rolling A Die screen
	 *@return the frame used for the RollingADie Screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmRollingADie;
	}
}

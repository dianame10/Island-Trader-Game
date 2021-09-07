package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import core.GameEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Class to perform rock paper scissors games in a screen using absolute layout
 *
 */
public class RockPaperScissorsScreen implements Screen {
	// The Game Environment that RockPaperScissors Screen interacts with
	private GameEnvironment game;
	
	//Frame for RockPaperScissors Screen
	private JFrame frmRockPaperScissors;
	
	//The selected Sign by the player
	private String selectedSign;
	
	// Label to show the instructions to play Rock Paper scissor game
	private JLabel instructionLabel;
	
	//Button to select rock sign
	private JButton rockButton;
	
	//Button to select paper Sign
	private JButton paperButton;
	
	//Button to select scissor sign
	private JButton scissorsButton;
	
	//Label to show instructions to play Rock Paper Scissor game
	private JLabel instructionToChooseActionLabel;
	
	// button to throw the selected sign
	private JButton throwButton;
	
	//Label to explain whether the player win or lose
	private JLabel explanationLabel;
	
	//OK Button
	private JButton confirmationButton;
	
	//Label to show the current selection of sign that the player want to throw
	private JLabel currentSelectionLabel;
	
	//Label to show what the pirate throw
	private JLabel piratesThrowLabel;
	
	// the state after playing the game
	private String state;


	/**
	 * Create RockPaper Scissor Game Screen
	 * @param incomingGame the Game Environment that the screen interacts with
	 */
	public RockPaperScissorsScreen(GameEnvironment incomingGame) {
		this.game = incomingGame;
		initialize();
		frmRockPaperScissors.setVisible(true);
	}
	
	/**
	 * Update the selected Sign that the player want to throw
	 * @param chosenSign the selected Sign
	 */
	public void updateSelectedSign(String chosenSign) {
		this.selectedSign = chosenSign;
		this.currentSelectionLabel.setText("Your current selection : "+chosenSign);
	}
	
	/**
	 * Make the paper, scissor, paper and throw button disabled if the player win or lose
	 * if the game result is draw then ask the player to play again
	 * Displaying the game result in explanationLabel
	 */
	public void decideWinOrLose() {
		this.throwButton.setEnabled(false);
		this.paperButton.setEnabled(false);
		this.rockButton.setEnabled(false);
		this.scissorsButton.setEnabled(false);
		int decision = game.determineWinnningRPSGame();
		String piratesThrows = game.getWhatPiratesThrowRPS(decision, this.selectedSign);
		this.piratesThrowLabel.setText("The pirate throws : "+piratesThrows);
		this.piratesThrowLabel.setVisible(true);
		this.explanationLabel.setVisible(true);
		if (decision == 0) {
			int piratesDemand = game.getPiratesDemand();
			this.explanationLabel.setText("You lose! Pirates demands goods in your ship worth at least: " + piratesDemand + " coins");
			if (game.checkPirateSatisfaction()) {
				game.removeAllGoods();
				this.state = "Lose but safe";
			}
			else {
				game.removeAllGoods();
				int money = game.getMoney();
				game.updateMoney(money * -1);
				this.state="Game over";
			}
			this.confirmationButton.setVisible(true);
		}
		else if (decision == 1) {
			this.explanationLabel.setText("You need to play again!");
			this.throwButton.setEnabled(true);
			this.paperButton.setEnabled(true);
			this.rockButton.setEnabled(true);
			this.scissorsButton.setEnabled(true);
		}
		else {
			this.confirmationButton.setVisible(true);
			this.state = "Win";
			this.explanationLabel.setText("You Win!! You can Continue your journey to your destination!");
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRockPaperScissors = new JFrame();
		frmRockPaperScissors.getContentPane().setBackground(new Color(218, 165, 32));
		frmRockPaperScissors.setTitle("Rock Paper scissors Game Screen");
		frmRockPaperScissors.setBounds(100, 100, 642, 518);
		frmRockPaperScissors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRockPaperScissors.getContentPane().setLayout(null);
		
		instructionLabel = new JLabel("The pirate ask you to play Rock paper Scissors Game!");
		instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLabel.setBounds(70, 43, 495, 14);
		frmRockPaperScissors.getContentPane().add(instructionLabel);
		
		rockButton = new JButton("Rock");
		rockButton.setBackground(new Color(255, 255, 0));
		rockButton.setIcon(new ImageIcon(RockPaperScissorsScreen.class.getResource("/images/rock.png")));
		rockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedSign("Rock");
			}
		});
		rockButton.setBounds(90, 100, 97, 100);
		frmRockPaperScissors.getContentPane().add(rockButton);
		
		paperButton = new JButton("Paper");
		paperButton.setBackground(Color.YELLOW);
		paperButton.setIcon(new ImageIcon(RockPaperScissorsScreen.class.getResource("/images/paper.png")));
		paperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedSign("Paper");
			}
		});
		paperButton.setBounds(264, 100, 97, 100);
		frmRockPaperScissors.getContentPane().add(paperButton);
		
		scissorsButton = new JButton("Scissors");
		scissorsButton.setBackground(Color.YELLOW);
		scissorsButton.setIcon(new ImageIcon(RockPaperScissorsScreen.class.getResource("/images/scissor.png")));
		scissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSelectedSign("Scissors");
			}
		});
		scissorsButton.setBounds(437, 100, 97, 100);
		frmRockPaperScissors.getContentPane().add(scissorsButton);
		
		instructionToChooseActionLabel = new JLabel("Select the one you want to throw!");
		instructionToChooseActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionToChooseActionLabel.setBounds(80, 67, 485, 14);
		frmRockPaperScissors.getContentPane().add(instructionToChooseActionLabel);
		
		throwButton = new JButton("Throw!");
		throwButton.setForeground(new Color(218, 165, 32));
		throwButton.setBackground(new Color(0, 0, 0));
		throwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decideWinOrLose();
			}
		});
		throwButton.setBounds(264, 286, 89, 23);
		frmRockPaperScissors.getContentPane().add(throwButton);
		
		explanationLabel = new JLabel("Explanation for win or lose");
		explanationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		explanationLabel.setBounds(10, 364, 620, 23);
		frmRockPaperScissors.getContentPane().add(explanationLabel);
		explanationLabel.setVisible(false);
		
		confirmationButton = new JButton("OK");
		confirmationButton.setBackground(new Color(0, 0, 0));
		confirmationButton.setForeground(new Color(218, 165, 32));
		confirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gameName = "rock paper scissors";
				game.executeOkButton(state, gameName);
			}
		});
		confirmationButton.setBounds(264, 414, 89, 23);
		frmRockPaperScissors.getContentPane().add(confirmationButton);
		confirmationButton.setVisible(false);
		
		currentSelectionLabel = new JLabel("Your current selection");
		currentSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentSelectionLabel.setBounds(199, 223, 254, 36);
		frmRockPaperScissors.getContentPane().add(currentSelectionLabel);
		
		piratesThrowLabel = new JLabel("WhatPirateThrow");
		piratesThrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		piratesThrowLabel.setBounds(25, 338, 593, 14);
		frmRockPaperScissors.getContentPane().add(piratesThrowLabel);
		piratesThrowLabel.setVisible(false);
	}

	/**
	 *Close the Rock Paper Scissor game  Screen window
	 */
	@Override
	public void closeWindow() {
		this.frmRockPaperScissors.dispose();
	}

	/**
	 * Return the frame used for Rock Paper Scissor game Screen
	 *@return the frame for Rock Paper Scissor game Screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmRockPaperScissors;
	}
}

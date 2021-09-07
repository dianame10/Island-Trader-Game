package gui;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import core.GameEnvironment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Class to perform loading screen when go from origin island to another island in a screen using absolute layout
 *
 */
public class LoadingScreen implements Screen{
	// The Game Environment that this screen interacts with
	private GameEnvironment game;
	
	//The frame for loading screen
	private JFrame loadingScreen;
	
	// A loading bar which indicates the sailing
	private JProgressBar progressBar;
	
	// a state to make the function to fill progressBar only runs once
	private boolean stateProgressBar = true;
	
	// Label that says "Sailing..."
	private JLabel sailingLabel;
	
	// Label to put loading picture
	private JLabel loadingPicture;


	/**
	 * Create the application.
	 * @param game The Game Environment this screen will interacts with
	 */
	public LoadingScreen(GameEnvironment game) {
		this.game = game;
		initialize();
		loadingScreen.setVisible(true);
	}
	
	
	/**
	 * make the progress bar runs and call function in Game Environment to decide whether player meet random event or not after the progress bar hits 100%
	 */
	public void fillProgressBar() {
		for(int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.progressBar.setValue(i);
			this.progressBar.update(this.progressBar.getGraphics());
		}
		game.decideMeetRandomEvent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loadingScreen = new JFrame();
		loadingScreen.getContentPane().setBackground(new Color(218, 165, 32));
		loadingScreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(stateProgressBar == true) {
					fillProgressBar();
					stateProgressBar = false;
				}
				
			}
		});

		loadingScreen.setTitle("Loading Screen");
		loadingScreen.setBounds(100, 100, 683, 523);
		loadingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingScreen.getContentPane().setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLACK);
		progressBar.setBounds(10, 440, 649, 35);
		loadingScreen.getContentPane().add(progressBar);
		progressBar.setStringPainted(true);
		
		sailingLabel = new JLabel("Sailing...");
		sailingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		sailingLabel.setBounds(10, 385, 178, 44);
		loadingScreen.getContentPane().add(sailingLabel);
		
		loadingPicture = new JLabel("");
		loadingPicture.setIcon(new ImageIcon(LoadingScreen.class.getResource("/images/sailing.png")));
		loadingPicture.setBounds(10, 38, 649, 339);
		loadingScreen.getContentPane().add(loadingPicture);
		
		
	}

	
	/**
	 * Return the frame of loading screen
	 *@return the frame for loading screen
	 */
	@Override
	public JFrame getFrame() {
		return this.loadingScreen;
	}
	
	/**
	 *close the Loading Screen window
	 */
	@Override
	public void closeWindow() {
		loadingScreen.dispose();
	}
}

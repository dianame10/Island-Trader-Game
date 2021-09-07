package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import core.GameEnvironment;
import core.Route;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 * Class to perform route between 2 islands in a screen using absolute layout
 *
 */
public class RouteScreen implements Screen{
	//The Game Environment the screen interacts with
	private GameEnvironment game;
	
	//the route between two islands
	private Route theRoute;
	
	//frame used for Route Screen
	private JFrame frmChooseYourRoute;
	
	//Label to show one end of the route
	private JLabel currentIslandLabel;
	
	//Label to show the other end of the route
	private JLabel destinationIslandLabel;
	
	//Button to set sail to destination island
	private JButton setSailButton;
	
	//Button to go back to main screen
	private JButton goBackButton;
	
	// Label to show the probability of meeting Random Event
	private JLabel routeExplanationLabel;
	
	// Label to show the traveling time for the route
	private JLabel requiredDaysLabel;
	
	// Label to show the distance between two islands
	private JLabel distanceLabel;
	
	/**
	 * Return the frame that used by Route Screen
	 *@return the frame for Route screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmChooseYourRoute;
	}

	/**
	 * Creating the Route screen
	 * @param game the Game Environment that the scren interacts with
	 */
	public RouteScreen(GameEnvironment game) {
		this.game = game;
		this.theRoute = game.getIncidentRoutes();
		initialize();
		frmChooseYourRoute.setVisible(true);
	}
	
	/**
	 *close the Route Screen window
	 */
	@Override
	public void closeWindow() {
		frmChooseYourRoute.dispose();
	}
	

	/**
	 * Initialize the contents for Route Screen
	 */
	private void initialize() {
		frmChooseYourRoute = new JFrame();
		frmChooseYourRoute.getContentPane().setBackground(new Color(218, 165, 32));
		frmChooseYourRoute.setTitle("Choose Your Route");
		frmChooseYourRoute.setBounds(100, 100, 611, 442);
		frmChooseYourRoute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChooseYourRoute.getContentPane().setLayout(null);
		
		currentIslandLabel = new JLabel(theRoute.getOneEnd());
		currentIslandLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		currentIslandLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentIslandLabel.setBounds(30, 189, 177, 31);
		frmChooseYourRoute.getContentPane().add(currentIslandLabel);
		
		destinationIslandLabel = new JLabel(theRoute.getOtherEnd());
		destinationIslandLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		destinationIslandLabel.setHorizontalAlignment(SwingConstants.CENTER);
		destinationIslandLabel.setBounds(352, 189, 177, 31);
		frmChooseYourRoute.getContentPane().add(destinationIslandLabel);
		
		setSailButton = new JButton("Set Sail!");
		setSailButton.setForeground(new Color(218, 165, 32));
		setSailButton.setBackground(new Color(0, 0, 0));
		setSailButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		setSailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.checkAbleToSail(theRoute);
			}
		});
		setSailButton.setBounds(198, 356, 203, 38);
		frmChooseYourRoute.getContentPane().add(setSailButton);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setForeground(new Color(218, 165, 32));
		goBackButton.setBackground(new Color(0, 0, 0));
		goBackButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.backToMain();
			}
		});
		goBackButton.setBounds(10, 11, 154, 31);
		frmChooseYourRoute.getContentPane().add(goBackButton);
		
		routeExplanationLabel = new JLabel(theRoute.toString());
		routeExplanationLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		routeExplanationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		routeExplanationLabel.setBounds(61, 232, 479, 38);
		frmChooseYourRoute.getContentPane().add(routeExplanationLabel);
		
		requiredDaysLabel = new JLabel("Total sailing days required is " + game.calculateSailingDays(theRoute) + "days");
		requiredDaysLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		requiredDaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
		requiredDaysLabel.setBounds(30, 314, 557, 23);
		frmChooseYourRoute.getContentPane().add(requiredDaysLabel);
		
		distanceLabel = new JLabel("The distance between those islands is "+ theRoute.getDistance() + " Km");
		distanceLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		distanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		distanceLabel.setBounds(10, 282, 595, 31);
		frmChooseYourRoute.getContentPane().add(distanceLabel);
	}
}

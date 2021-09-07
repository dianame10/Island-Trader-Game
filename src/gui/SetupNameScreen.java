package gui;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.GameEnvironment;
import seng201_project.GameEnviromentUi;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

/**
 * Class to perform setup name screen where the user should input their user name in a screen using absolute layout
 *
 */
public class SetupNameScreen implements Screen {
	//The Game Environment that the screen interacts with
	private GameEnvironment game;
	
	//Frame for SetupNameScreen
	private JFrame frmSetupnamescreen;
	
	//A field text so player can input a string of name
	private JTextField fillNameTextField;
	
	//Label to greet the player
	private JLabel greetingLabel;
	
	//Go Button
	private JButton goButton;
	
	//Label to show error
	private JLabel lblError;


	/**
	 * Create the Setup Name Screen
	 * 
	 * @param game The GameEnvironment that interacts with this screen
	 */
	public SetupNameScreen(GameEnvironment game)	{
		this.game = game;
		initialize();
		frmSetupnamescreen.setVisible(true);
	}

	/**
	 *Close the setup name screen window screen
	 */
	@Override
	public void closeWindow() 
	{
		frmSetupnamescreen.dispose();
	}

	/**
	 * Initialize the contents of setup name screen
	 */
	private void initialize()
	{
		frmSetupnamescreen = new JFrame();
		frmSetupnamescreen.getContentPane().setBackground(new Color(218, 165, 32));
		frmSetupnamescreen.setTitle("SetupNameScreen");
		frmSetupnamescreen.setBounds(100, 100, 850, 560);
		frmSetupnamescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetupnamescreen.getContentPane().setLayout(null);
		
		greetingLabel = new JLabel("Hey mate! What is your name?");
		greetingLabel.setForeground(Color.BLACK);
		greetingLabel.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 20));
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLabel.setBounds(12, 190, 826, 38);
		frmSetupnamescreen.getContentPane().add(greetingLabel);
		
		fillNameTextField = new JTextField();
		fillNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		fillNameTextField.setBounds(265, 240, 351, 32);
		frmSetupnamescreen.getContentPane().add(fillNameTextField);
		fillNameTextField.setColumns(10);
		
		
		lblError = new JLabel();
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setText(GameEnviromentUi.NAME_REQUIREMENTS);
		lblError.setForeground(Color.RED);
		lblError.setFont(lblError.getFont().deriveFont(10f));
		lblError.setPreferredSize(lblError.getPreferredSize());
		lblError.setBounds(12, 281, 826, 13);
		frmSetupnamescreen.getContentPane().add(lblError);
		
		
		fillNameTextField.getDocument().addDocumentListener(new DocumentListener() {
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


		goButton = new JButton("Ready to go!!!");
		goButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		goButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 14));
		goButton.setEnabled(false);
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setName(fillNameTextField.getText());
			}
		});
		goButton.setBounds(324, 306, 174, 29);
		frmSetupnamescreen.getContentPane().add(goButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SetupNameScreen.class.getResource("/images/startFrame.png")));
		lblNewLabel.setBounds(12, 0, 927, 536);
		frmSetupnamescreen.getContentPane().add(lblNewLabel);

		
		
		
	}
		
	/**
	 * check if the input name fulfill the requirement or not
	 */
	private void checkCanContinue() {
		boolean validName = fillNameTextField.getText().matches(GameEnviromentUi.NAME_REGEX);
		lblError.setText(validName ? null : GameEnviromentUi.NAME_REQUIREMENTS);
		goButton.setEnabled(validName);
	}

	/**
	 *return the frame used for setup name screen
	 *@return the frame for the setup name screen
	 */
	@Override
	public JFrame getFrame() {
		return this.frmSetupnamescreen;
	}
	}
	
	

